package com.dryve.vehicles.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle implements Serializable {
	
	@Id
	private String id;
	
	@ManyToOne
	private ModelYear modelYear;
	
	@Column(unique = true)
	private String plate;
	
	@Column 
	private BigDecimal adPrice;
	
	@Column 
	private BigDecimal priceKBB;
	
	@Column 
	private Date createdAt = new Date();
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public BigDecimal getAdPrice() {
		return adPrice;
	}

	public void setAdPrice(BigDecimal adPrice) {
		this.adPrice = adPrice;
	}

	public ModelYear getModelYear() {
		return modelYear;
	}

	public void setModelYear(ModelYear modelYear) {
		this.modelYear = modelYear;
	}

	public BigDecimal getPriceKBB() {
		return priceKBB;
	}

	public void setPriceKBB(BigDecimal priceKBB) {
		this.priceKBB = priceKBB;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", modelYear=" + modelYear + ", plate=" + plate + ", adPrice=" + adPrice
				+ ", priceKBB=" + priceKBB + ", createdAt=" + createdAt + "]";
	}


	
	

	
	
	
}
