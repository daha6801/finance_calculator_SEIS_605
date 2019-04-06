//package JavaFX11;

public class Info {
	String creditType;
	String creditInfo;
	
	public String toString() { //this is needed for combobox
		return creditType;
	}
	
	Info(String creditType, String creditInfo) {
		this.creditType = creditType;
		this.creditInfo = creditInfo;
	}

}
