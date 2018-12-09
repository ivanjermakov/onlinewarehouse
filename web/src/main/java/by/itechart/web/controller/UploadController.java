package by.itechart.web.controller;

import by.itechart.common.service.UploadService;
import by.itechart.exception.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/companies/{companyId}/upload")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public String upload(@PathVariable long companyId, @RequestParam("file") MultipartFile multipartFile) throws UploadException {
        return uploadService.upload(multipartFile);
    }
}
