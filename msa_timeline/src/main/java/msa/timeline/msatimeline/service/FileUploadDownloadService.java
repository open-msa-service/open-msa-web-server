package msa.timeline.msatimeline.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileUploadDownloadService {

    void storeFile(MultipartFile[] file);

}
