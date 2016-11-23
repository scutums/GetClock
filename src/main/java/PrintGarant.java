import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


/**
 * Created by Alexander on 23.11.2016.
 */

public class PrintGarant {
    OrderGarant or = new OrderGarant();
    @FXML
    private ListView myListView;

    @FXML
    private void initialize() {
        myListView.setItems(or.global);

    }

    @FXML
    public void print()
    {


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

                    for (int i = 0; i <14; i++ ) {
                        if (i == 0)
                        { shag = 1;}
                        else {shag = i+1; }

                        graphics.drawString(or.global.get(i),30,10*shag);
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

                or.closes();

            } catch(PrinterException pe) {
                System.out.println("Error printing: " + pe);
            }
    }

}



