//package JavaFX11;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;

public class View {

	ComboBox<Info> itemsComboBox = new ComboBox<>(); //the drop down will show the item-names from csv file
	
	Label creditInfoLabel = new Label();
	Label fixedCreditQuestionLabel = new Label("What's your credit score?");
	Label carPriceLabel = new Label("Car price ($)");
	TextField carPriceTextField = new TextField();
	Label afterNegotiations = new Label("After negotiations");
	
	Label tradeInDownPaymentLabel = new Label("Trade-in / down payment ($)");
	TextField tradeInDownPaymentTextField = new TextField();
	Label tradeInDownPaymentTextLabel = new Label("Your trade-in can be all or part of a down payment");
	
	Label interestRateLabel = new Label("Intesrest rate(%)");
	Spinner<Double> interestRate = new Spinner<>();
	Label interestRateTextLabel = new Label("A higher credit score means lower interest rates");
	
	Label months = new Label("Number of months");
	Spinner<Integer> noOfMonths = new Spinner<>();
	Label monthsLabel = new Label("Suggested max: 36 months for used cars, 60 for new");
	
	Label monthlyPaymentLabel = new Label("Monthly payment");
	Label monthlyPaymentAmountLabel = new Label("$475.95");
	Label monthlyPaymentTextLabel = new Label("(Before taxes & fees)");
	
	Label lastMonthPaymentLabel = new Label("Last Month's payment");
	Label lastMonthPaymentAmountLabel = new Label("$0.0");

	
	Label totalAmountPaidLabel = new Label("Total amount paid");
	Label totalAmountLabel = new Label("$17,134.20");
	Label totalAmountTextLabel = new Label("(Over the life of the loan)");
	
	Label totalInterestPaidLabel = new Label("Total interest paid");
	Label totalInterestLabel = new Label("$1,134.20");
	
	BorderPane setupScene() {
		
		BorderPane root = new BorderPane();
		GridPane topGrid = new GridPane();
		root.setTop(topGrid);
		
		
		/**setup TopGrid*/
		topGrid.setVgap(10);
		topGrid.setHgap(10);
		
		//add controls to topGrid

		topGrid.add(fixedCreditQuestionLabel, 0, 0);
		topGrid.add(itemsComboBox, 0, 1);
		topGrid.add(creditInfoLabel, 0, 2);
		
		topGrid.add(carPriceLabel, 0, 4);
		topGrid.add(carPriceTextField, 0, 5);
		topGrid.add(afterNegotiations, 0, 6);
		
		topGrid.add(tradeInDownPaymentLabel, 0, 8);
		topGrid.add(tradeInDownPaymentTextField, 0, 9);
		topGrid.add(tradeInDownPaymentTextLabel, 0, 10);
		

		

		topGrid.getRowConstraints().add(new RowConstraints(20));
		topGrid.getColumnConstraints().add(new ColumnConstraints(500));
		
		//setup look and feel, fonts, alignment, etc
		fixedCreditQuestionLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		fixedCreditQuestionLabel.setTextFill(Color.BLACK);
		carPriceLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		carPriceLabel.setTextFill(Color.BLACK);
		afterNegotiations.setTextFill(Color.BLACK);
		itemsComboBox.setPromptText("Select Credit Type");
		carPriceTextField.setPromptText("5000");
		tradeInDownPaymentTextField.setPromptText("4000");
		creditInfoLabel.setTextFill(Color.BLACK);
		creditInfoLabel.setFont(Font.font(15));
		
		
		DoubleSpinnerValueFactory spinnerFactory = new DoubleSpinnerValueFactory(0,100,4.5); //Figure out how to take user input
		
		topGrid.add(interestRateLabel, 0, 12);
		interestRate.setValueFactory(spinnerFactory);
		interestRate.setEditable(true);
	
		topGrid.add(interestRate, 0, 13);
		topGrid.add(interestRateTextLabel, 0, 14);
		
		IntegerSpinnerValueFactory spinnerFactoryMonths = new IntegerSpinnerValueFactory(0,84,36); //Figure out how to take user input
		
		topGrid.add(months, 0, 16);
		noOfMonths.setValueFactory(spinnerFactoryMonths);
		topGrid.add(noOfMonths, 0, 17);
		topGrid.add(monthsLabel, 0, 18);
		
		
		
		//Add controls to Grid Column
		topGrid.add(monthlyPaymentLabel, 1, 1);
		topGrid.add(monthlyPaymentAmountLabel, 1, 2);
		topGrid.add(monthlyPaymentTextLabel, 1, 3);
		
		topGrid. add(lastMonthPaymentLabel, 1, 5);
		topGrid.add(lastMonthPaymentAmountLabel, 1, 6);
		
		topGrid.add(totalAmountPaidLabel, 1, 8);
		topGrid.add(totalAmountLabel, 1, 9);
		topGrid.add(totalAmountTextLabel, 1, 10);
		
		
		topGrid.add(totalInterestPaidLabel, 1, 12);
		topGrid.add(totalInterestLabel, 1, 13);
		
		
		
		
		topGrid.setPrefSize(1700, 1500);
		root.setPrefSize(1700, 1500);
		BorderPane.setMargin(topGrid, new Insets(10, 10, 10, 10));
		return root;
	}
}
