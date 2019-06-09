package com.cafe24.demo.VO;

public class Auth{
    private String clientId;
    private String clientSecret;

    public Auth(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }    
    
}