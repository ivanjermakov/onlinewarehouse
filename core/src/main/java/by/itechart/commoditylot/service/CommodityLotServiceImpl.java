package by.itechart.commoditylot.service;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.commoditylot.repository.CommodityLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CommodityLotServiceImpl implements CommodityLotService {

    private final CommodityLotRepository commodityLotRepository;

    @Autowired
    public CommodityLotServiceImpl(CommodityLotRepository commodityLotRepository) {
        this.commodityLotRepository = commodityLotRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommodityLot> getCommodityLots(Long companyId, Pageable pageable, CommodityLotType commodityLotType, LocalDate from, LocalDate to) {
        if (from == null && to == null) {
            return commodityLotRepository.findAllByCompany_IdAndCommodityLotType(companyId, commodityLotType, pageable);
        }
        if (from != null && to != null) {
            return commodityLotRepository.findAllByCompany_IdAndCommodityLotTypeAndCreationBetween(companyId, commodityLotType, from, to, pageable);
        } else {
            if (from != null) {
                return commodityLotRepository.findAllByCompany_IdAndCommodityLotTypeAndCreationAfter(companyId, commodityLotType, from, pageable);
            } else {
                return commodityLotRepository.findAllByCompany_IdAndCommodityLotTypeAndCreationBefore(companyId, commodityLotType, to, pageable);
            }
        }
    }

    @Transactional
    @Override
    public Long saveCommodityLot(CommodityLot commodityLot) {
        return commodityLotRepository.save(commodityLot).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public CommodityLot getCommodityLot(Long commodityLotId) {
        return commodityLotRepository.findById(commodityLotId).orElse(null);
    }
}
