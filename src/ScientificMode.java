import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;


public class ScientificMode extends FlowPane {
	private boolean newCalc;
	
	public ScientificMode(){
		super(10, 10);
		this.setPadding(new Insets(10, 10, 10, 10));
		//TODO: scientific keypad
        
		
		//String array that processes the order of the buttons
        final String[] btns = {"x^2","x^y","sin","cos","tan","sqrt","10^x","log","exp","mod","2nd","C", "Del", "M", "*","pi", "7", "8", "9", "/", "n!","4", "5", "6", "+","(-)","1", "2", "3", "-", "(", ")","0", ".", "="};
        Button [] buttons  = new Button[btns.length];
        
        for (int i =0; i<btns.length; i++){
        	final int j = i;
        	if (btns[i].equals("x^2")) {
        		buttons[i] = new Button("x\u00B2");
        	} else if (btns[i].equals("x^y")) {
        		buttons[i] = new Button("x\u207F");
        	} else if (btns[i].equals("10^x")) {
        		buttons[i] = new Button("10\u207F");
        	} else if (btns[i].equals("(-)")) {
        		buttons[i] = new Button("\u00B1");
        	} else if (btns[i].equals("*")) {
        		buttons[i] = new Button("\u00D7");
        	} else if (btns[i].equals("sqrt")) {
        		buttons[i] = new Button("\u221A");
        	} else if (btns[i].equals("=")) {
        		buttons[i] = new Button("\uFF1D");
        	} else if (btns[i].equals("pi")) {
        		buttons[i] = new Button("\u03C0");
        	} else if (btns[i].equals("2nd")) {
        		buttons[i] = new Button("\u2191");
        	} else if (btns[i].equals("/")) {
        		buttons[i] = new Button("\u00F7");
        	} else {
        		buttons[i] = new Button(btns[i]);
        	}
        	
        	buttons[i].setStyle("-fx-font-size: 14px;");
        	buttons[i].setPadding(new Insets(5, 10, 10, 10));
        	buttons[i].setPrefWidth(70);
        	buttons[i].setPrefHeight(38);
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
