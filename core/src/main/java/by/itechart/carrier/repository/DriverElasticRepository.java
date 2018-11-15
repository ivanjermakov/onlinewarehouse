package by.itechart.carrier.repository;

import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.awt.print.Pageable;

public interface DriverElasticRepository extends ElasticsearchCrudRepository<Driver, String> {
    Page<Driver> findByInfoAndDeletedIsFalse(String name, Pageable pageable);
}
