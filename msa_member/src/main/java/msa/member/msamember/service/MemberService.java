package msa.member.msamember.service;


import msa.member.msamember.domain.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {

    void memberSignUp(Member member);

    Member findMemberInfoByUserId(String userId);

    void updateUserInfo(MultipartFile[] files, String members);

    List<Member> findAllMemberByUsername(String username);

}
