package com.itis.shaca.monitor.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.itis.shaca.monitor.entities.FilterWrapper;
import com.itis.shaca.monitor.entities.HPDFile;
import com.itis.shaca.monitor.entities.KPDSpillmanUpdate;
import com.itis.shaca.monitor.repositories.HPDFIleRepository;

@Service
public class FilterService {

	private static final Logger logger = LoggerFactory.getLogger(FilterService.class);

	@Autowired
	private HPDFIleRepository repository;

	@PersistenceContext
	protected EntityManager entityManager;

	public String getContentRangeHeader(FilterWrapper filterWrapper) {
		int[] range = getFilterWrapperRange(filterWrapper);
		String contentRange = range[0] + "-" + range[1] + "/" + repository.count();
		return contentRange;
	}

	public List<HPDFile> HPDFilesfilterHelper(FilterWrapper filterWrapper) {

		int[] pageRange = getFilterWrapperRange(filterWrapper);
		int pageSize = pageRange[1] - pageRange[0];
		int totalRecords = (int) repository.count();
		int totalPages = (int) Math.ceil((double) totalRecords / (double) pageSize);
		int pageNumber = (int) Math.ceil((double) pageRange[0] + 1 / (double) (pageSize));

		String sort[] = getFilterWrapperSort(filterWrapper);
		String jqlQuery = "SELECT h FROM HPDFile h ORDER BY h." + sort[0] + " " + sort[1];
		List<HPDFile> entries = entityManager.createQuery(jqlQuery, HPDFile.class)
										.setFirstResult(pageNumber - 1)
										.setMaxResults(pageSize)
										.getResultList();

		return entries;

	}
	
	
	public List<KPDSpillmanUpdate> KPDSpillmanUpdatefilterHelper(FilterWrapper filterWrapper) {

		int[] pageRange = getFilterWrapperRange(filterWrapper);
		int pageSize = pageRange[1] - pageRange[0];
		int totalRecords = (int) repository.count();
		int totalPages = (int) Math.ceil((double) totalRecords / (double) pageSize);
		int pageNumber = (int) Math.ceil((double) pageRange[0] + 1 / (double) (pageSize));

		String sort[] = getFilterWrapperSort(filterWrapper);
		String jqlQuery = "SELECT k FROM KPDSpillmanUpdate k ORDER BY k." + sort[0] + " " + sort[1];
		List<KPDSpillmanUpdate> entries = entityManager.createQuery(jqlQuery, KPDSpillmanUpdate.class)
										.setFirstResult(pageNumber - 1)
										.setMaxResults(pageSize)
										.getResultList();

		return entries;

	}

	protected int[] getFilterWrapperRange(FilterWrapper filterWrapper) {

		int[] rangeSet = { 0, 9 };

		filterWrapper.getRange().ifPresent(range -> {
			String rangeStr = range.replaceAll("[\\[\\](){}]", "");
			List<Integer> numberlList = Stream.of(rangeStr.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			rangeSet[0] = numberlList.get(0);
			rangeSet[1] = numberlList.get(1);
		});

		return rangeSet;
	}

	protected String[] getFilterWrapperSort(FilterWrapper filterWrapper) {

		String[] sortSet = { "id", "DESC" };

		filterWrapper.getSort().ifPresent(sort -> {
			String sortStr = sort.replaceAll("[\\[\\](){}\"]", "");
			List<String> sortList = Lists.newArrayList(Splitter.on(",").split(sortStr));
			sortSet[0] = sortList.get(0);
			sortSet[1] = sortList.get(1);
		});

		return sortSet;
	}

}
