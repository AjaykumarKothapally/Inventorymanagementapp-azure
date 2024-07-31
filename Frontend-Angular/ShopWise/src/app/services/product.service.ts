import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl = './assets/product.json'
  private productUrl2 = './assets/prod.json'
  private baseUrl = 'http://a38ffc5bc9ff3465aa137c68cfb02b13-1227728245.ap-south-1.elb.amazonaws.com:9091'  //backendloadbalancer


  public cartAddedSubject = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<any[]> {
    debugger;
    return this.http.get<any[]>(`${this.baseUrl}/products/all`);
  }
  
  makeSale(salePayload: any, options?: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/sales/makeSale`, salePayload, options);
  }
}
