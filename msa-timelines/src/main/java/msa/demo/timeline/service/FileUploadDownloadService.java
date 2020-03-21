package msa.demo.timeline.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileUploadDownloadService {

    void storeFile(MultipartFile[] file);

}
