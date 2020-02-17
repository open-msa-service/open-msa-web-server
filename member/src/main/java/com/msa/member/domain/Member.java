package com.msa.member.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    @SequenceGenerator(sequenceName = "MEMBER_SEQ", allocationSize = 1, name = "MEMBER_SEQ")
    Long id;

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

    @Column(name = "EMAIL" , nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "UPDATE_TIME", nullable = false)
    private LocalDateTime updateTime;

    public Member(){
        if(StringUtils.isEmpty(this.updateTime)){
            this.updateTime = LocalDateTime.now();
        }
    }
}
