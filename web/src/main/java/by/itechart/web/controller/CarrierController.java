package by.itechart.web.controller;

import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.enums.CarrierType;
import by.itechart.common.entity.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/company/{companyId}/carrier")
public class CarrierController {

    @GetMapping
    public List<Carrier> getCarriersList(@PathVariable long companyId, Pageable pageable) {
        ArrayList<Carrier> carriers = new ArrayList<>();
        //get carriers by pageable and return page. Pageable = ?page=1&size=5 for example
        for (int i = 0; i < 10; i++) {
            carriers.add(createCarrier(i));
        }
        return carriers;
    }

    @PostMapping
    public Long saveCarrier(@PathVariable long companyId, @RequestBody Carrier carrier) {
        //save carrier and return id
        Long carrierId = new Long(15);
        return carrierId;
    }

    @GetMapping("/{carrierId}")
    public Carrier getCarrier(@PathVariable long companyId, @PathVariable long carrierId) {
        //get carrier
        Carrier carrier = createCarrier(carrierId);
        return carrier;
    }

    @PutMapping("/{carrierId}")
    public Long editCarrier(@PathVariable long companyId, @PathVariable long carrierId, @RequestBody Carrier carrier) {
        //edit carrier
        return carrierId;
    }

    @DeleteMapping("/{carrierId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCarrier(@PathVariable long companyId, @PathVariable long carrierId) {
        // delete carrier
    }


    private Carrier createCarrier(long i) {
        Address address = new Address();
        address.setCountry("country");
        address.setLocality("locality");
        address.setRegion("region");
        Carrier carrier = new Carrier();
        carrier.setAddress(address);
        carrier.setName("carrier" + i);
        carrier.setCarrierType(CarrierType.values()[new Random().nextInt(4)]);
        carrier.setTaxNumber("100003" + i);
        return carrier;
    }
}
