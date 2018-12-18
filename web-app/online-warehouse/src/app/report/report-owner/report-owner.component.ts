import {Component, OnInit} from '@angular/core';
import {ReportService} from "../service/report.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-report-owner',
  templateUrl: './report-owner.component.html',
  styleUrls: ['./report-owner.component.css']
})
export class ReportOwnerComponent implements OnInit {

  private minDate: Date = new Date(2000, 0, 1);
  private today: Date = new Date();

  private reportDateFilterForm: FormGroup;

  constructor(
    private report: ReportService,
    private fb: FormBuilder
  ) {
    this.reportDateFilterForm = fb.group({
      "from": [''],
      "to": ['']
    });
  }

  ngOnInit() {
  }

  getIncomeReport() {
    this.report.getIncomeReport(this.reportDateFilterForm.value);
  }

  getPersonalLossReport() {
    this.report.getPersonalLossReport(this.reportDateFilterForm.value);
  }

  getProfitReport() {
    this.report.getProfitReport(this.reportDateFilterForm.value);
  }

  getWriteOffReport() {
    this.report.getWriteOffReport(this.reportDateFilterForm.value);
  }


}
