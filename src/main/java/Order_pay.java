import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Alexander on 17.11.2016.
 */
public class Order_pay extends SQL
{
    @FXML
    private Label local_time_l;
    @FXML
    private Label end_line;
    @FXML
    private Label end;

    private String local_time;
    private String stik;

    @FXML
    private TableView<Pay_End> table_pay;
    @FXML
    private TableColumn<Pay_End, String> stick_end;
    @FXML
    private TableColumn<Pay_End, String> model_end;
    @FXML
    private TableColumn<Pay_End, String> master_end;
    @FXML
    private TableColumn<Pay_End, String> date_start;
    @FXML
    private TableColumn<Pay_End, String> dat_end;
    @FXML
    private TableColumn<Pay_End, String> client_end;
    @FXML
    private TableColumn<Pay_End, Integer> phone_end;
    @FXML
    private TableColumn<Pay_End, String> note_end;
    @FXML
    private TableColumn<Pay_End, Integer> value;
    @FXML
    private TableColumn<Pay_End, Integer> payment;


    @FXML
    private TextField poisk;
    @FXML
    private TextField poisk_tel;

    @FXML
    private void initialize() {

        LOCAL_TIME();
        Clitent_table();
      //  end.setVisible(false);

    }
    private void LOCAL_TIME() //Время на машине.
    {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        local_time_l.setText("Дата сдачи заказа : " + String.valueOf(sqlDate));
        local_time = String.valueOf(sqlDate);
    }

    @FXML
    public void Clitent_table() //вывод таблицы клиентов
    {
        BD_PAY_TABLE();
        System.out.println(Pay_Data);
        stick_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("stick_end"));
        model_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("model_end"));
        master_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("master_end"));

      //  date_start.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("date_start"));

        dat_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("dat_end"));

       // client_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("client_end"));
       // phone_end.setCellValueFactory(new PropertyValueFactory<Pay_End, Integer>("phone_end"));

        note_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("note_end"));
        value.setCellValueFactory(new PropertyValueFactory<Pay_End, Integer>("value"));
        payment.setCellValueFactory(new PropertyValueFactory<Pay_End, Integer>("payment"));

        table_pay.setItems(Pay_Data);
    }


}
