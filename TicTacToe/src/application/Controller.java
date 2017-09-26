package application;

import javafx.scene.media.AudioClip;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
	
	private boolean isFirstPlayer=true;
	
	@FXML Button b1;
	@FXML Button b2;
	@FXML Button b3;
	@FXML Button b4;
	@FXML Button b5;
	@FXML Button b6;
	@FXML Button b7;
	@FXML Button b8;
	@FXML Button b9;

	@FXML GridPane gameBoard;
	
	public void buttonClickHandler(ActionEvent evt){
		
		AudioClip audio = new AudioClip("http://www.shockwave-sound.com/sound-effects/ocean-sounds/seashore2%20(waves%20seashore).wav");
		audio.play();
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if("".equals(buttonLabel) && isFirstPlayer){
			clickedButton.setText("X");
			isFirstPlayer= false;
		} else if("".equals(buttonLabel) && !isFirstPlayer){
			clickedButton.setText("O");
			isFirstPlayer=true;
			
		}
		
		find3InARow();
		
		
	}
	
	private boolean find3InARow(){
		
		if(""!=b1.getText() && b1.getText()==b2.getText() && b2.getText()==b3.getText()){
			highlightWinning(b1,b2,b3);
			return true;
		}
		
		if(""!=b4.getText() && b4.getText()==b5.getText() && b5.getText()==b6.getText()){
			highlightWinning(b4,b5,b6);
			return true;
		}
		
		if(""!=b7.getText() && b8.getText()==b7.getText() && b8.getText()==b9.getText()){
			highlightWinning(b7,b8,b9);
			return true;
		}
		
		if(""!=b1.getText() && b1.getText()==b4.getText() && b4.getText()==b7.getText()){
			highlightWinning(b1,b4,b7);
			return true;
		}
		
		if(""!=b2.getText() && b2.getText()==b5.getText() && b5.getText()==b8.getText()){
			highlightWinning(b2,b5,b8);
			return true;
		}
		
		if(""!=b3.getText() && b3.getText()==b6.getText() && b6.getText()==b9.getText()){
			highlightWinning(b3,b6,b9);
			return true;
		}
		
		if(""!=b1.getText() && b1.getText()==b5.getText() && b5.getText()==b9.getText()){
			highlightWinning(b1,b5,b9);
			return true;
		}
		
		if(""!=b3.getText() && b3.getText()==b5.getText() && b5.getText()==b7.getText()){
			highlightWinning(b3,b5,b7);
			return true;
		}
		
		return false;
	}
	
	private void highlightWinning(Button b1, Button b2, Button b3){
		
		b1.getStyleClass().add("winning-square");
		b2.getStyleClass().add("winning-square");
		b3.getStyleClass().add("winning-square");
		applyFadeTransition(b1);
		applyFadeTransition(b2);
		applyFadeTransition(b3);
		
	}
	
	private void applyFadeTransition(Button winningButton) {

		  FadeTransition ft = new FadeTransition(Duration.millis(1000), winningButton);

		  ft.setFromValue(1.0);
		  ft.setToValue(0.1);
		  ft.setCycleCount(10);
		  ft.setAutoReverse(true);
		  ft.play();
		}
	
	public void menuClickHandler(ActionEvent evt){
		
		 MenuItem clickedMenu = (MenuItem) evt.getTarget();
		 String menuLabel = clickedMenu.getText();
		 
		 if("Play".equals(menuLabel)){
			 ObservableList<Node> buttons = gameBoard.getChildren();
			 
			 buttons.forEach(btn -> {
		         ((Button) btn).setText("");      
                 btn.getStyleClass().remove("winning-square"); 
		       });
		 }
		 
		 isFirstPlayer=true;
		
	}

}
