package com.mydigipay.todo.services;

import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void signIn(UserDocument userDocument) {
    }

    @Override
    public UserDocument save(UserDocument userDocument) {

        if(userDocument.getPassword() == null || userDocument.getPassword().isBlank())
            throw new RuntimeException("password cannot be null or empty");
        try {
            //todo handle duplicate key exception for creating duplicate username
            userDocument.setPassword(passwordEncoder.encode(userDocument.getPassword().trim()));
            return userRepository.save(userDocument);
        } catch (DuplicateKeyException ex) {
            throw new RuntimeException("user name is not unique");
        }
    }

    @Override
    public UserDocument update(UserDocument userDocument) {
        if ((userDocument.getId() == null ))
            throw new RuntimeException("userid cannot be null in update");

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDocument user = findByUsername(username);
        if (user.getId().equals(userDocument.getId())){
          return   save(userDocument);
        }
        else throw new RuntimeException("only user can update username or password");
    }

    @Override
    public List<UserDocument> find() {
        return userRepository.findAll();
    }

    @Override
    public UserDocument findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public UserDocument findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
    }


    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDocument userDocument = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found in db"));
        User user = new User(userDocument.getUsername(),
                userDocument.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        return user;
    }
}
