import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


/**
 * Created by Alexander on 05.11.2016.
 */



public class PrintOrder extends Order {


    static Stage stage;
    @FXML
    private ListView myListView;

    @FXML
    private void initialize() {
        getList();
       myListView.setItems(global);
    }

    @FXML
    public void prin()
    {
      finalSQL();


           // Получаем объект PrinterJob - он будет предоставлять доступ к сервису печати.
        PrinterJob pjob = PrinterJob.getPrinterJob();

        // Устанавливаем задание для печати.
        pjob.setPrintable(new Printable(){
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

                int shag;
                // Поскольку мы печатаем только одну первую (ой! нулевую!) страницу, то отсекаем все остальные.
                if (pageIndex == 0) {
                    // Рисуем на graphics то, что должно быть отпечатано.
                    //graphics.drawString("Прежде чем задать вопрос, прочтите правила форума!!", 100, 100);

                    for (int i = 0; i <8; i++ ) {
                        if (i == 0)
                        { shag = 1;}
                        else {shag = i+1; }

                        graphics.drawString(global.get(i),100,30*shag);
                    }
                    return PAGE_EXISTS;
                }

                return NO_SUCH_PAGE;
            }

        });

        // Выводим на экран стандартное окно для настройки печати.
        if (pjob.printDialog())
            try {
                // Непосредственно печатаем текст.
                pjob.print();

              closes();

            } catch(PrinterException pe) {
                System.out.println("Error printing: " + pe);
            }
    }

}


