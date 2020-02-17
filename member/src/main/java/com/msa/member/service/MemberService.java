package com.msa.member.service;

import com.msa.member.domain.Member;

public interface MemberService {

    Member memberSearchById(Long id);

    Member memberSearchByUserId(String userId);

}
