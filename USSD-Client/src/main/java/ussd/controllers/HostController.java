/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ussd.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.mobicents.protocols.ss7.tools.simulator.common.EnumeratedBase;
import ussd.client.UssdClient;

/**
 * FXML Controller class
 *
 * @author joram
 */
public class HostController implements Initializable {

    @FXML
    private ComboBox<EnumeratedBase> cboChanel;
    @FXML
    private ComboBox<String> cboRole;
    @FXML
    private TextField txtLocalHost;
    @FXML
    private TextField txtLocalPort;
    @FXML
    private TextField txtRemotePort;
    @FXML
    private TextField txtRemoteHost;
    @FXML
    private TextField txtPhone;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Label logs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UssdClient.impl.showData(cboChanel, cboRole, txtLocalHost, txtLocalPort, txtRemoteHost, txtRemotePort, txtPhone);
        cboChanel.setCellFactory((ListView<EnumeratedBase> param) -> new ListCell<EnumeratedBase>() {
            @Override
            protected void updateItem(EnumeratedBase item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }

        });
    }

    @FXML
    private void btnSave(ActionEvent event) {
        try {
            UssdClient.impl.saveData(cboChanel, cboRole, txtLocalHost, txtLocalPort, txtRemoteHost, txtRemotePort, txtPhone);
            UssdClient.impl.startHost();
            UssdClient.st.setTitle(txtPhone.getText());
            logs.setText("Starting...");
            progressIndicator.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(HostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
    }

}
