/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.ClientPanel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author aelbaz
 */
public class MessagerieFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        ServerPanel serverPanel = new ServerPanel(stage);
        Group portServer = new Group();
        portServer.getChildren().add(serverPanel);
        Scene scenePort = new Scene(portServer, 600, 500);
        stage.setTitle("Connexion");
        stage.setScene(scenePort);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
