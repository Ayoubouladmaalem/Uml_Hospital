package org.example.uml_hospital.Entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String prenom;

    private String sexe;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String telephone;

    @Column(unique = true, nullable = false)
    private String email;

    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Implémentation des méthodes de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourner une liste vide ou des rôles si nécessaire
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Changez selon vos besoins
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Changez selon vos besoins
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Changez selon vos besoins
    }

    @Override
    public boolean isEnabled() {
        return true; // Changez selon vos besoins
    }
}
