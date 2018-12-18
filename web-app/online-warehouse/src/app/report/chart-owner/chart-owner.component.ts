import {Component, OnInit} from '@angular/core';
import * as Highcharts from 'highcharts';
import {ReportService} from "../service/report.service";
import {AuthorityNameEnum} from "../../user/dto/enum/authority-name.enum";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-chart-owner',
  templateUrl: './chart-owner.component.html',
  styleUrls: ['./chart-owner.component.css']
})
export class ChartOwnerComponent implements OnInit {

  Highcharts = Highcharts;
  showUserRolesStatisticsChart: boolean = false;
  userRolesStatisticsChart = {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false
    },
    title: {
      text: 'User roles statistics'
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',

        dataLabels: {
          enabled: true,
          format: '<b>{point.name}</b>: {point.y}',
          style: {
            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) ||
              'black'
          }
        }
      }
    },
    series: [{
      type: 'pie',
      name: 'Count of users',
      data: []
    }]
  };
  showActCreatorsStatisticsChart: boolean = false;
  actCreatorsStatisticsChart = {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false
    },
    title: {
      text: 'Write-off cases creators statistics'
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',

        dataLabels: {
          enabled: true,
          format: '<b>{point.name}</b>: {point.y}',
          style: {
            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) ||
              'black'
          }
        }
      }
    },
    series: [{
      type: 'pie',
      name: 'Count of cases',
      data: []
    }]
  };
  showInCounterpartiesGoodsCostStatisticsChart: boolean = false;
  showOutCounterpartiesGoodsCostStatisticsChart: boolean = false;
  inCounterpartiesGoodsCostStatisticsChart: any = {};
  outCounterpartiesGoodsCostStatisticsChart: any = {};
  counterpartiesGoodsCostStatisticsChart = {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false
    },
    title: {
      text: 'Counterparties goods cost statistics'
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          format: '<b>{point.name}</b>: ${point.y}',
          style: {
            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) ||
              'black',
            'font-size': '14px'
          }
        }
      }
    },
    series: [{
      type: 'pie',
      name: 'Counterparty',
      data: []
    }]
  };
  private loadingSubject = new BehaviorSubject<boolean>(true);
  loading$ = this.loadingSubject.asObservable();

  constructor(private report: ReportService) {
  }

  ngOnInit() {
    this.report.getUserRolesStatistics().subscribe((data) => {
      let arrayData = data.map((pieChartDto) => {
        return [AuthorityNameEnum[pieChartDto.name], pieChartDto.y]
      });
      this.userRolesStatisticsChart.series[0].data = arrayData;
      this.showUserRolesStatisticsChart = true;
    });
    this.report.getInCounterpartiesGoodsCostStatistics().subscribe((data) => {
      Object.assign(this.inCounterpartiesGoodsCostStatisticsChart, this.counterpartiesGoodsCostStatisticsChart);
      this.inCounterpartiesGoodsCostStatisticsChart.title.text = 'Counterparties income goods cost statistics';
      this.inCounterpartiesGoodsCostStatisticsChart.series[0].data = data;
      this.showInCounterpartiesGoodsCostStatisticsChart = true;
    });
    this.report.getOutCounterpartiesGoodsCostStatistics().subscribe((data) => {
      Object.assign(this.outCounterpartiesGoodsCostStatisticsChart, this.counterpartiesGoodsCostStatisticsChart);
      this.outCounterpartiesGoodsCostStatisticsChart.title.text = 'Counterparties outcome goods cost statistics';
      this.outCounterpartiesGoodsCostStatisticsChart.series[0].data = data;
      this.showOutCounterpartiesGoodsCostStatisticsChart = true;
    });
    this.report.getOutCounterpartiesGoodsCostStatistics().subscribe((data) => {
      Object.assign(this.outCounterpartiesGoodsCostStatisticsChart, this.counterpartiesGoodsCostStatisticsChart);
      this.outCounterpartiesGoodsCostStatisticsChart.title.text = 'Counterparties outcome goods cost statistics';
      this.outCounterpartiesGoodsCostStatisticsChart.series[0].data = data;
      this.showOutCounterpartiesGoodsCostStatisticsChart = true;
    });
    this.report.getActCreatorsStatistics().subscribe((data) => {
      this.actCreatorsStatisticsChart.series[0].data = data;
      this.showActCreatorsStatisticsChart = true;
      this.loadingSubject.next(false);
    });
    // this.report.getPaymentStatistics().subscribe((data) => {
    //   let categories = [];
    //   let someData = [];
    //   console.log(data);
    //   data.map(value => {
    //   });
    //   this.actCreatorsStatisticsChart.series[0].data = data;
    //   this.showActCreatorsStatisticsChart = true;
    //   this.loadingSubject.next(false);
    // });
  }

  getUserRolesStatistics(): Object {
    return this.userRolesStatisticsChart;
  }

  getInCounterpartiesGoodsCostStatistics(): Object {
    return this.inCounterpartiesGoodsCostStatisticsChart;
  }

  getOutCounterpartiesGoodsCostStatistics(): Object {
    return this.outCounterpartiesGoodsCostStatisticsChart;
  }

  getActCreatorsStatistics(): Object {
    return this.actCreatorsStatisticsChart;
  }

}
