import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * Created by Alexander on 21.11.2016.
 */
public class ReadFoto {
    SQL s = new SQL();
    @FXML
    private Label indic;
    @FXML
    private ImageView fotos;
    @FXML
    private TextField stic;
    @FXML
    private TableView<FOTOCLASS> ta;
    @FXML
    private TableColumn<FOTOCLASS,String> stick_end;
    private InputStream fot;

    @FXML
    private void initialize() {
        TABLE_CLICK();
        teble();
        filter();
    }

    @FXML
    private void go()
    {
  try {
      BufferedImage bIm = ImageIO.read(fot);

      WritableImage ima = SwingFXUtils.toFXImage(bIm, null);
      fotos.setImage(ima);
  }
  catch (Exception e){e.printStackTrace();}

    }

    private void teble()
    {
        s.UP_BACK_NOTE();
        stick_end.setCellValueFactory(new PropertyValueFactory<FOTOCLASS, String>("stick_end"));
        ta.setItems(s.fotoData);


    }
    public void TABLE_CLICK() {
        ta.getSelectionModel().selectedItemProperty().addListener(
                (obserdler, oldValue, newValue) -> Selectional(newValue)
        );
    }

    public void Selectional(FOTOCLASS line) {
        String stik  = line.getStick_end();
        fot = line.foto();
        indic.setText("Заказ № " + stik);
    }

    public void filter() {
        try {
            stick_end.setCellValueFactory(new PropertyValueFactory<FOTOCLASS, String>("stick_end"));


            FilteredList<FOTOCLASS> filteredData = new FilteredList<>(s.fotoData, p -> true);

            stic.textProperty().addListener((observable, oldValue, newValue) -> {
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


            SortedList<FOTOCLASS> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(ta.comparatorProperty());


            ta.setItems(sortedData);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
