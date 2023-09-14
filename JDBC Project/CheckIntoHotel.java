import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;	
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.math.BigDecimal;
import java.sql.Date;

public class CheckIntoHotel extends Application
{
	private Label     stayLabel;
  	private TextField stayField;
	private Label     clientLabel;
  	private TextField clientField;
	private Label     hotelLabel;
  	private TextField hotelField;
	private Label     roomLabel;
  	private TextField roomField;
	private Label     checkInLabel;
  	private TextField checkInField;
	private Label     checkOutLabel;
  	private TextField checkOutField;
	private Label     rateLabel;
  	private TextField rateField;
  	private Button    checkInButton;
	private Label     statusLabel;

  	//*********  start method for the JavaFX app  **************************
	public void start(Stage primaryStage)
	{	primaryStage.setTitle("Check Client Into Hotel");
    	
        stayField = new TextField();
		stayField.setPrefWidth(110);
        clientField = new TextField();
		clientField.setPrefWidth(110);
        hotelField = new TextField();
		hotelField.setPrefWidth(110);
        roomField = new TextField();
		roomField.setPrefWidth(110);
        checkInField = new TextField();
		checkInField.setPrefWidth(110);
        checkOutField = new TextField();
		checkOutField.setPrefWidth(110);
        rateField = new TextField();
		rateField.setPrefWidth(110);

		stayLabel = new Label("Stay Id:");
		clientLabel = new Label("Client Id:");
		hotelLabel = new Label("Hotel Id:");
		roomLabel = new Label("Room Number:");
		checkInLabel = new Label("Check In (yyyy-mm-dd):");
		checkOutLabel = new Label("Check Out (yyyy-mm-dd):");
		rateLabel = new Label("Nightly Rate:");
		
        checkInButton = new Button("Check In");
		checkInButton.setOnAction(this::eventHandler);
		
		statusLabel = new Label("Fill in all fields then click the button");
    	
    	GridPane mainPane  = new GridPane();
		mainPane.setAlignment(Pos.CENTER);

		mainPane.add(stayLabel,       0,0,1,1);
		mainPane.add(stayField,       1,0,1,1);
		mainPane.add(clientLabel,     0,1,1,1);
		mainPane.add(clientField,     1,1,1,1);
		mainPane.add(hotelLabel,      0,2,1,1);
		mainPane.add(hotelField,      1,2,1,1);
		mainPane.add(roomLabel,       0,3,1,1);
		mainPane.add(roomField,       1,3,1,1);
		mainPane.add(checkInLabel,    0,4,1,1);
		mainPane.add(checkInField,    1,4,1,1);
		mainPane.add(checkOutLabel,   0,5,1,1);
		mainPane.add(checkOutField,   1,5,1,1);
		mainPane.add(rateLabel,       0,6,1,1);
		mainPane.add(rateField,       1,6,1,1);
		mainPane.add(checkInButton,   0,7,1,1);
		mainPane.add(statusLabel,     0,8,2,1);
		
		mainPane.setHgap(12);
		mainPane.setVgap(12);

		Scene scene = new Scene(mainPane, 620, 520);
		primaryStage.setScene(scene);
		primaryStage.show();
 	} // end start() 
  
	//*************************************************************
    /* Open, use, and close a database connection each time the
       user clicks the button
    */
	public void eventHandler(ActionEvent event)
	{	
        // Retrieve and validate user input
        int stayId, clientId, hotelId, roomNumber;
        String checkInDate, checkOutDate, rate;
        try
        {   stayId     = Integer.parseInt(stayField.getText());
            clientId   = Integer.parseInt(clientField.getText());
            hotelId    = Integer.parseInt(hotelField.getText());
            roomNumber = Integer.parseInt(roomField.getText());
        }
        catch (Exception e)
        {   statusLabel.setText("Improper numeric entry");
            return;
        }
                
        try
        {   // Connect to the database
            Connection connector = DriverManager.getConnection
                   ("jdbc:mysql://localhost:3306/bank",  // Database URL
                    "root",   // MySQL username
                    "zuizui007");  // MySQL password

            // Create the executable SQL statement
            String call = "{CALL checkIn(?,?,?,?,?,?,?)}";

            CallableStatement procedureCall = connector.prepareCall(call);
            procedureCall.setInt(1, stayId);
            procedureCall.setInt(2, clientId);
            procedureCall.setInt(3, hotelId);
            procedureCall.setInt(4, roomNumber);
            procedureCall.setString(5, checkInField.getText());
            procedureCall.setString(6, checkOutField.getText());
            procedureCall.setBigDecimal(7, new BigDecimal(rateField.getText()));

            int affectedRows = procedureCall.executeUpdate();
            if (affectedRows == 0)
                statusLabel.setText("Check in failed");
            else
                statusLabel.setText("Check in succeeded");

            connector.close();
        }
        catch(SQLException e)
        {   statusLabel.setText("Database error" + e.getMessage());
        }
	} // end eventHandler()
public static void main(String[] args) {
	launch(args);
}
} // end class



