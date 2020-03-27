package msa.member.msamember.service;

import lombok.extern.slf4j.Slf4j;
import msa.member.msamember.domain.FileUploadProperties;
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

@Slf4j
@Service
public class FileUploadDownloadServiceImpl implements FileUploadDownloadService {

    private final Path fileLocation;

    @Autowired
    public FileUploadDownloadServiceImpl(FileUploadProperties properties) {
        this.fileLocation = Paths.get(properties.getUploadDir())
                .toAbsolutePath().normalize();
        log.info("FileUploadDownloadService fileLocation :::: {}", this.fileLocation);
        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            try {
                log.error("FileUploadDownloadService :::: 파일 업로드 실패", e);
                throw new FileUploadException("파일 업로드에 실패했습니다.");
            } catch (FileUploadException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        log.info("FileUploadDownloadService storeFile :::: {}", fileName);
        try {
            if (fileName.contains(".."))
                throw new FileUploadException("");

            Path targetLocation = this.fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            try {
                log.error("FileUploadDownloadService storeFile error :::: ", e);
                throw new FileUploadException("파일 업로드에 실패했습니다.");
            } catch (FileUploadException ex) {
                ex.printStackTrace();
            }
        }

    }

}
