package by.itechart.profit.repository;

import by.itechart.company.enums.CompanySize;
import by.itechart.profit.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface RateRepository extends PagingAndSortingRepository<Rate, Long> {
    @Query("SELECT r FROM Rate r " +
            "WHERE r.applyDate = (SELECT MAX(x.applyDate) " +
                          "FROM Rate x " +
                          "WHERE x.applyDate <= :endDate and x.companySize = :companySize)" +
            "and r.companySize = :companySize")
    Rate getLastRate(LocalDateTime endDate, CompanySize companySize);

}
