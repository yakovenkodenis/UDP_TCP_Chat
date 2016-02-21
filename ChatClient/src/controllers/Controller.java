package controllers;

import client.UDPClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Controller {


    @FXML
    private Button testButton;

    @FXML
    public void initialize() {
        testButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                new UDPClient("192.168.0.109", 37).echoServer();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("CLIENT EXCEPTION");
            }
        });
    }
}
