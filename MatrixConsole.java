import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.Random;

public class MatrixConsole extends Application implements EventHandler<ActionEvent> {

	Stage window;
	Scene scene1, scene2, scene3, scene4;
	Button button;
	GridPane matrixTable = new GridPane();
	private static Entry[][] matrixA;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Color c = Color.DARKSEAGREEN;

		Text t = new Text(10, 50, "This is a test");
    	t.setText("The Smith Matrix Simulator 1.0 was developed to help students practice elementary\n" +
    				"matrix manipulation skills that they will employ frequently during their\n" +
    				"pursuit in their studies of computer science, mathematics, engineering or related fields.\n" + 
    				"This version of the calculator is equip with basic tools such as a tutorials," + 
    				 "matrix calculator,\n " + "practice problem generator, and timed labs.\n" + "\n" +
    				"If you have questions or need a reminder on how to do some of the functions,\n" +
    				"click the \"tutorials\" button to see an example of how to work different types of problems.\n" +
    				"If you want to check your work on an already completed problem, the \"matrix calculator\"\n" +
    				"is great tool." + " Practice makes perfect! If you want to work on some extra problem\n" +
    				"the \"practice problems generator\" has three difficulty settings so you can improve\n" +
    				"at your own pace." + " Want to challenge yourself and get ready for the time constraints of\n" +
    				"of your next big exam? Click the \"timed lab\", where you can set the timer, number of problems,\n" +
    				"difficulty, and save your performance. Track your progress! Compete with your friends!\n" +
    				"Earn the title of Supreme Matrix Master!" + "...Or just learn a new thing or two while having fun! :)");
    	t.setFill(Color.PURPLE);
    	t.setFont(new Font(15));

		window = primaryStage; 
		
		Label label1 = new Label("WELCOME TO THE MATRIX CALCULATOR!");
		Label label2 = new Label("WHAT WOULD YOU LIKE TO DO TODAY?");
		Label label3 = new Label("PURPOSE");
		Label label4 = new Label("TUTORIALS");
		label1.setFont(new Font("Arial", 15));
		label3.setFont(Font.font("Arial", 15));
		//label1.setFill(c);
		Button enter = new Button("Enter");
		Button color = new Button("Color");
		Button description = new Button("Description");
		Button matrixRules = new Button("Matrix Rules and Functions");
		Button returnToFirstFromPg2 = new Button("Return to Home page.");
		Button returnToFirstFromDescr = new Button("Return to Home page.");
		Button tutorials = new Button("Tutorials");
		Button matrixCalculator = new Button("Matrix Calculator");
		Button timedLab = new Button("Time Lab");
		Button practice = new Button("Practice Problems Generator");
		Button addSubTut  = new Button("Lesson 1: Addition and Subtraction");
		Button multiplicationTut  = new Button("Lesson 2: Multiplication");
		Button determinantTut = new Button("Lesson 3: Basic Determinants");
		Button determinant2Tut = new Button("Lesson 4: Complex Determinants");
		Button returnToPrevious = new Button("Return to Previous");

		enter.setOnAction(e -> window.setScene(scene2));
		//color.setOnAction(e -> scene1.setBackground(new Background(new BackgroundFill(c))));
		description.setOnAction(e -> window.setScene(scene3));
		returnToFirstFromPg2.setOnAction(e -> primaryStage.setScene(scene1));
		returnToFirstFromDescr.setOnAction(e -> primaryStage.setScene(scene1));
		returnToPrevious.setOnAction(e -> primaryStage.setScene(scene2));
		tutorials.setOnAction(e -> primaryStage.setScene(scene4));
		matrixCalculator.setOnAction(e -> primaryStage.setScene(new Scene(matrixTable)));
		
		/*Stage where user is introduced to the product and shown around*/
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, enter, description, color);
		scene1 = new Scene(layout1, 450,450, c);

		/*Stage where user has option of using a calculator, creating their own problems, or playing games.*/
		VBox layout2 = new VBox(20);
		layout2.getChildren().addAll(label2, tutorials, matrixCalculator, timedLab, practice, returnToFirstFromPg2);
		scene2 = new Scene(layout2, 450, 450);
		//layout2.setAlignment(Pos.CENTER_RIGHT);
		
		// returnToFirst.setPrefSize(200, 20);
		// returnToFirst.setLayoutX(200);
		// returnToFirst.setLayoutY(100); 
		
		/*Stage for description box that describes the purposes and uses of the program.*/
		VBox layout3 = new VBox(20);
		layout3.getChildren().addAll(label3, returnToFirstFromDescr, t);
		scene3 = new Scene(layout3, 600, 0, c);

		/* Stage for tutorials buttons*/
		VBox layout4 = new VBox(20);
		layout4.getChildren().addAll(label4, addSubTut, multiplicationTut,
							determinantTut, determinant2Tut, returnToPrevious);
		scene4 = new Scene(layout4, 450, 450);

		// matrixA = new Entry[5][5];
  //	for (int col = 0; col < matrixA.length ; col++) {
  //           for (int row = 0; row < matrixA[0].length; row++) {
  //               GridPane box = new GridPane();
  //               VBox layout5 = new VBox(20);
  //               Button newMatrix = new Button("matrix");
  //               layout5.getChildren().add(newMatrix);
  //               box.setStyle("-fx-background-color: black, -fx-control-inner-background; " +
  //               			 "-fx-background-insets: 0, 2; -fx-padding: 2;");
  //                       TextField textField = new TextField("_");
  //                       textField.setStyle("-fx-pref-width: 5em;");
  //                       box.getChildren().add(textField);
  //               GridPane.setConstraints(box, col, row);
  //               matrixTable.getChildren().addAll(layout5, box);
  //           }
  //       }
		//int SIZE = 10;
        //int length = SIZE;
        //int width = SIZE;
		Random rand = new Random();
        
        int rows1 = rand.nextInt(10) + 1;
		int columns1 = rand.nextInt(10) + 1;
		int columns2 = rand.nextInt(10) + 1;

        GridPane root = new GridPane();    

        for(int y = 0; y < rows1; y++){
            for(int x = 0; x < columns1; x++){
                double entry =  rand.nextDouble() * 100;
                // Create a new TextField in each Iteration
                TextField index = new TextField();
                index.setPrefHeight(50);
                index.setPrefWidth(50);
                //index.setAlignment(Pos.CENTER);
                index.setEditable(false);
                index.setText("" + entry);

                // Iterate the Index using the loops
                root.setRowIndex(index,y);
                root.setColumnIndex(index,x);    
                root.getChildren().add(index);
            }
        }

        Scene matrixInput = new Scene(root, 500, 500);    
        window.setTitle("Random Binary Matrix (JavaFX)");
        //window.setScene(scene);
        //primaryStage.show();


		window.setScene(scene1);
		//window.setScene(new Scene(matrixTable));
		//window.setTitle("Matrix Calculator");
		window.show();
	}

	public void handle(ActionEvent multiplication) {
		if (multiplication.getSource() == button) {
			System.out.println("XXXXXXXX");
		}
	}

}
	
