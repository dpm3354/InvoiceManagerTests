package com.beaufortfairmont.invoicemanager.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfiguration {
    private String baseUrl;
    private Mock mock = new Mock();

    private ApplicationConfiguration() {
        Properties prop = new Properties();
        String filename = "/application.properties";
        try (InputStream input = getClass().getResourceAsStream(filename)) {
            if (input == null) {
                System.err.printf("unable to load \"%s\" from classpath\n", filename);
                System.exit(-1);
            }

            prop.load(input);

            //get the property value and print it out
            baseUrl = (prop.getProperty("baseUrl"));
            mock.port = Integer.parseInt(prop.getProperty("mock.port"));
            mock.globalFixedDelay = Integer.parseInt(prop.getProperty("mock.globalFixedDelay"));
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }

    public static ApplicationConfiguration initialize() {
        return new ApplicationConfiguration();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Mock getMock() {
        return mock;
    }

    public static class Mock {
        private int port;
        private int globalFixedDelay;

        public int getPort() {
            return port;
        }

        public int getGlobalFixedDelay() {
            return globalFixedDelay;
        }
    }
}
