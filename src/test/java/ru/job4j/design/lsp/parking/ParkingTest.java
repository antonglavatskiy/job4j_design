package ru.job4j.design.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ParkingTest {
    @Test
    public void whenAdd2CarsAndTruckThanAllPark() {
        Vehicle lada = new Car();
        Vehicle uaz = new Car();
        Vehicle man = new Truck(2);
        Parking parking = new Parking(2, 1);
        assertThat(parking.add(lada)).isEqualTo(true);
        assertThat(parking.add(uaz)).isEqualTo(true);
        assertThat(parking.add(man)).isEqualTo(true);
        /*
        assertThat(parking.getCarList()).isEqualTo(List.of(lada, uaz));
        assertThat(parking.getTruckList()).isEqualTo(List.of(man));
         */
    }

    @Test
    public void whenAdd2TrucksThanAllPark() {
        Vehicle man = new Truck(2);
        Vehicle iveco = new Truck(2);
        Parking parking = new Parking(2, 1);
        assertThat(parking.add(man)).isEqualTo(true);
        assertThat(parking.add(iveco)).isEqualTo(true);
        /*
        assertThat(parking.getCarList()).isEqualTo(List.of(iveco));
        assertThat(parking.getTruckList()).isEqualTo(List.of(man));
         */
    }

    @Test
    public void whenAddCarAnd2TrucksThanOneTruckParks() {
        Vehicle lada = new Car();
        Vehicle man = new Truck(2);
        Vehicle iveco = new Truck(2);
        Parking parking = new Parking(0, 1);
        assertThat(parking.add(lada)).isEqualTo(false);
        assertThat(parking.add(man)).isEqualTo(true);
        assertThat(parking.add(iveco)).isEqualTo(false);
        /*
        assertThat(parking.getCarList().size()).isEqualTo(0);
        assertThat(parking.getTruckList()).isEqualTo(List.of(man));
         */

    }

    @Test
    public void whenAddCarAnd2TrucksThanAllTrucksPark() {
        Vehicle lada = new Car();
        Vehicle man = new Truck(2);
        Vehicle iveco = new Truck(2);
        Parking parking = new Parking(4, 0);
        assertThat(parking.add(man)).isEqualTo(true);
        assertThat(parking.add(iveco)).isEqualTo(true);
        assertThat(parking.add(lada)).isEqualTo(false);
        /*
        assertThat(parking.getCarList()).isEqualTo(List.of(man, iveco));
        assertThat(parking.getTruckList().size()).isEqualTo(0);
         */
    }

    @Test
    public void whenCreateInvalidTruck() {
        assertThatThrownBy(() -> new Truck(1))
                .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("Truck is invalid size");
    }


}