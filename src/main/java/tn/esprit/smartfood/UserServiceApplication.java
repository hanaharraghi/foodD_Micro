package tn.esprit.smartfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }



    @Autowired
    private UserRepository repository;
    @Bean
    ApplicationRunner init() {
        return
                (args) -> {
// Vérifier si le repository est vide
                    if (repository.count() == 0) {
                        repository.save(new User("John","123","jhon@gmail.com", "123456789", "123 Main St"));
                        repository.save(new User("Jane","456","Jane@gmail.com", "987654321", "456 Elm St"));
                        repository.save(new User("Bob","789","bob@gmail.com", "555555555", "789 Oak St"));
                        repository.save(new User("Alice","321","alice@gmail.com", "111111111", "321 Pine St"));
                        repository.save(new User("Charlie","654","charlie@gmail.com", "222222222", "654 Maple St"));

                    }
// Affichage
                    repository.findAll().forEach(System.out::println);
                };
    }
}
