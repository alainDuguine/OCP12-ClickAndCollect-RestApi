package org.clickandcollect.business.impl;

import org.clickandcollect.consumer.repository.MenuRepository;
import org.clickandcollect.consumer.repository.OrderRepository;
import org.clickandcollect.consumer.repository.ProductInCourseRepository;
import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.BusinessHour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductInCourseRepository productInCourseRepository;
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private OrderServiceImpl orderService;
    List<BusinessHour> businessHours = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.businessHours.add(BusinessHour
                .builder()
                .startDay(DayOfWeek.MONDAY)
                .endDay(DayOfWeek.FRIDAY)
                .startTime(LocalTime.of(8,30))
                .endTime(LocalTime.of(12,0))
                .build());

        this.businessHours.add(BusinessHour
                .builder()
                .startDay(DayOfWeek.MONDAY)
                .endDay(DayOfWeek.FRIDAY)
                .startTime(LocalTime.of(18,0))
                .endTime(LocalTime.of(20,30))
                .build());
    }

    @Test()
    void givenPickUpDateTime_whenRestaurantIsOpenAndAfterDayToCompare_shouldReturnTrue() {
        LocalDateTime dateTime = LocalDateTime.of(2020,8,3,10,30);

        assertThat(this.orderService.checkPickupDateTime(
                this.businessHours,
                dateTime,
                LocalDateTime.of(2020,8,3,8,0))
        ).isTrue();

        dateTime = LocalDateTime.of(2020,8,3,20,0);

        assertThat(this.orderService.checkPickupDateTime(
                this.businessHours,
                dateTime,
                LocalDateTime.of(2020,8,3,8,0))
        ).isTrue();
    }

    @Test()
    void givenPickUpDate_whenRestaurantIsOpenAndBeforeDayToCompare_shouldReturnFalse() {
        LocalDateTime dateTime = LocalDateTime.of(2020,8,3,10,30);

        assertThat(this.orderService.checkPickupDateTime(
                this.businessHours,
                dateTime,
                LocalDateTime.of(2020,8,2,8,0))
        ).isFalse();
    }

    @Test()
    void givenPickUpDate_whenRestaurantIsOpenAndBeforeDayToCompareTime_shouldReturnFalse() {
        LocalDateTime dateTime = LocalDateTime.of(2020,8,3,10,30);

        assertThat(this.orderService.checkPickupDateTime(
                this.businessHours,
                dateTime,
                LocalDateTime.of(2020,8,3,11,0))
        ).isFalse();
    }

    @Test()
    void givenPickUpDateTime_whenRestaurantIsClosedOnOpenedDay_shouldReturnFalse() {
        LocalDateTime dateTime = LocalDateTime.of(2020,8,3,14,30);

        assertThat(this.orderService.checkPickupDateTime(
                this.businessHours,
                dateTime,
                LocalDateTime.of(2020,8,3,11,0))
        ).isFalse();
    }

    @Test()
    void givenPickUpDateTime_whenRestaurantIsClosed_shouldReturnFalse() {
        LocalDateTime dateTime = LocalDateTime.of(2020,8,2,14,30);

        assertThat(this.orderService.checkPickupDateTime(
                this.businessHours,
                dateTime,
                LocalDateTime.of(2020,8,2,11,0))
        ).isFalse();
    }
}
