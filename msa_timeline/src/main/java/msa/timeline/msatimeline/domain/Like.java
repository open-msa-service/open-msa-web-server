package msa.timeline.msatimeline.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name = "LIKES")
@Getter @Setter @ToString
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ")
    @SequenceGenerator(sequenceName = "LIKE_SEQ", allocationSize = 1, name = "LIKE_SEQ")
    Long likeId;

    private String userId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "timeId")
    private TimeLine timeId;

}
