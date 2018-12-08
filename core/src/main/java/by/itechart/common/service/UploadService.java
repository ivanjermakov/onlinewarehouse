package by.itechart.common.service;

import by.itechart.exception.UploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String upload(MultipartFile multipartFile) throws UploadException;

}
