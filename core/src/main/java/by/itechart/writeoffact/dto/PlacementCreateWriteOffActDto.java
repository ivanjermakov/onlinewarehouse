package by.itechart.writeoffact.dto;

import by.itechart.writeoffact.enums.WriteOffActType;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlacementCreateWriteOffActDto {
    private Long creatorId;
    private String responsiblePerson;
    private WriteOffActType writeOffActType;
    private List<PlacementWriteOffActGoodsDto> placementGoodsDtoList;

    public CreateWriteOffActDto mapToCreateWriteOffActDto() {
        CreateWriteOffActDto createWriteOffActDto = new CreateWriteOffActDto();
        createWriteOffActDto.setCreatorId(this.creatorId);
        createWriteOffActDto.setResponsiblePerson(this.responsiblePerson);
        createWriteOffActDto.setWriteOffActType(this.writeOffActType);
        for (int i = 0; i < this.placementGoodsDtoList.size(); i++) {
            for (int j = i + 1; j < this.placementGoodsDtoList.size(); ) {
                if (this.placementGoodsDtoList.get(i).getPlacementGoods().getGoods().getId()
                        .equals(this.placementGoodsDtoList.get(j).getPlacementGoods().getGoods().getId())) {
                    this.placementGoodsDtoList.get(i).setAmount(this.placementGoodsDtoList.get(i).getAmount() +
                            this.placementGoodsDtoList.get(j).getAmount());
                    this.placementGoodsDtoList.remove(j);
                } else {
                    j++;
                }
            }
        }

        List<CreateWriteOffActGoodsDto> createWriteOffActGoodsDtoList = this.placementGoodsDtoList.stream().map(placementGoodsDto -> {
            CreateWriteOffActGoodsDto createWriteOffActGoodsDto = new CreateWriteOffActGoodsDto();
            createWriteOffActGoodsDto.setAmount(placementGoodsDto.getAmount());
            createWriteOffActGoodsDto.setGoodsId(placementGoodsDto.getPlacementGoods().getGoods().getId());
            createWriteOffActGoodsDto.setWriteOffType(placementGoodsDto.getWriteOffType());
            return createWriteOffActGoodsDto;
        }).collect(Collectors.toList());

        createWriteOffActDto.setWriteOffActGoodsDtoList(createWriteOffActGoodsDtoList);
        return createWriteOffActDto;
    }
}
