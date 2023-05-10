import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

public class BMICalculator extends Application {
    
    private TextField inputHeight;
    private TextField inputWeight;
    @Override
    public void start(Stage primaryStage){
        Label titleLabel = new Label("BMI Calculator");
        titleLabel.setId("titleLabel");
        Label heightLabel = new Label("Height (m)");
        Label weightLabel = new Label("Weight (Kg)");
        inputHeight = new TextField();
        inputWeight = new TextField();
        Button calcBtn = new Button("Calculate");
        calcBtn.setOnAction( e -> calculateBMI());
        GridPane gPane = new GridPane();
        gPane.setHgap(10);
        gPane.setVgap(10);
        gPane.setAlignment(Pos.CENTER);
        gPane.add(heightLabel, 0,0);
        gPane.add(inputHeight, 1,0);
        
        gPane.add(weightLabel, 0,1);
        gPane.add(inputWeight, 1,1); 
        
        gPane.add(calcBtn, 1,2);
        
        BorderPane bPane = new BorderPane();
        bPane.setTop(titleLabel);
        bPane.setAlignment(titleLabel, Pos.CENTER);
        bPane.setCenter(gPane);
        
        Scene scene = new Scene(bPane, 300, 300);
        scene.getStylesheets().add("styles.css");
        
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BMI Calculator");
        primaryStage.show();
    }
    
    private void calculateBMI(){
        double heightValue = Double.parseDouble(inputHeight.getText().trim());
        double weightValue = Double.parseDouble(inputWeight.getText().trim());
        double bmiValue = weightValue/Math.pow(heightValue,2);
        
        if(bmiValue > 0 && bmiValue < 18.5){
            statusBMI(String.format("Your BMI value: %.2f \nStatus: Under Weight",bmiValue), AlertType.INFORMATION);
        } else if(bmiValue >= 18.5 && bmiValue <= 24.9){
             statusBMI(String.format("Your BMI value: %.2f \nStatus: Normal",bmiValue), AlertType.INFORMATION);
        }  else if(bmiValue >= 25 && bmiValue <= 29.9){
             statusBMI(String.format("Your BMI value: %.2f \nStatus: Over Weight",bmiValue), AlertType.INFORMATION);
        }  else if(bmiValue >= 30 && bmiValue <= 34.9){
             statusBMI(String.format("Your BMI value: %.2f \nStatus: Obesity (Class I)",bmiValue), AlertType.INFORMATION);
        } else if(bmiValue >= 35 && bmiValue <= 39.9){
             statusBMI(String.format("Your BMI value: %.2f \nStatus: Obesity (Class II)",bmiValue), AlertType.INFORMATION);
        } else if(bmiValue > 40){
             statusBMI(String.format("Your BMI value: %.2f \nStatus: Extreme Obesity",bmiValue), AlertType.INFORMATION);
        } else if(heightValue == 0){
             statusBMI(String.format("Height must not equal 0"), AlertType.ERROR);
        } else {
            statusBMI(String.format("Invalid input"), AlertType.ERROR);
        }
    }
    
    private void statusBMI(String status, AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle("BMI results");
        alert.setContentText(status);
        alert.showAndWait();
    }
}