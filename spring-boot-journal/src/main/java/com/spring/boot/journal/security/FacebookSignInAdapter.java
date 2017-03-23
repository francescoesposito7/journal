package com.spring.boot.journal.security;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public class FacebookSignInAdapter implements SignInAdapter {
	
	@Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(connection.getDisplayName(), null, Arrays.asList(new SimpleGrantedAuthority("USER"))));
        
        Facebook facebook = (Facebook) connection.getApi();
        String [] fields = { "id", "email"};
        User userProfile = facebook.fetchObject("me", User.class, fields);
        
        return "/tempoFacebook?id="+userProfile.getId()+"&email="+userProfile.getEmail()+"";
    }
}
