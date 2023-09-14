import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/** Text-based JDBC program to list all client names in the database.

    This is an example of how to execute a SQL statement directly from a
    Java program (rather than calling a stored procedure)

    KEY #1: Before executing this program in a UNB CS lab, remember
    to execute thefollowing statement at the Linux command prompt:

    setenv CLASSPATH .\:/usr/share/java/mysql-connector-java.jar
*/
public class ListClientNames
{
    public static void main(String[] args)
    {   // KEY #2: Handle exceptions - in this case with try /catch
        try
        {
            // KEY #3: Connect to the database
            Connection connector = DriverManager.getConnection
               ("jdbc:mysql://cs1103.cs.unb.ca:3306/dbname",  // Database URL
                "userid",   // MySQL username
                "ABCDEF");  // MySQL password

            // KEY #4: Create the executable SQL statement
            String query = "select client_name from client;";
            PreparedStatement queryStatement = connector.prepareStatement(query);
            // There are no parameters to set in the PreparedStatement


            // KEY #5: Execute the SQL statement
            // Note use of executeQuery() instead of executeUpdate()
            // The result is a ResultSet object, not an int
            ResultSet result = queryStatement.executeQuery();
            int counter = 0;

            // KEY #6: Make use of the ResultSet
            while(result.next())
            {    if (counter < 1)
                 {   // Display header information
                     System.out.println("\n       Client Names");
                     System.out.println("============================");
                 }
                 counter++;

                 System.out.println (result.getString(1));
            }

            if (counter > 0)
                System.out.println("============================\n");
            else
                System.out.println("*** No clients found in the database ***");
        }
        catch (SQLException e)
        {   System.out.println("SQL error: " + e.getMessage());
        }
    } // end main()

} // end ListClientNames class
