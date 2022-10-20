package ru.job4j.design.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingTest {
    @Test
    public void whenAdd2CarsAndTruckThanAllPark() {
        Vehicle lada = new Car();
        Vehicle uaz = new Car();
        Vehicle man = new Truck(2);
        Place parking = new Parking(2, 1);
        assertTrue(parking.add(lada));
        assertTrue(parking.add(uaz));
        assertTrue(parking.add(man));
    }

    @Test
    public void whenAdd2TrucksThanAllPark() {
        Vehicle man = new Truck(2);
        Vehicle iveco = new Truck(2);
        Place parking = new Parking(2, 1);
        assertTrue(parking.add(man));
        assertTrue(parking.add(iveco));
    }

    @Test
    public void whenAddCarAnd2TrucksThanOneTruckParks() {
        Vehicle lada = new Car();
        Vehicle man = new Truck(2);
        Vehicle iveco = new Truck(2);
        Place parking = new Parking(0, 1);
        assertFalse(parking.add(lada));
        assertTrue(parking.add(man));
        assertFalse(parking.add(iveco));
    }

    @Test
    public void whenAddCarAnd2TrucksThanAllTrucksPark() {
        Vehicle lada = new Car();
        Vehicle man = new Truck(2);
        Vehicle iveco = new Truck(2);
        Place parking = new Parking(4, 0);
        assertTrue(parking.add(man));
        assertTrue(parking.add(iveco));
        assertFalse(parking.add(lada));
    }

    @Test
    public void whenCreateInvalidTruck() {
        assertThatThrownBy(() -> new Truck(1))
                .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("Truck is invalid size");
    }


}