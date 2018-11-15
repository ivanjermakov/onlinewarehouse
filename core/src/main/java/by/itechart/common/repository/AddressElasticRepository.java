package by.itechart.common.repository;

import by.itechart.common.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.jpa.repository.Query;


public interface AddressElasticRepository extends ElasticsearchCrudRepository<Address, Long> {

    Page<Address> findByParams(String searchQuery);
}
