import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.Scanner;

/** Text-based JDBC program to check out a stay. This involves changing
    actual_check_out_date from null to the current date. I have a stored
    procedure called checkOut(<stayId>) already defined in the database.

    This is an example of how to call a stored procedure from a
    Java program.

    Before executing this program in a UNB CS lab, remember to execute the
    following statement at the Linux command prompt:

    setenv CLASSPATH .\:/usr/share/java/mysql-connector-java.jar
*/
public class CheckOut
{
    public static void main(String[] args)  throws SQLException
    {   // Allow the user to enter data
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the id of the stay to be checked out: ");
        int stayId = scan.nextInt();

        // Connect to the database
        Connection connector = DriverManager.getConnection
               ("jdbc:mysql://cs1103.cs.unb.ca:3306/dbname",  // Database URL
                "userid",   // MySQL username
                "ABCDEF");  // MySQL password

        // Create the executable SQL statement
        String checkOutCall = "{CALL checkOut(?)}";

        CallableStatement procedureCall = connector.prepareCall(checkOutCall);
        procedureCall.setInt(1, stayId);

        int affectedRows = procedureCall.executeUpdate();

        if (affectedRows == 0)
            System.out.println("\nCheck out failed\n");
        else
            System.out.println("\nCheck out succeeded\n");

        connector.close();

    } // end main()

} // end CheckOut class

