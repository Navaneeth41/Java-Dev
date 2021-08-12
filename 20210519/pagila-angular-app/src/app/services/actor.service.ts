import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Actor } from '../models/actor.model';



@Injectable({
  providedIn: 'root'
})
export class ActorService {
  private SRVC_URL = "http://localhost:8081/actor_data"; //from env
  constructor(private httpClient: HttpClient) { }
  async fetchAllActorData(): Promise<Actor[]> {
    return this.httpClient.get(this.SRVC_URL + '/actor').toPromise() as Promise<Actor[]>;
  }

  async fetchActorData(ActorId: any): Promise<Actor> {
    return this.httpClient.get(this.SRVC_URL + '/actor/' + ActorId).toPromise() as Promise<Actor>;
  }

  async removeActor(ActorId: number) {
    return this.httpClient.delete(this.SRVC_URL + '/actor/' + ActorId).toPromise();
  }

  async saveActorData(data: Actor) {
    return (data.actorId == null) ?
      this.httpClient.post(this.SRVC_URL + '/actor', data).toPromise() as Promise<Actor> :
      this.httpClient.put(this.SRVC_URL + '/actor', data).toPromise() as Promise<Actor>;
  }
}


