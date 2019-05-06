package com.interview.finra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinraApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinraApplication.class, args);
	}

	/**
	 * Listed few things which i have used in this coding exercise.
	 * - H2 Db is used to persist for metadata persistence.
	 * - com.interview.finra.controller.FileController - is the entry point, which has the rest endpoint.
	 * - Maven is the built tool
	 * - It's a Multipart file upload. Test file used are in /resources/input
	 * - Program starts at default 8080 port.
	 * - JPA style is leverage to interact.
	 *
	 * Note: Before you execute the program. Change the property - file.persist.location in application.yml to you suitable location.
	 *
	 */

	//Curl to upload file. Change the file location according to yours

	/**
	 * curl -X PUT \
	 *   http://localhost:8080/files \
	 *   -H 'Accept: application/json' \
	 *   -H 'Content-Type: multipart/form-data' \
	 *   -H 'content-length: 527' \
	 *   -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
	 *   -F file=@/Users/akruthi/Desktop/finra/finance.txt \
	 *   -F file-metadata=@/Users/akruthi/Desktop/metadata.json
	 */


	//Curl to retrieve metadata

	/**
	 * curl -X GET \
	 *   http://localhost:8080/files/3/metadata \
	 *   -H 'Accept: application/json' \
	 */
}
