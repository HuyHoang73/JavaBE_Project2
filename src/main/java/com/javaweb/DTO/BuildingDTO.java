package com.javaweb.DTO;

public class BuildingDTO {
	private String name;
	private String address;
	private Integer numberOfBasement;
	private String managerName;
	private String managerPhonenumber;
	private Integer floorArea;
	private String freeArea;
	private String rentArea;
	private Integer rentPrice;
	private String deposit;
	private String serviceFee;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhonenumber() {
		return managerPhonenumber;
	}
	public void setManagerPhonenumber(String managerPhone) {
		this.managerPhonenumber = managerPhone;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public String getFreeArea() {
		return freeArea;
	}
	public void setFreeArea(String freeArea) {
		this.freeArea = freeArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public String getRentArea() {
		return rentArea;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getServicefee() {
		return serviceFee;
	}
	public void setServicefee(String servicefee) {
		this.serviceFee = servicefee;
	}
	
	
}
