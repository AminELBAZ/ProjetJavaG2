/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageriefx;

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

    public ServerPanel(Stage portStage, Stage stage) {
        this.titre = new Text("Veuillez saisir un numéro de port entre 1024 et 49151 :");
        this.titre.setLayoutX(150);
        this.titre.setLayoutY(200);

        this.pseudo = new Text("Veuillez saisir un Pseudo:");
        this.pseudo.setLayoutX(150);
        this.pseudo.setLayoutY(50);

        this.inputPseudo = new TextArea();
        this.inputPseudo.setLayoutX(280);
        this.inputPseudo.setLayoutY(100);
        this.inputPseudo.setPrefSize(60, 50);

        
        this.port = new TextArea();
        this.port.setLayoutX(280);
        this.port.setLayoutY(250);
        this.port.setPrefSize(60, 50);
        
        this.erreur = new Text("Numéro de port invalide, vérifiez votre saisie.");
        this.erreur.setLayoutX(150);
        this.erreur.setLayoutY(240);
        this.erreur.setFill(Color.RED);
        this.erreur.setVisible(false);
        
        this.pseudo= new Text("Votre pseudo : ");
        this.pseudo.setLayoutX(280);
        this.pseudo.setLayoutY(50);
   
       
        this.inputPseudo= new TextArea();
        this.inputPseudo.setLayoutX(280);
        this.inputPseudo.setLayoutY(100);
        this.inputPseudo.setPrefSize(60, 50);

        this.erreur = new Text("Numéro de port invalide, vérifiez votre saisie.");
        this.erreur.setLayoutX(150);
        this.erreur.setLayoutY(240);
        this.erreur.setFill(Color.RED);
        this.erreur.setVisible(false);

        this.valid = new Button("Valider");
        this.valid.setLayoutX(280);
        this.valid.setLayoutY(310);
        this.valid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Server server = MainServer.getInstance();
                String adresseServer = "127.0.0.1";
                // Si il n'y a pas d'instance du serveur
                if (server == null) {
                    // Si la valeur saisie est un entier entre 1024 et 49151 on lance le serveur correspondant
                    if (estUnEntierValide(port.getText())) {
                        portStage.close();
                        //Définition du tableau d'arguments server
                        String[] args = new String[1];
                        args[0] = port.getText();
                        MainServer.main(args);
                        System.err.println("server ok");
                        server = MainServer.getInstance();

                        //Définition du tableau d'arguments client
                        String[] argsClient = new String[3];
                        argsClient[0] = adresseServer;
                        argsClient[1] = port.getText();
                        argsClient[2] = inputPseudo.getText();
                        //Lancement du client
                        MainClient.main(argsClient);
                        System.err.println("client ok");
                        stage.show();
                        // Sinon on affiche un message d'erreur
                    } else {
                        erreur.setVisible(true);
                    }
                } else {
                    System.err.println("not ok");
                    //Définition du tableau d'arguments
                    String[] args = new String[3];
                    args[0] = adresseServer;
                    args[1] = port.getText();
                    args[2] = inputPseudo.getText();
                    //Lancement du client
                    MainClient.main(args);

                    stage.show();

                }
            }
        }
        );
        this.getChildren().add(this.erreur);
        this.getChildren().add(this.pseudo);
        this.getChildren().add(this.titre);
        this.getChildren().add(this.port);
        this.getChildren().add(this.valid);
        this.getChildren().add(this.inputPseudo);
    }

    /**
     * Fonction qui vérifie que la chaine passée est un entier compris entre 1024
     * et 49151
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
