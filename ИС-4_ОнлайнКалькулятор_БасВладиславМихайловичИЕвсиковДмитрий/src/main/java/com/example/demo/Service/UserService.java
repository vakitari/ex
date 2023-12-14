package com.example.demo.Service;


import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
   private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user)
    {

        String username = user.getUsername();
        System.out.println(username);
        if (repo.findByUsername(username)!=null)  return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);
        repo.save(user);
        return true;
    }
    public List<Role> getAllRoles() {
        return Arrays.asList(Role.values());
    }
    public Iterable<User> getAll(){return repo.findAll();}
    public void add(User data){repo.save(data);}
    public void del(Long id){repo.deleteById(id);}

    public User getById(Long id){return repo.findById(id).orElse(null);}

    public User findByUserName(String name){return repo.findByUsername(name);}

}
