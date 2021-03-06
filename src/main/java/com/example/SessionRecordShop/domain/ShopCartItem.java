package com.example.SessionRecordShop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ShopCartItem { //----------- S E S S I O N -------------------------------------
	
	@Id // Primary Key
	@GeneratedValue(strategy=GenerationType.AUTO) // auto generate Primary Key
	private Long shopCartItemId;
	private int quantity;
	private double totalCost;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "recordId") // kts. Record-luokan id
	private Record record;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "orderId") // kts. Record-luokan id
	private MyOrder order;
	
	public ShopCartItem(int quantity, double totalCost, Record record, MyOrder order) {
		super();
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.record = record;
		this.order = order;
	}
	
	public ShopCartItem() {
		super();
		this.quantity = 0;
		this.totalCost = 0.0;
	}

	public Long getShopCartItemId() {
		return shopCartItemId;
	}

	public void setShopCartItemId(Long shopCartItemId) {
		this.shopCartItemId = shopCartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public MyOrder getOrder() {
		return order;
	}

	public void setOrder(MyOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "ShopCartItem [shopCartItemId=" + shopCartItemId + ", quantity=" + quantity + ", totalCost=" + totalCost
				+ ", record=" + record + ", order=" + order + "]";
	}

}