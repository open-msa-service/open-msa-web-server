package com.msa.timeline.service.impl;

import com.msa.timeline.dtos.FileUploadProperties;
import com.msa.timeline.dtos.ResponseMessage;
import com.msa.timeline.service.FileUploadDownloadService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadDownloadServiceImpl implements FileUploadDownloadService {
    private final Path fileLocation;

    private ResponseMessage responseMessage;

    @Autowired
    public FileUploadDownloadServiceImpl(FileUploadProperties properties){
        this.fileLocation = Paths.get(properties.getUploadDir())
                .toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileLocation);
        }catch (Exception e){
            try {
                throw new FileUploadException("");
            } catch (FileUploadException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public ResponseEntity<Object> storeFile(MultipartFile[] file) {
        for (MultipartFile multipartFile : file) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()).replaceAll(",", " ");

            try {
                if (fileName.contains(".."))
                    throw new FileUploadException("");

                Path targetLocation = this.fileLocation.resolve(fileName);
                Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                try {
                    throw new FileUploadException("");
                } catch (FileUploadException ex) {
                    ex.printStackTrace();
                }
            }
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "파일 업로드에 성공했습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
