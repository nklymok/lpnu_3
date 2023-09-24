package generator;

import aircraft.AbstractAircraft;
import aircraft.impl.CargoAircraft;
import aircraft.impl.PassengerAircraft;

import java.util.ArrayList;
import java.util.List;

public class AircraftGenerator {

    public static List<AbstractAircraft> generate() {
        List<AbstractAircraft> aircrafts = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            aircrafts.add(new PassengerAircraft(5000.0 + i * 100, 12.5 + i * 0.5, 150 + i * 10));
        }

        for (int i = 1; i <= 10; i++) {
            aircrafts.add(new CargoAircraft(8000.0 + i * 100, 10.0 + i * 0.5, 5000 + i * 100));
        }
        return aircrafts;
    }

}
