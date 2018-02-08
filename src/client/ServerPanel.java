/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import server.*;
import client.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Scene;

/**
 *
 * @author aelbaz
 */
public class ServerPanel extends Parent {

    private Text titre;
    private Text erreur;
    private TextArea port;
    private Button valid;
    private Text pseudo;
    private TextArea inputPseudo;
    private Text address;
    private TextArea inputAddress;

    public ServerPanel(Stage stage) {
        // PSEUDO //
        this.pseudo = new Text("Votre pseudo : ");
        this.pseudo.setLayoutX(100);
        this.pseudo.setLayoutY(50);

        this.inputPseudo = new TextArea();
        this.inputPseudo.setLayoutX(300);
        this.inputPseudo.setLayoutY(25);
        this.inputPseudo.setPrefSize(100, 50);

        // PORT //
        this.titre = new Text("N° du port (1024-49151) :");
        this.titre.setLayoutX(100);
        this.titre.setLayoutY(150);

        this.port = new TextArea();
        this.port.setLayoutX(300);
        this.port.setLayoutY(125);
        this.port.setPrefSize(100, 50);

        this.erreur = new Text("Numéro de port invalide, vérifiez votre saisie.");
        this.erreur.setLayoutX(100);
        this.erreur.setLayoutY(110);
        this.erreur.setFill(Color.RED);
        this.erreur.setVisible(false);

        // SERVEUR //
        this.address = new Text("Adresse du serveur :");
        this.address.setLayoutX(100);
        this.address.setLayoutY(250);

        this.inputAddress = new TextArea();
        this.inputAddress.setLayoutX(300);
        this.inputAddress.setLayoutY(225);
        this.inputAddress.setPrefSize(100, 50);

        this.valid = new Button("Valider");
        this.valid.setLayoutX(280);
        this.valid.setLayoutY(310);
        this.valid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String adresseServer = inputAddress.getText();
                // Si la valeur saisie est un entier entre 1024 et 49151 on lance le serveur correspondant
                if (estUnEntierValide(port.getText())) {
                    if (inputPseudo.getText().equals("")) {
                        erreur.setText("Veuillez saisir un pseudo !");
                        erreur.setLayoutX(100);
                        erreur.setLayoutY(25);
                        erreur.setVisible(true);
                        return;
                    }
                    if (adresseServer.equals("")) {
                        erreur.setText("Veuillez saisir l'adresse du serveur !");
                        erreur.setLayoutX(100);
                        erreur.setLayoutY(215);
                        erreur.setVisible(true);
                        return;
                    }
                    stage.close();
                    //Création du client
                    Client c = null;
                    try {
                        c = new Client(adresseServer, Integer.parseInt(port.getText()), inputPseudo.getText());
                    } catch (IOException ex) {
                        Logger.getLogger(ServerPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Création du stage
                    Stage clientStage = new Stage();
                    ClientPanel clientPanel = new ClientPanel(clientStage, c);
                    Group root = new Group();
                    root.getChildren().add(clientPanel);
                    Scene scene = new Scene(root, 600, 500);
                    clientStage.setTitle("Mon Chat");
                    clientStage.setScene(scene);
                    clientStage.show();
                    // Sinon on affiche un message d'erreur
                } else {
                    erreur.setVisible(true);
                }
            }
        }
        );
        this.getChildren().add(this.inputPseudo);
        this.getChildren().add(this.pseudo);
        this.getChildren().add(this.titre);
        this.getChildren().add(this.port);
        this.getChildren().add(this.erreur);
        this.getChildren().add(this.address);
        this.getChildren().add(this.inputAddress);
        this.getChildren().add(this.valid);
    }

    /**
     * Fonction qui vérifie que la chaine passée est un entier compris entre
     * 1024 et 49151
     *
     * @param chaine
     * @return boolean
     */
    public boolean estUnEntierValide(String chaine) {
        int test;
        try {
            test = Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            return false;
        }
        if (!(test >= 1024 && test <= 49151)) {
            return false;
        }

        return true;
    }

}
