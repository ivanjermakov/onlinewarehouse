package by.itechart.company.repository;

import by.itechart.company.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface CompanyElasticRepository extends ElasticsearchCrudRepository<Company, Long> {
    Page<Company> findAllByName(String name, Pageable pageable);
}
