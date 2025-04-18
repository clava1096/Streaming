package com.clava1096.musicstreaming.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.List;
import com.clava1096.musicstreaming.models.enumpack.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Override
    public String toString() {
        return name + " " + username + " " + userRole + " " + email + " " + password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }
}
