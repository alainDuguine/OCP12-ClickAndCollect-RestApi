package org.clickandcollect.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:/application-test.properties"})
class ClickAndCollectApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
