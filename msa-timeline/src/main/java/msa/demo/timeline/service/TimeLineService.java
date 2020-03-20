package msa.demo.timeline.service;

import msa.demo.timeline.domain.TimeLine;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TimeLineService {

    void writeTimeLine(MultipartFile[] file, String tempTimeline);

    void updateTImeLine(MultipartFile[] file, String tempTimeline);

    void deleteTimeLineByTimeId(Long timeId);

    List<TimeLine> findMyTimeLineByUserId(String userId);

}
