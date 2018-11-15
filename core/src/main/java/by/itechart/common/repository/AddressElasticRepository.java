package by.itechart.common.repository;

import by.itechart.common.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;


public interface AddressElasticRepository extends ElasticsearchCrudRepository<Address, Long> {

//    Page<Address> findByParams(String param, Pageable pageable);
}
