package com.msa.timeline.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "TIMELINE")
public class TimeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIME_SEQ")
    @SequenceGenerator(sequenceName = "TIME_SEQ", allocationSize = 1, name = "TIME_SEQ")
    private Long time_id;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "SCOPE", length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Scope scope;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "UPDATE_TIME", nullable = false, insertable = false)
    private LocalDateTime updateTime;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @OneToMany(mappedBy = "time_id", fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(mappedBy = "time_id", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
