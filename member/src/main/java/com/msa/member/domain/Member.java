package com.msa.member.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    @SequenceGenerator(sequenceName = "MEMBER_SEQ", allocationSize = 1, name = "MEMBER_SEQ")
    Long id;

    @Column(name = "USER_ID", unique = true)
    private String userId;

    @Column(name = "USERNAME", nullable = false, updatable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false, updatable = false)
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

    @Column(name = "STATUS_MESSAGE")
    private String statusMessage;

    @Column(name = "INTRODUCE_MESSAGE")
    private String introduceMessage;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_TIME", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "memberId2", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "memberId2")
    private List<Friend> friends;

    public Member(){
        if(StringUtils.isEmpty(this.updateTime)){
            this.updateTime = LocalDateTime.now();
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public long getSocialId() {
        return socialId;
    }

    public void setSocialId(long socialId) {
        this.socialId = socialId;
    }

    public String getProfileHref() {
        return profileHref;
    }

    public void setProfileHref(String profileHref) {
        this.profileHref = profileHref;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getIntroduceMessage() {
        return introduceMessage;
    }

    public void setIntroduceMessage(String introduceMessage) {
        this.introduceMessage = introduceMessage;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
