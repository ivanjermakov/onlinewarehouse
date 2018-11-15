package by.itechart.carrier.repository;

import by.itechart.carrier.entity.Carrier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;



public interface CarrierElasticRepository extends ElasticsearchCrudRepository<Carrier, String> {
    Page<Carrier> findByName(String name, Pageable pageable);
}
