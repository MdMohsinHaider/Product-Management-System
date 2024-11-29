package com.jspider.product_management.jdbc.curd.entity;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements Serializable {
    private int admin_id;
    private String admin_name;
    private String admin_password;

    public Admin(){
        super();
    }

    public Admin(int admin_id, String admin_name, String admin_password) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return admin_id == admin.admin_id && Objects.equals(admin_name, admin.admin_name) && Objects.equals(admin_password, admin.admin_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admin_id, admin_name, admin_password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "user_id=" + admin_id +
                ", user_name='" + admin_name + '\'' +
                ", user_password='" + admin_password + '\'' +
                '}';
    }
}
