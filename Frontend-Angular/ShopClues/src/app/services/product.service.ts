import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl = './assets/product.json'
  private productUrl2 = './assets/prod.json'
  private baseUrl = 'http://20.235.177.107:8080' //backendloadbalancer


  public cartAddedSubject = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<any[]> {
    return this.http.get<any[]>(`https://app-test-nitco.azurewebsites.net/api/func-testfunc?code=p2mrxT7B91UocMjf4chtW3xjhbQTKQzFRdxsQNzDu3BrAzFuoQjJcg%3D%3D`);
  }
  
  makeSale(salePayload: any, options?: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/sales/makeSale`, salePayload, options);
  }
}
