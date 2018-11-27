package by.itechart.mail.repository;

import by.itechart.mail.entity.BirthdayMailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MailTemplateRepository extends JpaRepository<BirthdayMailTemplate, Long> {

    @Query("select t from BirthdayMailTemplate t where t.company.id = :companyId")
    BirthdayMailTemplate getTemplate(@Param("companyId") Long companyId);

}
