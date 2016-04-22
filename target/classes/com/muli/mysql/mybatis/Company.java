package com.muli.mysql.mybatis;

/**
 * Created by muli on 15-5-29.
 */
public class Company {
    private int id;
    private String name;
    private String address;
    private int person;

    public Company(String na, String ad, int p){
        this.name = na;
        this.address = ad;
        this.person = p;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getPerson() {
        return person;
    }
    public void setPerson(int person) {
        this.person = person;
    }

}
