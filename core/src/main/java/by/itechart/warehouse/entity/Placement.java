package by.itechart.warehouse.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.enums.UnitOfMeasurement;
import by.itechart.common.enums.PlacementType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "placement")
public class Placement extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "size")
    private Integer size;

    @Column(name = "placement_type")
    @Enumerated(EnumType.STRING)
    private PlacementType placementType;

    @Column(name = "unit_of_measurement")
    @Enumerated(EnumType.STRING)
    private UnitOfMeasurement unitOfMeasurement;

    @Column(name = "cost_of_storage")
    private Integer costOfStorage;

    @OneToMany(mappedBy = "placement")
    private List<PlacementGoods> placementGoodsList;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public List<PlacementGoods> getPlacementGoodsList() {
        return placementGoodsList;
    }

    public void setPlacementGoodsList(List<PlacementGoods> placementGoodsList) {
        this.placementGoodsList = placementGoodsList;
    }
}
