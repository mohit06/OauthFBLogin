package com.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
 
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService  {
 
    @Autowired
    private UserRepository repo;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        return new CustomOAuth2User(user);
    }

	public void processOAuthPostLogin(String username) {
		  User existUser = repo.getUserByUsername(username);
		  System.out.println("Username received = "+ username);
	        if (existUser == null) {
	            User newUser = new User();
	            newUser.setUsername(username);
	            newUser.setProvider(Provider.FACEBOOK);
	            newUser.setEnabled(true);          
	             
	            
	            repo.save(newUser);        
	        }
	         
		
	}
 
}