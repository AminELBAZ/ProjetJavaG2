/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Panel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import server.*;
import client.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author aelbaz
 */
public class ClientPanel extends Parent {

    static Server server = null;

    private TextFlow receivedText;
    private ScrollPane scrollReceivedText;
    private TextArea textToSend;
    private Button sendBtn;
    private Button clearBtn;
    private TextArea connected;
    private Text textMembers;
    private ListView<String> chatListView;

    public ClientPanel(Stage stage, Client client) {

        this.receivedText = new TextFlow();
        this.receivedText.setPrefSize(400, 250);
        
        this.chatListView = new ListView<String>();
	this.chatListView.setItems(client.chatLog);
        this.chatListView.setPrefSize(300, 200);
        
        this.scrollReceivedText = new ScrollPane();
        this.scrollReceivedText.setContent(this.receivedText);
        this.scrollReceivedText.vvalueProperty().bind(this.receivedText.heightProperty());
        this.scrollReceivedText.setLayoutX(50);
        this.scrollReceivedText.setLayoutY(50);

        this.textToSend = new TextArea();
        this.textToSend.setLayoutX(50);
        this.textToSend.setLayoutY(350);
        this.textToSend.setPrefSize(400, 100);

        this.sendBtn = new Button("Envoyer");
        this.sendBtn.setLayoutX(470);
        this.sendBtn.setLayoutY(350);
        this.sendBtn.setPrefSize(100, 30);
        Label buffer = new Label();
        this.sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                buffer.setText(buffer.getText() + " " + textToSend.getText()+"\n");
//                try {
//                    buffer.setText(buffer.getText() + " " + client.getIn().readLine() +"\n");
//                } catch (IOException ex) {
//                    Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
                
                
                
                client.getOut().println(textToSend.getText());
                client.getOut().flush();
                
                textToSend.clear();
            }
        });

        this.clearBtn = new Button("Effacer");
        this.clearBtn.setLayoutX(470);
        this.clearBtn.setLayoutY(390);
        this.clearBtn.setPrefSize(100, 30);
        this.clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textToSend.clear();
            }
        });

        this.connected = new TextArea();
        this.connected.setLayoutX(470);
        this.connected.setLayoutY(50);
        this.connected.setPrefSize(100, 250);
        this.connected.setEditable(false);

        stage.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                connected.setText(client.getLogin());
            }
        });
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        this.textMembers = new Text("Connectés : ");
        this.textMembers.setLayoutX(470);
        this.textMembers.setLayoutY(40);
        
//        this.getChildren().add(chatListView);
        receivedText.getChildren().add(chatListView);
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);
        this.getChildren().add(sendBtn);
        this.getChildren().add(clearBtn);
        this.getChildren().add(connected);
        this.getChildren().add(textMembers);
        receivedText.getChildren().add(buffer);
    }

}
