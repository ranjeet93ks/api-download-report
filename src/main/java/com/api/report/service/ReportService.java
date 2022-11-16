package com.api.report.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

	public void reportFeedMain() {
		logger.info("  reportFeedMain --->> START");
	}

}
