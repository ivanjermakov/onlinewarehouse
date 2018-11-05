import {GoodsDto} from '../../shared/goods/goods.dto';
import {WriteOffActDto} from './write-off-act.dto';
import {WriteOffTypeEnum} from "./enum/write-off-type.enum";

export class CreateWriteOffActGoodsDto {
  id: number;
  goods: GoodsDto;
  writeOffAct: WriteOffActDto;
  writeOffType: WriteOffTypeEnum;
  amount: number;


  constructor(id: number, goods: GoodsDto, writeOffAct: WriteOffActDto, writeOffType: WriteOffTypeEnum, amount: number) {
    this.id = id;
    this.goods = goods;
    this.writeOffAct = writeOffAct;
    this.writeOffType = writeOffType;
    this.amount = amount;
  }
}
