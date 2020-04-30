package com.robinson.anyrentalapp.Model;

public class User {
    String firstname, lastname, email, password, image, address, phoneno, _id;

    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String firstname, String lastname, String email, String image, String address, String phoneno) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.image = image;
        this.address = address;
        this.phoneno = phoneno;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String get_id() {
        return _id;
    }
}
