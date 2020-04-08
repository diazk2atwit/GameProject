package racing_game;


import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Game");
		
		//Shapes
		Line line1 = new Line(300, 0, 300, 800);
		line1.setStrokeWidth(3);
		Line line2 = new Line(600, 0, 600, 800);
		line2.setStrokeWidth(3);
		Line line3 = new Line(150, 0, 150, 800);
		line3.getStrokeDashArray().addAll(25d);
		Line line4 = new Line(450, 0, 450, 800);
		line4.getStrokeDashArray().addAll(25d);
		Line line5 = new Line(750, 0, 750, 800);
		line5.getStrokeDashArray().addAll(25d);
		
		Rectangle enemy = new Rectangle(50, 50, Color.BLUE);
		enemy.setLayoutX(425);
		enemy.setLayoutY(100);
		
		Circle player = new Circle(25, Color.RED);
		player.setLayoutX(450);
		player.setLayoutY(600);
		
		//Player Movement
		TranslateTransition transitionP = new TranslateTransition();
		transitionP.setDuration(Duration.seconds(1));
		transitionP.setNode(player);
		
		player.setOnKeyPressed(new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.UP) {
			player.setLayoutY(player.getLayoutY()-10);
		} else if (event.getCode() == KeyCode.DOWN) {
			player.setLayoutY(player.getLayoutY()+10);
		} else if (event.getCode() == KeyCode.LEFT) {
			if(player.getLayoutX() != 150) {
				transitionP.setToX(-300);
				transitionP.play();
			}else if(player.getLayoutX() == 150) {
				transitionP.setToX(0);
				transitionP.play();
			}
		} else if (event.getCode() == KeyCode.RIGHT) {
			if(player.getLayoutX() != 750) {
				transitionP.setToX(300);
				transitionP.play();
			}else if(player.getLayoutX() == 750) {
				transitionP.setToX(0);
				transitionP.play();
			}
		}
		}
		});
		
		//Enemy Movement
		TranslateTransition transitionE = new TranslateTransition();
		transitionE.setDuration(Duration.seconds(3));
		transitionE.setToX(0);
		transitionE.setToY(600);
		transitionE.setNode(enemy);
		transitionE.play();
		
		//Labels
		
		//Buttons
		Button btdark = new Button("Dark Mode");
		Button btlight = new Button("Light Mode");
		
		//Layouts
		Pane root = new Pane();
		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(900, 800);
		
		holder.getChildren().add(canvas);
		holder.setStyle("-fx-background-color:darkgray");
		
		//Buttons change color of canvas and shapes
		btdark.setOnAction(e -> {
			holder.setStyle("-fx-background-color:black");
			player.setStroke(Color.GRAY);
			player.setFill(Color.WHITE);
			});
		btdark.setOnAction(e -> {
			holder.setStyle("-fx-background-color:white");
			player.setStroke(Color.BLACK);
			player.setFill(Color.GRAY);
			});
		
		HBox hBox = new HBox();
		//hBox.getChildren().addAll(btdark, btlight);
		root.getChildren().addAll(holder, line1, line2, line3, line4, line5, player, enemy);
		
		//Organize all here
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(root);
		//borderPane.setTop(hBox);
		
		//Show
		Scene scene = new Scene(borderPane, 900, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		player.requestFocus();

}
	
	public static void main(String[] args) {
		launch(args);
	}
}
