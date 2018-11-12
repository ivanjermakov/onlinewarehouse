package by.itechart.warehouse.repository;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, QuerydslPredicateExecutor<Warehouse> {
    Page<Warehouse> findWarehousesByCompanyId(long companyId, Pageable pageable);

    Warehouse findByCompanyIdAndId(long companyId, long warehouseId);

    @Modifying
    @Query("update Warehouse w set w.deleted = current_date where w.id = ?1")
    void setDeleted(long warehouseId);
}
