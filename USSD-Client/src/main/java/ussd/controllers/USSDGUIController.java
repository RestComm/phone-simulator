/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ussd.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.util.Duration;
import ussd.client.UssdClient;

/**
 * FXML Controller class
 *
 * @author joram
 */
public class USSDGUIController implements Initializable {

    @FXML
    private TextField txtExt;
    @FXML
    private AnchorPane pnlInitUSSD;

    private Impl impl;
    @FXML
    private Pane pnlContent;
    @FXML
    private Pane pnlProgress;
    @FXML
    private Pane pnlForm;
    @FXML
    private Label lbltext;
    @FXML
    private TextField txtSend;
    @FXML
    private Pane pnlMessage;
    @FXML
    private Label lblMessage;
    @FXML
    private Arc progress;

    private Timeline l1;
    private Timeline l2;
    private Timeline l3;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        impl = UssdClient.impl;
        impl.pnlProgress = pnlProgress;
        impl.pnlForm = pnlForm;
        impl.pnlContent = pnlContent;
        impl.lbltext = lbltext;
        impl.pnlMessage = pnlMessage;
        impl.lblMessage = lblMessage;
        impl.l1 = l1 = startAnimateUSSD();
        impl.l2 = l2 = startAnimateForm300();
        impl.l3 = l3 = startAnimateForm130();
    }

    @FXML
    private void btnCall(ActionEvent event) {
        l1.play();
        impl.closeCurrentDialog();
        tempCloseForm();
        impl.sendProcessUnstructuredRequest(txtExt.getText());
        txtExt.setText("");
        pnlInitUSSD.setVisible(true);

    }

    @FXML
    private void sendText(ActionEvent event) {
        tempCloseForm();
        Boolean b = impl.sendUnstructuredResponse(txtSend.getText());
        if (b) {
            onCloseUSSD(null);
        }
        txtSend.setText("");
    }

    @FXML
    private void onCloseUSSD(ActionEvent event) {
        impl.closeCurrentDialog();
        l1.stop();
        pnlProgress.setVisible(true);
        pnlContent.setMaxHeight(70);
        pnlForm.setVisible(false);
        pnlInitUSSD.setVisible(false);
        pnlMessage.setVisible(false);
        lbltext.setText("");
        lblMessage.setText("");
    }

    private void tempCloseForm() {
        l1.play();
        pnlProgress.setVisible(true);
        pnlContent.setMaxHeight(70);
        pnlForm.setVisible(false);
        pnlMessage.setVisible(false);
        lbltext.setText("");
        lblMessage.setText("");
    }

    private Timeline startAnimateUSSD() {
        Timeline ml1 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progress.startAngleProperty(), -180)),
                new KeyFrame(Duration.seconds(1), new KeyValue(progress.startAngleProperty(), 180))
        );
        ml1.setCycleCount(Timeline.INDEFINITE);
        return ml1;
    }

    @FXML
    private void numberClicked(MouseEvent event) {
        try {
            String n = ((Label) ((StackPane) event.getSource()).getChildren().get(0)).getText();
            txtExt.setText(txtExt.getText() + n);
        } catch (Exception e) {
            String n = ((Label) ((Pane) event.getSource()).getChildren().get(0)).getText();
            txtExt.setText(txtExt.getText() + n);
        }

    }

    private Timeline startAnimateForm300() {
        Timeline tml = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(pnlContent.maxHeightProperty(), 70)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(pnlContent.maxHeightProperty(), 300, Interpolator.EASE_BOTH)));
        tml.setOnFinished((ActionEvent event) -> {
            pnlForm.setVisible(true);
            txtSend.requestFocus();
        });
        return tml;
    }

    private Timeline startAnimateForm130() {
        Timeline tml = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(pnlContent.maxHeightProperty(), 70)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(pnlContent.maxHeightProperty(), 130, Interpolator.EASE_BOTH)));
        tml.setOnFinished((ActionEvent event) -> {

            pnlForm.setVisible(false);
            pnlMessage.setVisible(true);
        });

        return tml;
    }

}
