package by.itechart.web.controller;

import by.itechart.homecard.HomeCard;
import by.itechart.homecard.service.HomeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/home-cards")
public class HomeCardsController {

    private HomeCardService homeCardsService;

    @Autowired
    public HomeCardsController(HomeCardService homeCardsService) {
        this.homeCardsService = homeCardsService;
    }

    @GetMapping
    public List<HomeCard> getCarriersList(@PathVariable long companyId) {
        return homeCardsService.getHomeCards(companyId);
    }

    @PostMapping
    public Long saveCarrier(@PathVariable long companyId, @RequestBody HomeCard homeCard) {
        return homeCardsService.saveHomeCard(homeCard, companyId);
    }
}
