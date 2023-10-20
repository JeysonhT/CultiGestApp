package com.AgriGest.AgriGestApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.AgriGest.AgriGestApp.Dao.Interfaces.PlantDao;
import com.AgriGest.AgriGestApp.Models.Plant;
import com.AgriGest.AgriGestApp.Models.User;
import com.AgriGest.AgriGestApp.Utils.JWTUtil;

@RestController
public class PlantController {

    @Autowired
    private PlantDao plantDao;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token){
        String UserTkn = jwtUtil.getKey(token);
        return UserTkn != null;
    }
    
    @GetMapping(value = "api/Plant")
    public List<Plant> getPlant(@RequestHeader("Authorization") String token, User user){
        return !validarToken(token) ? null : plantDao.getPlants(user);
    }

    public ResponseEntity<String> deletePlant(@RequestHeader("Authorization") String token, String Id){
        if(!validarToken(token)){
            return ResponseEntity.badRequest().body("el token no es valido");
        }else{
            plantDao.DeletePlant(Id);
            return ResponseEntity.ok("Solicitud Procesada");
        }
    }


    public ResponseEntity<String> postPlant(@RequestHeader("Authorization") String token, @RequestBody Plant plant){
        if(!validarToken(token)){
            return ResponseEntity.badRequest().body("token no valido");
        }else{
            if(plant != null){
                return plantDao.postPlant(plant);
            }else{
                return ResponseEntity.badRequest().body("Objeto nulo");
            }
        }

    }
}
