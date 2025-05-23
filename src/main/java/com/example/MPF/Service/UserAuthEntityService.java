package com.example.MPF.Service;

import com.example.MPF.Model.UserAuthEntity;
import com.example.MPF.Repository.UserAuthEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementing UserDetailsService because during authentication, based on authentication methos, we are using
// Basic, JWT, OAuth etc. It will try to fetch the user first, since we are storing inside the database
// spring security don't know how to fetch the user, so we have to implement UserDetailsService and 
// override the loadUserByUsername method
@Service
public class UserAuthEntityService implements UserDetailsService {

    private final UserAuthEntityRepository userAuthEntityRepository;

    public UserAuthEntityService(UserAuthEntityRepository userAuthEntityRepository){
        this.userAuthEntityRepository = userAuthEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userAuthEntityRepository.findByUsername(username).get();
    }

    public List<UserAuthEntity> getAllUser(){
        return this.userAuthEntityRepository.findAll();
    }

    public void saveUser(UserAuthEntity userAuthEntity){
        this.userAuthEntityRepository.save(userAuthEntity);
    }
}
