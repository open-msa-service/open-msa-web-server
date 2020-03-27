package msa.member.msamember.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 회원 클래스
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEM_SEQ")
    @SequenceGenerator(sequenceName = "MEMBER_SEQ", allocationSize = 1, name = "MEM_SEQ")
    Long id;

    @Column(name = "USER_ID", unique = true)
    private String userId;

    @Column(name = "USERNAME", nullable = false, updatable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false, updatable = false)
    private String password;

    @Column(name = "SOCIAL_ID")
    private String socialId;

    @Column(name = "PROFILE_HREF")
    private String profileHref;  // 프로필 이미지 경로

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "STATUS_MESSAGE")
    private String statusMessage; // 상태 메시지

    @Column(name = "INTRODUCE_MESSAGE")
    private String introduceMessage; // 자기소개 메시지

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
