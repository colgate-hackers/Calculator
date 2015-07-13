import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;



public class StandardMode extends FlowPane {
	private boolean newCalc;

	public StandardMode() {
		super(10, 10);
		
		this.setPadding(new Insets(10, 10, 10, 10));
		//buttons will be held in an array
        
		
		//String array that processes the order of the buttons
        final String[] btns = {"C", "Del", "M", "*", "7", "8", "9", "/", "4", "5", "6", "+", "1", "2", "3", "-", ".", "0", "(-)", "="};
        Button [] buttons  = new Button[btns.length];
        
        for (int i =0; i<btns.length; i++){
        	final int j = i;
        	buttons[i] = new Button(btns[i]);
        	buttons[i].setStyle("-fx-font-size: 16px;");
        	buttons[i].setPadding(new Insets(10, 10, 10, 10));
        	buttons[i].setPrefWidth(90);
        	buttons[i].setPrefHeight(55);
        	if (!btns[i].equals("=")){
	        	buttons[i].setOnAction(new EventHandler<ActionEvent>(){
	        		public void handle(ActionEvent event){
	        			if (btns[j].equals("C")){
	        				Main.input.clear();
	        				return;
	        			}
	        			
	        			String e = Main.input.getText();
	        			
	        			if (btns[j].equals("Del")){
	        				e = e.trim();
	        				e = e.substring(0, e.length() - 1);
	        				Main.input.setText(e.trim());
	        				return;
	        			}
	        			
	        			if (btns[j].equals("(-)")){
	        				Main.input.setText("-");
	        				return;
	        			}
	        			
	        			
	        			
	        			if (btns[j].equals("-") || 
	        					btns[j].equals("+") ||
	        					btns[j].equals("/") ||
	        					btns[j].equals("*")){
	        				
								Main.input.setText(e+ " " +btns[j]);
								newCalc = false;
						}
						else {
							String num = btns[j];
							if (e.endsWith("/") ||
									e.endsWith("*") ||
									e.endsWith("-") ||
									e.endsWith("+")) {
								
								num = " " + num;
							}
							
							if (newCalc){
		        				Main.input.setText(num);
		        				newCalc = false;
		        			} else {
		        				Main.input.setText(e+num);
		        			}
						}
	        		}
	        	});
        	} else {
        		buttons[i].setOnAction(new EventHandler<ActionEvent>(){
	        		public void handle(ActionEvent event){
	        			String e = Main.input.getText();
	        			Main.input.setText("");
	        			//System.out.println(e);
	        			Evaluator eval = new Evaluator(e);
	        			Main.input.setText(eval.answer());
	        			newCalc = true;
	        		}
	        	});
        	}
        	this.getChildren().add(buttons[i]);
        	
        }
        
	}

}
