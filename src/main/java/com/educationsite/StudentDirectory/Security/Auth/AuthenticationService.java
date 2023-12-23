package com.educationsite.StudentDirectory.Security.Auth;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.educationsite.StudentDirectory.POJO.AuthenticationRequest;
import com.educationsite.StudentDirectory.POJO.AuthenticationResponse;
import com.educationsite.StudentDirectory.POJO.RegisterRequest;
import com.educationsite.StudentDirectory.POJO.Token;
import com.educationsite.StudentDirectory.POJO.TokenType;
import com.educationsite.StudentDirectory.Repository.UserRepository;
import com.educationsite.StudentDirectory.Security.Config.JwtService;
import com.educationsite.StudentDirectory.Security.User.User;
import com.educationsite.StudentDirectory.Util.LocalTokenStorage;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final LocalTokenStorage tokenRepository;
	  
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    User savedUser=null;
    String jwtToken="";
    var alreadyaUser=repository.getAllByEmail(user.getEmail());
    if(alreadyaUser.isEmpty()) {
         savedUser = repository.save(user);
         jwtToken = jwtService.generateToken(user);
         saveUserToken(savedUser, jwtToken);
    }
    else {
    	savedUser = alreadyaUser.get(0);
        jwtToken = tokenRepository.getToken(savedUser.getId());
        if(jwtToken==null) {
        	jwtToken = jwtService.generateToken(user);
            saveUserToken(savedUser, jwtToken);
        }
    }
    var refreshToken = jwtService.generateRefreshToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.addToken(user.getId(), token);
  }

  private void revokeAllUserTokens(User user) {
    tokenRepository.removeToken(user.getId());
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
