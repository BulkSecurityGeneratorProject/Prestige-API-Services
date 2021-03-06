import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PrestigeSharedModule } from 'app/shared';
import { PrestigeAdminModule } from 'app/admin/admin.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import {
    WalletComponent,
    WalletDetailComponent,
    WalletUpdateComponent,
    WalletDeletePopupComponent,
    WalletDeleteDialogComponent,
    walletRoute,
    walletPopupRoute,
    WalletImportComponent,
    WalletGetBalanceComponent,
    WalletNewPopupComponent,
    WalletNewDialogComponent
} from './';
import { WalletNewComponent } from './wallet-new/wallet-new.component';

const ENTITY_STATES = [...walletRoute, ...walletPopupRoute];

@NgModule({
    imports: [
        PrestigeSharedModule,
        PrestigeAdminModule,
        BrowserAnimationsModule,
        MatInputModule,
        FormsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        WalletComponent,
        WalletDetailComponent,
        WalletUpdateComponent,
        WalletDeleteDialogComponent,
        WalletDeletePopupComponent,
        WalletImportComponent,
        WalletGetBalanceComponent,
        WalletNewDialogComponent,
        WalletNewPopupComponent,
        WalletNewComponent
    ],
    entryComponents: [
        WalletComponent,
        WalletUpdateComponent,
        WalletDeleteDialogComponent,
        WalletDeletePopupComponent,
        WalletImportComponent,
        WalletGetBalanceComponent,
        WalletNewDialogComponent,
        WalletNewPopupComponent,
        WalletNewComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PrestigeWalletModule {}
