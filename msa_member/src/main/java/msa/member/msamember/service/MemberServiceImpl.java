package msa.member.msamember.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import msa.member.msamember.domain.Member;
import msa.member.msamember.event.EventDispatcher;
import msa.member.msamember.event.MemberSolvedEvent;
import msa.member.msamember.repository.MemberRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final FileUploadDownloadService fileUploadDownloadService;

    private EventDispatcher eventDispatcher;

    public MemberServiceImpl(MemberRepository memberRepository, FileUploadDownloadService fileUploadDownloadService, EventDispatcher eventDispatcher) {
        this.memberRepository = memberRepository;
        this.fileUploadDownloadService = fileUploadDownloadService;
        this.eventDispatcher = eventDispatcher;
    }

    /**
     * 사용자 회원가입
     *
     * @param member
     */
    @Override
    public void memberSignUp(Member member) {
        // TODO : profileHref default 경로 추가해 줄 것.
        log.info("MemberService memberSignUp :::: {}", member.toString());
        member.setProfileHref("/images/default_image.png");
        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException ex) {
            log.error("MemberService memberSignUp error :::: ",ex);
            throw new DataIntegrityViolationException("회원정보를 모두 입력해 주세요.");
        }
    }


    /**
     * 사용자 정보 수정
     *
     * @param files
     * @param members
     */
    @Transactional
    @Override
    public void updateUserInfo(MultipartFile[] files, String members) {
        log.info("MemberService updateUserInfo :::: {}", members);
        Member member = convertStringToMember(members);
        try {
            if (files.length != 0) {
                setFileNames(files[0], member);
                fileUploadDownloadService.storeFile(files[0]);
            }

            // set updateTime
            member.setUpdateTime(LocalDateTime.now());

            memberRepository.updateMemberInfo(member);

            // 이벤트 전송(프로필 사진 변경)
            eventDispatcher.send(new MemberSolvedEvent(
                    member.getUserId(),
                    member.getProfileHref()
            ));

        }catch (NullPointerException ex){
            log.error("MemberService updateUserInfo error :::: ", ex);
            throw new NullPointerException("유효하지 않은 값이 전송되었습니다.");
        }
    }

    private void setFileNames(MultipartFile files, Member member) {
        String fileNames = files.getOriginalFilename();
        fileNames = "/images/" + fileNames;
        member.setProfileHref(fileNames);
    }

    private Member convertStringToMember(String members) {
        ObjectMapper objectMapper = new ObjectMapper();
        Member member = null;
        try {
            member = objectMapper.readValue(members, Member.class);
        } catch (JsonProcessingException e) {
            throw new DataIntegrityViolationException("", e);
        } catch (IOException e) {
            e.printStackTrace();
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
        log.info("MemberService findAllMemberByUsername :::: {}", username);
        try {
            members = memberRepository.findByUsernameIgnoreCaseContaining(username);
        } catch (InvalidDataAccessApiUsageException ex) {
            log.error("MemberService findAllMemberByUsername error :::: ", ex);
            throw new InvalidDataAccessApiUsageException("Null값이 들어올 수 없습니다.");
        } catch (NoSuchElementException ex) {
            log.error("MemberService findAllMemberByUsername error :::: ", ex);
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
        log.info("MemberService findMemberInfoByUserId :::: {}", userId);
        Member member;
        try {
            member = Optional.of(memberRepository.findByUserId(userId).get()).get();
        } catch (NoSuchElementException ex) {
            log.error("MemberService findMemberInfoByUserId error :::: ", ex);
            throw new NoSuchElementException("해당 사용자를 찾을 수 없습니다.");
        }

        return member;
    }
}
