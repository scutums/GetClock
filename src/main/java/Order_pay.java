import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Alexander on 17.11.2016.
 */
public class Order_pay extends SQL
{
    Main_2 ma = new Main_2();
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
    @FXML
    private CheckBox debt;
    private String local_time, mod,stik,name_mas;
    private Integer paysis,val,pay;
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
    private ComboBox comboBox;
    @FXML
    public static Stage orders1;
    static public ObservableList<String> pay_glo = FXCollections.observableArrayList();


    @FXML
    private TextField poisk;
    @FXML
    private TextField poisk_tel;

    private String date_garant;

    @FXML
    private void initialize()
    {

        LOCAL_TIME();
        Clitent_table();
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
        table_pay.setItems(Pay_Data);
    }

    public void TABLE_CLICK() // выбор строки
    {
        table_pay.getSelectionModel().selectedItemProperty().addListener(
                (obserdler, oldValue, newValue) -> Selectional(newValue)
        );
    }

    public void Selectional(Pay_End line)  //выбор строки
    {
        stik  = line.getStick_end();
        mod = line.getModel_end();
        String fio = line.getClient();
        Integer ph = line.getPhone();
        name_mas = line.getMaster_end();
        val = line.getValue();
        pay = line.getPayment();
        int x = val-pay;
        end_line.setText("Заказ № " + stik  +'\n'+'\n' +  "Модель : " + mod + '\n'+'\n' + "Имя владельца : " + fio + '\n'+'\n' + "Телефон : " + ph);
        sym.setText("Общая сумма : " + val);
        vals.setText("Было уплачено : " + pay);
        if (x <=0)
        {
            pays.setText("Ремонт оплачен");
            debt.setVisible(false);
            paysis = val;
        }
        else
        {
            debt.setVisible(true);
            pays.setText("Требуется доплатить : " + x);
            prises();
        }

    }

    public void filter() //фильтрация таблицы
    {
        try {
            stick_end.setCellValueFactory(new PropertyValueFactory<Pay_End, String>("stick_end"));
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
                    else if(Integer.toString(person.getPhone()).indexOf(newValue) != -1)
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

    private void Box() // контейнер гарантии
    {
        comboBox.getItems().addAll(
                "Нет гарантии",
                "Гарантия на пол года",
                "Гарантия на год"
        );
    }

    @FXML
    void lopi() // гарантия
    {
        String com = (String) comboBox.getValue();
        if (com == "Нет гарантии")
        {
            end.setText("Тарантия отсуцтвуе");
            date_garant = local_time;

        }
        if (com == "Гарантия на пол года")
        {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH,6);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date_garant = (sdf.format(cal.getTime()));
            end.setText("Конечная дата : " + date_garant);
        }
        if (com == "Гарантия на год")
        {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR,1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date_garant = (sdf.format(cal.getTime()));
            end.setText("Конечная дата : " + date_garant);
        }
    }

    @FXML
    void print() throws Exception // кнопка
    {
        bid_pay();
        NewWindow();
        BD_PAY_WRITE(local_time,date_garant,paysis,stik);

    }

    private void prises() // данные об оплате
    {
        boolean pr = debt.isSelected();

        if (pr == false)
        {
            paysis = val;
        }
        if (pr == true)
        {
            paysis = pay;
        }
    }

    @FXML
    private void NewWindow() throws Exception // создение окна печати
    {
        orders1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Pay_Order.fxml"));
        orders1.setTitle("Pay Order");
        orders1.setScene(new Scene(root));
        orders1.setResizable(false);
        orders1.show();
    }
    @FXML
    public void closes() //мотод закрыя окна печати
    {
        orders1.close();
        ma.Close_order_pay();
    }
    private void bid_pay() // метод збора данных для печати
    {
        pay_glo.add("Номер заказа : " + stik);
        pay_glo.add("Модель часов : " + mod);
        pay_glo.add("Имя мастера : " + name_mas);
        pay_glo.add("Дата ремонта : " + local_time);
        pay_glo.add("Гарантия до : " + date_garant);
        pay_glo.add("Оплачено : "+ paysis + " грн.");
    }
    @FXML
    public ObservableList<String> getList() //гетер
    {
        return pay_glo;
    }
}
