package msa.timeline.msatimeline.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
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

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PROFILE_HREF")
    private String profileHref;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "timeId")
    private List<Comment> comments;

    @JsonManagedReference
    @OneToMany(mappedBy = "timeId")
    private List<Like> likes;

    @Transient
    private String[] fileNameList;


}
