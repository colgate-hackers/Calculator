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
        final String[] btns = {"C", "Del", "M", "*", "7", "8", "9", "/", "4", "5", "6", "+", "1", "2", "3", "-", ".", "0", "(-)", "="};
        Button [] buttons  = new Button[btns.length];
	}

}
