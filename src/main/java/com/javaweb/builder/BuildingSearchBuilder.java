package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private String ward;
	private Integer districtID;
	private String street;
	private Integer numberOfBasement;
	private List<String> typeCode = new ArrayList<String>();
	private String managerName;
	private String managerPhonenumber;
	private Integer floorArea;
	private String direction;
	private String minArea;
	private String maxArea;
	private Integer minPrice;
	private Integer maxPrice;
	private String level;
	private Integer staffID;
	
	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.ward = builder.ward;
		this.districtID = builder.districtID;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.typeCode = builder.typeCode;
		this.managerName = builder.managerName;
		this.managerPhonenumber = builder.managerPhonenumber;
		this.floorArea = builder.floorArea;
		this.direction = builder.direction;
		this.minArea = builder.minArea;
		this.maxArea = builder.maxArea;
		this.minPrice = builder.minPrice;
		this.maxPrice = builder.maxPrice;
		this.level = builder.level;
		this.staffID = builder.staffID;
	}
	
	public String getName() {
		return name;
	}
	public String getWard() {
		return ward;
	}
	public Integer getDistrictID() {
		return districtID;
	}
	public String getStreet() {
		return street;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhonenumber() {
		return managerPhonenumber;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public String getDirection() {
		return direction;
	}
	public String getMinArea() {
		return minArea;
	}
	public String getMaxArea() {
		return maxArea;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public String getLevel() {
		return level;
	}
	public Integer getStaffID() {
		return staffID;
	}
	
	public static class Builder {
		private String name;
		private String ward;
		private Integer districtID;
		private String street;
		private Integer numberOfBasement;
		private List<String> typeCode = new ArrayList<String>();
		private String managerName;
		private String managerPhonenumber;
		private Integer floorArea;
		private String direction;
		private String minArea;
		private String maxArea;
		private Integer minPrice;
		private Integer maxPrice;
		private String level;
		private Integer staffID;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setDistrictID(Integer districtID) {
			this.districtID = districtID;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhonenumber(String managerPhonenumber) {
			this.managerPhonenumber = managerPhonenumber;
			return this;
		}
		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}
		public Builder setMinArea(String minArea) {
			this.minArea = minArea;
			return this;
		}
		public Builder setMaxArea(String maxArea) {
			this.maxArea = maxArea;
			return this;
		}
		public Builder setMinPrice(Integer minPrice) {
			this.minPrice = minPrice;
			return this;
		}
		public Builder setMaxPrice(Integer maxPrice) {
			this.maxPrice = maxPrice;
			return this;
		}
		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}
		public Builder setStaffID(Integer staffID) {
			this.staffID = staffID;
			return this;
		}
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
		
	}
}
