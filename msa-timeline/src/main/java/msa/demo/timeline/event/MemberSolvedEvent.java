package msa.demo.timeline.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


/**
 * 시스템
 * profileHref 이벤트 발생
 */
@Getter
@ToString
@EqualsAndHashCode
public class MemberSolvedEvent implements Serializable {

    private String userId;
    private String profileHref;

    public MemberSolvedEvent(String userId, String profileHref) {
        this.userId = userId;
        this.profileHref = profileHref;
    }
}
