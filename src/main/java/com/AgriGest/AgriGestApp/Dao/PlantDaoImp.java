package com.AgriGest.AgriGestApp.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.AgriGest.AgriGestApp.Dao.Interfaces.PlantDao;
import com.AgriGest.AgriGestApp.Models.Plant;
import com.AgriGest.AgriGestApp.Models.User;
import com.jayway.jsonpath.Criteria;

@Repository
@Profile("mongodb")
public class PlantDaoImp implements PlantDao{

    @Autowired
    private MongoOperations operations;

    @Override
    public List<Plant> getPlants(User user) {
        Query query = new Query();
        query.addCriteria((CriteriaDefinition) Criteria.where("User").is("user"));
        return operations.find(query, Plant.class, "plant");
    }

    @Override
    public void DeletePlant(String Id) {
        Query query = new Query();
        query.addCriteria((CriteriaDefinition) Criteria.where("Id").is("Id_plant"));
        operations.remove(query, Plant.class, "Plant");
    }

    @Override
    public ResponseEntity<String> postPlant(Plant plant) {
       if(plant != null){
            operations.save(plant, "Plant");
            return ResponseEntity.ok("Planta Guardada");
       }else{
            return ResponseEntity.badRequest().body("Objeto nulo");
       }
    }
    
}
