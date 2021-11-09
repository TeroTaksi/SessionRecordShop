package com.example.SessionRecordShop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Record {
	
	@Id // Primary Key
	@GeneratedValue(strategy=GenerationType.AUTO) // auto generate Primary Key
	private Long recordId;
	private String artist;
	private String album;
	private String label;
	private int year;
	private double price;
	private String albumCover;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="record")
	private List<ShopCartItem> ShopCartItem;
	
	public Record(String artist,String album, String label, int year, double price, String albumCover) {
		super();
		this.artist = artist;
		this.album = album;
		this.label = label;
		this.year = year;
		this.price = price;
		this.albumCover = albumCover;
	}
	
	public Record() {
		super();
		this.artist = null;
		this.album = null;
		this.label = null;
		this.year = 0;
		this.price = 0;
		this.albumCover = null;
	}

	// Getters
	public Long getRecordId() {
		return recordId;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getLabel() {
		return label;
	}
	
	public int getYear() {
		return year;
	}

	public double getPrice() {
		return price;
	}
	
	public String getAlbumCover() {
		return albumCover;
	}
	
	public List<ShopCartItem> getShopCartItem() {
		return ShopCartItem;
	}

	// Setters
	public void setArtist(String artist) {
		this.artist = artist;
	}	

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setAlbumCover(String albumCover) {
		this.albumCover = albumCover;
	}

	public void setShopCartItem(List<ShopCartItem> shopCartItem) {
		ShopCartItem = shopCartItem;
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", artist=" + artist + ", album=" + album + ", label=" + label
				+ ", year=" + year + ", price=" + price + ", albumCover=" + albumCover + ", ShopCartItem="
				+ ShopCartItem + "]";
	}
	
}