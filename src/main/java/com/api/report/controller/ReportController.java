package com.api.report.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.report.common.ReportUtility;
import com.api.report.service.ReportService;

@RestController
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	ReportUtility utility;

	@Autowired
	ReportService reportService;

	@GetMapping("/api/report")
	public ResponseEntity<byte[]> getReport() {
		StringBuilder reportContent = new StringBuilder();
		reportContent.append("Header of Report -->>>>");
		reportContent.append(String.format("%n"));
		reportContent.append(String.format("%n"));
		reportContent.append("1st Line");
		reportContent.append(String.format("%n"));
		reportContent.append("2nd Line");
		reportContent.append(String.format("%n"));
		reportContent.append("3rd Line");
		reportContent.append(String.format("%n"));
		reportContent.append("4th Line");
		reportContent.append(String.format("%n"));
		return utility.createReport("ReportName", reportContent.toString());
	}

	@GetMapping("/api/zip")
	public ResponseEntity<byte[]> getZipReport() throws IOException {
		logger.info(" -- getZipReport -- START");
		Map<String, String> reportMapContent = new HashMap<>();
		StringBuilder reportContent1 = new StringBuilder();
		reportContent1.append("Header of Report 1-->>>>");
		reportContent1.append(String.format("%n"));
		reportContent1.append(String.format("%n"));
		reportContent1.append("1st Line");
		reportContent1.append(String.format("%n"));
		reportContent1.append("2nd Line");
		reportContent1.append(String.format("%n"));
		reportContent1.append("3rd Line");
		reportContent1.append(String.format("%n"));
		reportContent1.append("4th Line");
		reportContent1.append(String.format("%n"));
		reportMapContent.put("Report 1", reportContent1.toString());

		StringBuilder reportContent2 = new StringBuilder();
		reportContent2.append("Header of Report 2-->>>>");
		reportContent2.append(String.format("%n"));
		reportContent2.append(String.format("%n"));
		reportContent2.append("1st Line");
		reportContent2.append(String.format("%n"));
		reportContent2.append("2nd Line");
		reportContent2.append(String.format("%n"));
		reportContent2.append("3rd Line");
		reportContent2.append(String.format("%n"));
		reportContent2.append("4th Line");
		reportContent2.append(String.format("%n"));
		reportContent2.append("5th Line");
		reportMapContent.put("Report 2", reportContent2.toString());
		return utility.createZipReport("ZipName", reportMapContent);
	}

}
