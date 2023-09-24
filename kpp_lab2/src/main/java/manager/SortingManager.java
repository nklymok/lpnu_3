package manager;

import aircraft.AbstractAircraft;
import aircraft.impl.PassengerAircraft;

import java.util.*;

public class SortingManager {
    public static final Comparator<AbstractAircraft> FUEL_CAPACITY_COMPARATOR = new FuelCapacityStaticComparator();

    private final Comparator<PassengerAircraft> passengerCapacityComparator = new PassengerCapacityInnerComparator();

    public List<AbstractAircraft> sortAircraftByFuelCapacity(List<AbstractAircraft> aircrafts) {
        List<AbstractAircraft> sortedList = new ArrayList<>(aircrafts);
        sortedList.sort(FUEL_CAPACITY_COMPARATOR);
        return sortedList;
    }

    public List<PassengerAircraft> sortPassengerAircraftByPassengerCapacity(List<PassengerAircraft> aircrafts) {
        List<PassengerAircraft> sortedList = new ArrayList<>(aircrafts);
        sortedList.sort(passengerCapacityComparator);
        return sortedList;
    }

    public List<AbstractAircraft> sortAircraftByFuelPerKm(List<AbstractAircraft> aircrafts) {
        Comparator<AbstractAircraft> fuelPerKmComparator = new Comparator<AbstractAircraft>() {
            @Override
            public int compare(AbstractAircraft o1, AbstractAircraft o2) {
                return Double.compare(o1.getFuelPerKm(), o2.getFuelPerKm());
            }
        };

        List<AbstractAircraft> sortedList = new ArrayList<>(aircrafts);
        sortedList.sort(fuelPerKmComparator);
        return sortedList;
    }

    public List<AbstractAircraft> sortAircraftByFuelCapacityDescending(List<AbstractAircraft> aircrafts) {
        List<AbstractAircraft> sortedList = new ArrayList<>(aircrafts);
        sortedList.sort((o1, o2) -> Double.compare(o2.getFuelCapacity(), o1.getFuelCapacity()));
        return sortedList;
    }

    private static class FuelCapacityStaticComparator implements Comparator<AbstractAircraft> {
        @Override
        public int compare(AbstractAircraft o1, AbstractAircraft o2) {
            return Double.compare(o1.getFuelCapacity(), o2.getFuelCapacity());
        }
    }

    private class PassengerCapacityInnerComparator implements Comparator<PassengerAircraft> {
        @Override
        public int compare(PassengerAircraft o1, PassengerAircraft o2) {
            return Integer.compare(o1.getPassengerCapacity(), o2.getPassengerCapacity());
        }
    }
}
