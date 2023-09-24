import aircraft.AbstractAircraft;
import aircraft.impl.CargoAircraft;
import aircraft.impl.PassengerAircraft;
import generator.AircraftGenerator;
import manager.SearchManager;
import manager.SortingManager;
import repository.AircraftRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppMenu {
    private final Map<Integer, Runnable> operationMap = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final SortingManager sortingManager = new SortingManager();
    private final SearchManager searchManager = new SearchManager(new SearchManager.FuelCapacityStaticComparator());

    private boolean exit = false;

    public void loop() {
        AircraftRepository aircraftRepository = new AircraftRepository(AircraftGenerator.generate());
        List<AbstractAircraft> aircrafts = aircraftRepository.findAll();
        putMenuOptions(aircrafts, searchManager, sortingManager, scanner);
        printInitialList(aircrafts);

        while (!exit) {
            int choice = getChoice();

            Runnable operation = operationMap.get(choice);
            if (operation != null) {
                operation.run();
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        scanner.close();
    }

    private int getChoice() {
        printOptions();
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void printInitialList(List<AbstractAircraft> aircrafts) {
        System.out.println("Initial aircraft list:");
        printAircraftList(aircrafts);
        System.out.println("\n======\n");
    }

    private void printOptions() {
        System.out.println("Select an operation:");
        System.out.println("1. Search aircraft with max fuel per km");
        System.out.println("2. Search aircraft with max fuel capacity");
        System.out.println("3. Search aircraft with km consumption lower than");
        System.out.println("4. Search landed aircraft");
        System.out.println("5. Sort aircraft by fuel capacity");
        System.out.println("6. Sort passenger aircraft by passenger capacity");
        System.out.println("7. Sort aircraft by fuel per km");
        System.out.println("8. Sort aircraft by fuel capacity (descending)");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private void putMenuOptions(List<AbstractAircraft> aircrafts, SearchManager searchManager, SortingManager sortingManager, Scanner scanner) {
        operationMap.put(1, () -> {
            AbstractAircraft maxFuelPerKmAircraft = searchManager.searchMaxFuelPerKm(aircrafts).get();
            System.out.println("Aircraft with max fuel per km: " + maxFuelPerKmAircraft);
        });
        operationMap.put(2, () -> {
            AbstractAircraft maxFuelCapacityAircraft = searchManager.searchMaxFuelCapacity(aircrafts).get();
            System.out.println("Aircraft with max fuel capacity: " + maxFuelCapacityAircraft);
        });
        operationMap.put(3, () -> {
            System.out.print("Enter maximum fuel per km: ");
            double maxFuelPerKm = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            AbstractAircraft testAircraft = new CargoAircraft(0, maxFuelPerKm, 0);
            List<AbstractAircraft> lowerFuelPerKmAircraft = searchManager.searchWithKmConsumptionLowerThan(aircrafts, testAircraft);
            System.out.println("Aircraft with fuel per km lower than " + maxFuelPerKm + ": ");
            printAircraftList(lowerFuelPerKmAircraft);
        });
        operationMap.put(4, () -> {
            List<AbstractAircraft> landedAircraft = searchManager.searchLanded(aircrafts);
            System.out.println("Landed aircraft: ");
            printAircraftList(landedAircraft);
        });
        operationMap.put(5, () -> {
            List<AbstractAircraft> sortedByFuelCapacity = sortingManager.sortAircraftByFuelCapacity(aircrafts);
            System.out.println("Aircraft sorted by fuel capacity: ");
            printAircraftList(sortedByFuelCapacity);
        });
        operationMap.put(6, () -> {
            List<PassengerAircraft> passengerAircraftList = AircraftUtils.castToPassengerAircraftList(aircrafts);
            List<PassengerAircraft> sortedByPassengerCapacity = sortingManager.sortPassengerAircraftByPassengerCapacity(passengerAircraftList);
            System.out.println("Passenger aircraft sorted by passenger capacity: ");
            printAircraftList(sortedByPassengerCapacity);
        });
        operationMap.put(7, () -> {
            List<AbstractAircraft> sortedByFuelPerKm = sortingManager.sortAircraftByFuelPerKm(aircrafts);
            System.out.println("Aircraft sorted by fuel per km: ");
            printAircraftList(sortedByFuelPerKm);
        });
        operationMap.put(8, () -> {
            List<AbstractAircraft> sortedByFuelCapacityDescending = sortingManager.sortAircraftByFuelCapacityDescending(aircrafts);
            System.out.println("Aircraft sorted by fuel capacity (descending): ");
            printAircraftList(sortedByFuelCapacityDescending);
        });
        operationMap.put(9, () -> exit = true);
    }

    private static void printAircraftList(List<? extends AbstractAircraft> aircraftList) {
        for (AbstractAircraft aircraft : aircraftList) {
            System.out.println(aircraft);
        }
    }
}

class AircraftUtils {
    public static List<PassengerAircraft> castToPassengerAircraftList(List<AbstractAircraft> aircrafts) {
        return aircrafts.stream()
                .filter(aircraft -> aircraft instanceof PassengerAircraft)
                .map(aircraft -> (PassengerAircraft) aircraft)
                .collect(Collectors.toList());
    }
}

