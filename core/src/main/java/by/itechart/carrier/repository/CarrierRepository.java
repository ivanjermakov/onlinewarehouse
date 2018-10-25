package by.itechart.carrier.repository;

import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.enums.CarrierType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarrierRepository extends PagingAndSortingRepository<Carrier, Long> {



    Page<Carrier> findAllCarriersByCompanyIdAndDeletedIsFalse(Long companyId, Pageable page);
    Page<Carrier> findCarriersByCarrierTypeAndTrusted(CarrierType carrierType, Boolean trusted, Pageable page);
    Page<Carrier> findAllByNameStartingWith(String name);

    @Modifying
    @Query("update Carrier c set c.trusted = :trusted where c.id = :id")
    void setTrusted(Long id, Boolean trusted);

    @Modifying
    @Query("update Carrier c set c.deleted = current_date where c.id = :id")
    void setDeleted(Long id);

}
