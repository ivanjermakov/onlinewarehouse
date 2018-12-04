package by.itechart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceConfig.class,
        ServiceConfig.class,
        MailConfig.class,
        UploadConfig.class,
        UploadContextPathConfig.class,
        ElasticSearchConfig.class,
        ValidationConfig.class,
        ElasticSearchConfig.class,
        QuartzConfig.class})
public class CoreConfig {

}
