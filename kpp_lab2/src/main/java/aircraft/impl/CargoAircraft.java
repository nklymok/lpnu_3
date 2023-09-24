package aircraft.impl;

import aircraft.AbstractAircraft;
import aircraft.EngineType;
import aircraft.FlightStatus;

public class CargoAircraft extends AbstractAircraft {
    private final int cargoCapacity;

    public CargoAircraft(double fuelCapacity, double fuelPerKm, int cargoCapacity) {
        super(fuelCapacity, fuelPerKm);
        this.cargoCapacity = cargoCapacity;
        this.setEngineType(EngineType.TURBOFAN);
        this.setFlightStatus(FlightStatus.ONGOING);
    }

    public void startEngines() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void takeOff() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void land() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String toString() {
        return "CargoAircraft{" +
                "fuelCapacity=" + getFuelCapacity() +
                ", fuelPerKm=" + getFuelPerKm() +
                ", flightStatus=" + getFlightStatus() +
                ", engineType=" + getEngineType() +
                "cargoCapacity=" + cargoCapacity +
                '}';
    }
}
