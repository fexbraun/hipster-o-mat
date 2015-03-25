package com.ax.demo.entity;

import javax.validation.constraints.Min;

/**
 * A Hipster. Used as entity in this demo application.
 */
public class Hipster {

	public enum JeansType {
		SKINNY, SUPERSKINNY;
	}

	@Min(value = 0, message = "Id must be positive")
	private int id;

	private String name;
	private JeansType jeans;
	private boolean hornRimmedGlasses;

	private String imagePath = "";

	public Hipster() {
	}

	public Hipster(int id, String name, JeansType jeans,
			boolean hornRimmedGlasses, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.jeans = jeans;
		this.hornRimmedGlasses = hornRimmedGlasses;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public JeansType getJeans() {
		return jeans;
	}

	public boolean isHornRimmedGlasses() {
		return hornRimmedGlasses;
	}

	public void setImagePath(final String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	@Override
	public String toString() {
		return "Hipster [id=" + id + ", name=" + name + ", jeans=" + jeans
				+ ", hornRimmedGlasses=" + hornRimmedGlasses + ", imagePath="
				+ imagePath + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hornRimmedGlasses ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result
				+ ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((jeans == null) ? 0 : jeans.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Hipster other = (Hipster) obj;
		if (hornRimmedGlasses != other.hornRimmedGlasses)
			return false;
		if (id != other.id)
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (jeans != other.jeans)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
