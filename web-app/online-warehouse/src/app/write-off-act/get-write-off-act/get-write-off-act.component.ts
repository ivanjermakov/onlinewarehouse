import {Component, OnInit} from '@angular/core';
import {WriteOffActService} from "../service/write-off-act.service";
import {ActivatedRoute} from "@angular/router";
import {WriteOffActDto} from "../dto/write-off-act.dto";
import {BehaviorSubject, of} from "rxjs";
import {catchError, finalize} from "rxjs/operators";

@Component({
  selector: 'app-get-write-off-act',
  templateUrl: './get-write-off-act.component.html',
  styleUrls: ['./get-write-off-act.component.css']
})
export class GetWriteOffActComponent implements OnInit {

  writeOffActId: number;
  writeOffAct: WriteOffActDto;

  displayedColumns = ["writeOffType", "amount", "name", "placementType", "measurementUnit", "cost", "weight", "labelling", "description"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();

  private errors: any[];

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
    this.loadingSubject.next(true);
    this.writeOffActService.getWriteOffAct(this.writeOffActId).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(page => {
        if (page instanceof Array) {
          this.errors = page as any[];
          console.log('test', this.errors)
        } else {
          this.writeOffAct = page;
          console.log('test', this.writeOffAct)
        }
      });
  }

}
