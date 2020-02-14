package com.msa.gateway.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    private long socialId;

    private String profileHref;
}
