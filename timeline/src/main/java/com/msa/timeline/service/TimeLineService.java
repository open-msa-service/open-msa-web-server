package com.msa.timeline.service;


import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Transactional
public interface TimeLineService {

    ResponseEntity<Object> searchAllTimeLine();

    ResponseEntity<Object> writeTimeLine(MultipartFile[] file, String timeline, boolean update);

    ResponseEntity<Object> writeComments(Comment comment);

    ResponseEntity<Object> searchAllTimeLineByUserId(String userId);

    ResponseEntity<Object> deleteTimeLineById(Long timeId);


}
