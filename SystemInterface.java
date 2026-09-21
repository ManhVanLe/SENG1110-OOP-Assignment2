
import java.util.*;

public class SystemInterface {
    private ParkingLot[] parkingLots; // Array of parking lots
    private Scanner keyboard;

    // Constructor
    public SystemInterface() {
        parkingLots = new ParkingLot[10]; // Allow up to 10 parking lots
        keyboard = new Scanner(System.in);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    createParkingLot();
                    break;
                case 2:
                    addVehicle();
                    break;
                case 3:
                    removeParkingLot();
                    break;
                case 4:
                    removeVehicle();
                    break;
                case 5:
                    listParkingLots();
                    break;
                case 6:
                    listVehiclesInLot();
                    break;
                case 7:
                    listVehiclesByType();
                    break;
                case 8:
                    viewAverageParkingFees();
                    break;
                case 9:
                    loadFromFile();
                    break;
                case 10:
                    saveToFile();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        SystemInterface systemUI = new SystemInterface();
        systemUI.run();
    }

    // Show menu to the user
    private void showMenu() {
        System.out.println("1. Create a new Parking Lot");
        System.out.println("2. Add a vehicle");
        System.out.println("3. Remove a Parking Lot");
        System.out.println("4. Remove a vehicle");
        System.out.println("5. List all Parking Lots");
        System.out.println("6. List vehicles in a Parking Lot");
        System.out.println("7. List vehicles by type");
        System.out.println("8. View average parking fees");
        System.out.println("9. Load data from file");
        System.out.println("10. Save data to file");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to create a new parking lot
    private void createParkingLot() {
        System.out.print("Enter parking lot size (S/M/L): ");
        char size = keyboard.next().charAt(0);
        int parkID = generateParkingLotID();
        for (int i = 0; i < parkingLots.length; i++) {
            if (parkingLots[i] == null) {
                parkingLots[i] = new ParkingLot(parkID, size);
                System.out.println("Parking lot created with ID: " + parkID);
                return;
            }
        }
        System.out.println("Maximum number of parking lots reached.");
    }

    // Generate a random parking lot ID
    private int generateParkingLotID() {
        Random rand = new Random();
        return rand.nextInt(1000) + 1;
    }

    // Method to add a vehicle
    private void addVehicle() {
        System.out.print("Enter vehicle type (Car/Motorcycle/Bicycle): ");
        String vehicleType = keyboard.next();
        System.out.print("Enter entry time (military format, e.g., 800 for 8:00 AM): ");
        int entryTime = keyboard.nextInt();
        System.out.print("Enter parking duration (hours): ");
        int duration = keyboard.nextInt();

        Vehicle vehicle = new Vehicle(generateVehicleID(), vehicleType, entryTime, duration);

        for (ParkingLot lot : parkingLots) {
            if (lot != null && lot.addVehicle(vehicle)) {
                System.out.println("Vehicle added to Parking Lot #" + lot.getParkID());
                return;
            }
        }
        System.out.println("All parking lots are full.");
    }

    // Generate a random vehicle ID
    private int generateVehicleID() {
        Random rand = new Random();
        return rand.nextInt(1000) + 1;
    }

    // Method to remove a parking lot
    private void removeParkingLot() {
        System.out.print("Enter the parking lot ID to remove: ");
        int parkID = keyboard.nextInt();
        for (int i = 0; i < parkingLots.length; i++) {
            if (parkingLots[i] != null && parkingLots[i].getParkID() == parkID) {
                parkingLots[i] = null;
                System.out.println("Parking lot removed.");
                return;
            }
        }
        System.out.println("Parking lot not found.");
    }

    // Method to remove a vehicle by ID
    private void removeVehicle() {
        System.out.print("Enter the vehicle ID to remove: ");
        int vehicleID = keyboard.nextInt();
        for (ParkingLot lot : parkingLots) {
            if (lot != null && lot.removeVehicle(vehicleID)) {
                System.out.println("Vehicle removed from Parking Lot #" + lot.getParkID());
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }

    // List all parking lots
    private void listParkingLots() {
        for (ParkingLot lot : parkingLots) {
            if (lot != null) {
                lot.listVehicles();
            }
        }
    }

    // List vehicles in a specific parking lot
    private void listVehiclesInLot() {
        System.out.print("Enter the parking lot ID: ");
        int parkID = keyboard.nextInt();
        for (ParkingLot lot : parkingLots) {
            if (lot != null && lot.getParkID() == parkID) {
                lot.listVehicles();
                return;
            }
        }
        System.out.println("Parking lot not found.");
    }

    // List vehicles by type
    private void listVehiclesByType() {
        System.out.print("Enter vehicle type (Car/Motorcycle/Bicycle): ");
        String type = keyboard.next();
        for (ParkingLot lot : parkingLots) {
            if (lot != null) {
                for (Vehicle v : lot.getVehicles()) {
                    if (v != null && v.getVehicleType().equalsIgnoreCase(type)) {
                        System.out.println("Vehicle " + v.getVehicleID() + " of type " + type + " is parked in lot #" + lot.getParkID());
                    }
                }
            }
        }
    }

    // View average parking fees
    private void viewAverageParkingFees() {
        // Implementation for calculating and displaying the average fees
    }

    // Load data from a file
    private void loadFromFile() {
        // Implementation for loading data from a text file
    }

    // Save data to a file
    private void saveToFile() {
        // Implementation for saving data to a text file
    }
}
