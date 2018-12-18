package by.itechart.commoditylot.repository;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;

public interface InputGoodsStatistics {
    String getCounterpartyName();

    String getCounterpartyTaxNumber();

    String getGoodsName();

    PlacementType getGoodsPlacementType();

    MeasurementUnit getGoodsMeasurementUnitType();

    Integer getAmount();

    Integer getCost();

    Float getWeight();

    String getLabelling();

    String getDescription();
}
