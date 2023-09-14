import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplayMenu{
    public static void main(String[] args) {
        Connection connector = openConnection();
        if (connector == null)
        {   System.err.println("Unable to connect to the database");
            System.exit(1);
        }
        PreparedStatement menuLisStatement = prepareMenuLisStatement(connector);
        displayMenuItems(menuLisStatement);
        closeConnection(connector);
    }
    /* a method that establishes connection */
    private static Connection openConnection(){
        final String url = "jdbc:mysql://localhost:3306/donut shop";
        final String user = "root";
        final String password = "zuizui007";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    /* closes connection with the database*/
    private static void closeConnection(Connection connect){
        try{
            connect.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /** prepares the sql statement used to retrieve the informations from the menu  */
    public static PreparedStatement prepareMenuLisStatement(Connection connect){
        PreparedStatement result = null;
        try{
            String query = "SELECT Menu_types, Menu_id, item_name, item_price FROM "+
            "menu_items left OUTER JOIN menu_categories "+
            "ON menu_items.Item_category=menu_categories.Menu_ID "+
            "ORDER BY menu_id, item_id;";
            result=connect.prepareStatement(query);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }


    private static void displayMenuItems(PreparedStatement listMenuStatement) {
        int last = 0; /**keeps track of the last menu_id retrieved */
        int temp =0;  /** keeps track of the current menu_id */
        try{
        ResultSet result = listMenuStatement.executeQuery();
        System.out.println("======================= DONUT SHOP MENU =======================");
        /** whenever the menu_id changes, the new menu gets menu type gets printed */
        while(result.next()){
            temp=result.getInt(2);
            if(temp!=last){
                System.out.println(" == "+result.getString(1)+" ==");
                last=temp;        
            }  
                System.out.printf ("%-55s%.2f\n",
                                    result.getString(3), 
                                    result.getDouble(4));
                
                    last=result.getInt(2);   
        }
        System.out.println("===============================================================");

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
