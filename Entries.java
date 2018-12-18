
/*
* Class for adding Entries to a AviMatrix object so they can be manipulated as part of the matrix
*/
public class Entries {
	
	private double matrixIndex; 
	private boolean empty; 

	// Constructor to create an empty index at emptyIndex
	public AviMatrix(double emptyindex) {
		matrixIndex = emptyindex;
		empty = true; 
	}

	//Constructor with no parameters for altering/checking for an occupied index.
	public AviMatrix() {
		matrixIndex = -1; 
		empty = false;
	}

	//Simple toString for formating a matrix according to its empty/filled status
	public String toString() {
		if (empty == true) {
			return matrixIndex + " "; 
		} else {
			return "_";
		}
	}
}