
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Main extends Application {   
	private TextField input;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
		FlowPane mainPane = new FlowPane();
		//a pane holding all nodes
		FlowPane pane = new FlowPane(10, 10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		
		//Menu bar to hold all menus
        MenuBar menu = new MenuBar();
        menu.prefWidthProperty().bind(mainPane.widthProperty());
        
        Menu mode = new Menu("Mode");
        
        //menus to be placed under mode
        MenuItem normal = new MenuItem("Normal");
        MenuItem scientific = new MenuItem("Scientific");
        MenuItem exit = new MenuItem("Exit");
        
        mode.getItems().addAll(normal, scientific, exit);
        menu.getMenus().add(mode);
        
        mainPane.getChildren().add(menu);
		
		//input text to store all the 
        input = new TextField();
        input.prefWidthProperty().bind(pane.widthProperty().subtract(30));
		input.setPrefHeight(100);
		input.setAlignment(Pos.BASELINE_RIGHT);
		input.setStyle("-fx-font-size: 36px;");
        
		//buttons will be held in an array
        Button [] buttons  = new Button[16];
		
		//String array that processes the order of the buttons
        final String[] btns = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "/", ".", "0", "=", "*"};
		
        pane.getChildren().add(input); 
        
        for (int i =0; i<16; i++){
        	final int j = i;
        	buttons[i] = new Button(btns[i]);
        	buttons[i].setStyle("-fx-font-size: 16px;");
        	buttons[i].setPadding(new Insets(10, 10, 10, 10));
        	buttons[i].setPrefWidth(90);
        	buttons[i].setPrefHeight(50);
        
        	buttons[i].setOnAction(new EventHandler<ActionEvent>(){
        		public void handle(ActionEvent event){
        			String e = input.getText();
        			if (btns[j].equals("-") || 
        					btns[j].equals("+") ||
        					btns[j].equals("/") ||
        					btns[j].equals("*")){
							input.setText(e+ " " +btns[j] + " ");}
					else {
        				input.setText(e+btns[j]);
					}
        		}
        	});
        	pane.getChildren().add(buttons[i]);
        	
        }
        
        mainPane.getChildren().add(pane);
        Scene scene = new Scene(mainPane,400, 400);
        primaryStage.setScene(scene);
		primaryStage.setResizable(false);
        primaryStage.show();
        
        
    }
}
