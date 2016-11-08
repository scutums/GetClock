import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Alexander on 05.11.2016.
 */
public class Main_2 {

    SQL db = new SQL(); //связь с БД




    @FXML
    private void OpenOrder() throws Exception //Новое окно ордера
    {
        Stage orders = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));
        orders.setTitle("Order");
        orders.setScene(new Scene(root));
        orders.setResizable(false);
        orders.show();



    }

    @FXML
    private void OpenDb() // проверка на подключение к бд (тоже окно)
    {

    }

}
