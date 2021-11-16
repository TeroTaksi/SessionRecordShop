package com.example.SessionRecordShop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Format { //----------- S E S S I O N -------------------------------------
	
	@Id // Primary Key
	@GeneratedValue(strategy=GenerationType.AUTO) // auto generate Primary Key
	private Long formatId;
	
	@NotNull
	@Size(min=1, max=15)
	private String formatName;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "format")
	@JsonIgnore
	private List<Record> records;
	
	// Constructors
	public Format(String format) {
		this.formatName = format;
	}
	
	public Format() {
		super();
		this.formatName = null;
	}
	
	// Getterit & Setterit
	public Long getFormatId() {
		return formatId;
	}

	public void setFormatId(Long formatId) {
		this.formatId = formatId;
	}
	
	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "Format [formatId=" + formatId + ", formatName=" + formatName + ", records=" + records + "]";
	}

}
