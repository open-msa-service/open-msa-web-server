package msa.timeline.msatimeline.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_SEQ")
    @SequenceGenerator(sequenceName = "COMMENT_SEQ", allocationSize = 1, name = "COM_SEQ")
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "timeId")
    private TimeLine timeId;

    @Transient
    private Long tempTimeId;

}
