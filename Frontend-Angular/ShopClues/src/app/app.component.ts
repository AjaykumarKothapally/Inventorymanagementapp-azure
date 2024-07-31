import { Component, OnInit } from '@angular/core';
import { ProductService } from './services/product.service';
import { Router } from '@angular/router';
import { CartService } from './services/cart.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ShopClues';
  cartProducts: any[] = [];
  subTotal: number = 0;
  constructor(private productService: ProductService, private cartService: CartService, private router: Router) {
    this.productService.cartAddedSubject.subscribe(res=> {
      debugger;
      this.loadCart();
    })
  }

  ngOnInit(): void {
    this.loadCart();
  }
 
  redirectToSale() {
    this.router.navigateByUrl("/sale");
  }

  loadCart(){
    this.cartService.getCartItems().subscribe((result : any) => {
    this.cartProducts = result;  
  })
}

}
