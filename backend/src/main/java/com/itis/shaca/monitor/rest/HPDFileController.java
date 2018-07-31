package com.itis.shaca.monitor.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.itis.shaca.monitor.dto.HPDFileDTO;
import com.itis.shaca.monitor.entities.FilterWrapper;
import com.itis.shaca.monitor.service.FilterService;
import com.itis.shaca.monitor.service.HPDFileService;

@RestController
public class HPDFileController {

	private static final Logger logger = LoggerFactory.getLogger(HPDFileController.class);

	@Autowired
	private HPDFileService hpdFileService;

	@Autowired
	private FilterService filterService;

	@RequestMapping(value = "/hpdfiles", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<HPDFileDTO>> getList(
			@RequestParam(required = false, name = "filter") String filterStr,
			@RequestParam(required = false, name = "range", defaultValue = "[0,9]") String rangeStr,
			@RequestParam(required = false, name = "sort", defaultValue = "[\"id\",\"DESC\"]") String sortStr,
			HttpServletRequest request) {

		Map<String, String[]> parameters = request.getParameterMap();

		for (String key : parameters.keySet()) {
			StringBuffer sb = new StringBuffer();
			sb.append("key : " + key);
			String[] vals = parameters.get(key);
			for (String val : vals) {
				sb.append(", value : " + val);
			}
			logger.debug(sb.toString());
		}
		FilterWrapper filterWrapper = new FilterWrapper(filterStr, rangeStr, sortStr);
		String contentRange = filterService.getContentRangeHeader(filterWrapper);
		logger.debug("contentRange : "+ contentRange);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", contentRange);
		return new ResponseEntity<Iterable<HPDFileDTO>>(hpdFileService.getJournalEntries(filterWrapper), headers,
				HttpStatus.OK);
	}

	@GetMapping(path = "/hpdfiles/{id}", produces = "application/json")
	public ResponseEntity<HPDFileDTO> getOne(@PathVariable int id) {
		return new ResponseEntity<HPDFileDTO>(hpdFileService.getJournalEntryById(id), HttpStatus.OK);
	}

}
