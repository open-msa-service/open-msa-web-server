package msa.member.msamember.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadDownloadService {

    void storeFile(MultipartFile file);

}
