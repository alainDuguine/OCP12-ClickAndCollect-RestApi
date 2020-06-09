package org.clickandcollect.consumer.repository;

import org.clickandcollect.webservice.ClickAndCollectApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ClickAndCollectApiApplication.class)
@TestPropertySource(locations = {"classpath:/application-test.properties"})
public class MenuRepositoryIT {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void givenExistingRestaurantId_whenGetMenus_shouldReturnMenu(){
        assertThat(this.menuRepository.findAllByRestaurantId(1L).size()).isGreaterThan(0);
    }

    @Test
    void givenUnknownRestaurantId_whenGetMenus_shouldReturnMenu(){
        assertThat(this.menuRepository.findAllByRestaurantId(9999L).size()).isEqualTo(0);
    }
}
