/*
 * Aydan Romayor
 * 3/6/2026
 * Main.java
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    /**
     * Prints various stats of the given list of cars, including average mileage and the count of cars with specific fuel types
     * @param cars list of cars to search through
     */
    public static void stats(ArrayList<Car> cars) {
        double mileageSum = 0, averageMilegale = 0;
        int petrolCount = 0, hybridCount = 0, electricCount = 0, dieselCount = 0, cngCount = 0;

        for (Car c : cars) {
            mileageSum += c.getMileage();
            
            String fuelType = c.getFuelType();
            if (fuelType.equalsIgnoreCase("petrol")) {
                petrolCount++;
            }
            else if (fuelType.equalsIgnoreCase("hybrid")) {
                hybridCount++;
            }
            else if (fuelType.equalsIgnoreCase("electric")) {
                electricCount++;
            }
            else if (fuelType.equalsIgnoreCase("diesel")) {
                dieselCount++;
            }
            else if (fuelType.equalsIgnoreCase("cng")) {
                cngCount++;
            }
        }

        averageMilegale = mileageSum / cars.size();

        System.out.printf("Stats:%nAverage Mileage: %.2f%nPetrol Count: %s%nHybrid Count: %s%nElectric Count: %s%nDiesel Count: %s%nCNG Count: %s%n", averageMilegale, petrolCount, hybridCount, electricCount, dieselCount, cngCount);

    }

    /**
     * Reads the given the file and parses the data in car objects, then returns an ArrayList of the cars
     * 
     * @param fileName name of the file with the data of the cars
     * @return An ArrayList with Car objects made from the parsed data
     */
    public static ArrayList<Car> loadCars(String fileName) {
        int totalLoaded = 0;
        ArrayList<Car> cars = new ArrayList<Car>();
        File dataFile = new File(fileName);

        // Scans the file
        try(Scanner scnr = new Scanner(dataFile)) {
            scnr.nextLine(); // Skip the header

            while(scnr.hasNextLine()) {
                /*
                 * Splits the line for parsing
                 * Formatted {carID, brand, model, year, fuelType, color, mileage} 
                 */
                String[] data = scnr.nextLine().split(",");

                // Creates a car object, and assigns the parsed data to their corresponding field in the object
                Car currentCar = new Car(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], data[5], Double.parseDouble(data[6]));
                cars.add(currentCar);
                totalLoaded++;
            }
        } catch (FileNotFoundException e) {
            // Prints a message if an error occured with the file reading
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        
        System.out.println("Total cars loaded: " + totalLoaded);
        return cars;
    }

    /**
     * Uses insertion sort to sort the given array by year
     * 
     * @param cars
     */
    public static void sortByYear(ArrayList<Car> cars) {
        for (int i = 1; i < cars.size(); i++) {
            int j = i;

            while (j > 0 && cars.get(j).getYear() < cars.get(j - 1).getYear()) {
                Car temp = cars.get(j);
                cars.set(j, cars.get(j - 1));
                cars.set(j - 1, temp);
                j--;
            }
        }
    }

    /*public static void sortByColor(ArrayList<Car> cars) {
        for (int i = 1; i < cars.size(); i++) {
            int j = i;

            while (j > 0 && cars.get(j).getColor().compareTo(cars.get(j - 1).getColor()) < 0) {
                Car temp = cars.get(j);
                cars.set(j, cars.get(j - 1));
                cars.set(j - 1, temp);
                j--;
            }
        }
    }*/


    /**
     * This method takes a start and end year range (inclusive), and searches a sorted list for those years.
     * Returns the start and end indices of where the years are located.
     * 
     * @param start - the start year to search for
     * @param end - the end year to search for
     * @param cars - the list to search
     * @return {indexStart, indexEnd}, where indexStart is the first occurence of start and indexEnd is the last occurence of end. If -1, then it wasn't found in the list.
     */
    public static int[] searchByYear(int start, int end, ArrayList<Car> cars) {
        int indexStart = -1;
        int indexEnd = -1;

        int low = 0;
        int high = cars.size() - 1;

        // Searches for the first occurence of the start year
        while(low <= high) {
            int mid = (low + high) / 2;

            if (cars.get(mid).getYear() == start) {
                indexStart = mid;
                high = mid - 1;
            }
            else if (cars.get(mid).getYear() > start) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        // Searches for the last occurence of the end year
        low = 0;
        high = cars.size() - 1;
        while(low <= high) {
            int mid = (low + high) / 2;

            if (cars.get(mid).getYear() == end) {
                indexEnd = mid;
                low = mid + 1;
            }
            else if (cars.get(mid).getYear() > end) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        // Returns the range of the inputted years.
        return new int[]{indexStart, indexEnd};
    }

    public static void main(String[] args) {
        ArrayList<Car> cars = loadCars("Car_Data.csv"); // Loads the cars into an ArrayList
        ArrayList<Car> working = new ArrayList<Car>(cars.subList(0, 2000)); // Creates a sublist of the first 2000 cars
        int[] searchIndex = {-1, -1}; // Indices that show the range of cars that match the user's search

        System.out.println();
        stats(working); // Prints out stats of the working list

        // Menu for the user
        Scanner scnr = new Scanner(System.in);
        int userChoice;
        while(true) {
            System.out.println("\nMenu:\n1. Sort\n2. Search By Year\n3. Show found car object(s)\n4. Exit");
            System.out.print("> ");

            try {
                userChoice = scnr.nextInt();
            }
            catch (InputMismatchException e) {
                // Exception if the user enters a non-integer
                System.out.println("Invalid input. Try again.");
                scnr.next();
                continue;
            }

            if (userChoice == 1) {
                // Sorts the working list by year.
                sortByYear(working);

                // Print the first 10 cars on the list
                System.out.println();
                for(int i = 0; i < 10; i++) {
                    System.out.println(working.get(i));
                }
            }
            else if (userChoice == 2) {
                // Sorts the working list by year.
                sortByYear(working);

                int startYear;
                int endYear;

                // Ask the user for a year range.
                try {
                    System.out.println("\nEnter a start year: ");
                    startYear = scnr.nextInt();

                    System.out.println("\nEnter an end year: ");
                    endYear = scnr.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Try again.");
                    scnr.nextLine();
                    continue;
                }

                // Search the list for the years. Put the results in an array.
                searchIndex = searchByYear(startYear, endYear, working);
                System.out.println();

                // If one or both of the years wasn't found, tell the user.
                if (searchIndex[0] == -1 || searchIndex[1] == -1) {
                    System.out.println("One of both of the years you inputted wasn't found in the list. Try again with a different range.");
                }
                else {
                    System.out.println("Years found!");
                }
            }
            else if (userChoice == 3) {
                System.out.println();

                if (searchIndex[0] == -1 || searchIndex[1] == -1) {
                    System.out.println("Either you didn't search for a year range, or one or both of the years you inputted was not found in the list. Try again.");
                    continue;
                }

                // If the year range is valid, print all cars within that range
                for (int i = searchIndex[0]; i < searchIndex[1]; i++) {
                    System.out.println(working.get(i));
                }
            }
            else if (userChoice == 4) {
                // End the program
                break;
            }
            else {
                // Reprints the menu if the user inputs an invalid number
                continue;
            }
        }
        scnr.close();
    }
}