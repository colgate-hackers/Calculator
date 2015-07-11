
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Main extends Application {   
	protected static TextField input;
	
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        final FlowPane mainPane = new FlowPane();
        
        //an HBox is a container that adds children to its view horizontally
        //this HBox will hold the input bar
        HBox inputContainer = new HBox();
        inputContainer.prefWidthProperty().bind(mainPane.widthProperty());
        inputContainer.setPadding(new Insets(10, 10, 10, 10));
        
        input = new TextField();
        
        //pane holding all the buttons for standard mode
		final StandardMode stdPane = new StandardMode();
		//pane holding all the buttons for standard mode
		final ScientificMode sciPane = new ScientificMode();
		
        
		//Menus
		MenuBar menuBar = new MenuBar();
		//set the menu to be the width of the mainPane
		menuBar.prefWidthProperty().bind(mainPane.widthProperty());
		Menu mode = new  Menu("Mode");
		
		//menu options
		MenuItem standard = new MenuItem("Standard");
		MenuItem scientific = new MenuItem("Scientific");
		MenuItem exit = new MenuItem("Exit");
		
		//listener for exit button
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a) {
				System.exit(0);
			}
		});
		
		//adds all the menus to mode
		mode.getItems().addAll(standard, scientific);
		
		//adds mode to the menu bar
		menuBar.getMenus().add(mode);
		
		
		//adds all the children into the mainPane
        mainPane.getChildren().addAll(menuBar, inputContainer, stdPane);
        
        //some set up for the input bar. Some things done in javafx css
        input.prefWidthProperty().bind(inputContainer.widthProperty());
        input.setEditable(false);
		input.setAlignment(Pos.BASELINE_RIGHT);
		input.setStyle("-fx-font-size: 36px;");
		input.setPrefHeight(100);
		
		//add the stylised inputbar to the container
		inputContainer.getChildren().add(input);
		
		//handle mode changes
        scientific.setOnAction(new EventHandler<ActionEvent>(){
			public void handle (ActionEvent a) {
				try{
					//the mode change is a simple keypad swap
					mainPane.getChildren().remove(stdPane);
					mainPane.getChildren().add(sciPane);
					input.setText("");
				} catch (Exception e){
					//
				}
			}
		});
        standard.setOnAction(new EventHandler<ActionEvent>(){
			public void handle (ActionEvent a) {
				try{
					mainPane.getChildren().remove(sciPane);
					mainPane.getChildren().add(stdPane);
					input.setText("");
				} catch (Exception e){
					//
				}
			}
		});
        
        //set up
        Scene scene = new Scene(mainPane,400, 460);
        primaryStage.setScene(scene);
		primaryStage.setResizable(false);
        primaryStage.show();
        
        
    }
}
