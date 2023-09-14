import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.InputMismatchException;

/** Text-based JDBC program to display all rooms in a given hotel.

    Before executing this program in a UNB CS lab, remember to execute the
    following statement at the Linux command prompt:

    setenv CLASSPATH .\:/usr/share/java/mysql-connector-java.jar
*/
public class RoomList
{

    /******************************  main()  **********************************/

    public static void main(String[] args) 
    {
        Connection connector = openConnection();
        if (connector == null)
        {   System.err.println("Unable to connect to the database");
            System.exit(1);
        }

        PreparedStatement roomListStatement = 
                               prepareRoomListStatement(connector);

        char command = getCommand();

        while (command != 'q')
        {   if (command == 'r') // (r)oom list
                displayRoomList(roomListStatement);
            else
                System.out.println("Invalid command");

            command = getCommand();
        } // end while
    
        closeConnection(connector);

    } // end main()

    /**************************  getCommand()  ******************************/

    /** Determines the user's decision on what action to take next
    */
    private static char getCommand() 
    {   Scanner scan = new Scanner(System.in);
        System.out.print("\n(r)oom list or (q)uit? ");
        String input = scan.nextLine().toLowerCase();
        if (input.length() > 0)
            return input.charAt(0);
        else
            return ' ';
    }


    /**************************  openConnection()  ******************************/

    private static Connection openConnection() 
    {   final String url = "jdbc:mysql://cs1103.cs.unb.ca:3306/dbname";
        final String user = "userid";
        final String password = "ABCDEF";

        Connection conn = null;

        try
        {   conn = DriverManager.getConnection(url, user, password);
        } 
        catch (Exception e)
        {   System.err.println("Couldn't open a connection: " + e.getMessage());
        }

        return conn;
    }

    /**************************  closeConnection()  *****************************/

    private static void closeConnection(Connection connector)
    {   try
        {   connector.close();
        } 
        catch (Exception e) 
        {   System.err.println("Couldn't close connection: " + e.getMessage());
        }
    }

    /***********************  prepareRoomListStatement()  **************************/

    /** Prepares the SQL statement used to retrieve all the rooms in a given hotel. 
        Later, this SQL statement is able to accept a different hotel id as a parameter
        each time the user wishes to display some room information.

        This application uses the PreparedStatement class and the prepareStatement
        method from the Connection class. This is because we are submitting
        individual SQL statements to the database. If we were calling stored
        procedures, we would instead use the CallableStatement class and the
        prepareCall method from the Connection class.
    */
    private static PreparedStatement prepareRoomListStatement(Connection connector)
    {
        PreparedStatement result = null;
        try
        {
            String query = "select hotel_name, room_number, " +
                       "room_type_name " +
                       "from room R join hotel using (hotel_id) " +
                       "join room_type using (room_type_code) " +
                       "where R.hotel_id = ? " +
                       "order by room_number;";

            result = connector.prepareStatement(query);
        }
        catch (SQLException e)
        {   System.err.println("Couldn't prepare SQL statement: " + 
                               e.getMessage());
        } // end try/catch

        return result;
    } // end prepareTranscriptStatement

    /**************************  displayRoomList()  *****************************/

    private static void displayRoomList(PreparedStatement roomListStatement)
    {   System.out.print("\nEnter the hotel id: ");
        int hotelId = getIdFromUser();
        int counter = 0;

        try
        {   
            roomListStatement.setInt(1, hotelId);
            ResultSet result = roomListStatement.executeQuery();

            while(result.next())
            {    if (counter < 1)
                 {   // Display header information
                     System.out.println("\nDisplaying rooms for: " +
                                        result.getString(1) + "\n");
                     System.out.println("Room         Room Type");
                     System.out.println("====  ========================");
                 }
                 counter++;

                 System.out.printf ("%4d  %-24s\n",
                                    result.getInt(2), 
                                    result.getString(3));
            }

            if (counter > 0)
                System.out.println("==============================");
            else
                System.out.println("*** No rooms found for that hotel id ***");
        }
        catch (SQLException e)
        {   System.err.println("Couldn't execute database query: " + 
                               e.getMessage());
        } // end try/catch

    } // end displayRoomList()


    /***************************  getIdFromUser()  ******************************/
    /** Allows the user to enter a number id number. This works for either a student
        id or a section id
    */
    private static int getIdFromUser()
    {   Scanner scan = new Scanner(System.in);
        boolean success = false;
        int id = 0;
        while (!success)
        {   
            try
            {   id = scan.nextInt();
                success = true;
            }
            catch (InputMismatchException e)
            {   System.err.println("Id value must be numeric -- please try again");
            }
            scan.nextLine(); // Consume to the end of the line
       } // end while
       return id;
    } // end getIdFromUser()

} // end RoomList class
