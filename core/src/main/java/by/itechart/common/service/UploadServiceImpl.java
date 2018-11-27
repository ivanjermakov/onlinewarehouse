package by.itechart.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    @Value("${upload.placeholder}")
    private String uploadPlaceholder;

    @Value("${web.resources}")
    private String webResources;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        new File(uploadPlaceholder).mkdirs();
        File file = new File(uploadPlaceholder + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return webResources + multipartFile.getOriginalFilename();
    }

}
