/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ussd.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.management.Notification;
import javax.management.NotificationListener;
import org.mobicents.protocols.ss7.tools.simulator.common.EnumeratedBase;
import org.mobicents.protocols.ss7.tools.simulator.level1.BIpChannelType;
import org.mobicents.protocols.ss7.tools.simulator.level1.M3uaMan;
import org.mobicents.protocols.ss7.tools.simulator.management.TesterHost;
import ussd.client.UssdClient;

/**
 *
 * @author joram
 */
public class Impl {

    private TesterHost host;
    private M3uaMan m3ua;
    Pane pnlProgress;
    Pane pnlForm;
    Pane pnlContent;
    Label lbltext;
    Pane pnlMessage;
    Label lblMessage;
    Timeline l1;
    Timeline l2;
    Timeline l3;

    public Impl() {
        loadFiles();
    }

    private void loadFiles() {

        String sim_home = System.getenv(TesterHost.SIMULATOR_HOME_VAR);
        if (sim_home != null) {
            sim_home += File.separator + "data";
        }
        host = new TesterHost("main", sim_home);
        host.addNotificationListener(new NotificationListener() {
            @Override
            public void handleNotification(Notification notification, Object handback) {
                String message = notification.getMessage();
                if (pnlProgress != null && message.startsWith("Rcvd: unstrSsReq: ")) {
                    Platform.runLater(() -> {
                        l1.stop();
                        pnlProgress.setVisible(false);
                        l2.play();
                        lbltext.setText(message.replace("Rcvd: unstrSsReq: ", ""));

                    });

                } else if (pnlProgress != null && message.startsWith("Rcvd: procUnstrSsResp: ")) {
                    Platform.runLater(() -> {
                        l1.stop();
                        pnlProgress.setVisible(false);
                        l3.play();
                        lblMessage.setText(message.replace("Rcvd: procUnstrSsResp: ", ""));
                    });
                }

            }
        }, null, null);

        m3ua = host.getM3uaMan();

    }

    public void startHost() {

        Task<Void> task = new Task() {
            @Override
            protected Void call() throws Exception {
                host.start();
                return null;
            }

            @Override
            protected void succeeded() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            TesterHost thost = (TesterHost) host;
                            thost.execute();
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("/ussd/fxml/USSD-GUI.fxml"));
                                Stage st = UssdClient.st;
                                st.setScene(new Scene(root));
                                st.centerOnScreen();
                            } catch (IOException ex) {
                                Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                    }
                }, 10000);
            }

        };

        new Thread(task).start();
    }

    private void addCboChanel(ComboBox<EnumeratedBase> cb) {
        EnumeratedBase[] ebb = m3ua.getSctpIPChannelType().getList();
        EnumeratedBase dv = null;
        for (EnumeratedBase eb : ebb) {
            cb.getItems().add(eb);
            if (eb.intValue() == m3ua.getSctpIPChannelType().intValue()) {
                dv = eb;
            }
        }
        if (dv != null) {
            cb.getSelectionModel().select(dv);
        }

    }

    private void setCboRole(ComboBox<String> cb) {
        if (m3ua.isSctpIsServer()) {
            cb.getSelectionModel().select(1);
        } else {
            cb.getSelectionModel().select(0);
        }
    }

    void showData(ComboBox<EnumeratedBase> cboChanel, ComboBox<String> cboRole, TextField txtLocalHost, TextField txtLocalPort, TextField txtRemoteHost, TextField txtRemotePort, TextField txtPhone) {
        addCboChanel(cboChanel);
        cboRole.getItems().addAll("Client", "Server");
        setCboRole(cboRole);
        txtLocalHost.setText(m3ua.getSctpLocalHost());
        txtLocalPort.setText(m3ua.getSctpLocalPort() + "");
        txtRemoteHost.setText(m3ua.getSctpRemoteHost());
        txtRemotePort.setText(m3ua.getSctpRemotePort() + "");
        txtPhone.setText(host.getMapMan().getOrigReference());
    }

    void saveData(ComboBox<EnumeratedBase> cboChanel, ComboBox<String> cboRole, TextField txtLocalHost, TextField txtLocalPort, TextField txtRemoteHost, TextField txtRemotePort, TextField txtPhone) {

        m3ua.setSctpIPChannelType((BIpChannelType) cboChanel.getSelectionModel().getSelectedItem());
        if (cboRole.getSelectionModel().getSelectedIndex() == 0) {
            this.m3ua.setSctpIsServer(false);
        } else {
            this.m3ua.setSctpIsServer(true);
        }
        m3ua.setSctpLocalHost(txtLocalHost.getText());
        m3ua.setSctpLocalPort(Integer.parseInt(txtLocalPort.getText()));
        m3ua.setSctpRemoteHost(txtRemoteHost.getText());
        m3ua.setSctpRemotePort(Integer.parseInt(txtRemotePort.getText()));
        host.getMapMan().setOrigReference(txtPhone.getText());

    }

    void sendProcessUnstructuredRequest(String text) {
        host.getTestUssdClientMan().performProcessUnstructuredRequest(text);
    }

    Boolean sendUnstructuredResponse(String text) {
        String res = host.getTestUssdClientMan().performUnstructuredResponse(text);
        return res.equals("No current dialog exists. Start it previousely");
    }

    void closeCurrentDialog() {
        host.getTestUssdClientMan().closeCurrentDialog();
    }
}
