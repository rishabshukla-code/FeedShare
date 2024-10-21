package com.mycompany.model.pojo;

import org.springframework.stereotype.Component;

@Component
public class LoginUser {

    private String uname;
    private String pswd;
    
    //DO NOT MODIFY OR CHANGE THIS CONSTRUCTOR
    public LoginUser() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    
    
    
}