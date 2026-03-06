/* 
 * Aydan Romayor
 * 3/6/2028
 * Car Class
 */

public class Car {
    // Private fields
    private String carID;
    private String brand;
    private String model;
    private int year;
    private String fuelType;
    private String color;
    private double mileage_kmpl;

    // Constructor if no parameters are entered
    public Car() {
        carID = "No ID";
        brand = "No Brand";
        model = "No Model";
        year = 0;
        fuelType = "No Fuel Type";
        color = "No Color";
        mileage_kmpl = 0.0;
    }

    // Constructor with parameters
    public Car(String carID, String brand, String model, int year, String fuelType, String color, double mileage_kmpl) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.color = color;
        this.mileage_kmpl = mileage_kmpl;
    }

    // Setter methods
    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMileage(double mileage_kmpl) {
        this.mileage_kmpl = mileage_kmpl;
    }

    // Getter methods
    public String getCarID() {
        return carID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getColor() {
        return color;
    }

    public double getMileage() {
        return mileage_kmpl;
    }

    // Convert to string method
    public String toString() {
        return carID + ", " + brand + ", " + model + ", " + year + ", " + fuelType + ", " + color + ", " + mileage_kmpl;
    }
}