package com.api.report.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
public class ReportUtility {

	private static final Logger logger = LoggerFactory.getLogger(ReportUtility.class);

	// to generate report
	public ResponseEntity<byte[]> createReport(String fileName, String fileContent) {
		ByteArrayResource inputStream = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

		if (fileContent != null && fileContent.length() > 0) {
			inputStream = new ByteArrayResource(fileContent.getBytes());
			headers.set("Response-Message", "Report Creation Successful");
		} else {
			headers.set("Response-Message", "Report Creation Failed");
		}

		return new ResponseEntity(inputStream, headers, HttpStatus.OK);
	}

	// to generate zip file

	public ResponseEntity<byte[]> createZipReport(String zipName, Map<String, String> mapFileContent)
			throws IOException {
		ByteArrayOutputStream byteOutputStream = null;
		ByteArrayResource inputStream = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/zip"));
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(zipName + ".zip").build());
		if (mapFileContent != null) {
			byteOutputStream = new ByteArrayOutputStream();
			ZipOutputStream zipOut = new ZipOutputStream(byteOutputStream);
			mapFileContent.forEach((k, v) -> {
				FileSystemResource resource = new FileSystemResource(k);
				ZipEntry zipEntry = new ZipEntry(resource.getFilename());
				zipEntry.setSize(v.length());
				try {
					zipOut.putNextEntry(zipEntry);
					StreamUtils.copy(new ByteArrayInputStream(v.getBytes()), zipOut);
					zipOut.closeEntry();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(" error in createZipReport -- feed creation");
				}
			});
			zipOut.finish();
			zipOut.close();
			inputStream = new ByteArrayResource(byteOutputStream.toByteArray());
			headers.set("Response-Message", "Report Creation Successful");
		} else {
			headers.set("Response-Message", "Report Creation Failed");
		}

		return new ResponseEntity(inputStream, headers, HttpStatus.OK);
	}

}
