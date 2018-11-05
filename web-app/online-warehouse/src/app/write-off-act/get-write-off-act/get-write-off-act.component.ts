import {Component, OnInit} from '@angular/core';
import {WriteOffActService} from "../service/write-off-act.service";
import {ActivatedRoute} from "@angular/router";
import {WriteOffActDto} from "../dto/write-off-act.dto";

@Component({
  selector: 'app-get-write-off-act',
  templateUrl: './get-write-off-act.component.html',
  styleUrls: ['./get-write-off-act.component.css']
})
export class GetWriteOffActComponent implements OnInit {

  writeOffActId: number;
  writeOffAct: WriteOffActDto;

  constructor(
    private writeOffActService: WriteOffActService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    if (this.idExist()) {
      this.getWriteOffAct();
    } else {
    }
  }

  idExist(): boolean {
    if (this.route.snapshot.paramMap.get('id') !== null) {
      this.writeOffActId = +this.route.snapshot.paramMap.get('id');
      return true;
    } else {
      return false;
    }
  }

  getWriteOffAct() {
    this.writeOffActService.getWriteOffAct(2, this.writeOffActId)
      .subscribe((data: WriteOffActDto) => this.writeOffAct = data);
  }

}
