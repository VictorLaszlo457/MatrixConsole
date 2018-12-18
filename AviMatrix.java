
public class AviMatrix {
	
	private double matrixIndex; 
	private boolean empty; 

	// Constructor to create an empty index at emptyIndex
	public AviMatrix(double emptyindex) {
		matrixIndex = emptyindex;
		empty = true; 
	}

	//Constructor for occupied index.
	public AviMatrix() {
		matrixIndex = -1; 
		empty = false;
	}

	public String toString() {
		if (empty == true) {
			return matrixIndex + " "; 
		} else {
			return "_";
		}
	}
}