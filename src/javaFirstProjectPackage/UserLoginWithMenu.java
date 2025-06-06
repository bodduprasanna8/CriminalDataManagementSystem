package javaFirstProjectPackage;
import java.sql.*;
import java.util.Scanner;

public class UserLoginWithMenu {

    static final String DB_URL = "jdbc:mysql://localhost:3307/teamproject_1";
    static final String USER = "root";
    static final String PASS = "root"; 

    public static void main(String[] args) throws SQLException {
    	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        try (Scanner scanner = new Scanner(System.in);
             ) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            boolean loggedIn = false;
            int attempts = 3;

            while (!loggedIn && attempts > 0) {
            	System.out.println("WElCOME TO CRIME DATA MANAGEMENT APPLICATION");
            	System.out.println("Enter Credentials to login \n");
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();

                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                if (isLoginValid(conn, email, password)) {
                    System.out.println("Login successful! Welcome !");
                    loggedIn = true;
                } else {
                    attempts--;
                    System.out.println("Invalid credentials. Attempts left: " + attempts + "\n");
                    if (attempts == 0) {
                        System.out.println("Too many failed attempts. Exiting...");
                        break;
                    }
                }

            }GOTOMENU(conn);

        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void GOTOMENU(Connection conn) throws SQLException {
    	  Scanner sc = new Scanner(System.in);
          int choice;

          do {
              System.out.println("\n===== CRIME DATA MANAGEMENT MENU =====");
              System.out.println("1. Watch Table_Wise Data");
              System.out.println("2. Insert Data");
              System.out.println("3. Update Data");
              System.out.println("4. Delete Data");
              System.out.println("5. Access Data");
              System.out.println("6. Exit");
              System.out.print("Enter your choice: ");
              choice = sc.nextInt();

              switch (choice) {
                  case 1 -> watchData(conn);
                  case 2 -> insertData(conn);
                  case 3 -> updateData(conn);
                  case 4 -> deleteData(conn);
                  case 5 -> AccessData(conn);
                  case 6 -> {
                      System.out.println("Exiting... Thank you!");
                      sc.close();
                      System.exit(0);
                  }
                  default -> System.out.println("Invalid choice! Please enter a number from 1 to 5.");
              }
          } while (true);
      }
		
  
	
	
	
	private static void updateData(Connection conn) {
      
		int option;
		do {
			display_update_menu();
			Scanner sc=new Scanner(System.in);
			option=Integer.parseInt(sc.next());
			
			switch(option) {
			case 1:update_officer_table(conn);
			       break;
			case 2:update_crime_table(conn);
			       break;
			case 3:update_criminal_table(conn);
			      break;
			case 4:
				System.out.println("Returning to main menu...");
				return;
			default:System.exit(0);
			}
		} while (option>0);

	}

	private static void update_crime_table(Connection conn) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		 System.out.print("Enter Crime Type: ");
		    String type = sc.nextLine();

		    System.out.print("Enter Description: ");
		    String description = sc.nextLine();

		    System.out.print("Enter Date (YYYY-MM-DD): ");
		    String date = sc.nextLine();

		    System.out.print("Enter Location: ");
		    String location = sc.nextLine();

		    System.out.print("Enter Status: ");
		    String status = sc.nextLine();

		    System.out.print("Enter Officer ID: ");
		    int officerId = sc.nextInt();

		    System.out.print("Enter Criminal ID: ");
		    int criminalId = sc.nextInt();
		    
		    System.out.print("Enter Crime ID: ");
		    int crimeId = sc.nextInt();
		    sc.nextLine();
		    
		    String sql = "UPDATE crimes_information set Crime_type=?, Description=? , Date=?, Location=?, Status=?,Officer_id=?,criminal_id=? where Crime_id=?";
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, type);
		        pstmt.setString(2, description);
		        pstmt.setString(3, date);
		        pstmt.setString(4, location);
		        pstmt.setString(5, status);
		        pstmt.setInt(6, officerId);
		        pstmt.setInt(7, criminalId);
		        pstmt.setInt(8, crimeId);
		        
