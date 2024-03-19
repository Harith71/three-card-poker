import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;

public class JavaFXTemplate extends Application {

    private Player playerOne;
    private Player playerTwo;
    private Dealer theDealer;
	private Font f = Font.font("Verdana", FontWeight.BOLD, 20);
	private Font f2 = Font.font("Verdana", FontWeight.BOLD, 14);
	private Font f3 = Font.font("Verdana",12);
	private Font f4 = Font.font("Comic Sans MS", FontWeight.BOLD, 20);
	private Font f5 = Font.font("Comic Sans MS", FontWeight.BOLD, 14);
	PauseTransition pause = new PauseTransition(Duration.seconds(2));
	MenuBar mb;
	Menu menu;
	MenuItem menuItem1;
	MenuItem menuItem2;
	MenuItem menuItem3;
	Label p1L, p1ABL, p1PPBL, p1PBL, p2L, p2ABL, p2PPBL, p2PBL, p2CWL, p1CWL, info1, info2;
	TextField p1PPBTF, p1PBTF, p1ABTF, p2PPBTF, p2PBTF, p2ABTF;
	Button p1PBB, p1PB, p1FB, p2PBB, p2PB, p2FB, nR;
	VBox p1VB, p2VB;
	ImageView c1, c2, c3, c4, c5, c6, d1, d2, d3;
	Pane r;
	Rectangle disBox;



