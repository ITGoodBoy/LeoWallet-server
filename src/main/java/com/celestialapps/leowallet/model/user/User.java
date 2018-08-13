package com.celestialapps.leowallet.model.user;

import com.celestialapps.leowallet.model.Favorite;
import com.celestialapps.leowallet.model.Invoice;
import com.celestialapps.leowallet.model.Last;
import com.celestialapps.leowallet.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"account", "name"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String account;
    @JsonIgnore
    private String password;
    private String name;
    private long balance;
    @OneToMany
    private List<Invoice> invoices;
    @OneToMany
    private List<Favorite> favorites;
    @OneToMany
    private List<Last> lasts;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "auth_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    private boolean accountNonExpired = true;
    @JsonIgnore
    private boolean accountNonLocked = true;
    @JsonIgnore
    private boolean credentialsNonExpired = true;
    @JsonIgnore
    private boolean enabled = true;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            String name = role.name().toUpperCase();
            if (!name.startsWith("ROLE_"))
                name = "ROLE_" + name;
            grantedAuthorities.add(new SimpleGrantedAuthority(name));
        }
        return grantedAuthorities;
    }


    @JsonIgnore
    @Override
    public String getUsername() {
        return account;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", roles=" + roles +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }
}
