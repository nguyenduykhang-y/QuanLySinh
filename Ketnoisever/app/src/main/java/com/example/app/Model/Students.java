package com.example.app.Model;

public class Students {
    public String _id;
    public String id;
    public String name;
    public String email;
    public String sex;
    public String address;

    public Students() {
    }

    public Students(String id, String name, String email, String sex, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.address = address;
    }
}
