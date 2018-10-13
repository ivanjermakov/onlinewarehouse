package by.itechart.carrier.entity;

import javax.persistence.Entity;

@Entity
public class Driver {
    private String driverInfo;

    public String getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }
}
