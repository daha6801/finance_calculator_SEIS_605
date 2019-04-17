//package JavaFX11;

import java.math.BigDecimal;

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


				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty()) {

					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					double principle = 0;
					double monthly_interest_rate = 0;
					
					if (carPriceDouble > 0.0 && tradeInDownPaymentDouble >= 0.0) {
					
						principle = (carPriceDouble - tradeInDownPaymentDouble);	
						monthly_interest_rate = ((interestRatevalue/12)/100);
															
						
					}

					if (interestRatevalue == 0)
					{
						return principle/noOfMonthsvalue;
					}
					return principle * monthly_interest_rate * (Math.pow(1 + monthly_interest_rate, noOfMonthsvalue))/(Math.pow(1 + monthly_interest_rate, noOfMonthsvalue)-1);
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

				
				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty() ) {
					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					double principle = 0;
					double monthly_interest_rate = 0;
					double monthly_payment = 0;
					
					if (carPriceDouble > 0.0 && tradeInDownPaymentDouble >= 0.0) {
						principle = (carPriceDouble - tradeInDownPaymentDouble);	
						monthly_interest_rate = ((interestRatevalue/12)/100);
						
					}
					
					if (interestRatevalue == 0)
					{
						monthly_payment =  principle/noOfMonthsvalue;
						return monthly_payment * noOfMonthsvalue;
					}
					monthly_payment = principle * monthly_interest_rate * (Math.pow(1 + monthly_interest_rate, noOfMonthsvalue))/(Math.pow(1 + monthly_interest_rate, noOfMonthsvalue)-1);											
					return monthly_payment * noOfMonthsvalue;
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
				
				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty() ) {
					
					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					double principle = 0;
					double monthly_interest_rate = 0;
					
					if (carPriceDouble > 0.0 && tradeInDownPaymentDouble >= 0.0) {
						principle = (carPriceDouble - tradeInDownPaymentDouble);	
						monthly_interest_rate = ((interestRatevalue/12)/100);
					}
															
					double monthly_payment;
					if (interestRatevalue == 0)
					{
						monthly_payment =  principle/noOfMonthsvalue;
					}
					else {
						monthly_payment = principle * monthly_interest_rate * (Math.pow(1 + monthly_interest_rate, noOfMonthsvalue))/(Math.pow(1 + monthly_interest_rate, noOfMonthsvalue)-1);
					}
					
					double total_amount_paid = monthly_payment * noOfMonthsvalue;
					return total_amount_paid - principle;
					
				} else
				return 0;
			}
										
		};
				
		//bind totalAmountPaidBinding to realtimeBMILable
		view.totalInterestLabel.textProperty().bind(Bindings.format("%.2f", totalInterestPaidBinding));				
			
	}

}
