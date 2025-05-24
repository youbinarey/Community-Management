import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CommunitiesComponent } from './pages/communities/communities.component';
import { FlatComponent } from './pages/properties/flat/flat.component';
import { OwnerComponent } from './pages/owner/owner.component';



export const routes: Routes = [
    {path: 'dashboard', component: DashboardComponent},
    {path: 'communities', component: CommunitiesComponent},
    {path: 'properties/flat/:communityId', component: FlatComponent},
    {path: 'owners', component: OwnerComponent}

];
