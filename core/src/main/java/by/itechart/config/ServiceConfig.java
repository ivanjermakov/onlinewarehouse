package by.itechart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"by.itechart.*.service"})
public class ServiceConfig {
}
