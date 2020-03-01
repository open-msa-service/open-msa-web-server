package com.msa.timeline.controller;

import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.service.FileUploadDownloadService;
import com.msa.timeline.service.TimeLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/time")
public class TimeLineController {

    private static final Logger logger = LoggerFactory.getLogger(TimeLineController.class);

    @Autowired
    private TimeLineService timeLineService;

    @Autowired
    private FileUploadDownloadService fileUploadDownloadService;

    @PostMapping("/upload/content")
    ResponseEntity<Object> uploadTimeimage(@RequestPart("file") MultipartFile[] file, @RequestPart("timeline")String timeline){
        return timeLineService.writeTimeLine(file, timeline);
    }



}
