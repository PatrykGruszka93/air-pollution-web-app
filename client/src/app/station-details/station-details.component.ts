import { Component, OnInit } from '@angular/core';
import { StationService } from '../service/data/station.service';

@Component({
  selector: 'app-station-details',
  templateUrl: './station-details.component.html',
  styleUrls: ['./station-details.component.css']
})
export class StationDetailsComponent implements OnInit {

  private stationPollutionData: any[];
  private stationData: any[];

  constructor(
    private stationService : StationService
  ) { }

  ngOnInit() {
  }

  setStationDetailsData(data : any[]){
    console.log("in setStaionDetailsData", data)
    this.stationPollutionData = data;
  }

  setStationData(data : any[]){
    this.stationData = data;
    console.log(data);
  }
  

}
