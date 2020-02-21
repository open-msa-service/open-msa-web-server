package com.msa.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
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

}
