package com.msa.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @SequenceGenerator(sequenceName = "COMMENT_SEQ", allocationSize = 1, name = "COMMENT_SEQ")
    private Long comment_id;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private TimeLine time_id;

}