		        int rows = pstmt.executeUpdate();
		        if(rows==0) {
		        	System.out.println("No one is there in criminals_data table with mentioned id.");
		        }
		        else {
		        System.out.println(rows + " row(s) updated in Crime table.");
		        }
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		
	}
	private static void update_criminal_table(Connection conn) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		 System.out.print("Enter Name: ");
		    String name = sc.nextLine();

		    System.out.print("Enter Age: ");
		    int age = sc.nextInt();
		    sc.nextLine();

		    System.out.print("Enter Gender: ");
		    String gender = sc.nextLine();
		    
		    System.out.print("Enter Criminal ID: ");
		    int id = sc.nextInt();
		    sc.nextLine();
		    
		    String sql = "UPDATE criminals_data set name=?, age=? , gender=? where criminal_id=?";
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, name);
		        pstmt.setInt(2, age);
		        pstmt.setString(3, gender);
		        pstmt.setInt(4, id);


		        int rows = pstmt.executeUpdate();
		        if(rows==0) {
		        	System.out.println("No one is there in criminals_data table with mentioned id.");
		        }
		        else {
		        	
		        
		        System.out.println(rows + " row(s) updated in criminals_data table.");
		        }
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	private static void update_officer_table(Connection conn) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);

	    System.out.print("Enter Name: ");
	    String name = sc.nextLine();

	    System.out.print("Enter Officer Rank: ");
	    String o_rank = sc.nextLine();

	    System.out.print("Enter Salary: ");
	    Double salary = sc.nextDouble();
	    sc.nextLine();
	    
	    System.out.print("Enter Police station name: ");
	    String station = sc.nextLine();
	    
	    System.out.print("Enter contact number:");
	    String phn = sc.nextLine();
	    
	    System.out.print("Enter Officer ID: ");
	    int id = sc.nextInt();
	    sc.nextLine();
	    
	    String sql = "UPDATE  officers_data set Name=?, o_rank=? , Salary=?, PoliceStation_Name=?, Contact_number=? where Officer_id=?";					
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, 	name);
	        pstmt.setString(2, o_rank);
	        pstmt.setDouble(3, salary);
	        pstmt.setString(4, station);
	        pstmt.setString(5, phn);
	        pstmt.setInt(6, id);
	        
	        int rows = pstmt.executeUpdate();
	        if(rows==0) {
	        	System.out.println("No one is there in criminals_data table with mentioned id.");
	        }
	        else {
	        System.out.println(rows + " row(s) updated in Officer table.");
	        }
	        
}catch (Exception e) {
e.printStackTrace();
}


		
	}
	private static void display_update_menu() {
		System.out.println("1.Update for Officer Table");
		System.out.println("2.Update for Crime Table");
		System.out.println("3.Update for Criminal Table");
		System.out.println("4.Back to Main menu");
		System.out.println("Enter option: ");
		
		
	}
	private static void insertData(Connection conn) {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("\nWhich table would you like to insert data into?");
	    System.out.println("1. Criminal");
	    System.out.println("2. Police");
	    System.out.println("3. Crime");
	    System.out.print("Enter your choice: ");
	    int tableChoice = sc.nextInt();
	    sc.nextLine(); // Consume leftover newline

	    try {
	        switch (tableChoice) {
	            case 1 -> insertIntoCriminal(conn, sc);
	            case 2 -> insertIntoPolice(conn, sc);
	            case 3 -> insertIntoCrime(conn, sc);
	            default -> System.out.println("Invalid table choice.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Insertion failed: " + e.getMessage());
	    }
	}
	
	private static void insertIntoCrime(Connection conn, Scanner sc) throws SQLException {
	    System.out.print("Enter Crime ID: ");
	    int crimeId = sc.nextInt();
	    sc.nextLine();

	    System.out.print("Enter Crime Type: ");
	    String type = sc.nextLine();

	    System.out.print("Enter Description: ");
	    String description = sc.nextLine();

	    System.out.print("Enter Date (YYYY-MM-DD): ");
	    String date = sc.nextLine();

	    System.out.print("Enter Location: ");
	    String location = sc.nextLine();

	    System.out.print("Enter Status(ACTIVE OR CLOSED): ");
	    String status = sc.nextLine();

	    System.out.print("Enter Officer ID: ");
	    int officerId = sc.nextInt();

	    System.out.print("Enter Criminal ID: ");
	    int criminalId = sc.nextInt();

	    
	    if (!recordExists(conn, "officers_data", "Officer_id", officerId)) {
	        System.out.println("Officer ID not found. Please insert officer details first.");
	        insertIntoPolice(conn, sc); 
	    }

	   
	    if (!recordExists(conn, "criminals_data", "criminal_id", criminalId)) {
	        System.out.println("Criminal ID not found. Please insert criminal details first.");
	        insertIntoCriminal(conn, sc); 
	    }

	    String sql = "INSERT INTO crimes_information (crime_id, crime_type, description, date, location, status, officer_id, criminal_id) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, crimeId);
	        pstmt.setString(2, type);
	        pstmt.setString(3, description);
	        pstmt.setString(4, date);
	        pstmt.setString(5, location);
	        pstmt.setString(6, status);
	        pstmt.setInt(7, officerId);
	        pstmt.setInt(8, criminalId);

	        int rows = pstmt.executeUpdate();
	        System.out.println(rows + " row(s) inserted into Crime table.");
	    }
	}
	
	private static boolean recordExists(Connection conn, String table, String column, int id) throws SQLException {
	    String sql = "SELECT 1 FROM " + table + " WHERE " + column + " = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next(); // True if record found
	        }
	    }
	}
	
	private static void insertIntoCriminal(Connection conn, Scanner sc) throws SQLException {
	    System.out.print("Enter Criminal ID: ");
	    int id = sc.nextInt();
	    sc.nextLine();

	    System.out.print("Enter Name: ");
	    String name = sc.nextLine();

	    System.out.print("Enter Age: ");
	    int age = sc.nextInt();
	    sc.nextLine();

	    System.out.print("Enter Gender: ");
	    String gender = sc.nextLine();

	    String sql = "INSERT INTO criminals_data (criminal_id, name, age, gender) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, id);
	        pstmt.setString(2, name);
	        pstmt.setInt(3, age);
	        pstmt.setString(4, gender);

	        int rows = pstmt.executeUpdate();
	        System.out.println(rows + " row(s) inserted into criminals_data table.");
	    }
	}

	
	private static void insertIntoPolice(Connection conn, Scanner sc) throws SQLException {
	    System.out.print("Enter Police ID: ");
	    int id = sc.nextInt();
	    sc.nextLine();

	    System.out.print("Enter Name: ");
	    String name = sc.nextLine();

	    System.out.print("Enter Rank: ");
	    String o_rank = sc.nextLine();

	    System.out.print("Enter Salary: ");
	    Double salary = sc.nextDouble();
	    sc.nextLine();
	    
	    System.out.print("Enter Police station name: ");
	    String station = sc.nextLine();
	    
	    System.out.print("Enter contact number:");
	    String phn = sc.nextLine();
	    
	    

	    String sql = "INSERT INTO officers_data(Officer_id, Name, o_rank , salary, PoliceStation_Name, Contact_number) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, id);
	        pstmt.setString(2, name);
	        pstmt.setString(3, o_rank);
	        pstmt.setDouble(4, salary);
	        pstmt.setString(5, station);
	        pstmt.setString(6, phn);

	        int rows = pstmt.executeUpdate();
	        System.out.println(rows + " row(s) inserted into Police table.");
	    }
	}

	public static void watchData(Connection conn) throws SQLException {
	    int choice;
	    Scanner sc = new Scanner(System.in);

	    do {
	        System.out.println("\n--- WATCH DATA MENU ---");
	        System.out.println("1. View Officers Data");
	        System.out.println("2. View Crimes Information");
	        System.out.println("3. View Criminals Data");
	        System.out.println("4. Back to Main Menu");
	        System.out.print("Enter your choice: ");
	        
	        while (!sc.hasNextInt()) {
	            System.out.println("Please enter a valid number!");
	            sc.next(); // clear invalid input
	        }
	        choice = sc.nextInt();
	        sc.nextLine(); // Consume newline

	        switch (choice) {
	            case 1 -> viewOfficers(conn);
	            case 2 -> viewCrimes(conn);
	            case 3 -> viewCriminals(conn);
	            case 4 -> {
	                System.out.println("Returning to main menu...");
	                return;
	            }
	            default -> System.out.println("Invalid choice. Please enter 1-4.");
	        }

	    } while (true);
	}
	
    
	public static void viewOfficers(Connection conn) {
	    String query = "SELECT * FROM officers_data";
	    
	    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
	        System.out.println("\n--- Officers Data ---");
	        System.out.printf("%-10s %-20s %-15s %-10s %-25s %-15s%n",
	                "Officer ID", "Name", "Rank", "Salary", "Police Station", "Contact");

	        while (rs.next()) {
	            int id = rs.getInt("Officer_id");
	            String name = rs.getString("Name");
	            String rank = rs.getString("o_rank");
	            double salary = rs.getDouble("Salary");
	            String station = rs.getString("PoliceStation_Name");
	            String contact = rs.getString("Contact_number");

	            System.out.printf("%-10d %-20s %-15s %-10.2f %-25s %-15s%n",
	                    id, name, rank, salary, station, contact);
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error fetching officers data: " + e.getMessage());
	    }
	}


	public static void viewCrimes(Connection conn) {
	    String query = "SELECT * FROM crimes_information";

	    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
	        System.out.println("\n--- Crimes Information ---");
	        System.out.printf("%-10s %-15s %-25s %-12s %-20s %-10s %-12s %-15s%n",
	                "Crime ID", "Type", "Description", "Date", "Location", "Status", "Officer ID", "Criminal ID");

	        while (rs.next()) {
	            int crimeId = rs.getInt("Crime_id");
	            String type = rs.getString("Crime_type");
	            String description = rs.getString("Description");
	            Date date = rs.getDate("Date");
	            String location = rs.getString("Location");
	            String status = rs.getString("status");
	            int officerId = rs.getInt("Officer_id");
	            int criminalId = rs.getInt("criminal_id");

	            System.out.printf("%-10d %-15s %-25s %-12s %-20s %-10s %-12d %-15d%n",
	                    crimeId, type, description, date, location, status, officerId, criminalId);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error fetching crimes information: " + e.getMessage());
	    }
	}


	public static void viewCriminals(Connection conn) {
	    String query = "SELECT * FROM criminals_data";

	    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
	        System.out.println("\n--- Criminals Data ---");
	        System.out.printf("%-15s %-20s %-10s %-10s%n",
	                "Criminal ID", "Name", "Age", "Gender");

	        while (rs.next()) {
	            int criminalId = rs.getInt("criminal_id");
	            String name = rs.getString("name");
	            int age = rs.getInt("age");
	            String gender = rs.getString("gender");

	            System.out.printf("%-15d %-20s %-10d %-10s%n",
	                    criminalId, name, age, gender);
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error fetching criminals data: " + e.getMessage());
	    }
	}

	
	public static void AccessData(Connection conn) throws SQLException {
	    Scanner sc = new Scanner(System.in);
	    int choice;

	    do {
	        System.out.println("\n===== ACCESS DATA MENU =====");
	        System.out.println("1. Search officer by ID");
	        System.out.println("2. Search crime by ID");
	        System.out.println("3. Filter officers by station name");
	        System.out.println("4. Filter crimes by Officer ID");
	        System.out.println("5. Search criminal by name");
	       
	        System.out.println("6. Show all crimes handled by a specific officer");
	       
	        System.out.println("7. Show all officers who handled a specific crime type");
	        
	        System.out.println("8. Back to Main Menu");
	        System.out.print("Enter your choice: ");
	        choice = sc.nextInt();
	        sc.nextLine(); // Clear buffer

	        switch (choice) {
	            case 1 -> {
	                System.out.print("Enter Officer ID: ");
	                int id = sc.nextInt();
	                searchOfficerById(conn, id);
	            }
	            case 2 -> {
	                System.out.print("Enter Crime ID: ");
	                int id = sc.nextInt();
	                searchCrimeById(conn, id);
	            }
	            case 3 -> {
	                System.out.print("Enter Police Station Name: ");
	                String name = sc.nextLine();
	                filterOfficersByStation(conn, name);
	            }
	            case 4 -> {
	                System.out.print("Enter Officer ID: ");
	                int id = sc.nextInt();
	                filterCrimesByOfficerId(conn, id);
	            }
	            case 5 -> {
	                System.out.print("Enter Criminal Name: ");
	                String name = sc.nextLine();
	                searchCriminalByName(conn, name);
	            }
	            
	            case 6 -> {
	                System.out.print("Enter Officer ID: ");
	                int id = sc.nextInt();
	                viewCrimesByOfficer(conn, id);
	            }
	           
	            case 7 -> {
	                System.out.print("Enter Crime Type: ");
	                String crimeType = sc.nextLine();
	                viewOfficersByCrimeType(conn, crimeType);
	            }
	           
	            case 8 -> {
	                System.out.println("Returning to Main Menu...");
	                return;
	            }
	            default -> System.out.println("❌ Invalid choice. Please try again.");
	        }

	    } while (true);
	}

	
	

	private static void searchCriminalByName(Connection conn, String name) {
	    String sql = "SELECT * FROM criminals_data WHERE name LIKE ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, "%" + name + "%");
	        try (ResultSet rs = pstmt.executeQuery()) {
	            System.out.println("\n--- Criminals Matching: " + name + " ---");
	            boolean found = false;
	            while (rs.next()) {
	                found = true;
	                System.out.printf("ID: %d | Name: %s | Age: %d | Gender: %s%n",
	                        rs.getInt("criminal_id"), rs.getString("name"), rs.getInt("age"),
	                        rs.getString("gender"));
	            }
	            if (!found) System.out.println("No criminals found with that name.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error searching criminal: " + e.getMessage());
	    }
	}

	private static void filterCrimesByOfficerId(Connection conn, int officerId) {
	    String sql = "SELECT * FROM crimes_information WHERE officer_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, officerId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            System.out.println("\n--- Crimes Handled by Officer ID: " + officerId + " ---");
	            boolean found = false;
	            while (rs.next()) {
	                found = true;
	                System.out.printf("Crime ID: %d | Type: %s | Description: %s | Date: %s | Location: %s%n",
	                        rs.getInt("crime_id"), rs.getString("crime_type"), rs.getString("description"),
	                        rs.getString("date"), rs.getString("location"));
	            }
	            if (!found) System.out.println("No crimes found for this officer.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error filtering crimes: " + e.getMessage());
	    }
	}

	private static void filterOfficersByStation(Connection conn, String stationName) {
	    String sql = "SELECT * FROM officers_data WHERE policestation_name = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, stationName);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            System.out.println("\n--- Officers from " + stationName + " ---");
	            boolean found = false;
	            while (rs.next()) {
	                found = true;
	                System.out.printf("ID: %d | Name: %s | Rank: %s | Salary: %.2f | Contact: %s%n",
	                        rs.getInt("officer_id"), rs.getString("name"), rs.getString("o_rank"),
	                        rs.getDouble("salary"), rs.getString("contact_number"));
	            }
	            if (!found) System.out.println("No officers found from this station.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error filtering officers: " + e.getMessage());
	    }
	}

	private static void searchCrimeById(Connection conn, int id) {
	    String sql = "SELECT * FROM crimes_information WHERE crime_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                System.out.println("\n--- Crime Found ---");
	                System.out.printf("ID: %d | Type: %s | Description: %s | Date: %s | Location: %s | Officer ID: %d | Criminal ID: %d%n",
	                        rs.getInt("crime_id"), rs.getString("crime_type"), rs.getString("description"),
	                        rs.getString("date"), rs.getString("location"), rs.getInt("officer_id"), rs.getInt("criminal_id"));
	            } else {
	                System.out.println("Crime with ID " + id + " not found.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error searching crime: " + e.getMessage());
	    }
	}


	private static void searchOfficerById(Connection conn, int id) {
	    String sql = "SELECT * FROM officers_data WHERE officer_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                System.out.println("\n--- Officer Found ---");
	                System.out.printf("ID: %d | Name: %s | Rank: %s | Salary: %.2f | Station: %s | Contact: %s%n",
	                        rs.getInt("officer_id"), rs.getString("name"), rs.getString("o_rank"),
	                        rs.getDouble("salary"), rs.getString("policestation_name"), rs.getString("contact_number"));
	            } else {
	                System.out.println("Officer with ID " + id + " not found.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error searching officer: " + e.getMessage());
	    }		
	}
	public static void viewCrimesByOfficer(Connection conn, int officerId) {
	    String query = """
	        SELECT Crime_id, Crime_type, Description, Date, Location
	        FROM crimes_information
	        WHERE Officer_id = ?
	        """;

	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, officerId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            System.out.println("\n--- Crimes handled by Officer ID: " + officerId + " ---");

	            boolean found = false;
	            while (rs.next()) {
	                found = true;
	                System.out.printf("Crime ID: %d | Type: %s | Description: %s | Date: %s | Location: %s%n",
	                        rs.getInt("Crime_id"), rs.getString("Crime_type"),
	                        rs.getString("Description"), rs.getString("Date"),
	                        rs.getString("Location"));
	            }

	            if (!found) {
	                System.out.println("❌ No crimes found for this officer.");
	            }

	        }
	    } catch (SQLException e) {
	        System.out.println("❌ Error retrieving crimes: " + e.getMessage());
	    }
	}
	
	

	
	
	public static void viewOfficersByCrimeType(Connection conn, String crimeType) {
	    String query = """
	        SELECT o.Officer_id, o.Name, o.o_rank, o.Salary, o.PoliceStation_Name, o.Contact_Number
	        FROM officers_data o
	        JOIN crimes_information c ON o.Officer_id = c.Officer_id
	        WHERE c.Crime_type = ?
	        """;

	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, crimeType);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            System.out.println("\n--- Officers who handled the crime type: " + crimeType + " ---");

	            boolean found = false;
	            while (rs.next()) {
	                found = true;
	                System.out.printf("Officer ID: %d | Name: %s | Rank: %s | Salary: %.2f | Station: %s | Contact: %s%n",
	                        rs.getInt("Officer_id"), rs.getString("Name"),
	                        rs.getString("o_rank"), rs.getDouble("Salary"),
	                        rs.getString("PoliceStation_Name"), rs.getString("Contact_Number"));
	            }

	            if (!found) {
	                System.out.println("❌ No officers found for this crime type.");
	            }

	        }
	    } catch (SQLException e) {
	        System.out.println("❌ Error retrieving officers: " + e.getMessage());
	    }
	}

	
	public static void deleteData(Connection conn) {
	    Scanner sc = new Scanner(System.in);
	    int choice;

	    do {
	        System.out.println("\n--- DELETE DATA MENU ---");
	        System.out.println("1. Delete Officer by ID");
	        System.out.println("2. Delete Criminal by ID");
	        System.out.println("3. Delete Crime by ID");
	        System.out.println("4. Back to Main Menu");
	        System.out.print("Enter your choice: ");
	        choice = sc.nextInt();
	        sc.nextLine(); // Consume newline

	        switch (choice) {
	            case 1 -> {
	                System.out.print("Enter Officer ID to delete: ");
	                int officerId = sc.nextInt();
	                deleteById(conn, "officers_data", "officer_id", officerId);
	            }
	            case 2 -> {
	                System.out.print("Enter Criminal ID to delete: ");
	                int criminalId = sc.nextInt();
	                deleteById(conn, "criminals_data", "criminal_id", criminalId);
	            }
	            case 3 -> {
	                System.out.print("Enter Crime ID to delete: ");
	                int crimeId = sc.nextInt();
	                deleteById(conn, "crimes_information", "crime_id", crimeId);
	            }
	            case 4 -> {
	                System.out.println("Returning to main menu...");
	                return;
	                
	            }
	            default -> System.out.println("Invalid choice. Please enter 1 to 4.");
	        }
	    } while (true);
	}

	
	private static void deleteById(Connection conn, String tableName, String idColumn, int idValue) {
	    String sql = "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, idValue);
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Record deleted successfully from " + tableName + ".");
	        } else {
	            System.out.println("No record found with ID " + idValue + " in " + tableName + ".");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error deleting data: " + e.getMessage());
	    }
	}
	

	public static boolean isLoginValid(Connection conn, String email, String password) throws SQLException {
	    String sql = "SELECT * FROM user WHERE mail = ? AND password = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next();
	        }
	    }
	}

	
}