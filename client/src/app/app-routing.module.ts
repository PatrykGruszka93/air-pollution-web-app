import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MapComponent } from './map/map.component';
import { ChartComponent } from './chart/chart.component';


const routes: Routes = [
  { path:'', component:MapComponent},
  { path:'chart', component:ChartComponent}
 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
