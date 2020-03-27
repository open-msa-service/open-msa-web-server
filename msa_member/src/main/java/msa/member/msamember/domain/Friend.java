package msa.member.msamember.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter @ToString
@Entity
@Table(name = "FRIENDS")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEM_SEQ")
    @SequenceGenerator(sequenceName = "MEMBER_SEQ", allocationSize = 1, name = "MEM_SEQ")
    private Long friendId;

    private String userId1;
    private String userId2;

    @Enumerated(value = EnumType.STRING)
    private State state;

    public Friend(){};

    public Friend(String userId1, String userId2, State state) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.state = state;
    }

}
