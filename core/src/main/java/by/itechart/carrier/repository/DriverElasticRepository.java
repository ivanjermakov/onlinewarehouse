package by.itechart.carrier.repository;

import by.itechart.carrier.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;


public interface DriverElasticRepository extends ElasticsearchCrudRepository<Driver, String> {
    Page<Driver> findDriversByInfoAndDeletedIsFalse(String name, Pageable pageable);
}
