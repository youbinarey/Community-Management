import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CommunitiesComponent } from './pages/communities/communities.component';
import { FlatComponent } from './pages/properties/flat/flat.component';
import { OwnerComponent } from './pages/owner/owner.component';
import { StorageroomComponent } from './properties/storageroom/storageroom.component';
import { ParkingComponent } from './properties/parking/parking.component';



export const routes: Routes = [
    {path: 'dashboard', component: DashboardComponent},
    {path: 'communities', component: CommunitiesComponent},
    {path: 'properties/flat/:communityId', component: FlatComponent},
    {path: 'properties/storageroom/:communityId', component: StorageroomComponent},
    {path: 'properties/parking/:communityId', component: ParkingComponent},
    {path: 'owners', component: OwnerComponent}

];
