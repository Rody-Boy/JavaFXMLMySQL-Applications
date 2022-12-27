package hellofx;

import java.math.BigDecimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private Button btnCalculate;

    @FXML
    private Label sl;

    @FXML
    private Slider slPercent;

    @FXML
    private TextField tfAmount;

    @FXML
    private TextField tfTip;

    @FXML
    private TextField tfTotal;
    
    BigDecimal tipPercentage=new BigDecimal(0.15);

    void initialize() {
        assert btnCalculate != null : "fx:id=\"btnCalculate\" was not injected: check your FXML file 'hellofx.fxml'.";
        assert sl != null : "fx:id=\"sl\" was not injected: check your FXML file 'hellofx.fxml'.";
        assert slPercent != null : "fx:id=\"slPercent\" was not injected: check your FXML file 'hellofx.fxml'.";
        assert tfAmount != null : "fx:id=\"tfAmount\" was not injected: check your FXML file 'hellofx.fxml'.";
        assert tfTip != null : "fx:id=\"tfTip\" was not injected: check your FXML file 'hellofx.fxml'.";
        assert tfTotal != null : "fx:id=\"tfTotal\" was not injected: check your FXML file 'hellofx.fxml'.";
        
        slPercent.valueProperty().addListener((observable,oldValue,newValue)-> {
            sl.setText(Float.toString(newValue.intValue()));
    });

}

    @FXML
    public void calculate(ActionEvent act) { 
           BigDecimal amount=new BigDecimal(tfAmount.getText());
           BigDecimal tip=amount.multiply(tipPercentage);
           BigDecimal total=amount.add(tip);
           
           tfTip.setText(String.valueOf(tip));
           tfTotal.setText(String.valueOf(total));
    }
}