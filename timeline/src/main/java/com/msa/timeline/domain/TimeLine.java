package com.msa.timeline.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TIMELINE")
public class TimeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIME_SEQ")
    @SequenceGenerator(sequenceName = "TIME_SEQ", allocationSize = 1, name = "TIME_SEQ")
    private Long timeId;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "SCOPE", length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Scope scope;

    @Column(name = "FILE_LOCATION")
    private String fileLocation;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "UPDATE_TIME", nullable = false, insertable = false)
    private LocalDateTime updateTime;

    @Column(name = "PROFILE_HREF")
    private String profileHref;

    public String getProfileHref() {
        return profileHref;
    }

    public void setProfileHref(String profileHref) {
        this.profileHref = profileHref;
    }

    @Transient
    private String[] fileNameList;

    @Transient
    private boolean isUpdated;

    public boolean getIsUpdated(){
        return this.isUpdated;
    }

    @OneToMany(mappedBy = "timeId", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "timeId")
    private List<Like> likes;

    @OneToMany(mappedBy = "timeId", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "timeId")
    private List<Comment> comments;

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String[] getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(String[] fileNameList) {
        this.fileNameList = fileNameList;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
