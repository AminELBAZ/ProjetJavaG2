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
        
        this.port = new TextArea();
        this.port.setLayoutX(280);
        this.port.setLayoutY(250);
        this.port.setPrefSize(60, 50);
        
        this.erreur = new Text("Numéro de port invalide, vérifiez votre saisie.");
        this.erreur.setLayoutX(150);
        this.erreur.setLayoutY(240);
        this.erreur.setFill(Color.RED);
        this.erreur.setVisible(false);
        
        this.inputPseudo= new TextArea();
        this.inputPseudo.setLayoutX(280);
        this.inputPseudo.setLayoutY(100);
        this.inputPseudo.setPrefSize(60, 50);

        this.valid = new Button("Valider");
        this.valid.setLayoutX(280);
        this.valid.setLayoutY(310);
        this.valid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                // Si la valeur saisie est un entier entre 1024 et 49151 on lance le serveur correspondant
                if (estUnEntierValide(port.getText())) {
                portStage.close();
                //Définition du tableau d'arguments
                String[] args = new String[1];
                args[0] = port.getText();
                MainServer.main(args);
                stage.show();
                    
                // Sinon on affiche un message d'erreur
                } else {
                    erreur.setVisible(true);
            }
            }
        });
        this.getChildren().add(this.erreur);
        this.getChildren().add(this.titre);
        this.getChildren().add(this.port);
        this.getChildren().add(this.valid);
        this.getChildren().add(this.pseudo);
        this.getChildren().add(this.inputPseudo);
    }

    /**
     * Fonction qui vérifie que la chaine passé est un entier compris entre 1024
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
