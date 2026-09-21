
public class Vehicle
{
    private int vehicleID;          // the id of the vehicle.
    private String vehicleType;     // the type of the vehicle. It can be only “Car”, “Motorcycle” or “Bicycle”.
    private int entryTime;          // the time when the vehicle entered the parking lot (military time, e.g., 800 for 8:00am).
    private int parkingDuration;    // the expected parking duration of the vehicle.
    private double parkingFee;      // the calculated parking fee.

    // Constructor
    public Vehicle(int vehicleID, String vehicleType, int entryTime, int parkingDuration) {
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType.toLowerCase(); // Normalize vehicle type to lowercase
        this.entryTime = entryTime;
        this.parkingDuration = parkingDuration;
        this.parkingFee = calculateParkingFee(); // Calculate the fee upon creation
    }

    // Getters and Setters
    public int getVehicleID() {
        return vehicleID;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getEntryTime() {
        return entryTime;
    }

    public int getParkingDuration() {
        return parkingDuration;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    // Method to calculate parking fee based on peak/off-peak hours and duration
    private double calculateParkingFee() {
        double rate = 0;
        switch (vehicleType) {
            case "car":
                rate = 6.0;
                break;
            case "motorcycle":
                rate = 4.0;
                break;
            case "bicycle":
                rate = 2.0;
                break;
        }

        boolean isPeakTime = (entryTime >= 600 && entryTime < 1000) || (entryTime >= 1500 && entryTime < 1900);
        double surgeMultiplier = isPeakTime ? 1.30 : 0.95; // 30% increase during peak, 5% discount off-peak

        double fee = 0;
        if (parkingDuration <= 2) {
            fee = parkingDuration * rate * surgeMultiplier;
        } else {
            // First 2 hours at surge rate, rest at 10% discount
            fee = 2 * rate * surgeMultiplier + (parkingDuration - 2) * rate * 0.90;
        }
        return fee;
    }
}
