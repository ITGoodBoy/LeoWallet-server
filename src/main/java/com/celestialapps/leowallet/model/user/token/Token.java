package com.celestialapps.leowallet.model.user.token;

import com.celestialapps.leowallet.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @JsonIgnore
    private long id;
    @JsonIgnore
    private Date createdAt;
    @OneToOne
    @JsonIgnore
    private User user;
    @Column(unique = true)
    private String tokenString;

    public Token(User user, String tokenString) {
        this.createdAt = new Date(System.currentTimeMillis());
        this.user = user;
        this.tokenString = tokenString;
    }

}
