package by.itechart.warehouse.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.enums.PlacementType;
import by.itechart.common.enums.MeasurementUnit;

import javax.persistence.*;
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

    @Column(name = "measurement_unit_type")
    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    @Column(name = "storage_cost")
    private Integer storageCost;

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

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Integer getStorageCost() {
        return storageCost;
    }

    public void setStorageCost(Integer storageCost) {
        this.storageCost = storageCost;
    }

    public List<PlacementGoods> getPlacementGoodsList() {
        return placementGoodsList;
    }

    public void setPlacementGoodsList(List<PlacementGoods> placementGoodsList) {
        this.placementGoodsList = placementGoodsList;
    }
}
