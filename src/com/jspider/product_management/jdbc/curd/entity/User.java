package com.jspider.product_management.jdbc.curd.entity;

import java.util.Objects;

public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_email;

    public User(){
        super();
    }

    public User(int user_id, String user_name, String user_password, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id && Objects.equals(user_name, user.user_name) && Objects.equals(user_password, user.user_password) && Objects.equals(user_email, user.user_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_name, user_password, user_email);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                '}';
    }
}
