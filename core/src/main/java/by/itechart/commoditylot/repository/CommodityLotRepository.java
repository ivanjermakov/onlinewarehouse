package by.itechart.commoditylot.repository;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface CommodityLotRepository extends CrudRepository<CommodityLot, Long> {

    Page<CommodityLot> findAllByCompany_IdAndCommodityLotTypeAndCreationBetween(Long companyId, CommodityLotType commodityLotType, LocalDate from, LocalDate to, Pageable pageable);

    Page<CommodityLot> findAllByCompany_IdAndCommodityLotTypeAndCreationAfter(Long companyId, CommodityLotType commodityLotType, LocalDate from, Pageable pageable);

    Page<CommodityLot> findAllByCompany_IdAndCommodityLotTypeAndCreationBefore(Long companyId, CommodityLotType commodityLotType, LocalDate to, Pageable pageable);

    Page<CommodityLot> findAllByCompany_IdAndCommodityLotType(Long companyId, CommodityLotType commodityLotType, Pageable pageable);

}
