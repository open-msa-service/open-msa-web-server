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
    private Long like_id;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private TimeLine time_id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment_id;


}
