package com.group4.model.account;

public class Account {
    private int id;
    private String username;
    private String password;
    private String name;
    private String dob;
    private String email;
    private String address;
    private boolean status;
    private Role role;

    public Account(int id, String username, String password, String name, String dob, String email, String address, boolean status, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.status = status;
        this.role = role;
    }

    public Account() {
    }

    public Account(String username, String password, String name, String dob, String email, String address, boolean status, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.status = status;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
