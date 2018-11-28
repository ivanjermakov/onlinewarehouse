package by.itechart.web.controller;

import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.common.dto.Pair;
import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActFilter;
import by.itechart.writeoffact.dto.WriteOffActListDto;
import by.itechart.writeoffact.service.WriteOffActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/write-off-acts")
public class WriteOffActController {

    private final WriteOffActService writeOffActService;

    @Autowired
    public WriteOffActController(WriteOffActService writeOffActService) {
        this.writeOffActService = writeOffActService;
    }

    @GetMapping
    public Page<WriteOffActListDto> getWriteOffActs(@PathVariable long companyId,
                                                    WriteOffActFilter filter,
                                                    Pageable pageable) {
        return writeOffActService.getWriteOffActs(companyId, pageable, filter);
    }

    @GetMapping("/{writeOffActId}")
    public WriteOffActDto getWriteOffAct(@PathVariable long companyId, @PathVariable long writeOffActId) {
        return writeOffActService.getWriteOffAct(companyId, writeOffActId);
    }

    @PostMapping
    public Long saveWriteOffAct(@PathVariable long companyId, @RequestBody CreateWriteOffActDto createWriteOffActDto) {
        return writeOffActService.saveWriteOffAct(createWriteOffActDto, companyId);
    }

    @PutMapping("/create-commodity-lot")
    public Pair<Long, Long> saveWriteOffActAndCommodityLot(@PathVariable long companyId,
                                                           @RequestBody Pair<CreateWriteOffActDto, CreateCommodityLotDto> writeOffActAndCommodityLot) {
        return writeOffActService.saveWriteOffActAndCommodityLot(writeOffActAndCommodityLot, companyId);
    }
}
