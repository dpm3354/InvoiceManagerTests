package com.beaufortfairmont.invoicemanager;

import com.beaufortfairmont.invoicemanager.services.WireMockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class InvoiceTestsWithEmbeddedWireMock extends InvoiceTests {

    private WireMock wireMock;
    private WireMockServer wireMockServer;


    @Override
    @BeforeClass
    public void setupClass() {
        super.setupClass();
        wireMockServer = new WireMockServer(options().port(configuration.getMock().getPort())); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        wireMock = new WireMock("localhost", configuration.getMock().getPort()); // As above, 3rd param is for non-root servlet deployments

        try {
            WireMockService
                    .create(configuration, wireMock, createObjectMapper())
                    .initialize();
        } catch (JsonProcessingException e) {
            System.err.print(e);
            System.exit(-1);
        }
    }

    // should be moved to @Service
    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }


    @AfterClass
    public void tearDownWireMock() {
        wireMock.shutdown();
        wireMockServer.stop();

    }

}
