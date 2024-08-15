package com.Task.ShopClues.Entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;

@Container(containerName = "Products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {

	@Id
	private String id; // ID of the document

	@PartitionKey
	private Long productId; // Partition key

	private Long productQty; // Quantity of the product
	private String productSku; // SKU of the product
	private String productName; // Name of the product
	private Integer productPrice; // Price of the product
	private String productShortName; // Short name of the product
	private String productDescription; // Description of the product
	private String createdDate; // Date when the product was created
	private String deliveryTimeSpan; // Delivery time span
	private Integer categoryId; // Category ID
	private String productImageUrl; // URL of the product image
	private String categoryName; // Category name

	// Default constructor
	public Products() {
	}

	// Parameterized constructor
	public Products(String id, Long productId, Long productQty, String productSku, String productName,
			Integer productPrice, String productShortName, String productDescription,
			String createdDate, String deliveryTimeSpan, Integer categoryId,
			String productImageUrl, String categoryName) {
		this.id = id;
		this.productId = productId;
		this.productQty = productQty;
		this.productSku = productSku;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productShortName = productShortName;
		this.productDescription = productDescription;
		this.createdDate = createdDate;
		this.deliveryTimeSpan = deliveryTimeSpan;
		this.categoryId = categoryId;
		this.productImageUrl = productImageUrl;
		this.categoryName = categoryName;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductQty() {
		return productQty;
	}

	public void setProductQty(Long productQty) {
		this.productQty = productQty;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryTimeSpan() {
		return deliveryTimeSpan;
	}

	public void setDeliveryTimeSpan(String deliveryTimeSpan) {
		this.deliveryTimeSpan = deliveryTimeSpan;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", productId=" + productId + ", productQty=" + productQty + ", productSku="
				+ productSku + ", productName=" + productName + ", productPrice=" + productPrice + ", productShortName="
				+ productShortName + ", productDescription=" + productDescription + ", createdDate=" + createdDate
				+ ", deliveryTimeSpan=" + deliveryTimeSpan + ", categoryId=" + categoryId + ", productImageUrl="
				+ productImageUrl + ", categoryName=" + categoryName + "]";
	}
}
