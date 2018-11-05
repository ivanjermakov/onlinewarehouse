package by.itechart.writeoffact.service;

import by.itechart.common.service.GoodsService;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActFilter;
import by.itechart.writeoffact.dto.WriteOffActListDto;
import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.entity.WriteOffActGoods;
import by.itechart.writeoffact.repository.WriteOffActGoodsRepository;
import by.itechart.writeoffact.repository.WriteOffActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WriteOffActServiceImpl implements WriteOffActService {

    private final WriteOffActRepository writeOffActRepository;
    private final WriteOffActGoodsRepository writeOffActGoodsRepository;
    private final GoodsService goodsService;

    @Autowired
    public WriteOffActServiceImpl(WriteOffActRepository writeOffActRepository, WriteOffActGoodsRepository writeOffActGoodsRepository, GoodsService goodsService) {
        this.writeOffActRepository = writeOffActRepository;
        this.writeOffActGoodsRepository = writeOffActGoodsRepository;
        this.goodsService = goodsService;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WriteOffActListDto> getWriteOffActs(Long companyId, Pageable pageable, WriteOffActFilter writeOffActFilter) {
        Page<WriteOffAct> writeOffActList =
                writeOffActRepository.findAll(WriteOffActsPredicates.findByWriteOffActFilter(writeOffActFilter, companyId), pageable);
        List<WriteOffActListDto> writeOffActListDto = ObjectMapperUtils.mapAll(writeOffActList.getContent(), WriteOffActListDto.class);
        return new PageImpl<>(writeOffActListDto, pageable, writeOffActList.getTotalElements());
    }

    @Transactional
    @Override
    public Long saveWriteOffAct(CreateWriteOffActDto createWriteOffActDto, Long companyId) {
        WriteOffAct writeOffAct = ObjectMapperUtils.map(createWriteOffActDto, WriteOffAct.class);
        writeOffAct.setCompany(new Company(companyId));
        writeOffAct.setId(null);
        writeOffAct.setCreation(LocalDate.now());
        Integer amount = createWriteOffActDto.getWriteOffActGoodsDtoList()
                .stream()
                .map(dto -> goodsService.getCost(dto.getGoodsId(), dto.getAmount()))//TODO: so many queries to database, should do it in one query
                .reduce((result, cost) -> result + cost).orElse(0);
        writeOffAct.setTotalAmount(amount);
        Long id = writeOffActRepository.save(writeOffAct).getId();
        List<WriteOffActGoods> writeOffActGoodsList = createWriteOffActDto.getWriteOffActGoodsDtoList().stream().map(dto -> {
            WriteOffActGoods writeOffActGoods = ObjectMapperUtils.map(dto, WriteOffActGoods.class);
            writeOffActGoods.setId(null);
            writeOffActGoods.setWriteOffAct(new WriteOffAct(id));
            return writeOffActGoods;
        }).collect(Collectors.toList());
        writeOffActGoodsRepository.saveAll(writeOffActGoodsList);
        return id;
    }

    @Transactional(readOnly = true)
    @Override
    public WriteOffActDto getWriteOffAct(Long writeOffActId) {
        WriteOffAct writeOffAct = writeOffActRepository.getOne(writeOffActId);
        return ObjectMapperUtils.map(writeOffAct, WriteOffActDto.class);
    }
}
