package com.Task.ShopClues.Entity;

import java.util.List;

public class SaleRequest {

	private List<SaleItem> cartItems;

	public List<SaleItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<SaleItem> cartItems) {
		this.cartItems = cartItems;
	}

	public SaleRequest(List<SaleItem> cartItems) {
		this.cartItems = cartItems;
	}

	public SaleRequest() {
	}
}
