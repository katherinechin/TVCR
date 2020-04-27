package com.example.tvcr;

// TODO add comments and description

public class ContactInfo {

    private int id;
    private String name;
    private String number; // TODO int or string
//    private String email;
    private String url;
    private String address;

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    } // TODO email not working
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public ContactInfo(String name, String number, String url, String address) {
            //public ContactInfo(String name, String number, String email, String url, String address) {

            super();
        this.name = name;
        this.number = number;
//        this.email = email;
        this.url = url;
        this.address = address;
    }

    public ContactInfo(String name) {
        super();
        this.name = name;
    }

}
