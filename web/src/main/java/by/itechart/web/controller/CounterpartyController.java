package by.itechart.web.controller;

import by.itechart.common.repository.PieChartData;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.dto.CreateCounterpartyDto;
import by.itechart.counterparty.service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/counterparties")
public class CounterpartyController {

    private final CounterpartyService counterpartyService;

    @Autowired
    public CounterpartyController(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @GetMapping
    public Page<CounterpartyDto> getCounterparties(@PathVariable long companyId,
                                                   CounterpartyFilter filter,
                                                   Pageable pageable) {
        return counterpartyService.getCounterparties(companyId, filter, pageable);
    }

    @GetMapping("/goods-in-statistics")
    public List<PieChartData> getInCounterpartiesGoodsCostStatistics(@PathVariable long companyId) {
        return counterpartyService.getCounterpartiesGoodsCostStatistics(ConsignmentNoteType.IN, companyId);
    }

    @GetMapping("/goods-out-statistics")
    public List<PieChartData> getOutCounterpartiesGoodsCostStatistics(@PathVariable long companyId) {
        return counterpartyService.getCounterpartiesGoodsCostStatistics(ConsignmentNoteType.OUT, companyId);
    }

    @PostMapping
    public Long saveCounterparty(@RequestBody CreateCounterpartyDto counterparty) {
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    //    why we need companyId here if counterparty itself has it's own id?
    @GetMapping("/{counterpartyId}")
    public CounterpartyDto getCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        return counterpartyService.getCounterparty(companyId, counterpartyId);
    }

    @PutMapping("/{counterpartyId}")
    public Long updateCounterparty(@RequestBody CreateCounterpartyDto counterparty) {
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    @DeleteMapping("/{counterpartyId}")
    public void deleteCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        counterpartyService.deleteCounterparty(counterpartyId);
    }
}
