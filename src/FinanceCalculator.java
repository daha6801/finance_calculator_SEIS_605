//package JavaFX11;

import java.math.BigDecimal;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
		Scene scene = new Scene(view.setupScene(), 800, 1000);
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
		
		//bind the tradeInDownPaymentTextField with data
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
				super.bind(view.noOfMonths.valueProperty(), view.monthlyPaymentAmountLabel.textProperty());
			}
			@Override
			protected double computeValue() {

					Integer noOfMonthsvalue = view.noOfMonths.getValue();
					String monthly_payment = view.monthlyPaymentAmountLabel.getText();
					double monthly_payment_double = Double.parseDouble(monthly_payment);
					Double interestRatevalue = view.interestRate.getValue();

					String carPrice =view.carPriceTextField.getText();
					String tradeInDownPayment = view.tradeInDownPaymentTextField.getText();
					
					double total_interest_paid = 0;
					
					if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty() ) {
						
						double carPriceDouble = Double.parseDouble(carPrice);
						double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
						double principle = 0;
						
						if (carPriceDouble > 0.0 && tradeInDownPaymentDouble >= 0.0) {
							principle = (carPriceDouble - tradeInDownPaymentDouble);	
						}												
						total_interest_paid = (monthly_payment_double * noOfMonthsvalue) - principle;
					}
					
					if (interestRatevalue == 0)
					{
						return monthly_payment_double * noOfMonthsvalue - total_interest_paid;
					}
					return monthly_payment_double * noOfMonthsvalue;

			}
								
		};
		
		//bind totalAmountPaidBinding to totalAmountLabel
		view.totalAmountLabel.textProperty().bind(Bindings.format("%.2f", totalAmountPaidBinding));

				
		//bind data with the lastMonthPaymentAmountLable field
		view.lastMonthPaymentAmountLabel.setText("0.00");
		DoubleBinding lastMonthPaymentBinding = new DoubleBinding() {

			{
				super.bind( view.monthlyPaymentAmountLabel.textProperty(), view.totalAmountLabel.textProperty(), view.noOfMonths.valueProperty());
			}
					
			@Override
			protected double computeValue() {

				Integer noOfMonthsvalue = view.noOfMonths.getValue();

				String total_amount_paid = view.totalAmountLabel.getText();
				double total_amount_paid_double = Double.parseDouble(total_amount_paid);
				
				String monthly_payment = view.monthlyPaymentAmountLabel.getText();
				double monthly_payment_double = Double.parseDouble(monthly_payment);

				return total_amount_paid_double - monthly_payment_double * (noOfMonthsvalue -1);

			}
								
		};
			
		//bind lastMonthPaymentAmountLable to last months payment data
		view.lastMonthPaymentAmountLabel.textProperty().bind(Bindings.format("%.2f", lastMonthPaymentBinding));
				
		//bind the textField data with total interest paid
		DoubleBinding totalInterestPaidBinding = new DoubleBinding() {
				
			{
				super.bind(view.carPriceTextField.textProperty(), view.tradeInDownPaymentTextField.textProperty(), view.totalAmountLabel.textProperty());
			}
			@Override
			protected double computeValue() {

				String carPrice =view.carPriceTextField.getText();
				String tradeInDownPayment = view.tradeInDownPaymentTextField.getText();
				String total_amount_paid = view.totalAmountLabel.getText();
				double total_amount_paid_double = Double.parseDouble(total_amount_paid);
				double principle = 0;
				
				if (  !carPrice.isEmpty() && !tradeInDownPayment.isEmpty() ) {
					
					double carPriceDouble = Double.parseDouble(carPrice);
					double  tradeInDownPaymentDouble = Double.parseDouble(tradeInDownPayment);
					
					
					if (carPriceDouble > 0.0 && tradeInDownPaymentDouble >= 0.0) {
						principle = (carPriceDouble - tradeInDownPaymentDouble);	
					}												
					
				}
				return total_amount_paid_double - principle;
			}
										
		};
		
		//bind totalAmountPaidBinding to realtimeBMILable
		view.totalInterestLabel.textProperty().bind(Bindings.format("%.2f", totalInterestPaidBinding));			
	}
}
