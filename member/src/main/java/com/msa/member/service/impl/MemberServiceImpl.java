package com.msa.member.service.impl;

import com.msa.member.repository.MemberRepository;
import com.msa.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;



}
