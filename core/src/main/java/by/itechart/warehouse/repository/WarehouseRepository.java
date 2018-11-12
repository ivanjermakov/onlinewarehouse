package by.itechart.warehouse.repository;

import by.itechart.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    Page<Warehouse> findAllByCompany_Id(Long companyId, Pageable pageable);

    Warehouse findByCompany_IdAndId(Long companyId, Long id);

}
