package com.msa.member.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadDownloadService {

    ResponseEntity<Object> storeFile(MultipartFile file);

}
