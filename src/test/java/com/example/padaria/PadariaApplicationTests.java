package com.example.padaria;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootTest
class PadariaApplicationTests {

	@Test
	void contextLoads() {
	}

	private void expectStatus(HttpMethod method, String endpoint, String body, HttpStatus expectedStatus) {
		client.method(method)
				.uri(endpoint)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(body)
				.exchange()
				.expectStatus().isEqualTo(expectedStatus);
	}

	private void expectSuccess(HttpMethod method, String endpoint, String body) {
		final String responseBody = client.method(method)
				.uri(endpoint)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(body)
				.exchange()
				.expectStatus().isAccepted()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();
		assertNotNull(responseBody);
	}
}
