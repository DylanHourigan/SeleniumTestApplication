package com.example.seleniumdemo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class EmployeeApiSeleniumTest {

    @LocalServerPort
    private int port;

    // Defining the Postgre container and the selenium container which will run the tests
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test-employee-db")
            .withUsername("admin")
            .withPassword("admin");

    @Container
    private static final BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions());

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        // Connects to Testcontainers PostgreSQL
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }

    @BeforeEach
    void setup() {
        driver = chrome.getWebDriver();
    }

    // Checks if the POST request for employees is working
    @Test
    void testCreateEmployee() {
        // Must use host.docker.internal instead of localhost because the test is run within a container
        boolean isGitHubActions = System.getenv("GITHUB_ACTIONS") != null;

        String hostname = isGitHubActions ? "172.17.0.1" : "host.docker.internal";
        String appUrl = "http://" + hostname + ":" + port + "/employees";

        driver.get(appUrl);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("""
            fetch(arguments[0], {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ "name": "Selenium Test", "department": "QA", "salary": 65000.0 })
            }).then(response => response.json()).then(data => console.log(data));
        """, appUrl);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        driver.get(appUrl);
        String pageSource = driver.getPageSource();

        // Verify the new employee exists
        assertTrue(pageSource.contains("Selenium Test"));
    }

    @AfterEach
    void tearDown() {

    }
}
