import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

// =============================
// Controller Class (Controller)
// =============================
public class Controller {

    // import objects from fxml
    @FXML 
    private TextField tfName;
    @FXML 
    private TextField tfDesc;
    @FXML 
    private DatePicker tfDate;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private HBox cardsList;
    @FXML
    private Polygon myUpArrow;

    // create private variables
    private Scene scene;
    private static final ObservableList<Event> events = FXCollections.observableArrayList();

    // switch between scnees
    public void switchToAdd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventList.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        onBtnClick();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // add event info when button is clicked
    public void addToList() {
        // create new event
        Event newEvent = new Event(tfName.getText(), tfDesc.getText(), tfDate.getValue());
        events.add(newEvent);

        // transitions
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), myUpArrow);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(100);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), myUpArrow);
        fadeOut.setFromValue(100);
        fadeOut.setToValue(0);

        SequentialTransition sequence = new SequentialTransition(fadeIn, pause, fadeOut);

        sequence.play();
    }

    // create new card based on event info
    public void onBtnClick() {
        for (Event event : events) {
            // card (StackPane)
            StackPane eventCard = new StackPane();
            // linear gradient for card
            Stop[] stops = new Stop[]{
                new Stop(0, Color.LIGHTSKYBLUE),
                new Stop(1, Color.LIGHTGOLDENRODYELLOW)
            };
            LinearGradient linearGradient = new LinearGradient(0, 1, 0, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE, stops);
            // card outline
            Rectangle cardOutline = new Rectangle(100, 200);
            cardOutline.setFill(linearGradient);
            // card title
            Text cardTitle = new Text(event.getEventTitle());
            cardTitle.setWrappingWidth(100);
            cardTitle.setFont(new Font(20));
            StackPane.setAlignment(cardTitle, Pos.TOP_CENTER);
            // card desc
            Text cardDesc = new Text(event.getEventDesc());
            cardDesc.setWrappingWidth(100);
            StackPane.setAlignment(cardDesc, Pos.CENTER);
            // card date/time
            Label cardDate = new Label();
            cardDate.setText("" + event.getEventDate());
            StackPane.setAlignment(cardDate, Pos.BOTTOM_CENTER);
            // add all elements to card
            eventCard.getChildren().addAll(cardOutline, cardTitle, cardDesc, cardDate);
            cardsList.getChildren().add(eventCard);
        }
    }

}
