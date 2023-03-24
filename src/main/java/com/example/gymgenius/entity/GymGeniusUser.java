package com.example.gymgenius.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
public class GymGeniusUser extends User {

    @Id
    private int id;
    private String username;
    private String password;

    public GymGeniusUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


    public GymGeniusUser() {
        this(null, null, null);
    }
}
