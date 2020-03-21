package msa.demo.timeline.controller;

import msa.demo.timeline.domain.ResponseMessage;
import msa.demo.timeline.domain.TimeLine;
import msa.demo.timeline.service.TimeLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeLineController {

    private final TimeLineService timeLineService;

    private ResponseMessage responseMessage;

    public TimeLineController(TimeLineService timeLineService) {
        this.timeLineService = timeLineService;
    }

    @GetMapping("/{userId}")
    ResponseEntity<ResponseMessage> getMyTimeLineByUserId(@PathVariable String userId) {
        List<TimeLine> timeLines = timeLineService.findMyTimeLineByUserId(userId);
        responseMessage = new ResponseMessage(timeLines, userId + "의 타임라인을 조회했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{timeId}")
    ResponseEntity<ResponseMessage> deleteTimeLineByTimeId(@PathVariable Long timeId) {
        timeLineService.deleteTimeLineByTimeId(timeId);
        responseMessage = new ResponseMessage("", "타임라인을 삭제했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/write")
    ResponseEntity<ResponseMessage> writeTimeLine(@RequestPart("file") MultipartFile[] file, @RequestPart("timeLine") String tempTimeLine) {
        timeLineService.writeTimeLine(file, tempTimeLine);
        responseMessage = new ResponseMessage("", "타임라인을 작성했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseMessage> updateTimeLine(@RequestPart("file") MultipartFile[] file, @RequestPart("timeLine") String tempTimeLine) {
        timeLineService.updateTImeLine(file, tempTimeLine);
        responseMessage = new ResponseMessage("", "타임라인을 수정했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
