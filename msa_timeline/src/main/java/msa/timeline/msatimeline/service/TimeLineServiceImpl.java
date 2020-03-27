package msa.timeline.msatimeline.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import msa.timeline.msatimeline.client.MemberClientService;
import msa.timeline.msatimeline.client.MemberClientServiceImpl;
import msa.timeline.msatimeline.domain.Scope;
import msa.timeline.msatimeline.domain.TimeLine;
import msa.timeline.msatimeline.repository.CommentRepository;
import msa.timeline.msatimeline.repository.TimeLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TimeLineServiceImpl implements TimeLineService {

    private final TimeLineRepository timeLIneRepository;
    private final CommentRepository commentRepository;
    private final FileUploadDownloadService fileUploadDownloadService;
    private final MemberClientService memberClientService;


    public TimeLineServiceImpl(TimeLineRepository timeLIneRepository, CommentRepository commentRepository, FileUploadDownloadService fileUploadDownloadService, MemberClientService memberClientService) {
        this.timeLIneRepository = timeLIneRepository;
        this.commentRepository = commentRepository;
        this.fileUploadDownloadService = fileUploadDownloadService;
        this.memberClientService = memberClientService;
    }

    /**
     * main 타임라인 조회
     * @param friendList
     * @return
     */
    @Override
    public List<TimeLine> findMainTimeLineByUserId(List<String> friendList) {
        // 내 아이디와 친구아이디를 모두 가져와야 한다.
        List<Long> timeIdList = timeLIneRepository.timeLineIdList(friendList);
        if(timeIdList.size() == 0){
            return new ArrayList<>();
        }else{
            return timeLIneRepository.findTimeLineByTimeIdInOrderByUpdateTime(timeIdList);
        }
    }

    /**
     * 글 수정
     *
     * @param file
     * @param tempTimeline
     */
    @Override
    public void updateTImeLine(MultipartFile[] file, String tempTimeline) {
        TimeLine timeLine = convertStringToTimeLine(tempTimeline);
        timeLine.setUpdateTime(LocalDateTime.now());
        try {
            setImageFileNames(timeLine);
            fileUploadDownloadService.storeFile(file);
            timeLIneRepository.updateTimeline(timeLine);
        } catch (InvalidDataAccessApiUsageException ex) {
            throw new InvalidDataAccessApiUsageException("전송된 데이터가 올바르지 않습니다.");
        }
    }

    /**
     * 글 삭제
     *
     * @param timeId
     */
    @Transactional
    @Override
    public void deleteTimeLineByTimeId(Long timeId) {
        // 타임라인, 댓글, 좋아요 모두 삭제해야 한다.
        timeLIneRepository.deleteTimeLineByTimeId(timeId);

        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(timeId);
        commentRepository.deleteCommentByTimeId(timeLine);

    }

    /**
     * 글쓰기
     *
     * @param file
     * @param tempTimeline
     */
    @Override
    public void writeTimeLine(MultipartFile[] file, String tempTimeline) {
        TimeLine timeLine = convertStringToTimeLine(tempTimeline);
        timeLine.setUpdateTime(LocalDateTime.now());
        log.info("TimeLineServiceImpl writeTimeLine :::: {}", timeLine.toString());
        try {
            setImageFileNames(timeLine);
            fileUploadDownloadService.storeFile(file);
            timeLIneRepository.save(timeLine);
        } catch (InvalidDataAccessApiUsageException ex) {
            throw new InvalidDataAccessApiUsageException("전송된 데이터가 올바르지 않습니다.");
        }

    }

    private void setImageFileNames(TimeLine timeLine) {
        StringBuilder fileLocation = new StringBuilder();
        if (!StringUtils.isEmpty(timeLine.getFileNameList())) {
            for (String str : timeLine.getFileNameList()) {
                fileLocation.append(str.replaceAll(",", " ")).append(",");
            }
            timeLine.setFileLocation(fileLocation.toString());
        }
    }

    private TimeLine convertStringToTimeLine(String tempTimeline) {
        ObjectMapper objectMapper = new ObjectMapper();
        TimeLine timeLine = null;
        try {
            timeLine = objectMapper.readValue(tempTimeline, TimeLine.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeLine;
    }

    /**
     * 사용자 id로 타임라인 조회
     *
     * @param userId
     * @return
     */
    @Override
    public List<TimeLine> findMyTimeLineByUserId(String userId) {
        List<TimeLine> timeLines = new ArrayList<>();

        timeLines = timeLIneRepository.findTimeLineByUserIdOrderByUpdateTimeDesc(userId);

        return timeLines;
    }

    /**
     * 유저 프로필 사진 변경시 타임라인, 댓글 프로필 사진 변경
     *
     * @param userId
     * @param profileHref
     */
    @Override
    public void updateMemberProfileHref(String userId, String profileHref) {
        timeLIneRepository.updateUserProfile(userId, profileHref);
        commentRepository.updateUserProfile(userId, profileHref);
    }

    /**
     * 유저 페이지 방문
     * - 친구일 경우 전체 전부다, 아닐 경우 scope.ALL 만
     * @param userId
     * @param visitId
     * @return
     */
    @Override
    public List<TimeLine> findVisitTimeLineByUserId(String userId, String visitId) {
        boolean isFriend = memberClientService.retrieveIsFriend(userId, visitId);
        List<TimeLine> timeLines;
        if(isFriend){
            timeLines = timeLIneRepository.findTimeLineByUserIdOrderByUpdateTimeDesc(visitId);
        }else{
            timeLines = timeLIneRepository.findTimeLineByUserIdAndScopeOrderByUpdateTime(visitId, Scope.ALL);
        }

        return timeLines;
    }

    @Override
    public boolean isFriend(String userId, String visitId) {
        return memberClientService.retrieveIsFriend(userId, visitId);
    }
}
