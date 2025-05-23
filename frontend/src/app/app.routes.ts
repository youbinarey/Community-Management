import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CommunitiesComponent } from './pages/communities/communities.component';



export const routes: Routes = [
    {path: 'dashboard', component: DashboardComponent},
    {path: 'communities', component: CommunitiesComponent}

];
