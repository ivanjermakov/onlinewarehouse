package by.itechart.web.controller;

import by.itechart.common.service.UploadService;
import by.itechart.exception.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws UploadException {
        return uploadService.upload(multipartFile);
    }
}
