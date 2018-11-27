package by.itechart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadContextPathConfig implements WebMvcConfigurer {

    @Value("${upload.placeholder}")
    private String uploadPlaceholder;

    @Value("${web.resources}")
    private String webResources;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/" + webResources + "**")
                .addResourceLocations("file:/" + uploadPlaceholder);
    }
}
