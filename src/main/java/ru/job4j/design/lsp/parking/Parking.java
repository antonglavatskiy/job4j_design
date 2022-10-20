package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements Place {
    private int carCell;
    private int truckCell;
    private List<Vehicle> carList;
    private List<Vehicle> truckList;

    public Parking(int carCell, int truckCell) {
        this.carCell = carCell;
        this.truckCell = truckCell;
        carList = new ArrayList<>(carCell);
        truckList = new ArrayList<>(truckCell);
    }

    public List<Vehicle> getCarList() {
        return List.copyOf(carList);
    }

    public List<Vehicle> getTruckList() {
        return List.copyOf(truckList);
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }
}
