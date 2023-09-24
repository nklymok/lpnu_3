package manager;

import aircraft.AbstractAircraft;
import aircraft.FlightStatus;
import aircraft.impl.CargoAircraft;
import aircraft.impl.PassengerAircraft;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchManager {

    private final Comparator<AbstractAircraft> fuelPerKmComparator;
    private final Comparator<AbstractAircraft> fuelCapacityComparator;

    public SearchManager(FuelCapacityStaticComparator fuelCapacityStaticComparator) {
        this.fuelPerKmComparator = new FuelPerKmComparator();
        this.fuelCapacityComparator = fuelCapacityStaticComparator;
    }

    public Optional<AbstractAircraft> searchMaxFuelPerKm(List<AbstractAircraft> aircrafts) {
        return aircrafts.stream()
                .max(fuelPerKmComparator);
    }

    public Optional<AbstractAircraft> searchMaxFuelCapacity(List<AbstractAircraft> aircrafts) {
        return aircrafts.stream()
                .max(fuelCapacityComparator);
    }

    public List<AbstractAircraft> searchWithKmConsumptionLowerThan(List<AbstractAircraft> aircrafts, AbstractAircraft searchAgainst) {
        return aircrafts.stream()
                .filter(aircraft -> fuelPerKmComparator.compare(aircraft, searchAgainst) < 0)
                .collect(Collectors.toList());
    }

    public List<AbstractAircraft> searchLanded(List<AbstractAircraft> aircrafts) {
        Predicate<AbstractAircraft> predicate = new Predicate<>() {
            @Override
            public boolean test(AbstractAircraft aircraft) {
                return aircraft.getFlightStatus() == FlightStatus.IDLE;
            }
        };

        return aircrafts.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<PassengerAircraft> searchWithPassengerCapacityOver(List<PassengerAircraft> aircrafts, int capacity) {
        return aircrafts.stream()
                .filter(aircraft -> aircraft.getPassengerCapacity() > capacity)
                .collect(Collectors.toList());
    }

    public List<CargoAircraft> searchWithCargoCapacityOver(List<CargoAircraft> aircrafts, int capacity) {
        return aircrafts.stream()
                .filter(aircraft -> aircraft.getCargoCapacity() > capacity)
                .collect(Collectors.toList());
    }

    private class FuelPerKmComparator implements Comparator<AbstractAircraft> {
        @Override
        public int compare(AbstractAircraft o1, AbstractAircraft o2) {
            return Double.compare(o1.getFuelPerKm(), o2.getFuelPerKm());
        }
    }

    public static class FuelCapacityStaticComparator implements Comparator<AbstractAircraft> {
        @Override
        public int compare(AbstractAircraft o1, AbstractAircraft o2) {
            return Double.compare(o1.getFuelCapacity(), o2.getFuelCapacity());
        }
    }
}

