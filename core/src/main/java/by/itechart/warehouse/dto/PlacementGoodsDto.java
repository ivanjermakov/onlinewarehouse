package by.itechart.warehouse.dto;

import by.itechart.common.dto.GoodsDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlacementGoodsDto {
    private Long id;
    private Integer amount;
    private GoodsDto goods;
    private Long counterpartyId;
    private Integer storageTimeDays;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expirationDate;
}