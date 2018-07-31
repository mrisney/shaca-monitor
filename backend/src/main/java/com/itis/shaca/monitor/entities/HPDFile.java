package com.itis.shaca.monitor.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "journal")
@Data
public class HPDFile {

	@Id
	private Integer id;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "description")
	private String description;

	@Column(name = "errors")
	private String errors;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified")
	private Date lastModified;

	@Column(name = "meta_data")
	private String metaData;

}
