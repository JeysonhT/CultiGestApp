package com.AgriGest.AgriGestApp.Dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import com.AgriGest.AgriGestApp.Dao.Interfaces.CropsDao;
import com.AgriGest.AgriGestApp.Models.Crops;
import com.AgriGest.AgriGestApp.Models.User;

@Repository
@Profile("mongobd")
public class CropsDaoImp implements CropsDao{

    @Autowired
    private MongoOperations operations;


    @Override
    public List<Crops> getCrops(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("User").is(user));
        return operations.find(query, Crops.class, "Crops");
    }

    @Override
    public void deleteCrops(String Id) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Id).is("Id_Crops"));
        operations.remove(query, Crops.class, "Crops");
    }

    @Override
    public ResponseEntity<String> postCrops(Crops crops) {
        if(crops != null){
            operations.save(crops, "Crops");
            return ResponseEntity.ok("Cultivo Guardado");
        }else{
            return ResponseEntity.badRequest().body("Objeto nulo");
        }
    }

    
    
}
