//package JavaFX11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	
	ObservableList<Info> infoObservableList = FXCollections.observableArrayList();
	
	void loadData() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader (new FileReader("src/InfoData.csv"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				Info info = new Info(values[0], values[1]);
				infoObservableList.add(info);
			}
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
