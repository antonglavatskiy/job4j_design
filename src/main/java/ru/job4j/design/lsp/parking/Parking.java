package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements Place {
    private int carCell;
    private int truckCell;
    private List<Vehicle> vehicleList = new ArrayList<>();

    public Parking(int carCell, int truckCell) {
        this.carCell = carCell;
        this.truckCell = truckCell;
    }

    public List<Vehicle> getVehicleList() {
        return List.copyOf(vehicleList);
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }
}
