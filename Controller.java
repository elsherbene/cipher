package sherby;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author cse
 */
public class Controller {

    public Label algorithm;
    public ChoiceBox choicebox;
    public TextField textfield;

    public Button encryptbutton;
    public Button decryptbutton;
    public Label enceyptedtext;
    public CaeserCipher caeserCipher;
    public PlayfairCipher PlayfairCipher;

    @FXML
    private void initialize() {
        caeserCipher = new CaeserCipher();
        PlayfairCipher = new PlayfairCipher();
        choicebox.getItems().add("CaeserCipher");
        choicebox.getItems().add("PlayfairCipher");

        encryptbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleEncryptButton();

            }

        });
        decryptbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleDecryptButton();

            }

        });
    }

    private void handleEncryptButton() {
        String message = textfield.getText();
        String method = choicebox.getValue().toString();
        String encryptedMessage = "";

        if (method.equals("CaeserCipher")) {
            encryptedMessage = caeserCipher.Encrypt(message);

        } else if (method.equals("PlayfairCipher")) {
            encryptedMessage = PlayfairCipher.encrypt(message);
        }
        enceyptedtext.setText(encryptedMessage);
        textfield.clear();

    }

    private void handleDecryptButton() {
        String EncryptedMessage = enceyptedtext.getText();
        String method = choicebox.getValue().toString();
        String realMessage = "";

        if (method.equals("CaeserCipher")) {
            realMessage = caeserCipher.Decrypt(EncryptedMessage);

        } else if (method.equals("PlayfairCipher")) {
            realMessage = PlayfairCipher.decrypt(EncryptedMessage);
        }
        textfield.setText(realMessage);
        enceyptedtext.setText("");
    }

}
