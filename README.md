# Parking Lot Management System

A Java console application for managing parking lots and vehicles, developed for SENG1110 Assignment 2. The project uses classes and fixed-size arrays to track parking availability and calculate vehicle parking fees.

## Features

- Create and remove parking lots, with up to 10 lots in memory.
- Add vehicles to the first available space and remove them by vehicle ID.
- List all lots and their vehicles, vehicles in a specific lot, or vehicles of a selected type.
- Calculate a vehicle's parking fee from its type, entry time, and expected parking duration.

### Parking lot capacity

| Size | Maximum vehicles |
| --- | ---: |
| Small (`S`) | 1 |
| Medium (`M`) | 2 |
| Large (`L`) | 3 |

## Project files

| File | Purpose |
| --- | --- |
| `SystemInterface.java` | Application entry point, console menu, and parking lot management. |
| `ParkingLot.java` | Stores a lot's ID, size, and vehicles; handles adding, removing, and listing vehicles. |
| `Updated_Vehicle.java` | Declares the `Vehicle` class, stores vehicle details, and calculates parking fees. |

## Requirements and setup

Install a Java Development Kit (JDK) with `java` and `javac` available on your PATH. The source uses Java 7-compatible language features and has no external dependencies or build tool configuration.

**Before compiling, rename `Updated_Vehicle.java` to `Vehicle.java`.** Java requires the filename to match the public class name. From the project directory in PowerShell, run this once:

```powershell
Rename-Item -LiteralPath Updated_Vehicle.java -NewName Vehicle.java
```

Then compile and launch from the same directory:

```powershell
javac SystemInterface.java ParkingLot.java Vehicle.java
java SystemInterface
```

## Usage

Enter a menu option and follow the prompts:

| Option | Action | Status |
| --- | --- | --- |
| `1` | Create a parking lot | Implemented |
| `2` | Add a vehicle | Implemented |
| `3` | Remove a parking lot | Implemented |
| `4` | Remove a vehicle | Implemented |
| `5` | List all parking lots and their vehicles | Implemented |
| `6` | List vehicles in a parking lot | Implemented |
| `7` | List vehicles by type | Implemented |
| `8` | View average parking fees | Placeholder |
| `9` | Load data from a file | Placeholder |
| `10` | Save data to a file | Placeholder |
| `0` | Exit | Implemented |

For a first run:

1. Choose `1`, then enter `S`, `M`, or `L` in uppercase. Note the generated lot ID.
2. Choose `2`, enter `Car`, `Motorcycle`, or `Bicycle`, an entry time such as `800` for 8:00 AM, and an integer duration in hours.
3. Choose `5` to see the lot and the generated vehicle ID.
4. Choose `4` and enter the vehicle ID to remove it, or choose `0` to exit.

Vehicles are assigned automatically to the first lot with space. Lot size controls capacity; it does not restrict vehicle type.

## Parking fees

| Vehicle type | Base hourly rate |
| --- | ---: |
| Car | 6.00 |
| Motorcycle | 4.00 |
| Bicycle | 2.00 |

The entry time determines the multiplier for the first two hours:

- **Peak:** 06:00 up to, but excluding, 10:00; or 15:00 up to, but excluding, 19:00. The multiplier is `1.30` (30% surcharge).
- **Off-peak:** All other entry times. The multiplier is `0.95` (5% discount).
- **After two hours:** Each additional hour costs `0.90` times the base rate, regardless of entry time.

For example, a car entering at `800` for three hours costs `2 × 6.00 × 1.30 + 1 × 6.00 × 0.90 = 21.00`.

Fees are calculated when a `Vehicle` is created and can be accessed through `getParkingFee()`. The current console interface does not display individual fees, and the average-fee menu option is not implemented.

## Current limitations

- Data exists only in memory and is lost when the application exits. Load and save options currently do nothing.
- Lot and vehicle IDs are randomly generated from 1 to 1000, without checking for duplicates.
- Input validation is incomplete. Use uppercase lot sizes, the listed vehicle types, valid 24-hour times, and positive integer durations. Invalid input can produce incorrect results or terminate the application.
- Removing a parking lot also removes its stored vehicles from the system, without confirmation.
- There is no automated test suite in the repository.
