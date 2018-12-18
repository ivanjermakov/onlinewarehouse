package by.itechart.company.repository;

import by.itechart.company.entity.CompanyAction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CompanyActionRepository extends CrudRepository<CompanyAction, Long> {

    List<CompanyAction> findAllByCompany_Id(Long companyId);

    @Query("select ca " +
            "from CompanyAction ca " +
            "where ca.start between :dateFrom and :dateTo " +
            "or ca.end between :dateFrom and :dateTo " +
            "or ca.end = '2100-01-01'")
    List<CompanyAction> getCompanyActionStatistics(@Param("dateFrom") LocalDateTime from,
                                                   @Param("dateTo") LocalDateTime to);
}