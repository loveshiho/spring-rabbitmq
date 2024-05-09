package com.akai;

import com.akai.topic.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRabbitmqApplicationTests {
    @Autowired
    private Publisher publisher;

    @Test
    void contextLoads() {
    }

    @Test
    void testTopic() {
        publisher.publish();
        publisher.publishWithProps();
    }
}
