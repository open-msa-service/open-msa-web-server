package com.msa.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
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

//    @ManyToOne
//    @JoinColumn(name = "timeId")
//    private TimeLine timeId;
//
//    @OneToMany(mappedBy = "commentId", fetch = FetchType.EAGER)
//    private List<Like> likes;

}
