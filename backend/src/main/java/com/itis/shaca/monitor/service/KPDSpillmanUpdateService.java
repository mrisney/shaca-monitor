package com.itis.shaca.monitor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.shaca.monitor.dto.AccidentDTO;
import com.itis.shaca.monitor.dto.KPDSpillmanUpdateDTO;
import com.itis.shaca.monitor.dto.KPDSpillmanUpdateMetaDataDTO;
import com.itis.shaca.monitor.entities.FilterWrapper;
import com.itis.shaca.monitor.entities.KPDSpillmanUpdate;
import com.itis.shaca.monitor.repositories.KPDSpillmanUpdateRepository;

@Service
public class KPDSpillmanUpdateService {

	private static final Logger logger = LoggerFactory.getLogger(HPDFileService.class);

	@Autowired
	private KPDSpillmanUpdateRepository repository;

	@Autowired
	FilterService filterService;

	public KPDSpillmanUpdateDTO getJournalEntryById(Integer id) {
		KPDSpillmanUpdateDTO dto = new KPDSpillmanUpdateDTO();
		Optional<KPDSpillmanUpdate> journalEntry = repository.findById(id);
		ObjectMapper objectMapper = new ObjectMapper();
		if (journalEntry.isPresent()) {
			KPDSpillmanUpdate entry = journalEntry.get();
			dto.setId(entry.getId());
			dto.setDescription(entry.getDescription());
			dto.setErrors(entry.getErrors());
			dto.setNumberOfAccidents(entry.getNumberOfAccidents());
			dto.setNumberOfUpdates(entry.getNumberOfUpdates());
			dto.setSuccessFail(entry.getSuccessFail());
			dto.setLastModified(entry.getLastModified());
			KPDSpillmanUpdateMetaDataDTO metaDataDTO = new KPDSpillmanUpdateMetaDataDTO();
			try {
				JsonNode root = objectMapper.readTree(entry.getMetaData());
				JsonNode accidentsNode = root.path("accidents");
				List<AccidentDTO> accidents = new ObjectMapper().readValue(accidentsNode.traverse(),
						new TypeReference<ArrayList<AccidentDTO>>() {
						});
				metaDataDTO.setAccidents(accidents);
				metaDataDTO.setNewAccidentCount(root.path("newAccidentCount").asInt());
				metaDataDTO.setUpdateCount(root.path("updateCount").asInt());

				dto.setMetaData(metaDataDTO);
			} catch (Exception e) {
				logger.error(e.toString());
			}

			dto.setMetaData(metaDataDTO);
		}
		return dto;

	}

	public List<KPDSpillmanUpdateDTO> getJournalEntries(FilterWrapper filterWrapper) {

		List<KPDSpillmanUpdateDTO> entries = new ArrayList<KPDSpillmanUpdateDTO>();
		Iterable<KPDSpillmanUpdate> filteredEntries = filterService.KPDSpillmanUpdatefilterHelper(filterWrapper);
		ObjectMapper objectMapper = new ObjectMapper();

		for (KPDSpillmanUpdate entry : filteredEntries) {
			KPDSpillmanUpdateDTO dto = new KPDSpillmanUpdateDTO();
			dto.setId(entry.getId());
			dto.setDescription(entry.getDescription());
			dto.setErrors(entry.getErrors());
			dto.setNumberOfAccidents(entry.getNumberOfAccidents());
			dto.setNumberOfUpdates(entry.getNumberOfUpdates());
			dto.setSuccessFail(entry.getSuccessFail());
			dto.setLastModified(entry.getLastModified());
			KPDSpillmanUpdateMetaDataDTO metaDataDTO = new KPDSpillmanUpdateMetaDataDTO();
			try {
				JsonNode root = objectMapper.readTree(entry.getMetaData());
				JsonNode accidentsNode = root.path("accidents");
				List<AccidentDTO> accidents = new ObjectMapper().readValue(accidentsNode.traverse(),
						new TypeReference<ArrayList<AccidentDTO>>() {
						});
				metaDataDTO.setAccidents(accidents);
				metaDataDTO.setNewAccidentCount(root.path("newAccidentCount").asInt());
				metaDataDTO.setUpdateCount(root.path("updateCount").asInt());

				dto.setMetaData(metaDataDTO);
			} catch (Exception e) {
				logger.error(e.toString());
			}

			dto.setMetaData(metaDataDTO);

			entries.add(dto);
		}
		return entries;
	}
}
