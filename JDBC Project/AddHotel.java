import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

/** Text-based JDBC program to add a new hotel to the database.

    This is an example of how to execute a SQL statement directly from a
    Java program (rather than calling a stored procedure)

    KEY #1: Before executing this program in a UNB CS lab, remember
    to execute thefollowing statement at the Linux command prompt:

    setenv CLASSPATH .\:/usr/share/java/mysql-connector-java.jar
*/
public class AddHotel
{
                    // KEY #2: Handle exceptions in some way...
    public static void main(String[] args) throws SQLException
    {   // Allow the user to enter data
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the new hotel id: ");
        int hotelId = scan.nextInt();
        // Consume to end of line
        scan.nextLine();
        System.out.print("\nEnter the new hotel name: ");
        String hotelName = scan.nextLine();
        System.out.print("\nEnter the new hotel address: ");
        String hotelAddress = scan.nextLine();
      /*  try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        // KEY #3: Connect to the database
        Connection connector = DriverManager.getConnection
               ("jdbc:mysql://localhost:3306/bank",  // Database URL
                "root",   // MySQL username
                "ZUIzui::..10");  // MySQL password

        // KEY #4: Create the executable SQL statement
        String insert = "insert into hotel values (?,?,?);";
        PreparedStatement insertStatement = connector.prepareStatement(insert);
        insertStatement.setInt(1, hotelId);
        insertStatement.setString(2, hotelName);
        insertStatement.setString(3, hotelAddress);

        // KEY #5: Execute the SQL statement
        int affectedRows = insertStatement.executeUpdate();

        // KEY #6: Check the result
        if (affectedRows == 0)
            System.out.println("\nInsertion failed\n");
        else
            System.out.println("\nInsertion succeeded\n");

    } // end main()

} // end AddHotel class
