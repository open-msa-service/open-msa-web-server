package msa.member.msamember.event;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 회원가입 이벤트
 */
@RequiredArgsConstructor
@Getter @ToString @EqualsAndHashCode
public class GatewaySolvedEvent implements Serializable {

    private final String account;

}
