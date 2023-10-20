package com.AgriGest.AgriGestApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.AgriGest.AgriGestApp.Dao.Interfaces.CropsDao;
import com.AgriGest.AgriGestApp.Models.Crops;
import com.AgriGest.AgriGestApp.Models.User;
import com.AgriGest.AgriGestApp.Utils.JWTUtil;

@RestController
public class CropsController {
    @Autowired 
    private CropsDao cropsDao;

    @Autowired 
    private JWTUtil jwtUtil;

    @GetMapping(value = "api/Crops")
    public List<Crops> getCrops(@RequestHeader("Authorization") String token, User user){
        return !validarToken(token) ? null : cropsDao.getCrops(user);
    }

    @DeleteMapping(value = "api/Crops")
    public ResponseEntity<String> deleteCrops(@RequestHeader("Authorization") String token, String Id){
        if(!validarToken(token)){
            return ResponseEntity.badRequest().body("el token es invalido o no existe");
        }else{
            cropsDao.deleteCrops(Id);
            return ResponseEntity.ok("Solicitud procesada");
        }
    }

    @PostMapping(value = "api/Crops")
    public ResponseEntity<String> postCrops(@RequestHeader("Authorization") String token, Crops crops){
        if(!validarToken(token)){
            return ResponseEntity.badRequest().body("No estas autorizado");
        }else{
            return cropsDao.postCrops(crops);
        }
    }

    private boolean validarToken(String token){
        String UserTkn = jwtUtil.getKey(token);
        return UserTkn != null;
    }

}
