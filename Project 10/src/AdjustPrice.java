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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdjustPrice extends Application{
    private Label itemLabel;
    private TextField item;
    private Button adjust;
    private Label status;
    public void start(Stage primaryStage){
        primaryStage.setTitle("Adjust the price of a menu item");
        item = new TextField();
        item.setPrefWidth(50);
        itemLabel = new Label("Menu Item Id:");
        adjust = new Button("Adjust price");
        status = new Label("Enter the menu item id");
        adjust.setOnAction(this::eventHandler);

        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.add(itemLabel,0,0,1,1);
        mainPane.add(item,1,0,1,1);
        mainPane.add(adjust,0,1,1,1);
        mainPane.add(status,0,2,1,1);
        mainPane.setHgap(12);
		mainPane.setVgap(12);
        Scene scene = new Scene(mainPane, 600, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void eventHandler(ActionEvent event){
        int item_id;
        String query = null;
        String message=null;
        int newp;
        try{
            item_id= Integer.parseInt(item.getText());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        try{
            Connection connector = DriverManager.getConnection
                   ("jdbc:mysql://localhost:3306/donut shop",
                    "root",
                    "zuizui007");
                    /** callable statement that execute the price change */
            String call = "call adjustPrice(?);";
            CallableStatement procedureCall = connector.prepareCall(call);
            procedureCall.setInt(1, item_id);
            int affectedRows = procedureCall.executeUpdate();
            
            if (affectedRows == 0)
                status.setText("Adjustment failed: non existant item");
            else
                query = "select item_name, item_price from menu_items "+
                                    "where item_id=? ;";
            /** a prepared helps retrive the updated price and the item from the table */
            PreparedStatement preparePrice = connector.prepareStatement(query);
            preparePrice.setInt(1, item_id);
            ResultSet result = preparePrice.executeQuery();
            result.next();
            message = "The price for "+result.getString(1)+" is now "+
            result.getDouble(2)+"$" ;
            status.setText(message);
                
            connector.close();        

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}