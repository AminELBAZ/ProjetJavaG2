/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageriefx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import server.*;
import client.*;

/**
 *
 * @author aelbaz
 */
public class MessagerieFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Stage portStage = new Stage();

        ServerPanel serverPanel = new ServerPanel(portStage, stage);
        Group portServer = new Group();
        portServer.getChildren().add(serverPanel);
        Scene scenePort = new Scene(portServer, 600, 500);
        portStage.setTitle("Connexion");
        portStage.setScene(scenePort);
        portStage.show();
        
        Server server = MainServer.getInstance();
        if(server != null)
            System.out.println("messageriefx.MessagerieFX.start()");
        
        ClientPanel clientPanel = new ClientPanel(stage);
        Group root = new Group();
        root.getChildren().add(clientPanel);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Mon Chat");
        stage.setScene(scene);
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
