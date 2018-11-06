package springApp.configuration;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

@Configuration
public class SimpleConfiguration {

    @Bean
    public MongoClient mongo() {
        try {
            return new MongoClient("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException("unknown host", e);
        }
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "myDb");
    }
}
