package by.itechart.homecard.service;

import by.itechart.homecard.HomeCard;

import java.util.List;

public interface HomeCardService {
    List<HomeCard> getHomeCards(long companyId);

    Long saveHomeCard(HomeCard homeCard, long companyId);
}
