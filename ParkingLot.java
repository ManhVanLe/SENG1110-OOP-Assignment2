
public class ParkingLot 
{
    private int parkID; // the id of the parking lot.
    private char size; // the size of the parking lot (it can be ‘S’, ‘M’ or ‘L’)
    private Vehicle[] vehicles; // Array to hold vehicle objects, size depends on lot size
    
    public ParkingLot(int parkID, char size) {
        this.parkID = parkID;
        this.size = size;
        if (size == 'S') {
            vehicles = new Vehicle[1]; // small lot, 1 vehicle
        } else if (size == 'M') {
            vehicles = new Vehicle[2]; // medium lot, 2 vehicles
        } else if (size == 'L') {
            vehicles = new Vehicle[3]; // large lot, 3 vehicles
        }
    }
    
    // Method to set parkID
    public void setParkID(int parkID) {
        this.parkID = parkID;
    }
    
    // Method to get parkID
    public int getParkID() {
        return parkID;
    }
    
    // Method to set size
    public void setSize(char size) {
        this.size = size;
    }
    
    // Method to get size
    public char getSize() {
        return size;
    }
    
    // Method to add a vehicle
    public boolean addVehicle(Vehicle vehicle) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) { // If a spot is available
                vehicles[i] = vehicle;
                return true;
            }
        }
        return false; // No spots available
    }
    
    // Method to remove a vehicle by ID
    public boolean removeVehicle(int vehicleID) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null && vehicles[i].getVehicleID() == vehicleID) {
                vehicles[i] = null;
                return true;
            }
        }
        return false; // Vehicle not found
    }
    
    // Method to list vehicles in this parking lot
    public void listVehicles() {
        System.out.println("Parking lot #" + parkID + " has size " + size);
        for (Vehicle v : vehicles) {
            if (v != null) {
                System.out.println("Vehicle ID: " + v.getVehicleID() + ", Type: " + v.getVehicleType());
            }
        }
    }
    public Vehicle[] getVehicles(){
        return vehicles;
    }
}
