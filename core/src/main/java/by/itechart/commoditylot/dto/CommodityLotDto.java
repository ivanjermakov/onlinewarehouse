package by.itechart.commoditylot.dto;

import by.itechart.commoditylot.enums.CommodityLotType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CommodityLotDto {

    private Long id;
    private Long counterpartyId;
    private CommodityLotType commodityLotType;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creation;
    private List<CommodityLotGoodsDto> commodityLotGoodsList;
}
