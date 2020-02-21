package com.msa.timeline.service;


import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

@Transactional
public interface TimeLineService {

    ResponseEntity<Object> writeTimeLine(TimeLine timeLine);

    ResponseEntity<Object> writeComments(Comment comment);

    ResponseEntity<Object> clickLikes(Like like);

}
