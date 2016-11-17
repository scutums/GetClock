import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.Calendar;

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
    @FXML
    private Label vals;
    @FXML
    private Label pays;
    @FXML
    private Label sym;

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
    private TableColumn<Pay_End, String> start_end;
    @FXML
    private TableColumn<Pay_End, String> dat_end;
    @FXML
    private TableColumn<Pay_End, String> client;
    @FXML
    private TableColumn<Pay_End, Integer> phone;
    @FXML
    private TableColumn<Pay_End, String> note_end;
    @FXML
    private TableColumn<Pay_End, Integer> value;
    @FXML
    private TableColumn<Pay_End, Integer> payment;
    @FXML
    private ComboBox comboBox;


    @FXML
    private TextField poisk;
    @FXML
    private TextField poisk_tel;

    private String date_garant;

    @FXML
    private void initialize() {

        LOCAL_TIME();
        Clitent_table();
      //  end.setVisible(false);
        TABLE_CLICK();
        filter();
        Box();

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
        stick_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("stick_end"));
        model_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("model_end"));
        master_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("master_end"));
        start_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("start_end"));
        dat_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("dat_end"));
        client.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("client"));
        phone.setCellValueFactory(new PropertyValueFactory<Pay_End, Integer>("phone"));
        note_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("note_end"));
       // value.setCellValueFactory(new PropertyValueFactory<Pay_End, Integer>("value"));
       // payment.setCellValueFactory(new PropertyValueFactory<Pay_End, Integer>("payment"));
        table_pay.setItems(Pay_Data);
    }

    public void TABLE_CLICK() {
        table_pay.getSelectionModel().selectedItemProperty().addListener(
                (obserdler, oldValue, newValue) -> Selectional(newValue)
        );
    }

    public void Selectional(Pay_End line) {
        stik  = line.getStick_end();
        String mod = line.getModel_end();
        String fio = line.getClient();
        Integer ph = line.getPhone();
        Integer val = line.getValue();
        Integer pay = line.getPayment();
        int x = val-pay;
        end_line.setText("Заказ № " + stik + ".   Модель : " + mod + ".   Имя владельца : " + fio + ".   Телефон : " + ph + ".  -ВЫБРАН.");
        sym.setText("Общая сумма : " + val);
        vals.setText("Было уплачено : " + pay);
        pays.setText("Требуется доплатить : " + x);
    }

    public void filter() {
        try {
            stick_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("stick_end"));
            master_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("master_end"));
            phone.setCellValueFactory(new PropertyValueFactory<Pay_End, Integer>("phone"));


            FilteredList<Pay_End> filteredData = new FilteredList<>(Pay_Data, p -> true);

            poisk.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getStick_end().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else /*if(person.getMaster_end().toLowerCase().contains(lowerCaseFilter))*/ if(Integer.toString(person.getPhone()).indexOf(newValue) != -1)
                    {
                    return true;
                    }
                    return false;
                });
            });


            SortedList<Pay_End> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(table_pay.comparatorProperty());


            table_pay.setItems(sortedData);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void Box()
    {
        comboBox.getItems().addAll(
                "Нет гарантии",
                "Гарантия на пол года (182 дня)",
                "Гарантия на год (365 дней)"
        );
      String com = comboBox.getId();
        System.out.println(com);
    }

    @FXML
    void lopi()
    {
        String com = (String) comboBox.getValue();

        if (com == "Нет гарантии")
        {
            end.setText("Тарантия отсуцтвуе");
            date_garant = local_time;
        }
        if (com == "Гарантия на пол года (182 дня)")
        {
            date_garant = local_time + 182;
            end.setText("Конечная дата : " + date_garant);

        }
        if (com == "Гарантия на год (365 дней)")
        {
            date_garant = local_time + 365;
            end.setText("Конечная дата : " + date_garant);
        }
    }


}
