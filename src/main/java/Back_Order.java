import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Alexander on 19.11.2016.
 */
public class Back_Order extends SQL {
    Main_2 ma= new Main_2();

    private String stik,fio, local_time;
    private Integer ph;

    @FXML
    private CheckBox bed_client;
    @FXML
    private TextArea notes;
    @FXML
    private TableView<Order_back> table_back;
    @FXML
    private TableColumn<Order_back, String> stick_end;
    @FXML
    private TableColumn<Order_back, String> model_end;
    @FXML
    private TableColumn<Order_back, String> master_end;
    @FXML
    private TableColumn<Order_back, String> start_end;
    @FXML
    private TableColumn<Order_back, String> dat_end;
    @FXML
    private TableColumn<Order_back, String> note_end;
    @FXML
    private TableColumn<Order_back, String> client;
    @FXML
    private TableColumn<Order_back, Integer> phone;
    @FXML
    private TableColumn<Order_back, Integer> value;
    @FXML
    private TableColumn<Order_back, String> note_cl;
    @FXML
    private TextField poisk;
    @FXML
    private Label end_line;
    @FXML
    private Label local_time_l;

    @FXML
    private void initialize()
    {
     notes.setVisible(false);
        Clitent_Back();
        TABLE_CLICK();
        filter();
        LOCAL_TIME();
    }
    @FXML
    private void pluss()
    {

        boolean br = bed_client.isSelected();
        if (br == false)
        {
            notes.setVisible(false);
        }
        if (br == true)
        {
            notes.setVisible(true);

        }

    }
    @FXML
    private void update()
    {

        String x = " " + notes.getText();
        if (x !=null)
        {
        UP_CL_BED(ph,x);
        }
        UP_BACK_BED(stik,local_time);
        UP_BACK_NOTE(stik);
        ma.Close_order_back();



    }
    private void LOCAL_TIME() //Время на машине.
    {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        local_time_l.setText("Дата возврата заказа : " + String.valueOf(sqlDate));
        local_time = String.valueOf(sqlDate);
    }

    @FXML
    public void Clitent_Back() //вывод таблицы клиентов
    {
        BD_BACK_TABLE();
        stick_end.setCellValueFactory(new PropertyValueFactory<Order_back, String>("stick_end"));
        model_end.setCellValueFactory(new PropertyValueFactory<Order_back, String>("model_end"));
        master_end.setCellValueFactory(new PropertyValueFactory<Order_back, String>("master_end"));
        start_end.setCellValueFactory(new PropertyValueFactory<Order_back, String>("start_end"));
        dat_end.setCellValueFactory(new PropertyValueFactory<Order_back, String>("dat_end"));
        note_end.setCellValueFactory(new PropertyValueFactory<Order_back, String>("note_end"));
        client.setCellValueFactory(new PropertyValueFactory<Order_back, String>("client"));
        phone.setCellValueFactory(new PropertyValueFactory<Order_back, Integer>("phone"));
        value.setCellValueFactory(new PropertyValueFactory<Order_back, Integer>("value"));
        note_cl.setCellValueFactory(new PropertyValueFactory<Order_back, String>("note_cl"));
        table_back.setItems(BACK_Data);
    }

    public void TABLE_CLICK() // выбор строки
    {
        table_back.getSelectionModel().selectedItemProperty().addListener(
                (obserdler, oldValue, newValue) -> Selectional(newValue)
        );
    }

    public void Selectional(Order_back line)  //выбор строки
    {
        stik  = line.getStick_end();
        String mod = line.getModel_end();
        fio = line.getClient();
        ph = line.getPhone();
        end_line.setText("Заказ № " + stik  +'\n'+'\n' +  "Модель : " + mod + '\n'+'\n' + "Имя владельца : " + fio + '\n'+'\n' + "Телефон : " + ph);
    }

    public void filter() //фильтрация таблицы
    {
        try {
            stick_end.setCellValueFactory(new PropertyValueFactory<Order_back, String>("stick_end"));
            client.setCellValueFactory(new PropertyValueFactory<Order_back, String>("client"));
            phone.setCellValueFactory(new PropertyValueFactory<Order_back, Integer>("phone"));

            FilteredList<Order_back> filteredData = new FilteredList<>(BACK_Data, p -> true);

            poisk.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getStick_end().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if(Integer.toString(person.getPhone()).indexOf(newValue) != -1)
                    {
                        return true;
                    }
                    else if(person.getClient().toLowerCase().contains(lowerCaseFilter))
                    {
                        return true;
                    }
                    return false;
                });
            });


            SortedList<Order_back> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(table_back.comparatorProperty());


            table_back.setItems(sortedData);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
