package com.msa.timeline.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ")
    @SequenceGenerator(sequenceName = "LIKE_SEQ", allocationSize = 1, name = "LIKE_SEQ")
    private Long likeId;

    @Column(name = "USER_ID")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "timeId")
    private TimeLine timeId;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment commentId;

    @Enumerated(value = EnumType.STRING)
    private LikeType likeType;

    public long getTempTimeId() {
        return tempTimeId;
    }

    public void setTempTimeId(long tempTimeId) {
        this.tempTimeId = tempTimeId;
    }

    @Transient
    private long tempTimeId;

    public Like(){}

    public Like(String userId, TimeLine timeId, LikeType likeType) {
        this.userId = userId;
        this.timeId = timeId;
        this.likeType = likeType;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonBackReference(value = "timeId")
    public TimeLine getTimeId() {
        return timeId;
    }

    public void setTimeId(TimeLine timeId) {
        this.timeId = timeId;
    }

    public LikeType getLikeType() {
        return likeType;
    }

    public void setLikeType(LikeType likeType) {
        this.likeType = likeType;
    }

    @JsonBackReference(value = "commentId")
    public Comment getCommentId() {
        return commentId;
    }

    public void setCommentId(Comment commentId) {
        this.commentId = commentId;
    }
}
