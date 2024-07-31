import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  productList: any [] = [];
  cartProducts: any[] = [];


  constructor(private productService: ProductService, private cartService: CartService) { }

  
  ngOnInit(): void {
    this.loadAllProducts();
  }


  loadAllProducts() {
    this.productService.getAllProducts().subscribe((result: any)=>{
      this.productList = result;
      console.log(result);
    })
  }

addItemToCart(product: any) {
  if (!product.isInCart) {
    this.cartService.addToCart(product);
    product.isInCart = true;
    product.quantity = 1;  // Set the initial quantity to 1
  } else {
    product.quantity++;    // Increase the quantity if the item is already in the cart
  }
  alert('Product Added To Cart');
}

increaseQuantity(product: any) {
  product.quantity++;       // Increase the quantity when the + button is clicked
}

decreaseQuantity(product: any) {
  if (product.quantity > 0) {        
    product.quantity--;       // Decrease the quantity when the - button is clicked
  }
}

}

