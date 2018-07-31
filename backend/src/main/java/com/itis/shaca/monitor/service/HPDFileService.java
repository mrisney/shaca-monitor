package com.itis.shaca.monitor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itis.shaca.monitor.dto.HPDFileDTO;
import com.itis.shaca.monitor.dto.HPDFileMetaDataDTO;
import com.itis.shaca.monitor.entities.FilterWrapper;
import com.itis.shaca.monitor.entities.HPDFile;
import com.itis.shaca.monitor.repositories.HPDFIleRepository;
import com.itis.shaca.monitor.utils.JSON;

@Service
public class HPDFileService {

	private static final Logger logger = LoggerFactory.getLogger(HPDFileService.class);

	@Autowired
	private HPDFIleRepository repository;

	@Autowired
	FilterService filterService;

	public HPDFileDTO getJournalEntryById(Integer id) {
		HPDFileDTO dto = new HPDFileDTO();
		Optional<HPDFile> journalEntry = repository.findById(id);

		if (journalEntry.isPresent()) {
			HPDFile entry = journalEntry.get();
			dto.setId(entry.getId());
			dto.setDescription(entry.getDescription());
			dto.setErrors(entry.getErrors());
			dto.setFileName(entry.getFileName());
			dto.setLastModified(entry.getLastModified());
			HPDFileMetaDataDTO metaDataDTO = new HPDFileMetaDataDTO();
			try {
				metaDataDTO = JSON.toObject(entry.getMetaData(), HPDFileMetaDataDTO.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			dto.setMetaData(metaDataDTO);

		}
		return dto;

	}

	public List<HPDFileDTO> getJournalEntries(FilterWrapper filterWrapper) {
		List<HPDFileDTO> entries = new ArrayList<HPDFileDTO>();

		Iterable<HPDFile> filteredEntries = filterService.HPDFilesfilterHelper(filterWrapper);

		for (HPDFile entry : filteredEntries) {
			HPDFileDTO dto = new HPDFileDTO();
			dto.setId(entry.getId());
			dto.setDescription(entry.getDescription());
			dto.setErrors(entry.getErrors());
			dto.setFileName(entry.getFileName());
			dto.setLastModified(entry.getLastModified());

			HPDFileMetaDataDTO metaDataDTO = new HPDFileMetaDataDTO();
			try {
				metaDataDTO = JSON.toObject(entry.getMetaData(), HPDFileMetaDataDTO.class);
			} catch (Exception e) { 

			}
			dto.setMetaData(metaDataDTO);
			entries.add(dto);
		}
		return entries;
	}

}