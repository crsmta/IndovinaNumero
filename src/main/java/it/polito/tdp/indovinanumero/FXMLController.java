package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.security.InvalidParameterException;

import it.polito.tdp.indovinanumero.model.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

	private Model model;

    @FXML
    void doNuova(ActionEvent event) {
    	
    	model.nuovaPartita();
    	//gestione dell'interfaccia
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(model.getTMAX()));

    }

    @FXML
    void doTentativo(ActionEvent event) throws Exception {
    	//leggere l'input dell'utente
    	String ts = txtTentativi.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		scrivi("Devi inserire un numero!");
    		return;
    	}
    	
    	int controllo=-1;
    	try {
    		 controllo=model.checkNumber(tentativo);
    	}catch(IllegalStateException se ){
    		scrivi(se.getMessage());
    		return;
    	
    	}catch (InvalidParameterException pe) {
    		scrivi(pe.getMessage());
    		return;
    		
    	}catch (TentativiEsauritiException te) {
    		scrivi("HAI PERSO!!! Il numero segreto era: " + model.getSegreto());
    		layoutTentativo.setDisable(true);
    		return;
    		
    	}catch(NumeroUsatoException nu) {
    		scrivi(nu.getMessage());
    		return;
    	}
    	//controllo il tentativo fatto
    	aggiornaRimanenti();
    	if(controllo==0) {
    		//HO INDOVINATO!
    		scrivi("HAI VINTO!!! Hai utilizzato " + model.getTeentativiFatti() + " tentativi!");
    		layoutTentativo.setDisable(true);
    	}
    	if(controllo==-1)
    		scrivi("Tentativo troppo BASSO ");
    	if (controllo==1)
    		scrivi("Tentativo troppo ALTO ");
    	
    	
    }
    void setModel(Model model) {
    	this.model = model;
    }
    
    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    private void scrivi(String string) {
    	txtRisultato.appendText(string+"\n");
    }
    private void aggiornaRimanenti() {
    	txtRimasti.setText(Integer.toString(this.model.getTMAX()-this.model.getTeentativiFatti()));
    }
}
