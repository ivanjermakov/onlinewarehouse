package by.itechart.common.repository;

import by.itechart.common.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    List<Region> findAllByCountry_Id(Integer countryId);
}
