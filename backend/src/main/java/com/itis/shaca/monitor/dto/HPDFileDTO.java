package com.itis.shaca.monitor.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HPDFileDTO {
	private Integer Id;
	private String fileName;
	private String description;
	private String errors;
	private Date lastModified;
	private HPDFileMetaDataDTO metaData;
}
