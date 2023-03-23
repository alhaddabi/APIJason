package fetchData;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import storeData.DatabaseManager;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    
    private static final DatabaseManager databaseManager = new DatabaseManager();
    private static final APIManager apiManager = new APIManager(databaseManager);
    public static void main(String[] args) throws SQLException {
        boolean quit = false;
        while (!quit) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                	initializeDatabaseintheSQL();
                    break;
                case "2":
                	deleteTable();
                    break;
                case "3":
                    fetchDataFromAPI();
                    break;
                case "4":
                    fetchDataFromDatabase();
                    break;
                case "5":
                    dumpDataToFile();
                    break;
                case "6":
                    printUniversitiesByCountry();
                    break;
                case "7":
                    quit = true;
                    break;
                default:
                    System.out.println(" Please try valid number.");
            }
        }
        System.out.println("Thanks!!");
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Initialize Database");
        System.out.println("2. Delete Tables");
        System.out.println("3. Fetch Data From API");
        System.out.println("4. Fetch Data From Database");
        System.out.println("5. Dump Data To File & create a backup");
        System.out.println("6. Print Universities By Country");
        System.out.println("7. Quit");
        System.out.print("Please enter your choice: ");
    }

    private static void initializeDatabaseintheSQL() {
        System.out.print("Enter the DataBase you want to create : ");
        String dbName = scanner.nextLine();
        databaseManager.initializeDatabaseintheSQL(dbName);
    }


    private static void deleteTable() throws SQLException 
    {
    	System.out.println("table name :");
    	String tableName = scanner.nextLine();
    	DatabaseManager.deleteDataBaseTables(tableName);
    }


    private static void fetchDataFromAPI() {
        System.out.print("Please enter the name of the country: ");
        String countryName = scanner.nextLine();
        apiManager.fetchUniversitiesByCountry(countryName);
    }

    private static void fetchDataFromDatabase() {
        System.out.print("Please enter the name of the country: ");
        String countryName = scanner.nextLine();
        databaseManager.printTheUnivercityCountry(countryName);
    }

    private static void dumpDataToFile() {
        System.out.print("Please enter the name of the file: ");
        String fileName = scanner.nextLine();
        databaseManager.savaTXTfile(fileName);
    }

    private static void printUniversitiesByCountry() {
        System.out.print("Please enter the name of the country: ");
        String countryName = scanner.nextLine();
        databaseManager.printTheUnivercityCountry(countryName);
    }
    private static void fetchAllUniversities() {
        List<University> universities = databaseManager.getAllUniversities();
        for (University university : universities) {
            System.out.println(university);
        }
    }

}
