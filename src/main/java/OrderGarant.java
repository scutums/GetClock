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

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

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
    private TextField poisk;
    @FXML
    private Pane pan;
    @FXML
    private ComboBox<String> master_box = new ComboBox<String>();
    @FXML
    private Label stick_wiz;
    @FXML
    private Label error;
    @FXML
    public static Stage orders1;

    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    static public ObservableList<String> global = FXCollections.observableArrayList();

    private String local_time,time_end, master,stick,nodes,model_clock,local_client_f,local_client_p,stik_old;
    private Integer id_master,id_client;
    public static InputStream mas;

    SQL sq = new SQL();

    @FXML
    private void initialize()
    {
    Clitent_Back();
        LOCAL_TIME();
        filter();
        TABLE_CLICK();
        pan.setVisible(false);
        BOX_MASTER();
        Stiks();
    }

    @FXML
    private void Clitent_Back() //вывод таблицы клиентов
    {
        sq.BD_GARANT_TABLE();
        stick_end.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("stick_end"));
        model_end.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("model_end"));
        master_end.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("master_end"));

        guarante.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("guarante"));
        note_end.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("note_end"));
        client.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("client"));
        phone.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("phone"));
        payment.setCellValueFactory(new PropertyValueFactory<Garante_Table, Integer>("payment"));
        table_back.setItems(sq.GARANTData);

    }

    @FXML
    private void update()
    {

        nodes_client();
        ID_MASTER();
        Table_clock();
        if(id_master != null  & mas != null & !time_end.isEmpty())
        {
            sq.BDWORK_WRITE_REPAIR(sq.Clock_id_clock, id_master, stick, local_time,time_end, 0, 0, nodes, mas, 1);
           // NewWindow();
        }
        else
        {
            error.setText("ПРОВЕРЬТЕ ПРАВИЛЬНОСТЬ ВВОДА ДАННЫХ !");
        }
        sq.BD_WRITE_GARANTE_STAIT(stik_old);

    }
    private void BigOrder() // сбор данных
    {
        global.add("РЕМОНТ ЧАСОВ");
        global.add("Яковлевские часы");
        global.add("Харьков пр. Московский 1");
        global.add("т.7312601, т.0737312601");
        global.add("ф.ПО-Р1");
        global.add("Номер заказа : " + stick);
        global.add("Мастер : " + master);
        global.add("Дата сдачи в ремонт : " + local_time);
        global.add("Дата окончания ремонта : " + time_end);
        global.add("Цена ремонта : 0 (Гарантийный ремонт)");
        global.add("Уплачено клиентов (в данный момент) : 0");
        global.add("Имя клиента : " + local_client_f);
        global.add("Телефон клиента : " + local_client_p);
        global.add("Примечания к заказу : " + nodes);
    }
    @FXML
    private void pluss()
    {
        boolean br = bed_client.isSelected();
        if (br == false)
        {
            pan.setVisible(false);
        }
        if (br == true)
        {
            pan.setVisible(true);
        }
    }
    private void LOCAL_TIME() //Время на машине.
    {
        local_time_l.setText("Дата заказа : " + String.valueOf(sqlDate));
        local_time = String.valueOf(sqlDate);

        dates_end.setOnAction(event ->
        {
            LocalDate date = dates_end.getValue();
            time_end = String.valueOf(date);
        });
    }
    public void filter() //фильтрация таблицы
    {
        try {
            stick_end.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("stick_end"));
            client.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("client"));
            phone.setCellValueFactory(new PropertyValueFactory<Garante_Table, String>("phone"));

            FilteredList<Garante_Table> filteredData = new FilteredList<>(sq.GARANTData, p -> true);

            poisk.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getStick_end().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if(person.getPhone().toLowerCase().contains(lowerCaseFilter))
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


            SortedList<Garante_Table> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(table_back.comparatorProperty());


            table_back.setItems(sortedData);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void TABLE_CLICK() // выбор строки
    {
        table_back.getSelectionModel().selectedItemProperty().addListener(
                (obserdler, oldValue, newValue) -> Selectional(newValue)
        );
    }

    public void Selectional(Garante_Table line)  //выбор строки
    {
        id_client = line.getId_client();
        stik_old  = line.getStick_end();
        model_clock = line.getModel_end();
        local_client_f = line.getClient();
        String gar = line.getGuarante();
        local_client_p = line.getPhone();
        end_line.setText("Заказ № " + stik_old  +'\n'+'\n' +  "Модель : " + model_clock  + '\n'+'\n' + "Имя владельца : " + local_client_f + '\n'+'\n' + "Телефон : " + local_client_p +'\n'+'\n' + "Гарантия до : " + gar);
    }

    private void BOX_MASTER() // Вывод номера в контейнер мастера
    {
        sq.BDWOKR_READ_MASTER();
        final Collection<String> master = sq.masterData;
        master_box.setItems((ObservableList<String>) master);
    }

    public void ID_MASTER() //  Считывание номера мастера.
    {
        master = master_box.getValue();
        StringTokenizer st = new StringTokenizer(master_box.getValue(), " ");
        while (st.hasMoreTokens()) {
            String i = st.nextToken();
            id_master = Integer.parseInt(i);
            break;
        }
    }

    private void Stiks() {
        sq.BDWOKR_READ_STICK();

        char[] chars = "ABCDIFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        stick = sb.toString() + "-" + String.valueOf(random.nextInt(1000000));
        int collor = Collections.frequency(sq.stikk, stick);
        if (collor == 0) {
            stick_wiz.setText("Номер заказа : " + stick);
        } else {
            Stiks();
        }
    }

    private void nodes_client() // пометки от пользователя
    {

        String node = note_txt.getText();
        if (node.isEmpty()){
            nodes = ("Гарантийный ремонт : " + stik_old);
        }

        nodes = "Гарантийный ремонт : " + stik_old + " " + node;

    }

    @FXML
    private void Table_clock() //заполненеие таблицы часы
    {
            sq.BDWOKR_WRITE_CLOCK(model_clock,id_client);
    }
    @FXML
    private void Foto() throws Exception
    {
        Order or = new Order();
        or.fotoclick();
    }
    @FXML
    private void NewWindow() throws Exception // создение окна печати
    {
        orders1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PrintGarant.fxml"));
        orders1.setTitle("Печать чека");
        orders1.setScene(new Scene(root));
        orders1.setResizable(false);
        orders1.show();
    }
    @FXML
    public void closes() //мотод закрыя окна печати
    {
        Main_2 ma = new Main_2();
        orders1.close();
        ma.Close_order_garant();
    }


}
