package ru.vibelab.tplatfom;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ru.vibelab.tplatfom.requests.QuestionRequest;
import ru.vibelab.tplatfom.requests.TestRequest;
import ru.vibelab.tplatfom.services.TestService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })  // TODO: Вернуть авторизацию
@RequiredArgsConstructor
public class TestPlatformApplication implements CommandLineRunner {
    private final TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(TestPlatformApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Set<QuestionRequest> questions = new HashSet<>(
        //         Arrays.asList(
        //                 new QuestionRequest("Your name", "What is your name?", "Native"),
        //                 new QuestionRequest("Your surname", "What is your surname?", "Cognitive"),
        //                 new QuestionRequest("Your city", "What is your city?", "Moscow")
        //         )
        // );
        // testService.createTest(
        //         new TestRequest("Denis", "TestUser", questions)
        // );
    }
}
