package by.itechart.carrier.entity;

import by.itechart.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Driver extends BaseEntity {

    private String driverInfo;

    public String getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }
}
