package com.AgriGest.AgriGestApp.Models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "User")
public class User {

    @MongoId
    @Getter @Setter
    private String Id;
    @Field("name")
    private String Name;
    @Field("lastName")
    private String Lastname;
    @Field("email")
    private String Email;
    @Field("password")
    private String Password;
    @Field("isAgronomist")
    private boolean isAgronomist;
    
    public User() {
    }

    public User(String id, String name, String lastname, String email, String password, boolean isAgronomist) {
        Id = id;
        Name = name;
        Lastname = lastname;
        Email = email;
        Password = password;
        this.isAgronomist = isAgronomist;
    }


}
