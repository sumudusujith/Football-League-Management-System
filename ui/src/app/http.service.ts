import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  getclubList(){
    return this.http.get('/api/clubs');
  }
  getMatchList(){
    return this.http.get('/api/matches');
  }
  getMatchRandom(){
    return this.http.get('/api/random');
  }
  getSortGoals(){
    return this.http.get('/api/goals');
  }
  getSortWins(){
    return this.http.get('/api/wins');
  }

}
