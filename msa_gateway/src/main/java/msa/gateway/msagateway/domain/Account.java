package msa.gateway.msagateway.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Collection;


/**
 * Spring Security에서 사용자 인증할 때 사용되는 객체(DB에서 관리되는 객체)
 */
@Entity
@Getter @Setter @ToString
public class Account implements UserDetails {

    @Id
    private Long id;
    private String userId;
    private String password;
    private String username;
    private UserRole role;
    @Column(length=2000)
    private String access_token;
    private LocalDateTime access_token_validity;
    @Column(length=2000)
    private String refresh_token;

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
