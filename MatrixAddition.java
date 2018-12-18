import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Random; 

public class MatrixAddition {
  private static Entry[][] correctMatrix;
  private static Entry[][] updatedMatrix;
  private static Entry[][] matrixA;

	public static void main(String[] args) {
    Entry[][] matrix1 = {{new Entry(1),new Entry(2)},
                         {new Entry(3),new Entry(4)},
                         {new Entry(3),new Entry(4)}};
    Entry[][] matrix2 = {{new Entry(5),new Entry(6)},
                         {new Entry(7),new Entry(8)},
                         {new Entry(3),new Entry(4)}};
    Entry[][] matrix3 = {{new Entry(),new Entry()},
                         {new Entry(),new Entry()},
                         {new Entry(),new Entry()}};
    //matrixString(matrix3);
    int rows = 0;
    int columns = 0;
    Scanner in = new Scanner(System.in);
    userOrRandom();
    System.out.println("What is the sum of the matrices A and B given below?:");
    System.out.println("Matrix A: ");
    matrixString(matrix1);
    System.out.println("Matrix B: ");
    matrixString(matrix2); 
    rows = chanceLoop("rows", rows, matrix1);
    columns = chanceLoop("columns", columns, matrix2);
    correctMatrix = sumMatrices(matrix1, matrix2);
    System.out.println("Matrix Sum: ");
    matrixString(correctMatrix);
    Entry[][] userMatrix = enterMatrix(rows, columns);
    if (checkAnswer(correctMatrix, userMatrix) == true) {
      System.out.println("That is correct!");
      matrixString(userMatrix);
    } else {
      System.out.println("That is incorrect.");
      matrixString(updatedMatrix);
      Map<Integer, ArrayList<Integer>> missedEntries = new HashMap<>();
      ArrayList<Integer> listCol = missedEntries.get(1);
      for (int row = 0; row < updatedMatrix.length; row++) {
        for (int col = 0; col < updatedMatrix[0].length; col++) {
          if ((updatedMatrix[row][col].toString()).contains("_")) {
            if (missedEntries.get(row) == null) {
              listCol = new ArrayList<>();
              missedEntries.put(row, listCol);
            }
            listCol.add(col);
          }
        }
      }
    while (checkAnswer(correctMatrix, userMatrix) == false) {
      boolean valid = false;
      for (Integer row: missedEntries.keySet()) {
        ArrayList<Integer> curColumns = missedEntries.get(row);
        for (int y = 0; y < curColumns.size(); y++) {
          do {
            System.out.print("Enter a single value of index matrix[" + (row + 1) + "][" + (curColumns.get(y) + 1) + "]: ");
            try {
              userMatrix[row][curColumns.get(y)] = new Entry(in.nextDouble());
              valid = true;
              if ((correctMatrix[row][curColumns.get(y)]).toString().equals(userMatrix[row][curColumns.get(y)].toString())) {
                curColumns.remove(y);
                if (curColumns == null) {
                  System.out.println("curColumns is null");
                  missedEntries.remove(row, curColumns);
                }
              }
              else {
                userMatrix[row][curColumns.get(y)] = new Entry();
              }
            } catch (InputMismatchException ex) {
              // The line of code below prevents the program from overrunning itself.
              String s = in.nextLine();
              System.out.print("Invalid input - please try again: ");
              valid = false;      
            }
          } while(!valid);
        }
      }
    }
  }
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
    boolean invalid = true;
    do {
      try {
        String option = in.nextLine();
        if ((Integer.parseInt(option) == 1)) {
          System.out.println();
          rows = chanceLoop("rows", rows);
          columns = chanceLoop("columns", columns);
          matrixA = enterMatrix(rows, columns);
          invalid = false;
        } else if (Integer.parseInt(option) == 2) {
          System.out.println();
          rows = rand.nextInt(10) + 1;
          columns = rand.nextInt(10) + 1;
          System.out.println("The matrix has dimensions of " + rows + " rows - " + columns + " columns");
          matrixA = randomMatrix(rows, columns);
          invalid = false;
        } else  {
          System.out.print("Invalid input - please try again: ");
                invalid = true;
        }
      } catch (NumberFormatException ex) {
                System.out.print("Invalid input - please try again: ");
                invalid = true;
          }
      } while (invalid);
    return matrixA; 
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
    System.out.println("The matrix has dimensions of " + rows + 
      " rows - " + columns + " columns");
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
            // The line below prevents the program from over running itself
            String s = input.nextLine();
            System.out.print("Invalid input - please try again: ");
            valid = false;      
          }
        } while (!valid);
      }
    }
    return m;
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

	public static Entry[][] sumMatrices(Entry[][] m, Entry[][] n) {
	    int row = 0; 
      Entry[][] sum = new Entry[m.length][m[row].length];
      for(int i = 0; i < m.length; i++) {
        	for (int j = 0; j < m[i].length; j++) {
            double doubleSum = Double.parseDouble(m[i][j].toString()) + Double.parseDouble(n[i][j].toString()); 
        		sum[i][j] = new Entry(doubleSum);
        	}
      }
      return sum;
	}

  public static Entry[][] subtractMatrices(Entry[][] m, Entry[][] n) {
    int row = 0; 
    Entry[][] diff = new Entry[m.length][m[row].length];
    for(int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        double doubleDiff = Double.parseDouble(m[i][j].toString()) - Double.parseDouble(n[i][j].toString()); 
        diff[i][j] = new Entry(doubleDiff);
      }
    }
    return diff;
  }

  public static double determinant(Entry[][] m) {
    double determinant;
      if (m[0].length == m.length) {
          if (m.length == 2) {
              determinant = (Double.parseDouble(m[0][0]) * Double.parseDouble(m[1][1]))
                            - (Double.parseDouble(m[1][0]) * Double.parseDouble(m[0][1]));
              return determinant;
          }
          if (m.length == 3) {
              determinant = (Double.parseDouble(m[0][0]) * Double.parseDouble(m[1][1]))
                            - (Double.parseDouble(m[1][0]) * Double.parseDouble(m[0][1]));
              return determinant;
          }

      } else {
          System.out.println("Determinant does not exit");
          return 0;
      }
  }

  /**
  * Method that converts the matrix to a readable format. 
  * @param anyMatrix the matrix being passed in to be formated
  * @return matrixString the formated matrix
  */
  public static void matrixString(Entry[][] m) {
    for (Entry[] row: m) {
      System.out.print("[");
      for (int i = 0; i < (row.length - 1); i++) {
        String currentIndex = (row[i]).toString();
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
      } else {
        System.out.printf("%s", lastIndex);
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

  public static int chanceLoop(String thingBeingCounted, int thing, Entry[][] m) {
      Scanner in = new Scanner(System.in);
      int span = 0; 
      if (thingBeingCounted.equals("rows")) {
        span = m.length;
      } else if (thingBeingCounted.equals("columns")){
        span = m[0].length;
      }
      do {
          System.out.print("How many " +  thingBeingCounted + " should the matrix have?: ");
          try {
              thing = in.nextInt();
              if (thing != span) {
                System.out.print("Incorrect - please try again: ");
              }
          }
          catch (InputMismatchException ex) {
              // Line 37 prevents the program from over-running itself
              String s = in.nextLine();
              System.out.print("Incorrect - please try again: ");    
          }
      } while (thing != span);
      return thing;
  }

  public static boolean checkAnswer(Entry[][] correctMatrix, Entry[][] userMatrix) {
    Map<Integer, ArrayList<Integer>> incorrectAns = new HashMap<>();
    ArrayList<Integer> listi = new ArrayList<>();
    ArrayList<Integer> listj = new ArrayList<>();
    updatedMatrix = new Entry[correctMatrix.length][correctMatrix[0].length]; 
    for(int i = 0; i < correctMatrix.length; i++) {
      for (int j = 0; j < correctMatrix[i].length; j++) {
        if (!(userMatrix[i][j].toString()).equals(correctMatrix[i][j].toString())){
          listi.add(i);
          listj.add(j);
          updatedMatrix[i][j] = new Entry(); 
        } else {
          updatedMatrix[i][j] = userMatrix[i][j];
        }
      }
    }
    if (listi.isEmpty() && listj.isEmpty()) {
      return true;
    } else {
      return false;
    }
  } 
}