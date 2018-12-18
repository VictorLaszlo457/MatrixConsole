import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class RandomMatrix {
	private static Entry[][] matrixA;
	public static void main(String[] args) {
		Random rand = new Random();
		int rows1 = rand.nextInt(10) + 1;
		int columns1 = rand.nextInt(10) + 1;
		int columns2 = rand.nextInt(10) + 1;
		int numOfMatrices = rand.nextInt(4) + 1;
		Entry[][] matrixA = {{new Entry(1),new Entry(2)},
                            {new Entry(3),new Entry(4)}};
      	Entry[][] matrixB = {{new Entry(1),new Entry(2)},
                            {new Entry(3),new Entry(4)}};
		Entry[][] matrix1 = randomMatrix(rows1, columns1);
		int rows2 = columns1;
		Entry[][] matrix2 = randomMatrix(rows2,columns2); 
		System.out.println("The product of the two matrices has dimensions of " + rows1 + " rows - "
							+ columns2 + " columns");
		System.out.println("The final matrix is the following:\n"); 
		matrixString(multiplyMatrices(matrix1,matrix2));
	}

	/**
	* Method ask the user to fill out a matrix of the given dimensions  
	* and returns the completed matrix to the user. 
	* @param rows the number of rows of the matrix.
	* @param columns the number of columns of the matrix.
	* @return m the completed matrix with entries typed in by the user.
	*/
	public static Entry[][] enterMatrix(int rows, int columns) {
		Scanner input = new Scanner(System.in);
		Entry[][] m = new Entry[rows][columns];
		boolean valid = false;
		System.out.println("The matrix has dimensions of " + rows + " rows - " + columns + " columns");
		/* The two for loops are for traversing the matrix and the do-while loop
		* allows the user to type in an input as often as necessary until an acceptable
		* input in typed in.
		*/
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {	
				do {
					System.out.print("Enter a single value of index matrix[" + (i + 1) + "][" + (j + 1) + "]: ");
					try {
						m[i][j] = new Entry(input.nextDouble());
						valid = true;
				    }
			    	catch (InputMismatchException ex) {
			    		// Line 37 prevents the program from over-running itself
			    		String s = input.nextLine();
			    		System.out.print("Invalid input - please try again: ");
			    		valid = false;    	
					}
				} while (!valid);
			}
		}
		return m;
	}

	public static Entry[][] multiplyMatrices(Entry[][] m, Entry[][] n) {
		double[][] mDouble = new double[m.length][m[0].length];
		double[][] nDouble = new double[n.length][n[0].length];
		double[][] productDouble = new double[m.length][n[0].length];
	    Entry[][] product = new Entry[m.length][n[0].length];
	    matrixString(m);
      	System.out.println(" * ");
      	matrixString(n);
      	for (int a = 0; a < m.length; a++) {
      		for (int b = 0; b < m[0].length; b++) {
      			mDouble[a][b] = Double.parseDouble(m[a][b].toString());  
      		}
      	}
      	for (int c = 0; c < n.length; c++) {
      		for (int d = 0; d < n[0].length; d++) {
      			nDouble[c][d] = Double.parseDouble(n[c][d].toString());  
      		}
      	}
      	for(int i = 0; i < n[0].length; i++) { //Loop for change column #i in second matrix
	        for (int j = 0; j < m.length; j++) { //Loop for changing row #j in first matrix
	        	for (int k = 0; k < m[0].length; k++) { // Loop for changing index #k in row #j of matrix.
	        		productDouble[j][i] += nDouble[k][i] * mDouble[j][k];
	        		product[j][i] = new Entry(productDouble[j][i]);
	        	} 	
	        }
	    }
	    System.out.println("------------------------------");
	    return product;
	}

	public static Entry[][] userOrRandom() {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		int rows = 0;
		int columns = 0;
		System.out.println();
		System.out.println("Do you want enter your own matricies or " + 
							"receive a randomly generated matrix?");
		System.out.print("Type 1 to enter your own matrix and 2 to receive a matrix: ");
		String option = in.nextLine();
		if ((Integer.parseInt(option) == 1)) {
			System.out.println();
			rows = chanceLoop("rows", rows);
			columns = chanceLoop("columns", columns);
			matrixA = enterMatrix(rows, columns);
			//matrixString(matrixA);
		} else if (Integer.parseInt(option) == 2) {
			System.out.println();
			rows = rand.nextInt(10) + 1;
			columns = rand.nextInt(10) + 1;
			System.out.println("The matrix has dimensions of " + rows + " rows - " + columns + " columns");
			matrixA = randomMatrix(rows, columns);
			//matrixString(matrixA);
		}
		else {
			String s = in.nextLine();
			System.out.println("Invalid Input - please try again: " + in.nextLine());	
		}
		return matrixA; 
	}

	/**
	* Method fills a matrix of the parameratized dimensions with  
	* randomly generated Entrys in the indices.  
	* @param rows the number of rows of the matrix.
	* @param columns the number of columns of the matrix.
	* @return m the completed matrix with randomly generated inputs
	*/
	public static Entry[][] randomMatrix(int rows, int columns) {
		Random randIndex = new Random();
		Entry[][] m = new Entry[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				m[i][j] = new Entry(randIndex.nextDouble() * 100);
			}
		}
		return m; 
	}

	/**
	* Method returns the matrix in a readable format. 
	* @param anyMatrix the matrix being passed in to be formated
	* @return matrixString the formated matrix
	*/
	public static void matrixString(Entry[][] m) {
		for (Entry[] row: m) {
			System.out.print("[");
			for (int i = 0; i < row.length - 1; i++) {
				String currentIndex = row[i].toString();
				if (currentIndex.contains(".")) {
					System.out.printf("%s",currentIndex.substring(0, currentIndex.indexOf('.') + 3));
					System.out.print(" \t");
				} else {
					System.out.printf("%s", currentIndex);
					System.out.print(" \t");
				}	
			}
			String lastIndex = row[row.length - 1].toString(); 
			if (lastIndex.contains(".")) {
				System.out.printf("%s", lastIndex.substring(0, lastIndex.indexOf('.') + 3));
				System.out.print(" \t");
			} else {
				System.out.printf("%s", lastIndex);
				System.out.print(" \t");
			}
			System.out.print("]");
			System.out.println();
		}
		System.out.println();
	}

	/**
	* Method returns takes in the name of the things being counted and 
	* the initial number of the objects counted and gives the user unlimited
	* number of tries to until they enter acceptable input.  
	* @param thingBeingCounted the name of the objects that are to be counted. 
	* @param thing the initial number of objects
	* @return the number of objects typed in by the user. 
	*/
    public static int chanceLoop(String thingBeingCounted, int thing) {
    	Scanner in = new Scanner(System.in);
    	boolean valid = false;
    	do {
			System.out.print("Please enter an integer for the number of " + thingBeingCounted
							  + " you would like the matrix to have: ");
			try {
				thing = in.nextInt();
				valid = true;
			}
			catch (InputMismatchException ex) {
			    // Line 37 prevents the program from over-running itself
			    String s = in.nextLine();
			    System.out.print("Invalid input - please try again: ");
			    valid = false;    	
			}
		} while (!valid);
		return thing; 
    }
	
}