package com.muli.mongo.morphia;


import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.PrePersist;

import java.util.Date;

/**
 * Created by muli on 15-5-28.
 */
@Entity(noClassnameStored = true)
public class Students {
    @Id
    public org.bson.types.ObjectId id;
    @Indexed
    public String token;
    @Indexed
    public String name;
    public String address;
    public Integer score;
    public Date modifiedTime;

    public Students(String token, String name, String address, Integer score){
        this.name = name;
        this.token = token;
        this.address = address;
        this.score = score;
    }

    public Students(){

    }

    @PrePersist
    public void prePersist(){
        modifiedTime = new Date();
    }

    public String getAddress(){
        return this.address;
    }
}
