package com.rejointech.blooper.model;

public class Login_Cred {
    public Login_Cred() {
    }

    private String Username,
            Phone,
            Email_id,
            Uid,
            PicUri,
            Name;

    public Login_Cred(String username,
                      String phone,
                      String email_id,
                      String uid,
                      String picUri,
                      String name) {

        Username = username;
        Phone = phone;
        Email_id = email_id;
        Uid = uid;
        PicUri = picUri;
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail_id() {
        return Email_id;
    }

    public void setEmail_id(String email_id) {
        Email_id = email_id;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getPicUri() {
        return PicUri;
    }

    public void setPicUri(String picUri) {
        PicUri = picUri;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
