package aircraft.impl;

import aircraft.AbstractAircraft;
import aircraft.EngineType;
import aircraft.FlightStatus;

public class PassengerAircraft extends AbstractAircraft {
    private final int passengerCapacity;

    public PassengerAircraft(double fuelCapacity, double fuelPerKm, int passengerCapacity) {
        super(fuelCapacity, fuelPerKm);
        this.passengerCapacity = passengerCapacity;
        this.setEngineType(EngineType.TURBOPROP);
        this.setFlightStatus(FlightStatus.IDLE);
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

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        return "PassengerAircraft{" +
                "passengerCapacity=" + passengerCapacity +
                "fuelCapacity=" + getFuelCapacity() +
                ", fuelPerKm=" + getFuelPerKm() +
                ", flightStatus=" + getFlightStatus() +
                ", engineType=" + getEngineType() +
                '}';
    }
}
