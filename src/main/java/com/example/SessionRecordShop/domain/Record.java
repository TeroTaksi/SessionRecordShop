package com.example.SessionRecordShop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//
//import hh.swd20.recordshop.domain.Format;
//import hh.swd20.recordshop.domain.ShopCartItem;


@Entity
public class Record { //----------- S E S S I O N -------------------------------------
	
	//@NotNull // Jos päällä, /saverecord ei natsaa
	@Id // Primary Key
	@GeneratedValue(strategy=GenerationType.AUTO) // auto generate Primary Key
	private Long recordId;
	
	//@NotEmpty
	@NotNull
	@Size(min=1, max=60)
	private String artist;
	
	//@NotEmpty
	@NotNull
	@Size(min=1, max=60)
	private String album;
	
	//@NotEmpty
	@NotNull
	@Size(min=1, max=60)
	private String label;
	
	@NotNull
	@Digits(integer = 4, fraction = 0)
	private int year;

	@NotNull
	private double price;
	
	@NotNull
	private String albumCover;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="record")
	private List<ShopCartItem> ShopCartItem;
	
	@ManyToOne
	@JoinColumn(name = "formatId") // kts. Format-luokan id
	private Format format;
	
	public Record(String artist,String album, String label, int year, double price, String albumCover, Format format) {
		super();
		this.artist = artist;
		this.album = album;
		this.label = label;
		this.year = year;
		this.price = price;
		this.albumCover = albumCover;
		this.format = format;
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
	
	public Format getFormat() {
		return format;
	}
	
	public List<ShopCartItem> getShopCartItem() {
		return ShopCartItem;
	}

	// Setters -------------------------------------------
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
	
	public void setFormat(Format format) {
		this.format = format;
	}

	public void setShopCartItem(List<ShopCartItem> shopCartItem) {
		ShopCartItem = shopCartItem;
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", artist=" + artist + ", album=" + album + ", label=" + label
				+ ", year=" + year + ", price=" + price + ", albumCover=" + albumCover + ", ShopCartItem="
				+ ShopCartItem + ", format=" + format + "]";
	}
	
}