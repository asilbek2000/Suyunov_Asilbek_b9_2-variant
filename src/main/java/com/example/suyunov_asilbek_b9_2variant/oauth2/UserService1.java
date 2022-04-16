package com.example.suyunov_asilbek_b9_2variant.oauth2;


import com.example.suyunov_asilbek_b9_2variant.entity.User;
import com.example.suyunov_asilbek_b9_2variant.entity.enums.Provider;
import com.example.suyunov_asilbek_b9_2variant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService1 {

    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String username) {
        User existUser = repo.getByUserName(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUserName(username);
            newUser.setProvider(Provider.FACEBOOK);
            newUser.setActive(true);
            repo.save(newUser);
        }
    }
}
