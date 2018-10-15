package by.itechart.carrier.entity;

import by.itechart.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "driver")
public class Driver extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @Column(name = "info")
    private String info;

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
