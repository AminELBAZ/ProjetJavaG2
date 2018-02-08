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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author aelbaz
 */
public class ClientPanel extends Parent {

    private TextFlow receivedText;
    private ScrollPane scrollReceivedText;
    private TextArea textToSend;
    private Button sendBtn;
    private Button clearBtn;
    private Button nuitBtn;
    private TextArea connected;
    private Text textMembers;
    private ListView<String> chatListView;
    private ListView<String> clientCoListView;
    private Pane pane;
    private int cpt = 0;

    public ClientPanel(Stage stage, Client client) {
        
        this.pane = new Pane();
        this.pane.setPrefSize(600, 500);
        
        this.receivedText = new TextFlow();
        this.receivedText.setPrefSize(400, 250);

        this.chatListView = new ListView<String>();
        this.chatListView.setItems(client.chatLog);
        this.chatListView.setPrefSize(400, 200);

        this.scrollReceivedText = new ScrollPane();
//        this.scrollReceivedText.setContent(this.receivedText);
        this.scrollReceivedText.setContent(this.chatListView);
        this.scrollReceivedText.vvalueProperty().bind(this.chatListView.heightProperty());
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
        
        this.nuitBtn = new Button("Mode Nuit");
        this.nuitBtn.setLayoutX(50);
        this.nuitBtn.setLayoutY(300);
        this.nuitBtn.setPrefSize(100, 30);
        
        this.nuitBtn.setOnAction(new EventHandler<ActionEvent>() {
         @Override
            public void handle(ActionEvent event) {
              if (cpt==0)
              {
                pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                cpt++;
                nuitBtn.setText("Mode jour");
              }
              else
                  if (cpt==1)
                  {
                      pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                      cpt--;
                      nuitBtn.setText("Mode nuit");
                  }
            }
        });
        
        Label buffer = new Label();
        this.sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                buffer.setText(buffer.getText() + " " + textToSend.getText() + "\n");
//                try {
//                    buffer.setText(buffer.getText() + " " + client.getIn().readLine() +"\n");
//                } catch (IOException ex) {
//                    Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
                client.chatLog.add(textToSend.getText());
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

        
//        this.chatListView = new ListView<String>();
//        this.chatListView.setItems();
//        this.chatListView.setPrefSize(100, 250);
//        this.chatListView.setLayoutX(470);
//        this.chatListView.setLayoutY(50);
        
//        this.connected = new TextArea();
//        this.connected.setLayoutX(470);
//        this.connected.setLayoutY(50);
//        this.connected.setPrefSize(100, 250);
//        this.connected.setEditable(false);

//        stage.setOnShowing(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                connected.setText(client.getLogin());
//            }
//        });
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
//       receivedText.getChildren().add(chatListView);
//        receivedText.getChildren().add(buffer);
       pane.getChildren().addAll(receivedText,scrollReceivedText,textToSend,nuitBtn,sendBtn,clearBtn,chatListView,textMembers);
       this.getChildren().add(pane);
//        this.getChildren().add(scrollReceivedText);
//        this.getChildren().add(textToSend);
//        this.getChildren().add(sendBtn);
//        this.getChildren().add(clearBtn);
//        this.getChildren().add(connected);
//        this.getChildren().add(chatListView);
//        this.getChildren().add(textMembers);

    }

}
