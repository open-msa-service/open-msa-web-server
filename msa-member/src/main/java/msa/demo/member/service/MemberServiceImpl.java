package msa.demo.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import msa.demo.member.domain.Member;
import msa.demo.member.repository.MemberRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final FileUploadDownloadService fileUploadDownloadService;

    public MemberServiceImpl(MemberRepository memberRepository, FileUploadDownloadService fileUploadDownloadService) {
        this.memberRepository = memberRepository;
        this.fileUploadDownloadService = fileUploadDownloadService;
    }


    /**
     * 사용자 회원가입
     *
     * @param member
     */
    @Override
    public void memberSignUp(Member member) {
        // TODO : profileHref default 경로 추가해 줄 것.
        // gateway에서 타고 넘어 올 것이라 end-point 따로 필요 x

        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("회원정보를 모두 입력해 주세요.");
        }

    }


    /**
     * 사용자 정보 수정
     *
     * @param files
     * @param members
     */
    @Override
    public void updateUserInfo(MultipartFile[] files, String members) {
        Member member = convertStringToMember(members);
        try {
            if (files.length != 0) {
                setFileNames(files[0], member);
                fileUploadDownloadService.storeFile(files[0]);
            }

            // set updateTime
            member.setUpdateTime(LocalDateTime.now());

            memberRepository.updateMemberInfo(member);
        } catch (NullPointerException ex) {
            throw new NullPointerException("유효하지 않은 값이 전송되었습니다.");
        }
    }

    private void setFileNames(MultipartFile files, Member member) {
        String fileNames = files.getOriginalFilename();
        fileNames = "/static/images/" + fileNames;
        member.setProfileHref(fileNames);
    }

    private Member convertStringToMember(String members) {
        ObjectMapper objectMapper = new ObjectMapper();
        Member member = null;
        try {
            member = objectMapper.readValue(members, Member.class);
        } catch (JsonProcessingException e) {
            throw new DataIntegrityViolationException("", e);
        }
        return member;
    }


    /**
     * 이름으로 이용자 검색
     *
     * @param username
     * @return
     */
    @Override
    public List<Member> findAllMemberByUsername(String username) {
        List<Member> members = null;
        try {
            members = Optional.of(memberRepository.findMemberByUsernameIgnoreCaseContaining(username).get()).get();
        } catch (InvalidDataAccessApiUsageException ex) {
            throw new InvalidDataAccessApiUsageException("Null값이 들어올 수 없습니다.");
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("해당 사용자를 찾을 수 없습니다.");
        }

        return members;
    }

    /**
     * 사용자 정보 조회
     *
     * @param userId
     * @return Member.class
     */
    @Override
    public Member findMemberInfoByUserId(String userId) {

        Member member;
        try {
            member = Optional.of(memberRepository.findByUserId(userId).get()).get();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("해당 사용자를 찾을 수 없습니다.");
        }

        return member;
    }
}
