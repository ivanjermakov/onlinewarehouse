package by.itechart.web.controller;

import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.enums.CounterpartyType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company/{companyId}/counterparty")
public class CounterpartyController {

    @GetMapping
    public List<Counterparty> getCounterparties(@PathVariable long companyId,
                                                @RequestParam CounterpartyType counterpartyType) {
        List<Counterparty> counterparties = new ArrayList<>();
        // return list of counterparties with CounterpartyType
        for (int i = 0; i < 10; i++) {
            counterparties.add(createCounterparty(i));
        }
        return counterparties;
    }

    @PostMapping
    public Long saveCounterparty(@PathVariable long companyId, @RequestBody Counterparty counterparty) {
        // save counterparty and return generated counterparty id
        Long id = 10L;
        return id;
    }

    @GetMapping("/{counterpartyId}")
    public Counterparty getCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        Counterparty counterparty = createCounterparty(counterpartyId);
        // return counterparty with companyId and counterpartyId
        return counterparty;
    }

    @PutMapping("/{counterpartyId}")
    public Long updateCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId,
                                   @RequestBody Counterparty counterparty) {
        // update counterparty and return counterparty id
        Long id = 10L;
        return id;
    }

    @DeleteMapping("/{counterpartyId}")
    public void deleteCounterparty(@PathVariable long companyId, @PathVariable long counterpartyId) {
        // delete counterparty with companyId and counterpartyId
    }

    private Counterparty createCounterparty(long id) {
        Counterparty counterparty = new Counterparty();
        counterparty.setId(id);
        counterparty.setCounterpartyType(CounterpartyType.CONSIGNEE);
        return counterparty;
    }
}
