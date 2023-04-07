package com.example.bookstore.member;

public class MemberDTO {

    //member variables
    private int no;
    private String name;
    private String id;
    private String passwd;
    private String email;
    private String phone;
    private String address;
    private String joindate;

    //constructor
    public MemberDTO() {

    }

    //methods(getter, setter)
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

}