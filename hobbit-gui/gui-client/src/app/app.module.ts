import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule, LocationStrategy, HashLocationStrategy } from '@angular/common';
import { XHRBackend, Http, RequestOptions, HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DataTableModule, ConfirmDialogModule, ConfirmationService, CalendarModule, TooltipModule } from 'primeng/primeng';
import { DynamicFormsCoreModule } from '@ng2-dynamic-forms/core';
import { DynamicFormsBootstrapUIModule } from './dyn-form/ui-bootstrap.module';

import { rootRouterConfig } from './app.routes';
import { AppComponent } from './app.comp';
import { MenuItemComponent, NavbarComponent } from './navbar.comp';
import { HomeComponent } from './home.comp';
import { UploadBenchmarkComponent } from './upload-benchmark.comp';
import { UploadSystemComponent } from './upload-system.comp';
import { BenchmarkSubmitComponent } from './benchmark-submit.comp';
import { BenchmarkSubmitResponseComponent } from './benchmark-submit-response.comp';
import { BenchmarkStatusComponent } from './benchmark-status.comp';
import { BenchmarkResultDisplayComponent } from './benchmark-result-display.comp';
import { BenchmarkConfigParamsComponent } from './benchmark-configparams.comp';
import { SubmissionDetailsComponent } from './submission-details.comp';
import { ChallengesListComponent } from './challenges-list.comp';
import { ChallengeEditComponent } from './challenge-edit.comp';
import { ChallengeTaskEditComponent } from './challenge-task-edit.comp';
import { ExperimentsComponent } from './experiments.comp';
import { ExperimentsDetailsComponent } from './experiments-details.comp';
import { ExperimentsDetailsWrapperComponent } from './experiments-details-wrapper.comp';
import { ChallengeRegisterSystemsComponent } from './challenge-register-systems.comp';
import { ChallengeShowRegistrationsComponent } from './challenge-show-registrations.comp';
import { ChallengeTasksExperimentsComponent } from './challenge-tasks-experiments.comp';

import { PageHeaderComponent } from './shared/pageHeader.comp';
import { WaitLoadingComponent } from './shared/waitLoading.comp';
import { ShowErrorComponent } from './shared/showError.comp';
import { CheckboxComponent } from './shared/checkbox.comp';

import { BackendService } from './services/backend.service';
import { CustomHttp } from './services/customHttp';
import { KeycloakService } from './services/keycloakService';

const httpProvide = { provide: Http,
        useFactory: (backend: XHRBackend, defaultOptions: RequestOptions, keycloakService: KeycloakService) => new CustomHttp(backend, defaultOptions, keycloakService),
        deps: [XHRBackend, RequestOptions, KeycloakService] };

const hashStrategyProvide = { provide: LocationStrategy, useClass: HashLocationStrategy };

@NgModule({
  imports:      [ BrowserModule, CommonModule, FormsModule, ReactiveFormsModule,
                  HttpModule, RouterModule.forRoot(rootRouterConfig),
                  DynamicFormsCoreModule.forRoot(),
                  DynamicFormsBootstrapUIModule, DataTableModule, ConfirmDialogModule, CalendarModule, TooltipModule ],
  providers:    [ BackendService, KeycloakService, httpProvide, hashStrategyProvide, ConfirmationService ],
  declarations: [ AppComponent, MenuItemComponent, NavbarComponent, HomeComponent,
                  UploadBenchmarkComponent, UploadSystemComponent,
                  PageHeaderComponent, WaitLoadingComponent, ShowErrorComponent, CheckboxComponent,
                  BenchmarkSubmitComponent, BenchmarkSubmitResponseComponent,
                  BenchmarkConfigParamsComponent,
                  BenchmarkStatusComponent, BenchmarkResultDisplayComponent, SubmissionDetailsComponent,
                  ChallengesListComponent, ChallengeEditComponent, ChallengeTaskEditComponent,
                  ExperimentsComponent, ExperimentsDetailsComponent, ExperimentsDetailsWrapperComponent,
                  ChallengeRegisterSystemsComponent, ChallengeShowRegistrationsComponent,
                  ChallengeTasksExperimentsComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
