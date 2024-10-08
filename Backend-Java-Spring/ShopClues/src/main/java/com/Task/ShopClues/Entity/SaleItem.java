package com.Task.ShopClues.Entity;

public class SaleItem {
	private Long productId;
	private Long quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public SaleItem(Long productId, Long quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public SaleItem() {
	}

}
