package com.msa.gateway.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @SequenceGenerator(sequenceName = "ACCOUNT_SEQ", allocationSize = 1, name = "ACCOUNT_SEQ")
    private Long id;

    @Column(name = "USER_ID", unique = true)
    private String userId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false, length = 15)
    private UserRole userRole;

    @Column(name = "SOCIAL_ID")
    private long socialId;

    @Column(name = "PROFILE_HREF")
    private String profileHref;
}
