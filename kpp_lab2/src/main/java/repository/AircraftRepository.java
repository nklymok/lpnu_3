package repository;

import aircraft.AbstractAircraft;

import java.util.List;
import java.util.stream.Collectors;

public class AircraftRepository {

    private final List<AbstractAircraft> aircrafts;

    public AircraftRepository(List<AbstractAircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<AbstractAircraft> findAll() {
        return aircrafts.stream()
                .map(AbstractAircraft::clone)
                .collect(Collectors.toList());
    }
}
