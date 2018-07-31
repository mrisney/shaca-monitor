package com.itis.shaca.monitor.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
public class KPDSpillmanUpdateMetaDataDTO {
	private int updateCount;
	private int newAccidentCount;
	@JsonProperty("accidents")
	private List<AccidentDTO> accidents;

}
