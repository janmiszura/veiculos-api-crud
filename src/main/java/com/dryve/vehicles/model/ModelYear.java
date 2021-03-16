package com.dryve.vehicles.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ModelYear implements Serializable {

	@Id
	private String id;
	
	@ManyToOne
	private Model model;
	
	@Column 
	private Integer year;
	
	@Column
	private Long kbbId;

	
	
	public ModelYear() {
		super();
	}
	
	public ModelYear(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getKbbId() {
		return kbbId;
	}

	public void setKbbId(Long kbbId) {
		this.kbbId = kbbId;
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
		ModelYear other = (ModelYear) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModelYear [id=" + id + ", model=" + model + ", year=" + year + ", kbbId=" + kbbId + "]";
	}
	
}
