import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;

/**
 * Created by Alexander on 16.11.2016.
 */

public class Clock_end extends SQL {

    Main_2 ma = new Main_2();

    @FXML
    private Label local_time_l;
    @FXML
    private Label end_line;
    @FXML
    private Label end;

    private String local_time;
    private String stik;

    @FXML
    private TableView<Date_end> table_end;
    @FXML
    private TableColumn<Date_end, String> stick_end;
    @FXML
    private TableColumn<Date_end, String> model_end;
    @FXML
    private TableColumn<Date_end, String> master_end;
    @FXML
    private TableColumn<Date_end, String> dat_end;
    @FXML
    private TableColumn<Date_end, String> note_end;
    @FXML
    private TextField poisk;


    @FXML
    private void initialize() {

        LOCAL_TIME();
        END_TABLE();
        filter();
        TABLE_CLICK();
        end.setVisible(false);

    }


    private void LOCAL_TIME() //Время на машине.
    {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        local_time_l.setText("Дата завершения заказа : " + String.valueOf(sqlDate));
        local_time = String.valueOf(sqlDate);
    }
    @FXML
    public void END_TABLE()
    {
        BD_END_TABLE();
        stick_end.setCellValueFactory(new PropertyValueFactory<Date_end, String>("stick_end"));
        model_end.setCellValueFactory(new PropertyValueFactory<Date_end, String>("model_end"));
        master_end.setCellValueFactory(new PropertyValueFactory<Date_end, String>("master_end"));
        dat_end.setCellValueFactory(new PropertyValueFactory<Date_end, String>("dat_end"));
        note_end.setCellValueFactory(new PropertyValueFactory<Date_end, String>("note_end"));
        table_end.setItems(End_Data);
    }

    public void filter() {
        try {
            stick_end.setCellValueFactory(new PropertyValueFactory<Date_end, String>("stick_end"));


            FilteredList<Date_end> filteredData = new FilteredList<>(End_Data, p -> true);

            poisk.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getStick_end().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });


            SortedList<Date_end> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(table_end.comparatorProperty());


            table_end.setItems(sortedData);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void TABLE_CLICK() {
        table_end.getSelectionModel().selectedItemProperty().addListener(
                (obserdler, oldValue, newValue) -> Selectional(newValue)
        );
    }

    public void Selectional(Date_end line) {
        stik  = line.getStick_end();
        String mod = line.getModel_end();
        String tim = line.getDat_end();
        end_line.setText("Заказ № " + stik + '\n'+'\n'+ "Модель : " + mod + '\n'+'\n'+"Дата поступления : " + tim );
    }

    public void update()
    {
        BD_WRITE_END(local_time, stik);
        end.setVisible(true);
        ma.Close_order_end();

    }



}
