package msa.timeline.msatimeline.service;


import msa.timeline.msatimeline.domain.FileUploadProperties;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileUploadDownloadServiceImpl implements FileUploadDownloadService {

    private final Path fileLocation;

    @Autowired
    public FileUploadDownloadServiceImpl(FileUploadProperties properties) {

        this.fileLocation = Paths.get(properties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            try {
                throw new FileUploadException("");
            } catch (FileUploadException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void storeFile(MultipartFile[] file) {
        for (MultipartFile multipartFile : file) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())).replaceAll(",", " ");

            try {
                if (fileName.contains(".."))
                    throw new FileUploadException("파일 업로드에 실패했습니다.");

                Path targetLocation = this.fileLocation.resolve(fileName);
                Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                try {
                    throw new FileUploadException("파일 업로드에 실패했습니다.");
                } catch (FileUploadException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
