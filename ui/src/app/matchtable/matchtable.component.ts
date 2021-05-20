import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";

@Component({
  selector: 'app-matchtable',
  templateUrl: './matchtable.component.html',
  styleUrls: ['./matchtable.component.scss']
})
export class MatchtableComponent implements OnInit {
matchtabs:object;
randomobj: object;

  constructor(private myhttp: HttpService) {

  }

  ngOnInit(): void {
    this.myhttp.getMatchList().subscribe(data =>{
      this.matchtabs=data;
    });
  }
  randomBtn(): void{
    this.myhttp.getMatchRandom().subscribe(data =>{
      this.randomobj=data;
      console.log(this.randomobj);
      this.ngOnInit();
    });
  }

}
