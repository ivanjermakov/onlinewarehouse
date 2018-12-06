package by.itechart.homecard.service;

import by.itechart.company.entity.Company;
import by.itechart.homecard.HomeCard;
import by.itechart.homecard.repository.HomeCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeCardServiceImpl implements HomeCardService {

    private final HomeCardRepository homeCardRepository;

    @Autowired
    public HomeCardServiceImpl(HomeCardRepository homeCardRepository) {
        this.homeCardRepository = homeCardRepository;
    }

    @Override
    public List<HomeCard> getHomeCards(long companyId) {
        return homeCardRepository.findFirst9ByCompany_IdOrderByIdDesc(companyId);
    }

    @Override
    public Long saveHomeCard(HomeCard homeCard, long companyId) {
        homeCard.setCompany(new Company(companyId));
        return homeCardRepository.save(homeCard).getId();
    }
}
