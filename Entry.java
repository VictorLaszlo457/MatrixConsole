public class Entry {
	
	private double matrixIndex;
	private boolean empty;

	//Constructor for occupied index.
	public Entry() {
		matrixIndex = -1; 
		empty = false;
	} 

	// Constructor to fill an emptyIndex
	public Entry(double newEntry) {
		matrixIndex = newEntry;
		empty = true; 
	}

	public String toString() {
		if (empty == true) {
			return matrixIndex + " "; 
		} else {
			return "_";
		}
	}
}