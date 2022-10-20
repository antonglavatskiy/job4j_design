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
        boolean rsl = false;
        if (vehicle.getSize() == Car.SIZE && carCell >= Car.SIZE) {
            carList.add(vehicle);
            carCell--;
            rsl = true;
        } else if (vehicle.getSize() > Car.SIZE && truckCell >= Car.SIZE) {
            truckList.add(vehicle);
            truckCell--;
            rsl = true;
        } else if (vehicle.getSize() > Car.SIZE && truckCell < Car.SIZE && carCell >= vehicle.getSize()) {
            carList.add(vehicle);
            carCell -= vehicle.getSize();
            rsl = true;
        }
        return rsl;
    }
}
