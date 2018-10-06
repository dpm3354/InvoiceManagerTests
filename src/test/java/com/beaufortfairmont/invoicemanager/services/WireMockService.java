package com.beaufortfairmont.invoicemanager.services;

import com.beaufortfairmont.invoicemanager.InvoiceFactory;
import com.beaufortfairmont.invoicemanager.models.ApplicationConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockService {

    private final ObjectMapper mapper;
    private final WireMock wireMock;
    private final ApplicationConfiguration configuration;

    private WireMockService(ApplicationConfiguration configuration, WireMock wireMock, ObjectMapper mapper) throws JsonProcessingException {
        this.configuration = configuration;
        this.mapper = mapper;
        this.wireMock = wireMock;
        wireMock.setGlobalFixedDelayVariable(configuration.getMock().getGlobalFixedDelay());
        setupGetInvoices();
    }

    public static WireMockService create(ApplicationConfiguration configuration, WireMock wireMock, ObjectMapper mapper) throws JsonProcessingException {
        return new WireMockService(configuration, wireMock, mapper);
    }

    public void initialize() throws JsonProcessingException {
        setupGetInvoices();
    }

    private void setupGetInvoices() throws JsonProcessingException {
        wireMock.register(get(urlEqualTo("/invoices")).inScenario("invoices manager")
                .willReturn(okJson(this.mapper.writeValueAsString(Arrays.asList()))
                        .withHeader("Access-Control-Allow-Origin", "*")));

        wireMock.register(options(urlEqualTo("/invoices")).inScenario("invoices manager")
                .willReturn(
                        ok().withHeader("Access-Control-Allow-Origin", "*")
                                .withHeader("Access-Control-Allow-Headers", "Content-Type")
                                .withHeader("Access-Control-Allow-Methods", "GET, POST, DELETE")));

        wireMock.register(delete(urlEqualTo("/invoices/" + InvoiceFactory.getSimpleInvoice().getInvoiceNumber())).inScenario("invoices manager").willSetStateTo("deleted")
                .willReturn(aResponse().proxiedFrom("/")
                .withHeader("Access-Control-Allow-Origin", "*")
                                .withHeader("Access-Control-Allow-Methods", "GET, POST, DELETE")));


        wireMock.register(post(urlEqualTo("/invoices")).inScenario("invoices manager").willSetStateTo("created")
                .willReturn(okJson(this.mapper.writeValueAsString(Arrays.asList(InvoiceFactory.getSimpleInvoice())))
                .withHeader("Access-Control-Allow-Origin", "*")
                .withHeader("Access-Control-Allow-Methods", "GET, POST, DELETE")));

        wireMock.register(get(urlEqualTo("/invoices")).inScenario("invoices manager").whenScenarioStateIs("created")
                .willReturn(okJson(this.mapper.writeValueAsString(Arrays.asList(InvoiceFactory.getSimpleInvoice())))
                        .withHeader("Access-Control-Allow-Origin", "*")));



    }

}
