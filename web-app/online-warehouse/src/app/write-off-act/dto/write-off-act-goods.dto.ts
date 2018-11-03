import {GoodsDto} from '../../shared/goods/goods.dto';
import {WriteOffType} from '../enum/write-off-type.enum';
import {WriteOffActDto} from './write-off-act.dto';

export class WriteOffActGoodsDto {
  id: number;
  goods: GoodsDto;
  writeOffAct: WriteOffActDto;
  writeOffType: WriteOffType;
  amount: number;


  constructor(id: number, goods: GoodsDto, writeOffAct: WriteOffActDto, writeOffType: WriteOffType, amount: number) {
    this.id = id;
    this.goods = goods;
    this.writeOffAct = writeOffAct;
    this.writeOffType = writeOffType;
    this.amount = amount;
  }
}
