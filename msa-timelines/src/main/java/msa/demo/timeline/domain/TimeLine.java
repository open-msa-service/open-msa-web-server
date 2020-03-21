package msa.demo.timeline.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @GeneratedValue
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "timeId")
    private List<Comment> comments;

    @Transient
    private String[] fileNameList;


}
