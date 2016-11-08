import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

/**
 * Created by Alexander on 05.11.2016.
 */
public class PrintOrder {

  //  Order or = new Order();
    @FXML
    private ListView myListView;
    public ObservableList<String> g = FXCollections.observableArrayList();

    @FXML
    public void lists()
    {
        ListView<String> list = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        myListView.setItems(items);

        g.add("wwww");

    }
    @FXML
    public void prin(){
        // Получаем объект PrinterJob - он будет предоставлять доступ к сервису печати.
        PrinterJob pjob = PrinterJob.getPrinterJob();


        ArrayList<String> lot = new ArrayList<>();
        lot.add("qaz");
        lot.add("1qaz");
        lot.add("q2az");
        lot.add("qa3z");
        lot.add("qaz4");
        lot.add("5qaz");



        // Устанавливаем задание для печати.
        pjob.setPrintable(new Printable(){
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

                // Поскольку мы печатаем только одну первую (ой! нулевую!) страницу, то отсекаем все остальные.
                if (pageIndex == 0) {
                    // Рисуем на graphics то, что должно быть отпечатано.
                    //graphics.drawString("Прежде чем задать вопрос, прочтите правила форума!!", 100, 100);

                    for (int i = 1; i <5; i++ ) {
                        graphics.drawString(lot.get(i),100*i,100);
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
            } catch(PrinterException pe) {
                System.out.println("Error printing: " + pe);
            }
    }

}
