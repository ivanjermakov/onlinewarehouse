package by.itechart.carrier.repository;

import by.itechart.carrier.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DriverRepository extends PagingAndSortingRepository<Driver, Long> {

    Page<Driver> findAllByInfoContains(String info, Pageable page);

    @Modifying
    @Query("update Driver d set d.deleted = current_date where d.id = :id")
    void setDeleted(Long id);
}
