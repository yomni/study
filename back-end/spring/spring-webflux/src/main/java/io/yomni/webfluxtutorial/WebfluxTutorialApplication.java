package io.yomni.webfluxtutorial;

import io.yomni.webfluxtutorial.client.EmployeeWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;

@SpringBootApplication(exclude = MongoReactiveAutoConfiguration.class)
public class WebfluxTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxTutorialApplication.class, args);

        EmployeeWebClient employeeWebClient = new EmployeeWebClient();
        employeeWebClient.consume();
    }
}
