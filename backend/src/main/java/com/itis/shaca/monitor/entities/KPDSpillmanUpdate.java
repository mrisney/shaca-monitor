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
@Table(name = "journal", schema="kpd_stage")
@Data
public class KPDSpillmanUpdate {


		@Id
		private Integer id;

		@Column(name = "description")
		private String description;

		@Column(name = "spillman_query_success")
		private String successFail;
		
		@Column(name = "number_of_accidents")
		private Integer numberOfAccidents;
		
		@Column(name = "number_of_updates")
		private Integer numberOfUpdates;
		
		@Column(name = "errors")
		private String errors;

		@Column(name = "meta_data")
		private String metaData;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "last_modified")
		private Date lastModified;
	
}
