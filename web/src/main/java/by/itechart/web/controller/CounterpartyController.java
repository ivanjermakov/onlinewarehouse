package by.itechart.web.controller;

import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.dto.CreateCounterpartyDto;
import by.itechart.counterparty.service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Long saveCounterparty(@PathVariable long companyId, @RequestBody CreateCounterpartyDto counterparty) {
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    //    why we need companyId here if counterparty itself has it's own id?
    @GetMapping("/{counterpartyId}")
    public CounterpartyDto getCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        return counterpartyService.getCounterparty(companyId, counterpartyId);
    }

    @PutMapping("/{counterpartyId}")
    public Long updateCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId,
                                   @RequestBody CreateCounterpartyDto counterparty) {
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    @DeleteMapping("/{counterpartyId}")
    public void deleteCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        counterpartyService.deleteCounterparty(counterpartyId);
    }
}
