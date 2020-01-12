import { Component, AfterViewInit, ViewChild, ElementRef, Input } from '@angular/core';
import { StationService } from '../service/data/station.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  
  public myData: any[];

  private stationData: any[];
  private stationPollutionData: any[];

  map: google.maps.Map;
  marker: google.maps.Marker;
  @ViewChild('mapWrapper', {static: false}) mapElement: ElementRef;
  

  constructor(
    private stationService : StationService
  ){}
    
  ngAfterViewInit() {
    this.initializeMap();
  }

  initializeMap() {
    const lngLat = new google.maps.LatLng(52, 19);
    const mapOptions: google.maps.MapOptions = {
      center: lngLat,
      zoom: 0,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      fullscreenControl: false,
      mapTypeControl: false,
      streetViewControl: false,

      restriction : {
        latLngBounds: {
          east: 25,
          north: 56,
          south: 48,
          west: 13
        },
        strictBounds: true
      },
      
      styles: [
        {
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#f5f5f5"
            }
          ]
        },
        {
          "elementType": "labels.icon",
          "stylers": [
            {
              "visibility": "off"
            }
          ]
        },
        {
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#616161"
            }
          ]
        },
        {
          "elementType": "labels.text.stroke",
          "stylers": [
            {
              "color": "#f5f5f5"
            }
          ]
        },
        {
          "featureType": "administrative.country",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#000000"
            },
            {
              "visibility": "on"
            },
            {
              "weight": 2
            }
          ]
        },
        {
          "featureType": "administrative.land_parcel",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#bdbdbd"
            }
          ]
        },
        {
          "featureType": "administrative.locality",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#000000"
            },
            {
              "visibility": "on"
            },
            {
              "weight": 1
            }
          ]
        },
        {
          "featureType": "administrative.locality",
          "elementType": "labels",
          "stylers": [
            {
              "visibility": "simplified"
            }
          ]
        },
        {
          "featureType": "administrative.province",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#000000"
            },
            {
              "visibility": "on"
            },
            {
              "weight": 1
            }
          ]
        },
        {
          "featureType": "poi",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#eeeeee"
            }
          ]
        },
        {
          "featureType": "poi",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#757575"
            }
          ]
        },
        {
          "featureType": "poi.park",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#e5e5e5"
            }
          ]
        },
        {
          "featureType": "poi.park",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#9e9e9e"
            }
          ]
        },
        {
          "featureType": "road",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#ffffff"
            }
          ]
        },
        {
          "featureType": "road.arterial",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#757575"
            }
          ]
        },
        {
          "featureType": "road.highway",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#dadada"
            }
          ]
        },
        {
          "featureType": "road.highway",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#616161"
            }
          ]
        },
        {
          "featureType": "road.local",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#9e9e9e"
            }
          ]
        },
        {
          "featureType": "transit.line",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#e5e5e5"
            }
          ]
        },
        {
          "featureType": "transit.station",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#eeeeee"
            }
          ]
        },
        {
          "featureType": "water",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#c9c9c9"
            }
          ]
        },
        {
          "featureType": "water",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#9e9e9e"
            }
          ]
        }
      ]      
    };

    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

    this.stationService.getAllStations().subscribe((geoData: any[])=>{
      this.map.data.addGeoJson(geoData);
    })
    this.setMarkersStyle();
    this.addMarkerListiner(event)
  }



  setMarkersStyle(){
    this.map.data.setStyle(feature => {
      var index = parseInt(feature.getProperty("indexId"));
      var value = this.getCircle(index);
      return {
        icon: value
      }
    });
  }

  getCircle(index){
    var colour;
    if(index===1) colour = 'green';
    if(index===2) colour = 'yellow';
    if(index===3) colour = 'orange';
    if(index===4) colour = 'red';
    if(index===5) colour = 'black';
    if(index===6) colour = 'white';
    if(index===7) colour = 'gray';
      
    return {
      path: google.maps.SymbolPath.CIRCLE,
      fillColor: colour,
      fillOpacity: .6,
      scale: 10,
      strokeColor: 'white',
      strokeWeight: .01
    };   
  }

  addMarkerListiner($event) {
    this.map.data.addListener('click', (event) => {
      var stationId = parseInt(event.feature.getProperty('id'));
      var city = event.feature.getProperty('city');
      var streetAddress = event.feature.getProperty('streetAddress');
      var indexName = event.feature.getProperty('indexName');
      var stationData = {"city": city, "streetAddress" : streetAddress, "indexName" : indexName};
      this.stationService.getStationRecentData(stationId).subscribe(
        (res: any) => {
          this.myData = res;
          this.setOnStationPollutionDetails(res);
          this.setStationData(stationData);
          console.log('res is ', res);
        },
        error => {
          alert("ERROR");
        });
      }
    )
    
  }
  setOnStationPollutionDetails(data : any[]){
    this.stationPollutionData = data;
    console.log("setOnStationDetails");
  }

  setStationData(data : any){
    this.stationData = data;
  }

  


 
}