package by.itechart;

import by.itechart.company.entity.Company;
import by.itechart.company.enums.CompanySize;
import by.itechart.company.service.CompanyService;
import by.itechart.config.CoreConfig;
import by.itechart.config.PersistenceConfig;
import by.itechart.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PersistenceConfig.class, CoreConfig.class, ServiceConfig.class);

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");
        Company company = new Company();
        company.setName("ss");
        company.setSizeType(CompanySize.LARGE);

        System.out.println(companyService.saveOrUpdateUser(company));
        System.out.println("Hello World!");
    }
}
