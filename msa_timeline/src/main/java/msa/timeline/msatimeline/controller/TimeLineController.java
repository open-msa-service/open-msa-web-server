package msa.timeline.msatimeline.controller;

import msa.timeline.msatimeline.client.MemberClientService;
import msa.timeline.msatimeline.client.dto.Member;
import msa.timeline.msatimeline.domain.ResponseMessage;
import msa.timeline.msatimeline.domain.TimeLine;
import msa.timeline.msatimeline.service.TimeLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/time")
public class TimeLineController {

    private final TimeLineService timeLineService;
    private final MemberClientService memberClientService;

    private ResponseMessage responseMessage;

    public TimeLineController(TimeLineService timeLineService, MemberClientService memberClientService) {
        this.timeLineService = timeLineService;
        this.memberClientService = memberClientService;
    }

    @GetMapping("/main/{userId}")
    ResponseEntity<ResponseMessage> getMainTimeLineByUserId(@PathVariable String userId){
        Member member = memberClientService.retrieveMemberByUserId(userId);
        String[] friendArray = memberClientService.retrieveFriendList(userId);
        List<String> friendList = new ArrayList<>();
        friendList.add(userId);
        friendList.addAll(Arrays.asList(friendArray));

        List<TimeLine> timeLines = timeLineService.findMainTimeLineByUserId(friendList);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("member", member);
        responseMap.put("timeline", timeLines);

        responseMessage = new ResponseMessage(responseMap, "타임라인을 조회했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    ResponseEntity<ResponseMessage> getMyTimeLineByUserId(@PathVariable String userId) {
        List<TimeLine> timeLines = timeLineService.findMyTimeLineByUserId(userId);
        Member member = memberClientService.retrieveMemberByUserId(userId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("timeLine", timeLines);
        responseMap.put("member", member);

        responseMessage = new ResponseMessage(responseMap, userId + "의 타임라인을 조회했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/visit/{userId}/{visitName}")
    ResponseEntity<ResponseMessage> visitMember(@PathVariable String userId, @PathVariable String visitName){
        Member member = memberClientService.retrieveMemberByUserId(visitName);
        List<TimeLine> timeLines = timeLineService.findVisitTimeLineByUserId(userId, visitName);
        boolean isFriend = timeLineService.isFriend(userId, visitName);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("timeline", timeLines);
        responseMap.put("member", member);
        responseMap.put("isFriend", isFriend);

        responseMessage = new ResponseMessage(responseMap, visitName + "의 타임라인을 조회했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{timeId}")
    ResponseEntity<ResponseMessage> deleteTimeLineByTimeId(@PathVariable Long timeId) {
        timeLineService.deleteTimeLineByTimeId(timeId);
        responseMessage = new ResponseMessage("", "타임라인을 삭제했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/write")
    ResponseEntity<ResponseMessage> writeTimeLine(@RequestPart("file") MultipartFile[] file, @RequestPart("timeline") String tempTimeLine) {
        timeLineService.writeTimeLine(file, tempTimeLine);
        responseMessage = new ResponseMessage("", "타임라인을 작성했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseMessage> updateTimeLine(@RequestPart("file") MultipartFile[] file, @RequestPart("timeline") String tempTimeLine) {
        timeLineService.updateTImeLine(file, tempTimeLine);
        responseMessage = new ResponseMessage("", "타임라인을 수정했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
