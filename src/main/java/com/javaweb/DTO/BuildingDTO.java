package com.javaweb.DTO;

import java.util.List;

public class BuildingDTO {
	private String name;
	private String address;
	private int numberOfBasement;
	private String managerName;
	private String managerPhone;
	private int floorArea;
	private String freeArea;
	private List<String> rentArea;
	private int rentPrice;
	private String deposit;
	private String servicefee;
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
	public int getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(int numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public int getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(int floorArea) {
		this.floorArea = floorArea;
	}
	public String getFreeArea() {
		return freeArea;
	}
	public void setFreeArea(String freeArea) {
		this.freeArea = freeArea;
	}
	public void setRentArea(List<String> rentArea) {
		this.rentArea = rentArea;
	}
	public List<String> getRentArea() {
		return rentArea;
	}
	public int getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getServicefee() {
		return servicefee;
	}
	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}
	
	
}
