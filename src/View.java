//package JavaFX11;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class View {

	ComboBox<Info> itemsComboBox = new ComboBox<>(); //the drop down will show the item-names from csv file
	
	
	Label title = new Label("Car payment calculator");
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
	
	//interestRate.setEditable(false);
	Label pymtDetailsLabel = new Label("Payment details");
	Label monthlyPaymentLabel = new Label("Monthly payment");
	Label monthlyPaymentAmountLabel = new Label("$475.95");
	Label monthlyPaymentTextLabel = new Label("(Before taxes & fees)");
	
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
		

		topGrid.add(title, 0, 0);
		topGrid.add(fixedCreditQuestionLabel, 0, 1);
		topGrid.add(itemsComboBox, 0, 2);
		topGrid.add(creditInfoLabel, 0, 3);
		
		topGrid.add(carPriceLabel, 0, 5);
		topGrid.add(carPriceTextField, 0, 6);
		topGrid.add(afterNegotiations, 0, 7);
		
		topGrid.add(tradeInDownPaymentLabel, 0, 9);
		topGrid.add(tradeInDownPaymentTextField, 0, 10);
		topGrid.add(tradeInDownPaymentTextLabel, 0, 11);
		

		

		topGrid.getRowConstraints().add(new RowConstraints(80));
		topGrid.getColumnConstraints().add(new ColumnConstraints(500));
		
		//setup look and feel, fonts, alignment, etc
		
	
		
		root.setStyle("-fx-border-style: solid none none none; -fx-border-width: 5; -fx-border-color: green;");
		title.setFont(Font.font("sans-serif", FontWeight.EXTRA_BOLD, 34));
		fixedCreditQuestionLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		creditInfoLabel.setWrapText(true);
		fixedCreditQuestionLabel.setTextFill(Color.BLACK);
		carPriceLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		carPriceLabel.setTextFill(Color.BLACK);
		carPriceTextField.setMaxWidth(400);
		afterNegotiations.setTextFill(Color.BLACK);
		itemsComboBox.setPromptText("Select Credit Type");
		carPriceTextField.setPromptText("5000");
		tradeInDownPaymentTextField.setPromptText("4000");
		tradeInDownPaymentTextField.setMaxWidth(400);
		tradeInDownPaymentLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		creditInfoLabel.setTextFill(Color.BLACK);
		creditInfoLabel.setFont(Font.font(15));
		pymtDetailsLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 17));
		
		
		DoubleSpinnerValueFactory spinnerFactory = new DoubleSpinnerValueFactory(0,100,4.5); //Figure out how to take user input
		spinnerFactory.setMax(400);
		
		topGrid.add(interestRateLabel, 0, 13);
		interestRateLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		interestRate.setValueFactory(spinnerFactory);
		topGrid.add(interestRate, 0, 14);
		interestRate.setMaxWidth(400);
		topGrid.add(interestRateTextLabel, 0, 15);
		
		IntegerSpinnerValueFactory spinnerFactoryMonths = new IntegerSpinnerValueFactory(0,84,36); //Figure out how to take user input
		
		topGrid.add(months, 0, 17);
		months.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		noOfMonths.setValueFactory(spinnerFactoryMonths);
		topGrid.add(noOfMonths, 0, 18);
		noOfMonths.setMaxWidth(400);
		topGrid.add(monthsLabel, 0, 19);
		
		
		
		//Add controls to Grid Column
		topGrid.add(pymtDetailsLabel, 4, 1);
		topGrid.add(monthlyPaymentLabel, 4, 2);
		monthlyPaymentLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		topGrid.add(monthlyPaymentAmountLabel, 4, 3);
		topGrid.add(monthlyPaymentTextLabel, 4, 4);
		
		topGrid.add(totalAmountPaidLabel, 4, 6);
		totalAmountPaidLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		topGrid.add(totalAmountLabel, 4, 7);
		topGrid.add(totalAmountTextLabel, 4, 9);
		
		
		topGrid.add(totalInterestPaidLabel, 4, 11 );
		totalInterestPaidLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
		topGrid.add(totalInterestLabel, 4, 12);
		
		
		
		
		topGrid.setPrefSize(1000, 1500);
		root.setPrefSize(1000, 1500);
		BorderPane.setMargin(topGrid, new Insets(10, 10, 10, 10));
		return root;
	}
}
