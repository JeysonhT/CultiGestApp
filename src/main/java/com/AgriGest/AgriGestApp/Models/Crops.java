package com.AgriGest.AgriGestApp.Models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document("Crops")
public class Crops {

    @MongoId
    private String Id_Crops;
    @Field("Name")
    private String Name;
    @Field("AreaCultivo")
    private String AreaCultivo;
    @Field("seedTime") @DateTimeFormat(style = "M")
    private String seedTime;
    @Field("user")
    private User user;
    
    public Crops() {
    }

    public Crops(String id_Crops, String name, String areaCultivo, String seedTime, User user) {
        Id_Crops = id_Crops;
        Name = name;
        AreaCultivo = areaCultivo;
        this.seedTime = seedTime;
        this.user = user;
    }

}
