package by.itechart.warehouse.entity;

import by.itechart.common.enums.UnitOfMeasurement;
import by.itechart.common.enums.PlacementType;

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public PlacementType getPlacementType() {
        return placementType;
    }

    public void setPlacementType(PlacementType placementType) {
        this.placementType = placementType;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Integer getCostOfStorage() {
        return costOfStorage;
    }

    public void setCostOfStorage(Integer costOfStorage) {
        this.costOfStorage = costOfStorage;
    }

    public List<PGoods> getpGoodsList() {
        return pGoodsList;
    }

    public void setpGoodsList(List<PGoods> pGoodsList) {
        this.pGoodsList = pGoodsList;
    }
}
