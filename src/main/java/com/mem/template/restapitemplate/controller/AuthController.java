package com.mem.template.restapitemplate.controller;

import com.mem.template.restapitemplate.dto.AuthenticationResponse;
import com.mem.template.restapitemplate.dto.AuthenticatonRequest;
import com.mem.template.restapitemplate.services.MyUserDetailsService;
import com.mem.template.restapitemplate.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticatonRequest authenticatonRequest) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticatonRequest.username, authenticatonRequest.password));
        }catch(BadCredentialsException e){ throw new Exception("Incorrect username & password!"); }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticatonRequest.username);
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.jwt = jwt;

        return authResponse;
        
    }
}