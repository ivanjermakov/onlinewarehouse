package by.itechart.carrier.repository;

import by.itechart.carrier.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DriverRepository extends JpaRepository<Driver, Long>, QuerydslPredicateExecutor<Driver> {

    Page<Driver> findByCarrierId(long carrierId, Pageable pageable);

    @Modifying
    @Query("update Driver d set d.deleted = current_date where d.id = :id")
    void setDeleted(Long id);
}
