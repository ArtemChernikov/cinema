package utils;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("integration-test")
@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {IntegrationTest.Initializer.class})
public abstract class IntegrationTest {

    private static final String DATABASE_NAME = "test-database";

    @Container
    public static final PostgreSQLContainer<?> testContainer = new PostgreSQLContainer<>(PostgreSQLContainer.IMAGE)
            .withReuse(true)
            .withDatabaseName(DATABASE_NAME);

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "TEST_CONTAINER.USERNAME=" + testContainer.getUsername(),
                    "TEST_CONTAINER.PASSWORD=" + testContainer.getPassword(),
                    "TEST_CONTAINER.URL=" + testContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
