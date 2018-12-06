package by.itechart.homecard.repository;

import by.itechart.homecard.HomeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HomeCardRepository extends CrudRepository<HomeCard, Long> {

    List<HomeCard> findFirst9ByCompany_IdOrderByIdDesc(Long companyId);
}
