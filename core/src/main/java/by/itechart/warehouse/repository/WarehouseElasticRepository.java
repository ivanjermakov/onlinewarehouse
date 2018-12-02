package by.itechart.warehouse.repository;

import by.itechart.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface WarehouseElasticRepository extends ElasticsearchCrudRepository<Warehouse, String> {
    Page<Warehouse> findByNameAndCompany_IdAndDeletedIsNull(String name, Pageable pageable);
}
