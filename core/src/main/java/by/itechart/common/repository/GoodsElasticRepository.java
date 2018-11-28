package by.itechart.common.repository;

import by.itechart.common.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface GoodsElasticRepository extends ElasticsearchCrudRepository<Goods, Long> {

    Page<Goods> findGoodsByNameAndDeletedIsFalse(String name, Pageable pageable);

    Page<Goods> findGoodsByCompanyIdAndDeletedIsFalse(Long companyId, Pageable pageable);

}
