package com.AgriGest.AgriGestApp.Utils;

import com.AgriGest.AgriGestApp.Dao.Interfaces.UserDao;
import com.AgriGest.AgriGestApp.Dao.UserDaoImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public UserDao userDao(){
        return new UserDaoImp();
    }
}
