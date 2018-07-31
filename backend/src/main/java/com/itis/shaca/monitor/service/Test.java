package com.itis.shaca.monitor.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class Test {

	public static int getCurrentPage(int totalRecords, int[] range) {
		int pageSize = 10;
		int start = range[0];
		// int totalPages = (int) Math.ceil((double) totalRecords / (double) pageSize);
		int currentPage = (int) Math.ceil((double) start / (double) pageSize) + 1;

		return currentPage;
	}

	public static int getTotalPages(int totalRecords, int pageSize) {
		int totalPages = (int) Math.ceil((double) totalRecords / (double) pageSize);
		return totalPages;
	}

	public static void main(String[] args) {
		String str = "[0,9]";
		str = str.replaceAll("[\\[\\](){}]","");
		
		String sortString = "[\"id\",\"ASC\"]";
		sortString = sortString.replaceAll("[\\[\\](){}\"]","");
		List<Integer> numberlList = Stream.of(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		
		System.out.println(numberlList.get(0));
		System.out.println(numberlList.get(1));

		List<String> sortList = Lists.newArrayList(Splitter.on(",").split(sortString));
		System.out.println(sortList.get(0));
		System.out.println(sortList.get(1));

		
		
		
		// int[] range = { 260, 269 };
		// System.out.println(getCurrentPage(267, range));

	}

}
