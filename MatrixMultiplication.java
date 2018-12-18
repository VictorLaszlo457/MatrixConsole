import java.util.Arrays;

public class MatrixMultiplication {
	private static double[][] matrixA;
	public static void main(String[] args) {
	  	double[][] matrix1 = {{1,2},
                            {3,4}};
      	double[][] matrix2 = {{1,2},
                            {3,4}};
      	double[][] matrix3 = {{1,2,3},
  							{4,5,6}};
  		double[][] matrix4 = {{-1,0,4},
  							  {2,0,0}};
  		double[][] matrix5 = {{-1,1},
  							  {-1,3},
  							  {2,4}};					  
      	matrixA = multiplyMatrices(matrix4, matrix5);
      	System.out.println(matrixString(matrixA));
  	}

	public static double[][] multiplyMatrices(double[][] m, double[][] n) {
		int row = 0; 
	    double[][] product = new double[m.length][n[row].length];
      	System.out.println(matrixString(m) + " * ");
	    System.out.print(matrixString(n)); 
	    for(int i = 0; i < n[row].length; i++) { //Loop for change column #i in second matrix
	        for (int j = 0; j < m.length; j++) { //Loop for changing row #j in first matrix
	        	for (int k = 0; k < m[row].length; k++) { // Loop for changing index #k in row #j of matrix.
	        		product[j][i] += n[k][i] * m[j][k];
	        	} 	
	        }
	    }
	    System.out.println("------------------------------");
	    return product;
	}

	public static String matrixString(double[][] anyMatrix) {
	        String matrixString = "";
	        for(int row = 0; row < anyMatrix.length; row++) {
	            matrixString += "[";
	            for(int col = 0; col < anyMatrix[row].length; col++) {
	              matrixString += String.format(" %10.1f", anyMatrix[row][col]);
	            if (col == anyMatrix[row].length - 1) {
	              	matrixString = matrixString + "]\n";
	            }
	        }
	    }
	    return matrixString;
	}
}