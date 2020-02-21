package com.msa.timeline.service.impl;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.repository.CommentRepository;
import com.msa.timeline.repository.LikeRepository;
import com.msa.timeline.repository.TimeLineRepository;
import com.msa.timeline.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired private TimeLineRepository timeLineRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LikeRepository likeRepository;

    @Override
    public ResponseEntity<Object> writeTimeLine(TimeLine timeLine) {
        return null;
    }

    @Override
    public ResponseEntity<Object> writeComments(Comment comment) {
        return null;
    }

    @Override
    public ResponseEntity<Object> clickLikes(Like like) {
        return null;
    }
}
