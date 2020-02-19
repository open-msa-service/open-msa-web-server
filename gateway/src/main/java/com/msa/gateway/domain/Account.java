package com.msa.gateway.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false, length = 15)
    private UserRole userRole;

    @Column(name = "SOCIAL_ID")
    private long socialId;

    @Column(name = "PROFILE_HREF")
    private String profileHref;

    @Transient
    private String email;

    @Transient
    private String phoneNumber;

    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
