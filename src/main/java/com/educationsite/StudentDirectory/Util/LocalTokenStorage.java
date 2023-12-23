package com.educationsite.StudentDirectory.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.educationsite.StudentDirectory.POJO.Token;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LocalTokenStorage {
	
	private Map<Integer,Token> tokenDb=new HashMap<>();
	
	public boolean addToken(Integer userId,Token token) {
		try{
			tokenDb.put(userId, token);
			return true;
		}catch (Exception e) {
			log.error("addToken: {}",e.getMessage());
			return false;
		}
	}
	
	public String getToken(Integer userId) {
		try{
			Token token =tokenDb.get(userId);
			return token.getToken();
		}catch (Exception e) {
			log.error("getToken: {}",e.getMessage());
			return null;
		}
	}
	
	public boolean refreshToken(Integer userId,Token token) {
		try{
			tokenDb.put(userId, token);
			return true;
		}catch (Exception e) {
			log.error("refreshToken: {}",e.getMessage());
			return false;
		}
	}
	
	public boolean removeToken(Integer userId) {
		try{
			tokenDb.remove(userId);
			return true;
		}catch (Exception e) {
			log.error("removeToken: {}",e.getMessage());
			return false;
		}
	}
	
	public boolean removeByToken(String jwt) {
		try{
			Optional<Entry<Integer, Token>> userId=tokenDb.entrySet().stream()
					.filter(entry -> entry.getValue().getToken().equalsIgnoreCase(jwt))
					.findFirst();
			if(userId.isPresent()) {
				tokenDb.remove(userId.get().getKey());
			}return true;
			
		}catch (Exception e) {
			log.error("removeByToken: {}",e.getMessage());
			return false;
		}
	}
	
	public boolean findByToken(String jwt) {
		Optional<Entry<Integer,Token>> any=tokenDb.entrySet().stream()
		.filter(entry -> entry.getValue().getToken().equalsIgnoreCase(jwt))
		.findAny();
		return any.isPresent();
	}

}
