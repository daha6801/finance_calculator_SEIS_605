package JavaFX11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinanceCalculator extends Application {

	Model model = new Model();
	View view = new View();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		model.loadData(); //this will load the data from .csv file
		Scene scene = new Scene(view.setupScene(), 700, 150);
		setupActions();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Finance Calculator");
		primaryStage.show();
	}

	void setupActions() {
		//bind itemsComboBox with data
		view.itemsComboBox.setItems(model.infoObservableList);

		//attach a listener to itemComboBox to display Credit Info description
		view.itemsComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (view.itemsComboBox.getSelectionModel().getSelectedIndex() >= 0 ) {
				view.creditInfoLabel.setText(String.format("%s",newValue.creditInfo));
			}
		});
		
		//bind TextField with data
		view.carPriceTextField.setText("500");
		
		//bind the textField data with monthly payment
		DoubleBinding monthlyPaymentBinding = new DoubleBinding() {

			{
				super.bind( view.carPriceTextField.textProperty(), view.tradeInDownPaymentTextField.textProperty());
			}
			
			@Override
			protected double computeValue() {
				//Double interestRateValue = view.interestRate.getValue();
				//Integer noOfMonths = view.noOfMonths.getValue();
				@SuppressWarnings("unchecked")
				String carPrice =view.carPriceTextField.getText();
				String tradeInDownPayment = view.tradeInDownPaymentTextField.getText();
				
				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty()) {
					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					return carPriceDouble * tradeInDownPaymentDouble;
				} else
				return 0;
			}
						
		};
		
		//bind bmiBinding to realtimeBMILable
		view.monthlyPaymentAmountLabel.textProperty().bind(Bindings.format("%.2f", monthlyPaymentBinding));
	}

}
