package com.msa.member.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRIEND_SEQ")
    @SequenceGenerator(sequenceName = "FRIEND_SEQ", allocationSize = 1, name = "FRIEND_SEQ")
    Long friend_id;

    @Column(name = "USER_ID1" , nullable = false)
    private String userId1;

    @Column(name = "USER_ID2", nullable = false)
    private String userId2;

    @Column(name = "STATE", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "memberId2")
    private Member memberId2;

    @JsonBackReference(value = "memberId2")
    public Member getMemberId2() {
        return memberId2;
    }

    public void setMemberId2(Member memberId2) {
        this.memberId2 = memberId2;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    Friend(){}

    public Friend(String userId1, String userId2, State state) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.state = State.WAIT;
    }

    public Long getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Long friend_id) {
        this.friend_id = friend_id;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }
}
