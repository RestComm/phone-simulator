/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ussd.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ussd.controllers.Impl;

/**
 *
 * @author joram
 */
public class UssdClient extends Application {

    public static Stage st;
    public static Impl impl;

    @Override
    public void start(Stage stage) throws Exception {
        impl = new Impl();
        st=stage;
        Parent root = FXMLLoader.load(getClass().getResource("/ussd/fxml/Host.fxml"));
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent event) -> {
            System.exit(0);
        });
        stage.setResizable(false);
        stage.show();
       
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
