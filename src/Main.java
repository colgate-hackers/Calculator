
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
 
public class Main extends Application {   
	private TextField input;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("calculator");
        input = new TextField();
        input.setPrefWidth(400);
        FlowPane pane = new FlowPane();
        Button [] buttons  = new Button[16];
        final String[] btns = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "/", ".", "0", "=", "*"};
        pane.getChildren().add(input); 
        for (int i =0; i<16; i++){
        	final int j = i;
        	buttons[i] = new Button(btns[i]);
        	buttons[i].setPrefWidth(100);
        	buttons[i].setPrefHeight(50);
        	buttons[i].setOnAction(new EventHandler<ActionEvent>(){
        		public void handle(ActionEvent event){
        			String e = input.getText();
        			if (btns[j].equals("-") || 
        					btns[j].equals("+") ||
        					btns[j].equals("/") ||
        					btns[j].equals("*"))
        				input.setText(e+ " " +btns[j] + " ");
        			//
        			else if(btns[j])
        			else
        				input.setText(e+btns[j]);
        		}
        	});
        	pane.getChildren().add(buttons[i]);
        	
        }
        
        Scene scene = new Scene(pane,400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
}
