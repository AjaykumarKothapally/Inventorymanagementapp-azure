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
 

  constructor(private productService: ProductService, private cartService: CartService, private router: Router) {}

  ngOnInit(): void {
      this.cartService.getCartItems().subscribe((cartItems: any[]) => {
      this.cartProducts = cartItems;
      this.subTotal = this.cartProducts.reduce((total, cartItem) => total + cartItem.productPrice  * cartItem.quantity, 0 );
      console.log('Cart Items:', this.cartProducts);
      console.log('SubTotal:', this.subTotal);
    });
}
 
delete(product: any) {
  const index = this.cartProducts.indexOf(product);
  if (index !== -1) {
    this.cartProducts.splice(index, 1);
    this.subTotal -= product.productPrice * product.quantity;
    console.log('Product removed:', product);
    console.log('Updated Cart Items:', this.cartProducts);
    console.log('Updated SubTotal:', this.subTotal);
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
        console.log('Sale Payload:', salePayload);

        this.productService.makeSale(salePayload, { responseType: 'text' }).subscribe(
            (res: any) => {
                console.log('Response from server :', res);
                if (res.includes('Sale processed successfully')) {
                    this.cartProducts = [];
                    this.subTotal = 0;
                    alert("Sale successful");
                    this.router.navigateByUrl("/");
                }
            },
            (error) => {
              console.error('Error occurred during sale:', error);
              alert("Sale failed. Please try again.");
            }
        );
    }
}

}
