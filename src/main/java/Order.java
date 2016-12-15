import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.omg.CORBA.INTF_REPOS;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Alexander on 05.11.2016.
 */
public class Order {

    SQL db_cont = new SQL();
    Main_2 ma = new Main_2();

    @FXML
    private TableView<Client_table> table_client;
    @FXML
    private TableColumn<Client_table, Integer> key_client;
    @FXML
    private TableColumn<Client_table, String> fio_client;
    @FXML
    private TableColumn<Client_table, String> phone_client;
    @FXML
    private TableColumn<Client_table, String> adres;
    @FXML
    private TableColumn<Client_table, String> note_client;
    @FXML
    private Pane pane_new_cl;
    @FXML
    private ComboBox<String> master_box = new ComboBox<String>();
    @FXML
    private TextField fio_client_new;
    @FXML
    private TextField price1_t;
    @FXML
    private TextField price2_t;
    @FXML
    private TextField phon_client_new;
    @FXML
    private TextArea note_client_new;
    @FXML
    private Label client_new;
    @FXML
    private Label client_in_db;
    @FXML
    private Label stik;
    @FXML
    private Label Layout_local_time;
    @FXML
    private TextField models_clock;
    @FXML
    private TextArea node_clients;
    @FXML
    private String local_time, nodes, Ctiks, masters_name, local_client_f, local_client_p, local_client_a, time_end,model_clocks;
    @FXML
    private CheckBox price_box;
    @FXML
    public int local_ckient; // перименная для регулярного выражения
    public boolean visible_pane_client = false;
    private Integer price1, price2,id_master;
    @FXML
    private TextField poisk;
    @FXML
    public static Stage orders;
    @FXML
    public static Stage orders1;
    @FXML
    private Label error;
    @FXML
    private TextField adres_client;
    @FXML
    private DatePicker end_date;
    public static InputStream mas;


