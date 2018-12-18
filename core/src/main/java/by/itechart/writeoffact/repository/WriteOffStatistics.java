package by.itechart.writeoffact.repository;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;

public interface WriteOffStatistics {
    String getResponsiblePerson();

    String getWriteOffType();

    String getGoodsName();

    PlacementType getGoodsPlacementType();

    MeasurementUnit getGoodsMeasurementUnitType();

    Integer getAmount();

    Integer getCost();

    Float getWeight();

    String getLabelling();

    String getDescription();
}
