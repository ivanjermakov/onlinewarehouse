package by.itechart.carrier.repository;

import by.itechart.carrier.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CarrierRepository extends JpaRepository<Carrier, Long>, QuerydslPredicateExecutor<Carrier> {

//    Page<Carrier> findAllCarriersByCompanyIdAndDeletedIsFalse(Long companyId, Pageable page);
//
//    Page<Carrier> findCarriersByCarrierTypeAndTrusted(CarrierType carrierType, Boolean trusted, Pageable page);
//
//    Page<Carrier> findAllByNameStartingWith(String name, Pageable pageable);
//
//    @Modifying
//    @Query("update Carrier c set c.trusted = :trusted where c.id = :id")
//    void setTrusted(Long id, Boolean trusted);

    @Modifying
    @Query("update Carrier c set c.deleted = current_date where c.id = ?1 and c.company.id = ?2")
    void setDeleted(Long carrierId, Long companyId);

}
