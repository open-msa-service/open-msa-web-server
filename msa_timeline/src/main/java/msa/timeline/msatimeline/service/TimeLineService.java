package msa.timeline.msatimeline.service;

import msa.timeline.msatimeline.domain.TimeLine;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TimeLineService {

    void writeTimeLine(MultipartFile[] file, String tempTimeline);

    void updateTImeLine(MultipartFile[] file, String tempTimeline);

    void deleteTimeLineByTimeId(Long timeId);

    List<TimeLine> findMyTimeLineByUserId(String userId);

    void updateMemberProfileHref(String userId, String profileHref);

    List<TimeLine> findVisitTimeLineByUserId(String userId, String visitId);

    boolean isFriend(String userId, String visitId);

    List<TimeLine> findMainTimeLineByUserId(List<String> friendList);

}
