//package JavaFX11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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
		Scene scene = new Scene(view.setupScene(), 800, 600);
		setupActions();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Finance Calculator");
		primaryStage.show();
	}

	void setupActions() {
		
		//bind itemsComboBox with data
		view.itemsComboBox.setItems(model.infoObservableList);
		
		//by default show the super prime (781-850) in the combobox
		view.itemsComboBox.setPromptText(String.format("%s",model.infoObservableList.get(0)));
		view.creditInfoLabel.setText("Based on your score the average rate is 3.68%(new) or 4.34%(used).");
		
		//attach a listener to itemComboBox to display Credit Info description
		view.itemsComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
						
			if (view.itemsComboBox.getSelectionModel().getSelectedIndex() >= 0 ) {
				view.creditInfoLabel.setText(String.format("%s",newValue.creditInfo));
			}
		});
		
		//bing the tradeInDownPaymentTextField with data
		view.tradeInDownPaymentTextField.setText("4000");
		
		//bind TextField with data
		view.carPriceTextField.setText("20000");
		
		//bind the textField data with monthly payment
		DoubleBinding monthlyPaymentBinding = new DoubleBinding() {

			{
				super.bind( view.carPriceTextField.textProperty(), view.tradeInDownPaymentTextField.textProperty(), view.interestRate.valueProperty(), view.noOfMonths.valueProperty() );
			}
			
			@Override
			protected double computeValue() {

				String carPrice =view.carPriceTextField.getText();
				String tradeInDownPayment = view.tradeInDownPaymentTextField.getText();
				Double interestRatevalue = view.interestRate.getValue();
				Integer noOfMonthsvalue = view.noOfMonths.getValue();
				
				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty() && interestRatevalue >= 0.0 ) {
					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					double noOfMonthsvalueDouble = Double.valueOf(noOfMonthsvalue);
					return carPriceDouble * tradeInDownPaymentDouble * interestRatevalue * noOfMonthsvalueDouble;
				} else
				return 0;
			}
						
		};
		
		//bind monthlyPaymentBinding to monthlyPaymentAmountLabel
		view.monthlyPaymentAmountLabel.textProperty().bind(Bindings.format("%.2f", monthlyPaymentBinding));
						
		//bind the textField data with total amount paid
		DoubleBinding totalAmountPaidBinding = new DoubleBinding() {

			{
				super.bind( view.carPriceTextField.textProperty(), view.tradeInDownPaymentTextField.textProperty(), view.interestRate.valueProperty(), view.noOfMonths.valueProperty() );
			}
					
			@Override
			protected double computeValue() {

				String carPrice =view.carPriceTextField.getText();
				String tradeInDownPayment = view.tradeInDownPaymentTextField.getText();
				Double interestRatevalue = view.interestRate.getValue();
				Integer noOfMonthsvalue = view.noOfMonths.getValue();
						
				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty() && interestRatevalue >= 0.0 ) {
					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					double noOfMonthsvalueDouble = Double.valueOf(noOfMonthsvalue);
					return carPriceDouble * tradeInDownPaymentDouble * interestRatevalue * noOfMonthsvalueDouble;
				} else
				return 0;
			}
								
		};
		
		//bind totalAmountPaidBinding to totalAmountLabel
		view.totalAmountLabel.textProperty().bind(Bindings.format("%.2f", totalAmountPaidBinding));

								
		//bind the textField data with total interest paid
		DoubleBinding totalInterestPaidBinding = new DoubleBinding() {

			{
				super.bind( view.carPriceTextField.textProperty(), view.tradeInDownPaymentTextField.textProperty(), view.interestRate.valueProperty(), view.noOfMonths.valueProperty() );
			}
							
			@Override
			protected double computeValue() {

				String carPrice =view.carPriceTextField.getText();
				String tradeInDownPayment = view.tradeInDownPaymentTextField.getText();
				Double interestRatevalue = view.interestRate.getValue();
				Integer noOfMonthsvalue = view.noOfMonths.getValue();
								
				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty() && interestRatevalue >= 0.0 ) {
					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					double noOfMonthsvalueDouble = Double.valueOf(noOfMonthsvalue);
					return carPriceDouble * tradeInDownPaymentDouble * interestRatevalue * noOfMonthsvalueDouble;
				} else
				return 0;
			}
										
		};
				
		//bind totalAmountPaidBinding to realtimeBMILable
		view.totalInterestLabel.textProperty().bind(Bindings.format("%.2f", totalInterestPaidBinding));				
			
	}

}
