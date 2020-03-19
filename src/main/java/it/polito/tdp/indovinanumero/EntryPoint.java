package it.polito.tdp.indovinanumero;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javax.management.modelmbean.ModelMBeanAttributeInfo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.polito.tdp.indovinanumero.model.*;;

public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	//set variables
    	Model model;
    	FXMLController controller;
    	FXMLLoader loader;
    	//inizializzo variabili
		//carico il loader con la scena root in questo caso perchè ho solo questa
		loader=new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
	    Parent root = loader.load();
        Scene scene = new Scene(root);
        model=new Model();
        // non ho un file css 
        //scene.getStylesheets().add("/styles/Styles.css");
        
        //setto il modello
        controller=loader.getController();
        controller.setModel(model);
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
