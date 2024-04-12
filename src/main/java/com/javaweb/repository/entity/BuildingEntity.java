package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="street")
	private String street;
	
	@Column(name="ward")
	private String ward;
	
	@Column(name="structure")
	private String structure;
	
	@Column(name="numberofbasement")
	private Integer numberOfBasement;
	
	@Column(name="floorarea")
	private Integer floorArea;
	
	@Column(name="direction")
	private String direction;
	
	@Column(name="level")
	private String level;
	
	@Column(name="rentprice")
	private Integer rentPrice;
	
	@Column(name="rentpricedescription")
	private String rentPriceDescription;
	
	@Column(name="servicefee")
	private String serviceFee;
	
	@Column(name="carfee")
	private String carFee;
	
	@Column(name="motorbikefee")
	private String motorbikeFee;
	
	@Column(name="overtimefee")
	private String overtimeFee;
	
	@Column(name="waterfee")
	private String waterFee;
	
	@Column(name="electricityfee")
	private String electricityFee;
	
	@Column(name="deposit")
	private String deposit;
	
	@Column(name="payment")
	private String payment;
	
	@Column(name="renttime")
	private String rentTime;
	
	@Column(name="decorationtime")
	private String decorationTime;
	
	@Column(name="brokeragefee")
	private String brokerAgeFee;
	
	@Column(name="note")
	private String note;
	
	@Column(name="linkofbuilding")
	private String linkOfBuilding;
	
	@Column(name="map")
	private String map;
	
	@Column(name="image")
	private String image;
	
	@Column(name="managername")
	private String managerName;
	
	@Column(name="managerphonenumber")
	private String managerPhonenumber;
	
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> rentAreas = new ArrayList<RentAreaEntity>();
	
	@ManyToOne
	@JoinColumn(name = "districtid")
	private DistrictEntity district;
}
