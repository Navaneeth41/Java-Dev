import { ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Actor } from 'src/app/models/actor.model';
import { ActorService } from 'src/app/services/actor.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {
  actors: Actor[] = [];
  displayedColumns= ["actorId", "firstName", "lastName",  "lastUpdate", "actions"];
  isLoading = true;
  errorMsg: string|undefined;
  dataSource: MatTableDataSource<Actor>|undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator|undefined;


  constructor(private actorService: ActorService) { }

  ngOnInit(): void {
    this.initialize();
  }

  ngAfterViewInit() {
    this.setupPaginator();
  }

  private async initialize() {
    await this.fetchActorData();
    this.isLoading = false;
    this.dataSource = new MatTableDataSource(this.actors);
    this.setupPaginator();
  }

  private async fetchActorData () {
    this.actors = await this.actorService.fetchAllActorData();

  }

  setupPaginator() {
    if (this.paginator && this.dataSource) {
      this.dataSource.paginator = this.paginator;
    }
  }

  async actorRemove(accountId: number) {
    this.isLoading = true;
    this.errorMsg = "";
    try {
      await this.actorService.removeActor(accountId);
      this.initialize();
    } catch(error) {
      console.log(error);
      this.errorMsg = "Delete is failed. " + error?.error?.message ;
      this.isLoading = false;
    }
  }

  
}
