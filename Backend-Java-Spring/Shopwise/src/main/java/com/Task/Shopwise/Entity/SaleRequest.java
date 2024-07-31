package com.Task.Shopwise.Entity;

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

}
