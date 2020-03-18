package com.msa.timeline.service;


import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.xml.ws.Response;
import java.util.List;

@Transactional
public interface TimeLineService {

    ResponseEntity<Object> searchAllTimeLine();

    ResponseEntity<Object> writeTimeLine(MultipartFile[] file, String timeline, boolean update);

    ResponseEntity<Object> searchAllTimeLineByUserId(String userId);

    ResponseEntity<Object> deleteTimeLineById(Long timeId);

    void updateUserInfo(String userInfo);

    ResponseEntity<Object> searchTimeLineByUserIdAndIsFriend(String userId, boolean isFriends);

    ResponseEntity<Object> timeLineList(List<String> friendId);

}
