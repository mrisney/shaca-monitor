package com.itis.shaca.monitor.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itis.shaca.monitor.dto.KPDSpillmanUpdateDTO;
import com.itis.shaca.monitor.entities.FilterWrapper;
import com.itis.shaca.monitor.service.FilterService;
import com.itis.shaca.monitor.service.KPDSpillmanUpdateService;

@RestController
public class KPDSpillmanUpateController {

	
	private static final Logger logger = LoggerFactory.getLogger(KPDSpillmanUpateController.class);

	@Autowired
	private KPDSpillmanUpdateService kPDSpillmanUpdateService;

	@Autowired
	private FilterService filterService;
	
	
	@RequestMapping(value = "/kpdspillmanupdates", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<KPDSpillmanUpdateDTO>> getList(
			@RequestParam(required = false, name = "filter") String filterStr,
			@RequestParam(required = false, name = "range", defaultValue = "[0,9]") String rangeStr,
			@RequestParam(required = false, name = "sort", defaultValue = "[\"id\",\"DESC\"]") String sortStr) {

		FilterWrapper filterWrapper = new FilterWrapper(filterStr, rangeStr, sortStr);
		String contentRange = filterService.getContentRangeHeader(filterWrapper);
		logger.debug("contentRange : "+ contentRange);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", contentRange);
		return new ResponseEntity<Iterable<KPDSpillmanUpdateDTO>>(kPDSpillmanUpdateService.getJournalEntries(filterWrapper), headers,
				HttpStatus.OK);
	}

	@GetMapping(path = "/kpdspillmanupdates/{id}", produces = "application/json")
	public ResponseEntity<KPDSpillmanUpdateDTO> getOne(@PathVariable int id) {
		return new ResponseEntity<KPDSpillmanUpdateDTO>(kPDSpillmanUpdateService.getJournalEntryById(id), HttpStatus.OK);
	}
}
