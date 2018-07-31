package com.itis.shaca.monitor.dto;

import java.util.Date;

import lombok.Data;

@Data
public class KPDSpillmanUpdateDTO {

	private Integer id;
	private String description;
	private String successFail;
	private Integer numberOfAccidents;
	private Integer numberOfUpdates;
	private String errors;
	private KPDSpillmanUpdateMetaDataDTO metaData;
	private Date lastModified;

}
