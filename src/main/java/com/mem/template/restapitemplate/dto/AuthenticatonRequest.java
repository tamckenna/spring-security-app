package com.mem.template.restapitemplate.dto;

public class AuthenticatonRequest {

    public AuthenticatonRequest(){}
    public AuthenticatonRequest(String _username, String _password){
        username = _username;
        password = _password;
    }
    
    public String username;
    public String password;
}