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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Alexander on 05.11.2016.
 */
public class Order {

    SQL db_cont = new SQL();
    //  Main_2 ma = new Main_2();

    @FXML
    private TableView<Client_table> table_client;
    @FXML
    private TableColumn<Client_table, Integer> key_client;
    @FXML
    private TableColumn<Client_table, String> fio_client;
    @FXML
    private TableColumn<Client_table, Integer> phone_client;
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
    public int id_master;
    @FXML
    private String local_time, nodes, Ctiks;
    @FXML
    private CheckBox price_box;
    @FXML
    public int local_ckient; // перименная для регулярного выражения

    public boolean visible_pane_client = false;

    private int price1, price2;
    @FXML
    private TextField poisk;

   // public List<String> global = new ArrayList<String>();
    public ObservableList<String>global = FXCollections.observableArrayList();


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

    @FXML
    private void FinalOrder() throws Exception {

       /* Table_clock();
        ID_MASTER();
        prises();
        nodes_client();

        global.add("Номер " + String.valueOf(db_cont.Clock_id_clock));
        global.add(String.valueOf(id_master));
        global.add(Ctiks);
        global.add(local_time);
        global.add(String.valueOf(price1));
        global.add(String.valueOf(price2));
        global.add(nodes);
*/

        Stage orders = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FinalOrder.fxml"));
        orders.setTitle("Final Order");
        orders.setScene(new Scene(root));
        orders.setResizable(false);
        orders.show();

        PrintOrder p = new PrintOrder();
        p.lists();
        //db_cont.BDWORK_WRITE_REPAIR(db_cont.Clock_id_clock, id_master, Ctiks, local_time, price1, price2, nodes, "noll");
        //System.out.println("Peremoga" + global );


    }

    @FXML
    public void Clitent_table() //вывод таблицы клиентов
    {
        db_cont.BDWORK_READ_CLIENT();
        key_client.setCellValueFactory(new PropertyValueFactory<Client_table, Integer>("key_client"));
        fio_client.setCellValueFactory(new PropertyValueFactory<Client_table, String>("name_client"));
        phone_client.setCellValueFactory(new PropertyValueFactory<Client_table, Integer>("phone_client"));
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
        StringTokenizer st = new StringTokenizer(master_box.getValue(), " ");
        while (st.hasMoreTokens()) {
            String i = st.nextToken();
            id_master = Integer.parseInt(i);
            break;
        }
    }

    @FXML
    private void ButtonNewClient() //ввод нового клиента
    {
        String f = fio_client_new.getText();
        int p = Integer.parseInt(phon_client_new.getText());
        String n = note_client_new.getText();
        db_cont.BDWORK_WRITE_CLIENT(f, p, n);
        client_new.setText("Клиент добавден в базу, и выбран.");

    }

    @FXML
    private void Table_clock() //заполненеие таблицы часы
    {
        String m = models_clock.getText();
        if (visible_pane_client == false) {
            db_cont.BDWOKR_WRITE_CLOCK(m, local_ckient);
        }
        if (visible_pane_client == true) {
            db_cont.BDWOKR_WRITE_CLOCK(m, db_cont.Client_id_clock);
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
    }

    public void filter() {
        try {

            fio_client.setCellValueFactory(new PropertyValueFactory<Client_table, String>("name_client"));
            phone_client.setCellValueFactory(new PropertyValueFactory<Client_table, Integer>("phone_client"));

            FilteredList<Client_table> filteredData = new FilteredList<>(db_cont.Client_Data, p -> true);

            poisk.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Integer.toString(person.getPhone_client()).indexOf(newValue) != -1) {
                        return true;
                    }
                    /*else if(person.getName_client().toLowerCase().contains(lowerCaseFilter));
                    {
                    return true;
                    }*/
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
    private void fotoclick() throws IOException {
/*
        Webcam webcam = Webcam.getDefault();
        if (webcam != null) {
            System.out.println("Webcam" + webcam.getName());
        } else {
            System.out.println("Non webcam");
        }
        webcam.open();
        BufferedImage image = webcam.getImage();

        ImageIO.write(image, "PNG", new File("test.png"));*/
    }
}
