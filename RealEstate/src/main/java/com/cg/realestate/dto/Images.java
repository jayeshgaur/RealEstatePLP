package com.cg.realestate.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "ems_images")
public class Images {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger imageId;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "image_type")
	private String imageType;
	
	@Column(name="image_url")
	private String url;
	
	@Lob
	private byte[] data;
	
	public Images() {

	}

	public BigInteger getImageId() {
		return imageId;
	}

	public void setImageId(BigInteger imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}



	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Images(String imageName, String imageType, byte[] data) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.data = data;
	}
	
	

}