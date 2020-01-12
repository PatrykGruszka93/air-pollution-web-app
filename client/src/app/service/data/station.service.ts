import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class StationService {

  constructor(
    private http:HttpClient
  ) { }

  getAllStations(){
    return this.http.get('http://localhost:8080/api/stations/geoJson');
  }

  getStationRecentData(id){
    return this.http.get(`http://localhost:8080/api/data/recent/station/${id}`);
  }

}
