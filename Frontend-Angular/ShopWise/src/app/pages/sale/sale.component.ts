import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { BehaviorSubject, Subscription } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent implements OnInit {

  cartProducts: any[] = [];
  subTotal: number = 0;
  product: any; 

  constructor(private productService: ProductService, private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
      this.cartService.getCartItems().subscribe((cartItems: any[]) => {
      this.cartProducts = cartItems;
      this.subTotal = this.cartProducts.reduce((total, cartItem) => total + cartItem.productPrice  * cartItem.quantity, 0 );
    });
}
 
delete(product: any) {
  const index = this.cartProducts.indexOf(product);
  if (index !== -1) {
    this.cartProducts.splice(index, 1);
    this.subTotal -= product.productPrice * product.quantity;
  }
}
 

  HomePage(){
    this.router.navigateByUrl("/products");
  }

   makeSale() {
     if (this.cartProducts.length > 0) {
       const salePayload = {
        cartItems: this.cartProducts.map(cartItem => {
           return {
             productId: cartItem.productId,
             quantity: cartItem.quantity
           };
         })
       };
      this.productService.makeSale(salePayload ,{ responseType: 'text' }).subscribe((res: any) => {
        console.log('Response:', res);
        if (res.includes('Sale successful')) {
          this.cartProducts = [];
          alert("Sale successful");
          this.router.navigateByUrl("/products");
        }
      });
    }
  }
}
