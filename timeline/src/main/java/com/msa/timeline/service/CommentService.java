package com.msa.timeline.service;

import com.msa.timeline.domain.Comment;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

@Transactional
public interface CommentService {

    ResponseEntity<Object> writeCommentInTimeLine(Comment comment);

}
