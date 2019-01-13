package com.example.ayan.parliamentaryelections;

import android.widget.RadioButton;
import android.widget.RadioGroup;

public class User {

    private String email;
    private String uid;
    private String username;
    private String name;
    private String password;
    private String mob;
    private RadioButton radioButton;
    private RadioButton radioButton1;
    private RadioGroup radioGroup;

    public User(){

    }

    public User(String email, String uid, String username, String name, String password, String mob, RadioGroup radioGroup) {
        this.email = email;
        this.uid = uid;
        this.username = username;
        this.name = name;
        this.password = password;
        this.mob = mob;
        this.radioGroup = radioGroup;
    }

    public User(String email, String uid, String username, RadioGroup radioGroup) {
        this.email = email;
        this.uid = uid;
        this.username = username;
        this.radioGroup = radioGroup;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public void setRadioButton1(RadioButton radioButton1) {
        this.radioButton1 = radioButton1;
    }
}
