package by.itechart.warehouse.entity;

import by.itechart.common.enums.UnitOfMeasurement;
import by.itechart.warehouse.enums.PlacementType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Placement {
    private Integer size;
    private PlacementType placementType;
    private UnitOfMeasurement unitOfMeasurement;
    private Integer costOfStorage;
    @OneToMany
    private List<PGoods> pGoodsList;
}
