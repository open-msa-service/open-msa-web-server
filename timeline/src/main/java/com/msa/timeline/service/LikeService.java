package com.msa.timeline.service;

import com.msa.timeline.domain.Like;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

@Transactional
public interface LikeService {

    ResponseEntity<Object> clickLikeService(Like like);

}
