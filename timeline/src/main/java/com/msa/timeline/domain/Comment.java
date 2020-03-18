package com.msa.timeline.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @SequenceGenerator(sequenceName = "COMMENT_SEQ", allocationSize = 1, name = "COMMENT_SEQ")
    private Long commentId;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "PROFILE_HREF")
    private String profileHref;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileHref() {
        return profileHref;
    }

    public void setProfileHref(String profileHref) {
        this.profileHref = profileHref;
    }

    @ManyToOne
    @JsonBackReference(value = "timeId")
    @JoinColumn(name = "timeId")
    private TimeLine timeId;

    @JsonManagedReference(value = "commentId")
    @OneToMany(mappedBy = "commentId", fetch = FetchType.EAGER)
    private List<Like> likes;

    @Transient
    private Long tempTimeId;

    public Long getTempTimeId() {
        return tempTimeId;
    }

    public void setTempTimeId(Long tempTimeId) {
        this.tempTimeId = tempTimeId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TimeLine getTimeId() {
        return timeId;
    }

    public void setTimeId(TimeLine timeId) {
        this.timeId = timeId;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
