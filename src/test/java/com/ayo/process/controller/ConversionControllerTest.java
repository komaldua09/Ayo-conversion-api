package com.ayo.process.controller;

import com.ayo.process.ConversionApplication;
import com.ayo.process.enums.ConversionType;
import com.ayo.process.enums.ConversionUnits;
import com.ayo.process.model.APIError;
import com.ayo.process.model.ConversionDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        classes={ConversionApplication.class}
)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Slf4j
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ConversionControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testConvertDataCelciusToFahrenheit() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.TEMPERATURE)
                .convertFrom(ConversionUnits.CELCIUS)
                .convertTo(ConversionUnits.FAHRENHEIT)
                .valueToConvert(99)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 210.20000000000002).isTrue();

    }
    @Test
    public void testConvertDataFahrenheitToCelcius() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.TEMPERATURE)
                .convertFrom(ConversionUnits.FAHRENHEIT)
                .convertTo(ConversionUnits.CELCIUS)
                .valueToConvert(38)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 3.3000000000000003).isTrue();

    }

    @Test
    public void testConvertDataKgToPound() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.WEIGHT)
                .convertFrom(ConversionUnits.KG)
                .convertTo(ConversionUnits.POUND)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 2.2).isTrue();

    }

    @Test
    public void testConvertDataPoundToKg() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.WEIGHT)
                .convertFrom(ConversionUnits.POUND)
                .convertTo(ConversionUnits.KG)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 0.45).isTrue();

    }

    @Test
    public void testConvertDataMileToKm() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.DISTANCE)
                .convertFrom(ConversionUnits.MILE)
                .convertTo(ConversionUnits.KM)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 0.62).isTrue();

    }

    @Test
    public void testConvertDataKmToMile() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.DISTANCE)
                .convertFrom(ConversionUnits.KM)
                .convertTo(ConversionUnits.MILE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 1.61).isTrue();

    }

    @Test
    public void testConvertDataGallonToLitre() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.VOLUME)
                .convertFrom(ConversionUnits.GALLON)
                .convertTo(ConversionUnits.LITRE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 3.78).isTrue();

    }

    @Test
    public void testConvertDataLitreToGallon() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.VOLUME)
                .convertFrom(ConversionUnits.LITRE)
                .convertTo(ConversionUnits.GALLON)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 0.26).isTrue();

    }

    @Test
    public void testConvertSqMileToSqKm() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.AREA)
                .convertFrom(ConversionUnits.SQ_MILE)
                .convertTo(ConversionUnits.SQ_KM)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 2.59).isTrue();

    }

    @Test
    public void testConvertSqKmToSqMile() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.AREA)
                .convertFrom(ConversionUnits.SQ_MILE)
                .convertTo(ConversionUnits.SQ_KM)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<ConversionDetails> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ConversionDetails.class);

        ConversionDetails response = fluxExchangeResult.getResponseBody().single().block();
        assertThat(response.getConvertedValue() == 2.59).isTrue();

    }

    @Test
    public void testConvertInvalidPayload() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.AREA)
                .convertFrom(ConversionUnits.SQ_MILE)
                .convertTo(ConversionUnits.SQ_MILE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<APIError> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is4xxClientError()
                .returnResult(APIError.class);

    }

    @Test
    public void testConvertInvalidPayloadDistance() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.DISTANCE)
                .convertFrom(ConversionUnits.KM)
                .convertTo(ConversionUnits.SQ_MILE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<APIError> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is4xxClientError()
                .returnResult(APIError.class);

    }

    @Test
    public void testConvertInvalidPayloadArea() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.AREA)
                .convertFrom(ConversionUnits.KM)
                .convertTo(ConversionUnits.SQ_MILE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<APIError> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is4xxClientError()
                .returnResult(APIError.class);

    }

    @Test
    public void testConvertInvalidPayloadTemperature() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.AREA)
                .convertFrom(ConversionUnits.KM)
                .convertTo(ConversionUnits.SQ_MILE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<APIError> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is4xxClientError()
                .returnResult(APIError.class);

    }

    @Test
    public void testConvertInvalidPayloadWeight() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.WEIGHT)
                .convertFrom(ConversionUnits.KM)
                .convertTo(ConversionUnits.SQ_MILE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<APIError> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is4xxClientError()
                .returnResult(APIError.class);

    }


    @Test
    public void testConvertInvalidPayloadVolume() {
        ConversionDetails conversionDetails = ConversionDetails.builder()
                .converstionType(ConversionType.VOLUME)
                .convertFrom(ConversionUnits.KM)
                .convertTo(ConversionUnits.SQ_MILE)
                .valueToConvert(1)
                .build();

        FluxExchangeResult<APIError> fluxExchangeResult = this.webTestClient.post()
                .uri("/convert")
                .body(Mono.just(conversionDetails), ConversionDetails.class)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .exchange()
                .expectStatus().is4xxClientError()
                .returnResult(APIError.class);
    }
}