    static public ObservableList<String> global = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Clitent_table();
        pane_new_cl.setVisible(false);
        BOX_MASTER();
        TABLE_CLICK();
        LOCAL_TIME();
        Stiks();
        filter();

    }

    void text()
    {

    }
    @FXML
    private void FinalOrder() throws Exception
    {
        ID_MASTER();
        prises();
        nodes_client();
        BigOrder();
        Table_clock();
        if(id_master != null  & price1 != null &  !nodes.isEmpty()  & !time_end.isEmpty())
        {
            NewWindow();
        }
        else
        {
            error.setText("ПРОВЕРЬТЕ ПРАВИЛЬНОСТЬ ВВОДА ДАННЫХ !");
        }
    }
    private void BigOrder() // сбор данных
    {
        System.out.println(db_cont.Clock_id_clock +" " + id_master + " " + Ctiks + " " + local_time +" "+ time_end +" "+ price1 +" "+ price2 +" "+ nodes +" "+ mas +" "+ 1);

         global.clear();
        global.add("РЕМОНТ ЧАСОВ");
        global.add("Яковлевские часы");
        global.add("Харьков пр. Московский 1");
        global.add("т.7312601, т.0737312601");
        global.add("ф.ПО-Р1");
        global.add("Номер заказа    : " + Ctiks);
        global.add("Имя клиента     : " + local_client_f);
        global.add("Телефон клиента : " + local_client_p);
        global.add("Адрес клиента   : " + local_client_a);
        global.add("Мастер          : " + id_master);
        global.add("Марка чавов     : " + model_clocks);
        global.add("Примечания со слов клиента : " + nodes);
        global.add("Дата сдачи в ремонт        : " + local_time);
        global.add("Дата окончания ремонта*    : " + time_end);
        global.add("Цена ремонта               : " + String.valueOf(price1) + " грн." );
        global.add("Уплачено клиентов          : " + String.valueOf(price2) + " грн.");
        global.add("Долг                       : " + String.valueOf(price1-price2) + " грн.");
        System.out.println(global);


    }

    @FXML
    public ObservableList<String> getList()
    {
        return global;
    }

    @FXML
    public void closes()
    {
        System.out.println(db_cont.Clock_id_clock +" " + id_master + " " + Ctiks + " " + local_time +" "+ time_end +" "+ price1 +" "+ price2 +" "+ nodes +" "+ mas +" "+ 1);
        db_cont.BDWORK_WRITE_REPAIR(db_cont.Clock_id_clock, id_master, Ctiks, local_time,time_end, price1, price2, nodes, mas, 1);
        System.out.println("OK");
        orders.close();
        ma.Close_order();

    }

    @FXML
    public void NewWindow() throws Exception
    {
        orders = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FinalOrder.fxml"));
        orders.setTitle("Распечатка чека");
        orders.setScene(new Scene(root));
        orders.setResizable(false);
        orders.show();

    }

    @FXML
    public void Clitent_table() //вывод таблицы клиентов
    {
        db_cont.BDWORK_READ_CLIENT();
       // key_client.setCellValueFactory(new PropertyValueFactory<Client_table, Integer>("key_client"));
        fio_client.setCellValueFactory(new PropertyValueFactory<Client_table, String>("name_client"));
        phone_client.setCellValueFactory(new PropertyValueFactory<Client_table, String>("phone_client"));
        adres.setCellValueFactory(new PropertyValueFactory<Client_table, String>("adres"));
        note_client.setCellValueFactory(new PropertyValueFactory<Client_table, String>("note_client"));
        table_client.setItems(db_cont.Client_Data);
    }

    @FXML
    private void Client_vars() //отображение панели нового клиента
    {
        pane_new_cl.setVisible(true);
        visible_pane_client = true;
        client_in_db.setText("Клиент формируется.");
        table_client.setVisible(false);

    }

    private void BOX_MASTER() // Вывод номера в контейнер мастера
    {
        db_cont.BDWOKR_READ_MASTER();
        final Collection<String> master = db_cont.masterData;
        master_box.setItems((ObservableList<String>) master);
    }

    public void ID_MASTER() //  Считывание номера мастера.
    {
        masters_name = master_box.getValue();
        StringTokenizer st = new StringTokenizer(master_box.getValue(), " ");
        while (st.hasMoreTokens()) {
            String i = st.nextToken();
            id_master = Integer.parseInt(i);
            break;
        }
    }

    @FXML
    private void ButtonNewClient() //ввод нового клиента + защита от дурака
    {
        String p = phon_client_new.getText();
        String f = fio_client_new.getText();
        String a = adres_client.getText();
        String n = note_client_new.getText();
        if (n.isEmpty())
        {
            n = ".";
        }
        if (a.isEmpty())
        {
            a = ".";
        }


        if (f !=" " & n !=" " & p !=" ") {
            db_cont.BDWORK_WRITE_CLIENT(f, p, a, n);
            local_client_f = f;
            local_client_p = String.valueOf(p);
            local_client_a = a;
            client_new.setText("Клиент " + local_client_f + " добавден в базу, и выбран.");
        }
    }

    @FXML
    private void Table_clock() //заполненеие таблицы часы
    {
        model_clocks = models_clock.getText();
        if (visible_pane_client == false) {
            db_cont.BDWOKR_WRITE_CLOCK(model_clocks, local_ckient);
        }
        if (visible_pane_client == true) {
            db_cont.BDWOKR_WRITE_CLOCK(model_clocks, db_cont.Client_id_clock);
            client_in_db.setText("Клиент формируется.");
        }
    }

    public void TABLE_CLICK() {
        table_client.getSelectionModel().selectedItemProperty().addListener(
                (obserdler, oldValue, newValue) -> Selectional(newValue)
        );
    }

    public void Selectional(Client_table line) {
        local_ckient = line.getKey_client();
        local_client_f = line.getName_client();
        local_client_p = String.valueOf(line.getPhone_client());
        client_in_db.setText("Клиент " + local_ckient + " выбран.");
    }

    private void Stiks() {
        db_cont.BDWOKR_READ_STICK();

        char[] chars = "ABCDIFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        Ctiks = sb.toString() + "-" + String.valueOf(random.nextInt(1000000));
        int collor = Collections.frequency(db_cont.stikk, Ctiks);
        if (collor == 0) {
            stik.setText(Ctiks);
        } else {
            Stiks();
        }
    }

    @FXML
    private void prise() //визуализация ячеек оплаты
    {
        boolean pr = price_box.isSelected();
        if (pr == false) {
            price2_t.setVisible(true);
        }
        if (pr == true) {
            price2_t.setVisible(false);
        }
    }

    private void prises() // сбор данных об оплате
    {
        boolean pr = price_box.isSelected();

        if (pr == false) {
            price1 = Integer.parseInt(price1_t.getText());
            price2 = Integer.parseInt(price2_t.getText());
        }
        if (pr == true) {
            price1 = Integer.parseInt(price1_t.getText());
            price2 = price1;
        }
    }

    private void nodes_client() // пометки от пользователя
    {
        nodes = node_clients.getText();
    }

    private void LOCAL_TIME() //Время на машине.
    {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Layout_local_time.setText("Дата заказа : " + String.valueOf(sqlDate));
        local_time = String.valueOf(sqlDate);

        end_date.setOnAction(event ->
        {
           LocalDate date = end_date.getValue();
            time_end = String.valueOf(date);
        });
    }

    public void filter() {
        try {

            fio_client.setCellValueFactory(new PropertyValueFactory<Client_table, String>("name_client"));
            phone_client.setCellValueFactory(new PropertyValueFactory<Client_table, String >("phone_client"));
            adres.setCellValueFactory(new PropertyValueFactory<Client_table, String>("adres"));

            FilteredList<Client_table> filteredData = new FilteredList<>(db_cont.Client_Data, p -> true);

            poisk.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getPhone_client().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if(person.getName_client().toLowerCase().contains(lowerCaseFilter))
                    {
                    return true;
                    }
                    else if(person.getAdres().toLowerCase().contains(lowerCaseFilter))
                    {
                        return true;
                    }
                    return false;
                });
            });


            SortedList<Client_table> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(table_client.comparatorProperty());


            table_client.setItems(sortedData);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void fotoclick() throws IOException {
        orders1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("WebCamOrder.fxml"));
        orders1.setTitle("Фото изделия");
        orders1.setScene(new Scene(root));
        orders1.setResizable(false);
        orders1.show();
    }


}
