package by.itechart.web.controller;

import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.enums.CounterpartyType;
import by.itechart.counterparty.service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CounterpartyDto> getCounterparties(@PathVariable long companyId,
                                                   @RequestParam CounterpartyType counterpartyType,
                                                   @RequestParam(required = false) Pageable pageable) {
        return counterpartyService.getCounterparties(companyId, counterpartyType, pageable).getContent();
    }

    @PostMapping
    public Long saveCounterparty(@PathVariable long companyId, @RequestBody CounterpartyDto counterparty) {
        // save counterparty and return generated counterparty id
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    //    why we need companyId here if counterparty itself has it's own id?
    @GetMapping("/{counterpartyId}")
    public CounterpartyDto getCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        // return counterparty with companyId and counterpartyId
        return counterpartyService.getCounterparty(counterpartyId);
    }

    @PutMapping("/{counterpartyId}")
    public Long updateCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId,
                                   @RequestBody CounterpartyDto counterparty) {
        // update counterparty and return counterparty id
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    @DeleteMapping("/{counterpartyId}")
    public void deleteCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
//         delete counterparty with companyId and counterpartyId
        counterpartyService.deleteCounterparty(counterpartyId);
    }
}
