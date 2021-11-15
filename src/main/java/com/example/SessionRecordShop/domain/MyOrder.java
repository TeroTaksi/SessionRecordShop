package com.example.SessionRecordShop.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class MyOrder { //----------- S E S S I O N -------------------------------------
	
	@Id // Primary Key
	@GeneratedValue(strategy=GenerationType.AUTO) // auto generate Primary Key
	private Long orderId;
	private String orderNumber;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="order")
	private List<ShopCartItem> ShopCartItemList;
	
	public MyOrder(String orderNumber, Date date) {
		this.orderNumber = orderNumber;
		this.date = date;	
	}
	
	public MyOrder() {
		super();
		this.orderNumber = null;
		this.date = null;
	}

	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ShopCartItem> getShopCartItemList() {
		return ShopCartItemList;
	}

	public void setShopCartItemList(List<ShopCartItem> shopCartItemList) {
		ShopCartItemList = shopCartItemList;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNumber=" + orderNumber + ", date=" + date + ", ShopCartItemList="
				+ ShopCartItemList + "]";
	}
	
}
