package lk.ijse.gdse71.serenity_therapy;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.gdse71.serenity_therapy.config.FactoryConfiguration;
import lk.ijse.gdse71.serenity_therapy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoadingScreenView.fxml"))));
        stage.show();
        stage.centerOnScreen();

        Task<Scene> loadMainSceneTask = new Task<>() {
            @Override
            protected Scene call() throws Exception {
                // Load the main layout from FXML
                FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/Login.fxml"));
                return new Scene(fxmlLoader.load()); // Return the loaded scene
            }
        };

        loadMainSceneTask.setOnSucceeded(event -> {
            Scene value = loadMainSceneTask.getValue();

            stage.setTitle("Serenity Therapy");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
//            stage.setMaximized(true);

            // Switch to the main scene
            stage.setScene(value);
            stage.centerOnScreen();
        });

        loadMainSceneTask.setOnFailed(event -> {
            System.err.println("Failed to load the main layout."); // Print error message
        });

        new Thread(loadMainSceneTask).start();
    }

    public static void main(String[] args) {launch();}
}