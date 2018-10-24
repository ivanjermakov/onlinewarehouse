package by.itechart.web.controller;

import by.itechart.counterparty.entity.Counterparty;
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
    public List<Counterparty> getCounterparties(@PathVariable long companyId,
                                                @RequestParam CounterpartyType counterpartyType,
                                                @RequestParam(required = false) Pageable pageable) {
//        List<Counterparty> counterparties = new ArrayList<>();
//        // return list of counterparties with CounterpartyType
//        for (int i = 0; i < 10; i++) {
//            counterparties.add(createCounterparty(i));
//        }
//        return counterparties;
        return counterpartyService.getCounterparties(companyId, counterpartyType, pageable).getContent();
    }

    @PostMapping
    public Long saveCounterparty(@PathVariable long companyId, @RequestBody Counterparty counterparty) {
        // save counterparty and return generated counterparty id
//        Long id = 10L;
//        return id;
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    @GetMapping("/{counterpartyId}")
    public Counterparty getCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        Counterparty counterparty = createCounterparty(counterpartyId);
        // return counterparty with companyId and counterpartyId
//        return counterparty;
        return counterpartyService.getCounterparty(counterpartyId);
    }

    @PutMapping("/{counterpartyId}")
    public Long updateCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId,
                                   @RequestBody Counterparty counterparty) {
        // update counterparty and return counterparty id
//        Long id = 10L;
//        return id;
        return counterpartyService.saveOrUpdateCounterparty(counterparty);
    }

    @DeleteMapping("/{counterpartyId}")
    public void deleteCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
//         delete counterparty with companyId and counterpartyId
        counterpartyService.deleteCounterparty(counterpartyId);
    }

    private Counterparty createCounterparty(long id) {
        Counterparty counterparty = new Counterparty();
        counterparty.setId(id);
        counterparty.setCounterpartyType(CounterpartyType.CONSIGNEE);
        return counterparty;
    }
}
