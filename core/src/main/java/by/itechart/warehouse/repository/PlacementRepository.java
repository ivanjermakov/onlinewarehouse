package by.itechart.warehouse.repository;

import by.itechart.warehouse.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PlacementRepository extends JpaRepository<Placement, Long>, QuerydslPredicateExecutor<Placement> {

    Placement findPlacementById(long id);

    @Modifying
    @Query("update Placement p set p.deleted = current_date where p.id = ?1")
    void setDeleted(long placementId);
}
