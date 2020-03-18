package com.msa.timeline.controller;

import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.service.CommentService;
import com.msa.timeline.service.FileUploadDownloadService;
import com.msa.timeline.service.LikeService;
import com.msa.timeline.service.TimeLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/time")
public class TimeLineController {

    private static final Logger logger = LoggerFactory.getLogger(TimeLineController.class);

    @Autowired
    private TimeLineService timeLineService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private FileUploadDownloadService fileUploadDownloadService;

    @PostMapping("/upload/content")
    ResponseEntity<Object> uploadTimeimage(@RequestPart("file") MultipartFile[] file, @RequestPart("timeline")String timeline){
        return timeLineService.writeTimeLine(file, timeline, false);
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<Object> getTimelineByUserId(@PathVariable String userId){
        return timeLineService.searchAllTimeLineByUserId(userId);
    }

    @DeleteMapping("/{timeId}")
    ResponseEntity<Object> deleteTimelineByTimeId(@PathVariable Long timeId){
        return timeLineService.deleteTimeLineById(timeId);
    }

    @PutMapping("/upload/content")
    ResponseEntity<Object> updateTimeLine(@RequestPart("file") MultipartFile[] file, @RequestPart("timeline")String timeline){
        return timeLineService.writeTimeLine(file, timeline, true);
    }

    @PostMapping("/like")
    ResponseEntity<Object> clickLike(@RequestBody Like like){
        return likeService.clickLikeService(like);
    }

    @PostMapping("/comments")
    ResponseEntity<Object> writeComment(@RequestBody Comment comment){
        return commentService.writeCommentInTimeLine(comment);
    }

    @PutMapping("/profile")
    void updateUserProfileHref(@RequestBody String userInfo){
        timeLineService.updateUserInfo(userInfo);
    }

    @GetMapping("/user/{userId}/{isFriend}")
    ResponseEntity<Object> getUserTimeLineByUserIdAndIsFriends(@PathVariable String userId, @PathVariable boolean isFriend){
        return timeLineService.searchTimeLineByUserIdAndIsFriend(userId, isFriend);
    }

    @PostMapping("/main/timeList")
    ResponseEntity<Object> mainTimeLineList(@RequestBody List<String> friends){
        return timeLineService.timeLineList(friends);
    }

}
