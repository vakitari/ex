package com.example.demo.Service;

import com.example.demo.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDetailsImp implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> Authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    public static UserDetailsImp build(User user) {
        // List<GrantedAuthority> authorityList = List.of( new SimpleGrantedAuthority(user.getRoles()))
        List<GrantedAuthority> authorityList = user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority( role.name()))
                .collect(Collectors.toList());
        return new UserDetailsImp(user.getId(), user.getUsername(), user.getPassword(), authorityList);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
