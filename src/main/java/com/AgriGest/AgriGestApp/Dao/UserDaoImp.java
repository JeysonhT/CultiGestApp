package com.AgriGest.AgriGestApp.Dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.AgriGest.AgriGestApp.Dao.Interfaces.UserDao;
import com.AgriGest.AgriGestApp.Models.User;

@Repository
@Profile("mongodb")
public class UserDaoImp implements UserDao {
    @Autowired
    private MongoOperations operations;
    @Autowired
    private User opUser;

    //metodos Crud para la clase/coleccion User
    @Override
    public List<User> GetUser() {
        return operations.findAll(User.class, "User");
    }

    @Override
    public ResponseEntity<String> deleteUserbyId(String Id) {
        User eliminado = operations.findById(Id, User.class, "User");
        if(eliminado == null){
            return ResponseEntity.badRequest().body("El usuario no existe");
        }else{
            operations.remove(eliminado, "User");
            return ResponseEntity.ok(eliminado.getName()+" Fue eliminado correctamente");
        }
    }

    @Override
    public ResponseEntity<String> deleteUserbyName(String Name, String Email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(Name)).addCriteria(Criteria.where("email").is(Email));
        List<User> eliminado = operations.find(query,User.class, "User");
        if(eliminado!=null){
            operations.remove(eliminado, "User");
            return ResponseEntity.ok("El usuario: "+eliminado+" fue eliminado correctamente");
        }else{
            return ResponseEntity.badRequest().body("No fue posible encontrar al usuario");
        }
    }

    @Override
    public void UpdateUser() {
        
    }
    
    //metodo para crear usuarios 
    @Override
    public ResponseEntity<String> postUser(User user) {
        if(!validacionEmailPass(user)){
            return ResponseEntity.badRequest().body("Ingrese email y contraseña");
        }else{
            operations.save(user, "User");
            return ResponseEntity.ok("Registro exitoso");
        }
        
    }

    //valida que el email y contraseña no sean nulos
    public boolean validacionEmailPass(User user){
        opUser = user;

        return opUser.getEmail()!=null && opUser.getPassword()!=null;
    }

    //metodo de Inicio de sesion para usuarios previamente Registrados
    @Override
    public ResponseEntity<String> LoginUser() {

        return ResponseEntity.ok("pendiente");    
    }

}
