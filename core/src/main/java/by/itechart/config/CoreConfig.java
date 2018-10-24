package by.itechart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceConfig.class, ServiceConfig.class})
public class CoreConfig {
}
