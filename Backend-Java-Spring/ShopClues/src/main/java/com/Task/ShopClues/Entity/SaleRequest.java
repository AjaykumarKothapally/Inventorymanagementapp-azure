package com.Task.ShopClues.Entity;

import java.util.List;

public class SaleRequest {

	private SaleDetails saleObj;
	private List<SaleItem> cartItems;

	public SaleDetails getSaleObj() {
		return saleObj;
	}

	public void setSaleObj(SaleDetails saleObj) {
		this.saleObj = saleObj;
	}

	public List<SaleItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<SaleItem> cartItems) {
		this.cartItems = cartItems;
	}

	public SaleRequest(SaleDetails saleObj, List<SaleItem> cartItems) {
		this.saleObj = saleObj;
		this.cartItems = cartItems;
	}

	public SaleRequest() {
	}

}
