import {GoodsDto} from "../../shared/goods/dto/goods.dto";

export class ConsignmentNoteGoodsDto {
  id: number;
  goods: GoodsDto;
  amount: number;
}
