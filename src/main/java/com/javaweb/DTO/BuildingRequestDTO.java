package com.javaweb.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRequestDTO {
	private Integer id;
	private Integer districtID;
	private String name;
	private String street;
	private String ward;
	private Integer numberOfBasement;
	private Integer rentPrice;
	private String managerName;
	private String managerPhonenumber;
}
