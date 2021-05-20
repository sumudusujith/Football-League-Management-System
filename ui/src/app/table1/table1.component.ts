import { Component, OnInit } from '@angular/core';
import { HttpService} from '../http.service';

@Component({
  selector: 'app-table1',
  templateUrl: './table1.component.html',
  styleUrls: ['./table1.component.scss']
})
export class Table1Component implements OnInit {
  tabloopes: object;
 

  constructor(private myhttp: HttpService) { }

  ngOnInit(): void {
  }

  sortByWins(){
    this.myhttp.getSortWins().subscribe(data =>{
      this.tabloopes=data;
      console.log(this.tabloopes);
    });
  }
    sortByGoals(){
      this.myhttp. getSortGoals().subscribe(data =>{
        this.tabloopes=data;
        console.log(this.tabloopes);
      });
    }
    sortByPts(){
      this.myhttp.getclubList().subscribe(data =>{
        this.tabloopes=data;
        console.log(this.tabloopes);
      });
    }

}

