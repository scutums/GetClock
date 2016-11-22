import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Alexander on 22.11.2016.
 */
public class OrderGarant {

    @FXML
    private TableView<Garante_Table> table_back;
    @FXML
    private TableColumn<Garante_Table, String> stick_end;
    @FXML
    private TableColumn<Garante_Table, String> model_end;
    @FXML
    private TableColumn<Garante_Table, String> master_end;
    @FXML
    private TableColumn<Garante_Table, String> guarante;
    @FXML
    private TableColumn<Garante_Table, String> note_end;
    @FXML
    private TableColumn<Garante_Table, String> client;
    @FXML
    private TableColumn<Garante_Table, String> phone;
    @FXML
    private TableColumn<Garante_Table, Integer> payment;


    @FXML
    private Label end_line;
    @FXML
    private Label local_time_l;
    @FXML
    private CheckBox bed_client;
    @FXML
    private DatePicker dates_end;
    @FXML
    private TextField note_txt;

    @FXML
    private void update()
    {

    }
    @FXML
    private void pluss()
    {

    }

}
