import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Created by Alexander on 21.11.2016.
 */
public class ReadFoto {
    SQL s = new SQL();
    @FXML
    private Label indic;
    @FXML
    private ImageView foto;
    @FXML
    private TextField stic;
    @FXML
    private void go()
    {
        String poll = stic.getText();
         s.UP_BACK_NOTE();
    }
}