	private boolean p1BetPlaced = false;
	private boolean p2BetPlaced = false;
	private boolean p1Fold = false;
	private boolean p2Fold = false;
	private boolean p1R = false;
	private boolean p2R = false;
	private int sc = 1;
	boolean setup = false;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}



	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();

		primaryStage.setTitle("Harith Patel Project 2");

		initializeGUIItems();
		setLayoutPositions();
		setGUIItemSettings();
		addItemsToScene();

		p1PBB.setOnAction(e-> {
			info1.setText("");
			int bet = Integer.parseInt(p1ABTF.getText());
			int ppBet = 0;
			if(bet < 5 || bet > 25) {
				info1.setText("Player 1 ante bet not between $5 and $25");
				return;
			}

			if(p1PPBTF.getText() != "") {
				ppBet = Integer.parseInt(p1PPBTF.getText());
				if(ppBet < 5 || ppBet > 25) {
					info1.setText("Player 1 pair+ bet not between $5 and $25 \nor 0$");
					return;
				}
			}

			playerOne.anteBet = bet;
			playerOne.pairPlusBet = ppBet;
			p1BetPlaced = true;
			p1PBB.setDisable(true);
			if(p1BetPlaced && p2BetPlaced) {
				displayCards();
			}
		});

		p2PBB.setOnAction(e-> {
			info1.setText("");
			int bet = Integer.parseInt(p2ABTF.getText());
			int ppBet = 0;
			if(bet < 5 || bet > 25) {
				info1.setText("Player 2 ante bet not between $5 and $25");
				return;
			}

			if(p2PPBTF.getText() != "") {
				ppBet = Integer.parseInt(p2PPBTF.getText());
				if(ppBet < 5 || ppBet > 25) {
					info1.setText("Player 2 pair+ bet not between $5 and $25\nor $0");
					return;
				}
			}

			playerTwo.anteBet = bet;
			playerTwo.pairPlusBet = ppBet;
			p2BetPlaced = true;
			p2PBB.setDisable(true);
			if(p1BetPlaced && p2BetPlaced) {
				displayCards();
			}
		});

		p1FB.setOnAction(e -> {
			playerOne.totalWinnings = playerOne.totalWinnings - (playerOne.anteBet + playerOne.pairPlusBet);
			playerOne.anteBet = 0;
			playerOne.pairPlusBet = 0;
			p1Fold = true;
			p1R = true;
			p1FB.setDisable(true);
			p1PB.setDisable(true);
			if(p1R && p2R) {
				disDealerCards();
			}
		});

		p2FB.setOnAction(e -> {
			playerTwo.totalWinnings = playerTwo.totalWinnings - (playerTwo.anteBet + playerTwo.pairPlusBet);
			playerTwo.pairPlusBet = 0;
			playerTwo.anteBet = 0;
			p2Fold = true;
			p2R = true;
			p2FB.setDisable(true);
			p2PB.setDisable(true);
			if(p1R && p2R) {
				disDealerCards();
			}
		});

		p1PB.setOnAction(e -> {
			info1.setText("");
			int playBet = Integer.parseInt(p1PBTF.getText());
			if(playBet != playerOne.anteBet) {
				info1.setText("Player 1 play bet is not equal to\nante bet");
				return;
			}
			playerOne.playBet = playBet;
			p1R = true;
			p1FB.setDisable(true);
			p1PB.setDisable(true);
			if(p1R && p2R) {
				disDealerCards();
			}
		});

		p2PB.setOnAction(e -> {
			info1.setText("");
			int playBet = Integer.parseInt(p2PBTF.getText());
			if(playBet != playerTwo.anteBet) {
				info1.setText("Player 2 play bet is not equal to\nante bet");
				return;
			}
			playerTwo.playBet = playBet;
			p2R = true;
			p2FB.setDisable(true);
			p2PB.setDisable(true);
			if(p1R && p2R) {
				disDealerCards();
			}
		});

		nR.setOnAction(e -> {
			playerOne.anteBet = 0;
			playerTwo.anteBet = 0;
			info1.setText("");
			info2.setText("");
			p1PBB.setDisable(false);
			p2PBB.setDisable(false);
			nR.setDisable(true);
			c1.setImage(null);
			c2.setImage(null);
			c3.setImage(null);
			c4.setImage(null);
			c5.setImage(null);
			c6.setImage(null);
			d1.setImage(null);
			d2.setImage(null);
			d3.setImage(null);
			p1BetPlaced = false;
			p2BetPlaced = false;
			p1Fold = false;
			p2Fold = false;
			p1R = false;
			p2R = false;
			p1PBTF.setDisable(true);
			p2PBTF.setDisable(true);
		});

		menuItem1.setOnAction(e ->{
			Platform.exit();
		});

		menuItem2.setOnAction(e ->{
			playerOne.anteBet = 0;
			playerTwo.anteBet = 0;
			info1.setText("");
			info2.setText("");
			p1PBB.setDisable(false);
			p2PBB.setDisable(false);
			nR.setDisable(true);
			c1.setImage(null);
			c2.setImage(null);
			c3.setImage(null);
			c4.setImage(null);
			c5.setImage(null);
			c6.setImage(null);
			d1.setImage(null);
			d2.setImage(null);
			d3.setImage(null);
			p1BetPlaced = false;
			p2BetPlaced = false;
			p1Fold = false;
			p2Fold = false;
			p1R = false;
			p2R = false;
			p1PBTF.setDisable(true);
			p2PBTF.setDisable(true);
			playerOne = new Player();
			playerTwo = new Player();
			theDealer = new Dealer();
			p1CWL.setText("Current Winnings: $0");
			p2CWL.setText("Current Winnings: $0");
		});

		menuItem3.setOnAction(e->{
			if(sc == 2) {
				setGUIItemSettings();
				sc = 1;
				return;
			}
			sc = 2;
			r.setBackground(new Background((new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY))));
			p1PBB.setStyle("-fx-background-color: #FA8128;-fx-text-fill: black;");
			p2PBB.setStyle("-fx-background-color: #FA8128;-fx-text-fill: black;");
			p1FB.setStyle("-fx-background-color: #FA8128;-fx-text-fill: black;");
			p2FB.setStyle("-fx-background-color: #FA8128;-fx-text-fill: black;");
			p1PB.setStyle("-fx-background-color: #FA8128;-fx-text-fill: black;");
			p2PB.setStyle("-fx-background-color: #FA8128;-fx-text-fill: black;");
			nR.setStyle("-fx-background-color: red;-fx-text-fill: black");
			p1CWL.setStyle("-fx-text-fill: Black;");
			p2CWL.setStyle("-fx-text-fill: Black;");
			p1L.setFont(f4);
			p2L.setFont(f4);
			p1ABL.setFont(f5);
			p2ABL.setFont(f5);
			p1PPBL.setFont(f5);
			p2PPBL.setFont(f5);
			p1PBL.setFont(f5);
			p2PBL.setFont(f5);
		});


		Scene scene = new Scene(r, 1000,1000, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initializeGUIItems() {
		nR = new Button("Next Round");
		info1 = new Label();
		info2 = new Label();
		disBox = new Rectangle(325, 100, 350, 100);
		r = new Pane();
		mb = new MenuBar();
		menu = new Menu("Options");
		menuItem1 = new MenuItem("Exit");
		menuItem2 = new MenuItem("Fresh Start");
		menuItem3 = new MenuItem("New Look");
		p1L = new Label("Player 1");
		p1ABL = new Label("Ante Bet");
		p1ABTF = new TextField();
		p1PPBL = new Label("Pair Plus Bet");
		p1PPBTF = new TextField();
		p1PBL = new Label("Play Bet");
		p1PBTF = new TextField();
		p1PBB = new Button("Place Bet");
		p1PB = new Button("Play");
		p1FB = new Button("Fold");
		p1VB = new VBox(20, p1L, p1ABL, p1ABTF, p1PPBL, p1PPBTF, p1PBL, p1PBTF, p1PBB, p1PB, p1FB);
		p2L = new Label("Player 2");
		p2ABL = new Label("Ante Bet");
		p2ABTF = new TextField();
		p2PPBL = new Label("Pair Plus Bet");
		p2PPBTF = new TextField();
		p2PBL = new Label("Play Bet");
		p2PBTF = new TextField();
		p2PBB = new Button("Place Bet");
		p2PB = new Button("Play");
		p2FB = new Button("Fold");
		p2VB = new VBox(20, p2L, p2ABL, p2ABTF, p2PPBL, p2PPBTF, p2PBL, p2PBTF, p2PBB, p2PB, p2FB);
		c1 = new ImageView();
		c2 = new ImageView();
		c3 = new ImageView();
		c4 = new ImageView();
		c5 = new ImageView();
		c6 = new ImageView();
		d1 = new ImageView();
		d2 = new ImageView();
		d3 = new ImageView();
		p1CWL = new Label("Current Winnings: $0");
		p2CWL = new Label("Current Winnings: $0");
	}

	private void setLayoutPositions() {
		nR.setLayoutX(445);
		nR.setLayoutY(450);
		info1.setLayoutX(330);
		info1.setLayoutY(100);
		info2.setLayoutX(330);
		info2.setLayoutY(160);
		p1VB.setLayoutX(10);
		p1VB.setLayoutY(30);
		p2VB.setLayoutX(830);
		p2VB.setLayoutY(30);
		c6.setLayoutX(680);
		c6.setLayoutY(500);
		c5.setLayoutX(785);
		c5.setLayoutY(500);
		c4.setLayoutX(890);
		c4.setLayoutY(500);
		c3.setLayoutX(220);
		c3.setLayoutY(500);
		c2.setLayoutX(115);
		c2.setLayoutY(500);
		c1.setLayoutX(10);
		c1.setLayoutY(500);
		d1.setLayoutX(340);
		d1.setLayoutY(250);
		d2.setLayoutX(450);
		d2.setLayoutY(250);
		d3.setLayoutX(560);
		d3.setLayoutY(250);
		mb.setLayoutX(0);
		mb.setLayoutY(0);
		p2CWL.setLayoutX(700);
		p2CWL.setLayoutY(700);
		p1CWL.setLayoutX(10);
		p1CWL.setLayoutY(700);
	}

	private void setGUIItemSettings() {
		nR.setFont(f2);
		nR.setDisable(true);
		p1PBTF.setDisable(true);
		p2PBTF.setDisable(true);
		p1PB.setDisable(true);
		p1FB.setDisable(true);
		p2PB.setDisable(true);
		p2FB.setDisable(true);
		info1.setFont(f2);
		info2.setFont(f2);
		info1.setStyle("-fx-text-fill: Black;");
		info2.setStyle("-fx-text-fill: Black;");
		disBox.setFill(Color.WHITE);
		r.setBackground(new Background((new BackgroundFill(Color.rgb(53,53,53), CornerRadii.EMPTY, Insets.EMPTY))));
		mb.setStyle("-fx-background-color: #FA8128;-fx-text-fill: white;");
		if(setup == false) {
			menu.getItems().add(menuItem1);
			menu.getItems().add(menuItem2);
			menu.getItems().add(menuItem3);
			mb.getMenus().add(menu);
			setup = true;
		}
		p1L.setFont(f);
		p1L.setStyle("-fx-text-fill: white;");
		p1ABL.setFont(f2);
		p1ABL.setStyle("-fx-text-fill: white;");
		p1ABTF.setFont(f3);
		p1PPBL.setFont(f2);
		p1PPBL.setStyle("-fx-text-fill: white;");
		p1PPBTF.setFont(f3);
		p1PBL.setFont(f2);
		p1PBL.setStyle("-fx-text-fill: white;");
		p1PBTF.setFont(f3);
		p1PBB.setFont(f2);
		p1PB.setFont(f2);
		p1FB.setFont(f2);
		p1VB.setAlignment(Pos.CENTER);
		p2L.setFont(f);
		p2L.setStyle("-fx-text-fill: white;");
		p2ABL.setFont(f2);
		p2ABL.setStyle("-fx-text-fill: white;");
		p2ABTF.setFont(f3);
		p2PPBL.setFont(f2);
		p2PPBL.setStyle("-fx-text-fill: white;");
		p2PPBTF.setFont(f3);
		p2PBL.setFont(f2);
		p2PBL.setStyle("-fx-text-fill: white;");
		p2PBTF.setFont(f3);
		p2PBB.setFont(f2);
		p2PB.setFont(f2);
		p2FB.setFont(f2);
		p2VB.setAlignment(Pos.CENTER);
		p2FB.setFont(f2);
		p1CWL.setFont(f);
		p1CWL.setStyle("-fx-text-fill: white;");
		p2CWL.setFont(f);
		p2CWL.setStyle("-fx-text-fill: white;");
		c1.setFitWidth(100);
		c1.setPreserveRatio(true);
		c1.setSmooth(true);
		c1.setCache(true);
		c2.setFitWidth(100);
		c2.setPreserveRatio(true);
		c2.setSmooth(true);
		c2.setCache(true);
		c3.setFitWidth(100);
		c3.setPreserveRatio(true);
		c3.setSmooth(true);
		c3.setCache(true);
		c4.setFitWidth(100);
		c4.setPreserveRatio(true);
		c4.setSmooth(true);
		c4.setCache(true);
		c5.setFitWidth(100);
		c5.setPreserveRatio(true);
		c5.setSmooth(true);
		c5.setCache(true);
		c6.setFitWidth(100);
		c6.setPreserveRatio(true);
		c6.setSmooth(true);
		c6.setCache(true);
		d1.setFitWidth(100);
		d1.setPreserveRatio(true);
		d1.setSmooth(true);
		d1.setCache(true);
		d2.setFitWidth(100);
		d2.setPreserveRatio(true);
		d2.setSmooth(true);
		d2.setCache(true);
		d3.setFitWidth(100);
		d3.setPreserveRatio(true);
		d3.setSmooth(true);
		d3.setCache(true);
	}

	private void addItemsToScene() {
		r.getChildren().add(nR);
		r.getChildren().add(d1);
		r.getChildren().add(d2);
		r.getChildren().add(d3);
		r.getChildren().add(disBox);
		r.getChildren().add(info1);
		r.getChildren().add(info2);
		r.getChildren().add(mb);
		r.getChildren().add(p1VB);
		r.getChildren().add(p2VB);
		r.getChildren().add(p1CWL);
		r.getChildren().add(p2CWL);
		r.getChildren().add(c1);
		r.getChildren().add(c2);
		r.getChildren().add(c3);
		r.getChildren().add(c4);
		r.getChildren().add(c5);
		r.getChildren().add(c6);
	}

	private void displayCards () {
		playerOne.hand = theDealer.dealHand();
		playerTwo.hand = theDealer.dealHand();
		String p1c1 = Integer.toString(playerOne.hand.get(0).getValue()) + playerOne.hand.get(0).getSuit();
		String p1c2 = Integer.toString(playerOne.hand.get(1).getValue()) + playerOne.hand.get(1).getSuit();
		String p1c3 = Integer.toString(playerOne.hand.get(2).getValue()) + playerOne.hand.get(2).getSuit();
		String p2c1 = Integer.toString(playerTwo.hand.get(0).getValue()) + playerTwo.hand.get(0).getSuit();
		String p2c2 = Integer.toString(playerTwo.hand.get(1).getValue()) + playerTwo.hand.get(1).getSuit();
		String p2c3 = Integer.toString(playerTwo.hand.get(2).getValue()) + playerTwo.hand.get(2).getSuit();
		Image one = new Image("Cards/" + p1c1 + ".png");
		Image two = new Image("Cards/" + p1c2 + ".png");
		Image three = new Image("Cards/" + p1c3 + ".png");
		Image four = new Image("Cards/" + p2c1 + ".png");
		Image five = new Image("Cards/" + p2c2 + ".png");
		Image six = new Image("Cards/" + p2c3 + ".png");

		c1.setImage(one);
		c4.setImage(four);
		c2.setImage(two);
		c5.setImage(five);
		c3.setImage(three);
		c6.setImage(six);


		p1PB.setDisable(false);
		p1FB.setDisable(false);
		p2PB.setDisable(false);
		p2FB.setDisable(false);
		p1PBTF.setDisable(false);
		p2PBTF.setDisable(false);
	}

	private void disDealerCards() {
		theDealer.dealersHand = theDealer.dealHand();
		String p1c1 = Integer.toString(theDealer.dealersHand.get(0).getValue()) + theDealer.dealersHand.get(0).getSuit();
		String p1c2 = Integer.toString(theDealer.dealersHand.get(1).getValue()) + theDealer.dealersHand.get(1).getSuit();
		String p1c3 = Integer.toString(theDealer.dealersHand.get(2).getValue()) + theDealer.dealersHand.get(2).getSuit();
		Image one = new Image("Cards/" + p1c1 + ".png");
		Image two = new Image("Cards/" + p1c2 + ".png");
		Image three = new Image("Cards/" + p1c3 + ".png");
		d1.setImage(one);
		d2.setImage(two);
		d3.setImage(three);

		getWinners();
	}

	private void getWinners() {
		int highest = theDealer.dealersHand.get(0).getValue();
		for(int i = 1; i < 3; i++) {
			if(theDealer.dealersHand.get(i).getValue() > highest) {
				highest = theDealer.dealersHand.get(0).getValue();
			}
		}
		if(highest < 12) {
			noHighCard();
			return;
		}
		if(p1Fold == false) checkP1Win();
		else info1.setText("Player 1 folded");
		if(p2Fold == false) checkP2Win();
		else info2.setText("Player 2 folded");

		p1CWL.setText("Current Winnings: $" + playerOne.totalWinnings);
		p2CWL.setText("Current Winnings: $" + playerTwo.totalWinnings);
		nR.setDisable(false);
	}

	private void checkP1Win() {
		int winner = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		int winnings = 0;
		if(winner == 2) {
			info1.setText("Player 1 won");
			winnings = playerOne.anteBet * 4;
		} else if (winner == 1) {
			playerOne.totalWinnings = playerOne.totalWinnings - (playerOne.anteBet * 2);
			info1.setText("Player 1 lost");
		} else {
			info1.setText("Player 1 drew");
		}

		if (playerOne.pairPlusBet != 0) {
			int ppWin = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);
			winnings += ppWin;
			if(ppWin == 0) {
				playerOne.totalWinnings -= playerOne.pairPlusBet;
			}
		}
		playerOne.totalWinnings += winnings;
	}

	private void checkP2Win() {
		int winner = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);
		int winnings = 0;
		if(winner == 2) {
			info2.setText("Player 2 won");
			winnings = playerTwo.anteBet * 4;
		} else if (winner == 1) {
			playerTwo.totalWinnings = playerTwo.totalWinnings - (playerTwo.anteBet * 2);
			info2.setText("Player 2 lost");
		} else {
			info1.setText("Player 2 drew");
		}


		if (playerTwo.pairPlusBet != 0) {
			int ppWin = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);
			winnings += ppWin;
			if(ppWin == 0) {
				playerTwo.totalWinnings -= playerTwo.pairPlusBet;
			}
		}
		playerTwo.totalWinnings += winnings;
	}

	private void noHighCard() {
		info1.setText("Dealers high card is less than a Q");
		disDealerCards();
	}
}
