package com.oauth;

import java.io.IOException;
import java.net.http.HttpClient.Redirect;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		
		   http
           .authorizeRequests(a -> a
               .antMatchers("/").permitAll()
               .anyRequest().authenticated()
           )      
           .oauth2Login();
		   
		   http.oauth2Login().defaultSuccessUrl("/resource.html",true);
		   
		   /* Logout link */
		   http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
		   

		   http  .oauth2Login().successHandler(new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				System.out.println("Request: "+request);
				System.out.println("Response: "+response);
				
				 CustomOAuth2User oauthUser =
						 (CustomOAuth2User) authentication.getPrincipal();
				 oauth2UserService.processOAuthPostLogin(oauthUser.getEmail());
				
			
				 
				 //response.sendRedirect("http://localhost:8080/resource");
			}});
		
			/*
			 * http.oauth2Login().loginPage("/").userInfoEndpoint().userService(
			 * oauth2UserService).and() .successHandler(new AuthenticationSuccessHandler() {
			 * 
			 * 
			 * 
			 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
			 * HttpServletResponse response, Authentication authentication) throws
			 * IOException, ServletException { System.out.println("Called it.");
			 * System.out.println(request); System.out.println(response);
			 * System.out.println(authentication); CustomOAuth2User oauthUser =
			 * (CustomOAuth2User) authentication.getPrincipal();
			 * 
			 * oauth2UserService.processOAuthPostLogin(oauthUser.getEmail());
			 * 
			 * 
			 * System.out.println("Redirecting");
			 * response.sendRedirect("https://localhost:8080/resource"); } });
			 */
	}

	@Autowired
	private CustomOAuth2UserService oauth2UserService;

}