import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Actor } from 'src/app/models/actor.model';
import { ActorService } from 'src/app/services/actor.service';

@Component({
  selector: 'app-actor-update',
  templateUrl: './actor-update.component.html',
  styleUrls: ['./actor-update.component.scss']
})
export class ActorUpdateComponent implements OnInit {
  actorId: string | null = null;
  actor: Actor | undefined;
  constructor(private route: ActivatedRoute, private accountDataService: ActorService) { }

  async ngOnInit() {
    this.actorId = this.route.snapshot.params.actorId;
    if (this.actorId != null) {
      this.actor = await this.accountDataService.fetchActorData(this.actorId);
    }
  }

}
