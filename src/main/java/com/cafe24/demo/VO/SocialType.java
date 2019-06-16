package com.cafe24.demo.VO;

public enum SocialType{
    GOOGLE("google");

    private final String ROLE_PREFIX = "ROLE_";
    private String name;

    SocialType(String name){
        this.name = name;
    }

    public String getRoleType() { return ROLE_PREFIX + name.toUpperCase(); }

    public String getValue() { return name; }

    public boolean isEquals(String authority){
        return this.getRoleType().equals(authority);
    }

}