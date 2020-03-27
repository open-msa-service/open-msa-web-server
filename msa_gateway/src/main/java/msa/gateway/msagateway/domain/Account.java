package msa.gateway.msagateway.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;


/**
 * Spring Security에서 사용자 인증할 때 사용되는 객체(DB에서 관리되는 객체)
 */
@Entity
@Getter @Setter @ToString
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_SEQ")
    @SequenceGenerator(sequenceName = "ACCOUNT_SEQ", allocationSize = 1, name = "AUTH_SEQ")
    private Long id;
    private String userId;
    private String password;
    private String username;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(length=2000)
    private String access_token;
    private LocalDateTime access_token_validity;
    @Column(length=2000)
    private String refresh_token;

    // Member의 회원가입을 위한 Column
    @Transient
    private String social_id;
    @Transient
    private String email;
    @Transient
    private String phoneNumber;



    @Transient
    private Collection<? extends GrantedAuthority> authorities;
    @Transient
    private boolean accountNonExpired = true;
    @Transient
    private boolean accountNonLocked = true;
    @Transient
    private boolean credentialsNonExpired = true;
    @Transient
    private boolean enabled = true;

}
