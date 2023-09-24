package aircraft;

public abstract class AbstractAircraft implements AircraftOperations, Cloneable {
    private double fuelCapacity;
    private double fuelPerKm;
    private FlightStatus flightStatus;
    private EngineType engineType;

    public AbstractAircraft(double fuelCapacity, double fuelPerKm) {
        this.fuelCapacity = fuelCapacity;
        this.fuelPerKm = fuelPerKm;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public double getFuelPerKm() {
        return fuelPerKm;
    }

    public void setFuelPerKm(double fuelPerKm) {
        this.fuelPerKm = fuelPerKm;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public AbstractAircraft clone() {
        try {
            AbstractAircraft clone = (AbstractAircraft) super.clone();
            clone.setFuelCapacity(this.fuelCapacity);
            clone.setFuelPerKm(this.fuelPerKm);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "AbstractAircraft{" +
                "fuelCapacity=" + fuelCapacity +
                ", fuelPerKm=" + fuelPerKm +
                ", flightStatus=" + flightStatus +
                ", engineType=" + engineType +
                '}';
    }
}
