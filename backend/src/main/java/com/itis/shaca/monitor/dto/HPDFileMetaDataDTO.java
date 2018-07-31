package com.itis.shaca.monitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HPDFileMetaDataDTO {

	@JsonProperty("main_data")
	private Integer mainData;

	@JsonProperty("mvc_major_report")
	private Integer mvcMajorReport;

	@JsonProperty("mvc_major_gps")
	private Integer mvcMajorGPS;

	@JsonProperty("units_major")
	private Integer unitsMajor;

	private Integer address;
	
	private Integer object;
	
	private Integer vehicles;
	
	private Integer persons;
	
	private Integer phone;
	
	private Integer identification;
}
