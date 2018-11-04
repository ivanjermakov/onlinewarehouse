package by.itechart.commoditylot.dto;

import by.itechart.commoditylot.enums.CommodityLotType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CommodityLotFilter {

    private CommodityLotType commodityLotType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate from;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate to;
    private String counterpartyName;

}
