import {Component, OnInit} from '@angular/core';
import {WriteOffActListDto} from "../dto/write-off-act-list.dto";
import {WriteOffActTypeEnum} from "../dto/enum/write-off-act-type.enum";
import {WriteOffActService} from "../service/write-off-act.service";
import {WriteOffActFilter} from "../dto/write-off-act.filter";

@Component({
  selector: 'app-write-off-act-list',
  templateUrl: './write-off-act-list.component.html',
  styleUrls: ['./write-off-act-list.component.css']
})
export class WriteOffActListComponent implements OnInit {
  writeOffActList: WriteOffActListDto[];

  constructor(private writeOffActService: WriteOffActService) {
  }

  ngOnInit() {
    this.writeOffActService
      .getWriteOffActs(2, new WriteOffActFilter(WriteOffActTypeEnum.LOSS, null, new Date(Date.now())))
      .subscribe((acts: WriteOffActListDto[]) => {
        this.writeOffActList = acts;
      })
  }
}
