package msa.demo.timeline.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import msa.demo.timeline.domain.TimeLine;
import msa.demo.timeline.repository.TimeLineRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class TimeLineServiceImpl implements TimeLineService{

    private final TimeLineRepository timeLIneRepository;

    private final FileUploadDownloadService fileUploadDownloadService;

    public TimeLineServiceImpl(TimeLineRepository timeLIneRepository, FileUploadDownloadService fileUploadDownloadService) {
        this.timeLIneRepository = timeLIneRepository;
        this.fileUploadDownloadService = fileUploadDownloadService;
    }

    /**
     * 글 수정
     * @param timeLine
     */
    @Override
    public void updateTImeLine(TimeLine timeLine) {
        timeLIneRepository.updateTimeline(timeLine);
    }

    /**
     * 글 삭제
     * @param timeId
     */
    @Override
    public void deleteTimeLineByTimeId(Long timeId) {
        // 타임라인, 댓글, 좋아요 모두 삭제해야 한다.
        timeLIneRepository.deleteTimeLineByTimeId(timeId);

    }

    /**
     * 글쓰기
     * @param file
     * @param tempTimeline
     */
    @Override
    public void writeTimeLine(MultipartFile[] file, String tempTimeline) {
        TimeLine timeLine = convertStringToTimeLine(tempTimeline);

        try{
            setImageFileNames(timeLine);
            fileUploadDownloadService.storeFile(file);
            timeLIneRepository.save(timeLine);
        }catch (InvalidDataAccessApiUsageException ex){
            throw new InvalidDataAccessApiUsageException("전송된 데이터가 올바르지 않습니다.");
        }

    }

    private void setImageFileNames(TimeLine timeLine) {
        StringBuilder fileLocation = new StringBuilder();
        if(!StringUtils.isEmpty(timeLine.getFileNameList())){
            for(String str : timeLine.getFileNameList()){
                fileLocation.append(str.replaceAll(",", " ")).append(",");
            }
            timeLine.setFileLocation(fileLocation.toString());
        }
    }

    private TimeLine convertStringToTimeLine(String tempTimeline) {
        ObjectMapper objectMapper = new ObjectMapper();
        TimeLine timeLine = null;
        try {
            timeLine = objectMapper.readValue(tempTimeline, TimeLine.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return timeLine;
    }
}
