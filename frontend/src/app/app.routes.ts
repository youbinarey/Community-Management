import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CommunitiesComponent } from './pages/communities/communities.component';
import { FlatComponent } from './pages/properties/flat/flat.component';
import { OwnerComponent } from './pages/owner/owner.component';

import { ParkingComponent } from './pages/properties/parking/parking.component';
import { StorageroomComponent } from './pages/properties/storageroom/storageroom.component';
import { InvoicesComponent } from './pages/invoices/invoices.component';
import { OwnerInvoicesComponent } from './pages/invoices/owner/owner.component';



/**
 * Defines the application's main route configuration.
 *
 * - Redirects the root path ('') to the 'dashboard' route.
 * - Maps specific paths to their corresponding components:
 *   - 'dashboard': Displays the dashboard view.
 *   - 'communities': Shows the list of communities.
 *   - 'properties/flat/:communityId': Displays flats for a given community.
 *   - 'properties/storageroom/:communityId': Displays storagerooms for a given community.
 *   - 'properties/parking/:communityId': Displays parking spaces for a given community.
 *   - 'owners': Shows the list of property owners.
 *   - 'invoices/:communityId': Displays invoices for a specific community.
 *   - 'invoices/owner/:ownerId': Displays invoices for a specific owner.
 *
 * @type {Routes}
 */
export const routes: Routes = [
    {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'communities', component: CommunitiesComponent},
    {path: 'properties/flat/:communityId', component: FlatComponent},
    {path: 'properties/storageroom/:communityId', component: StorageroomComponent},
    {path: 'properties/parking/:communityId', component: ParkingComponent},
    {path: 'owners', component: OwnerComponent},
    {path: 'invoices/:communityId', component: InvoicesComponent},
    {path: 'invoices/owner/:ownerId', component: OwnerInvoicesComponent},
];
