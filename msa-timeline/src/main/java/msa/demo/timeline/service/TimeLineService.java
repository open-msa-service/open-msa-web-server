package msa.demo.timeline.service;

import msa.demo.timeline.domain.TimeLine;
import org.springframework.web.multipart.MultipartFile;

public interface TimeLineService {

    void writeTimeLine(MultipartFile[] file, String tempTimeline);

    void updateTImeLine(TimeLine timeLine);

    void deleteTimeLineByTimeId(Long timeId);

    // 조회 메서드 추가 필요

}
