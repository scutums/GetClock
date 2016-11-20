import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Alexander on 05.11.2016.
 */
public class Main_2 {

    //SQL db = new SQL(); //связь с БД
    @FXML
    public static Stage orders;
    @FXML
    public static Stage orders1;
    @FXML
    public static Stage orders2;
    @FXML
    public static Stage orders3;




    @FXML
    private void OpenOrder() throws Exception //Новое окно ордера
    {
        orders = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));
        orders.setTitle("Оформление заказа");
        orders.setScene(new Scene(root));
        orders.setResizable(false);
        orders.show();



    }

    @FXML
    private void OpenDb() // проверка на подключение к бд (тоже окно)
    {

    }

    @FXML
    private void EndOrder() throws Exception
    {
        orders1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OrderEnd.fxml"));
        orders1.setTitle("Ремонт заказа");
        orders1.setScene(new Scene(root));
        orders1.setResizable(false);
        orders1.show();
    }

    @FXML
    private void PayOrder() throws Exception
    {
        orders2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OrderPay.fxml"));
        orders2.setTitle("Выдача заказа");
        orders2.setScene(new Scene(root));
        orders2.setResizable(false);
        orders2.show();
    }
    @FXML
    private void BackOrder() throws Exception
    {
        orders3 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OrderBack.fxml"));
        orders3.setTitle("Возврат заказа");
        orders3.setScene(new Scene(root));
        orders3.setResizable(false);
        orders3.show();
    }
    @FXML
    public void Close_order()
    {
        orders.close();
    }
    @FXML
    public void Close_order_end()
    {
        orders1.close();
    }
    @FXML
    public void Close_order_pay()
    {
        orders2.close();
    }
    @FXML
    public void Close_order_back()
    {
        orders3.close();
    }

}
