package by.itechart.commoditylot.dto;

import by.itechart.commoditylot.enums.CommodityLotStatus;
import by.itechart.commoditylot.enums.CommodityLotType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommodityLotListDto {
    private Long id;
    private String counterpartyName;
    private CommodityLotStatus commodityLotStatus;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creation;
    private CommodityLotType commodityLotType;
}
