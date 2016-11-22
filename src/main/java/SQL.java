import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexander on 05.11.2016.
 */
public class SQL {
    private static final String URL = "jdbc:mysql://localhost:3306/clock?zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private Statement statement;
    public ObservableList<Client_table> Client_Data = FXCollections.observableArrayList();
    public ObservableList<Date_end> End_Data = FXCollections.observableArrayList();
    public ObservableList<Pay_End> Pay_Data = FXCollections.observableArrayList();
    public ObservableList<Order_back> BACK_Data = FXCollections.observableArrayList();
    public ObservableList<String>masterData = FXCollections.observableArrayList();
    public ObservableList<FOTOCLASS>fotoData = FXCollections.observableArrayList();
    public List<String> stikk = new ArrayList<String>();
    public Integer Client_id_clock;
    public Integer Clock_id_clock;





    public SQL()
    {
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            if(!connection.isClosed())
            {
                //System.out.println( "Соединение с БД установлено");
            }
            statement = (Statement) connection.createStatement();

        } catch (SQLException e){ e.printStackTrace();}
    }

    public void BDWORK_READ_CLIENT()// метод заполнения таблицы базы клиентов
    {

        try {
            ResultSet tab = statement.executeQuery("select * from client_tb;");

            int x = tab.getMetaData().getColumnCount();

            while (tab.next()) {
                int A1 = tab.getInt(1);
                String A2 = tab.getString(2);
                String A3 = tab.getString(3);
                String A4 = tab.getString(4);
                String A5 = tab.getString(5);
                Client_Data.add(new Client_table(A1,A2,A3,A4,A5));
            }
        }catch (SQLException e){e.printStackTrace();}
    }

    public void BDWOKR_READ_MASTER() // таблица мастер чтение
    {
        try {
            ResultSet master = statement.executeQuery("SELECT id_master, name_master FROM master_tb;");

            while (master.next())
            {
                int master_id = master.getInt(1);
                String A2 = master.getString(2);
                String A = master_id +" "+ A2;
                masterData.addAll(A);
            }
        }catch (SQLException e){e.printStackTrace();}

    }

    public void BDWOKR_READ_STICK() // таблица мастер чтение
    {
        try {
            ResultSet stickss = statement.executeQuery("SELECT id_stick FROM repair_tb;");

            while (stickss.next())
            {
                String X = stickss.getString(1);
                stikk.add(X);
            }
        }catch (SQLException e){e.printStackTrace();}

    }

    public void BDWORK_WRITE_CLIENT(String FIO, String PHON_CLI, String ADRES, String NOTE_CLI)
    {
        try {
            ArrayList id = new ArrayList();

            ResultSet id_client = statement.executeQuery("select * from client_tb");
            while (id_client.next()) {
                int i = id_client.getInt("id_client");
                id.add(i);
            }
            System.out.println(id);
            Integer maxKey = 0;
            if (!id.isEmpty())
            {
                maxKey = (Integer) Collections.max(id);
            }
            Client_id_clock = maxKey + 1;
            String name_client = FIO;
            String phone_cl = PHON_CLI;
            String adres = ADRES;
            String note_cl = NOTE_CLI;

            String stri = "insert into client_tb values(?,?,?,?,?)";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(stri);
            prep.setInt(1, Client_id_clock);
            prep.setString(2, name_client);
            prep.setString(3, phone_cl);
            prep.setString(4, adres);
            prep.setString(5, note_cl);


            prep.execute();
        } catch (SQLException e){e.printStackTrace();}
    }

    public void BDWOKR_WRITE_CLOCK(String MODEL_CL, int CLIENT_ID_CL) //таблица цасы запись
    {
        try {
            ArrayList ids = new ArrayList();

            ResultSet id_clock = statement.executeQuery("select * from clock_tb");
            while (id_clock.next()) {
                int i = id_clock.getInt("id_clock");
                ids.add(i);
            }
            Integer maxKey = 0;
            if (!ids.isEmpty())
            {
                maxKey = (Integer) Collections.max(ids);
            }
            System.out.println(maxKey);
            Clock_id_clock = maxKey + 1;
            String model_clock = MODEL_CL;
            int id_clients = CLIENT_ID_CL;


            String strit = "insert into clock_tb values(?,?,?)";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(strit);
            prep.setInt(1, Clock_id_clock);
            prep.setString(2, model_clock);
            prep.setInt(3, id_clients);


            prep.execute();
        } catch (SQLException e){e.printStackTrace();}
    }

    public void BDWORK_WRITE_REPAIR(int clock_id, int id_master, String stick, String date_start, String date_end,int value, int payment,String note,InputStream image, int INDIC)
    {
        try {
            ArrayList ids = new ArrayList();

            ResultSet id_repair = statement.executeQuery("select * from repair_tb");
            while (id_repair.next())
            {
                int i = id_repair.getInt("id_repair");
                ids.add(i);
            }
            Integer maxKeys = 0;
            if (!ids.isEmpty())
            {
                maxKeys = (Integer) Collections.max(ids);
            }
            Integer id_repa =maxKeys + 1;
            int id_cloc = clock_id;
            int id_maste = id_master;
            String stikc = stick;
            String date_st = date_start;
            int vale = value;
            int paym = payment;
            String notes = note;
            InputStream imeg = image;
            String dat_en = date_end;
            int ind = INDIC;


            String striq = "insert into repair_tb values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(striq);
            prep.setInt(1, id_repa);
            prep.setInt(2, id_cloc);
            prep.setInt(3, id_maste);
            prep.setString(4, stikc);
            prep.setString(5, date_st);
            prep.setString(6, "0000-00-00");
            prep.setInt(7, vale);
            prep.setInt(8, paym);
            prep.setString(9,"0000-00-00");
            prep.setString(10,notes);
            prep.setBlob(11,imeg);
            prep.setString(12, dat_en);
            prep.setInt(13, ind);


            prep.execute();
        } catch (SQLException e){e.printStackTrace();}
    }

    public void BD_END_TABLE() //выборка в завершение работы
    {
        try {
            ResultSet tabl = statement.executeQuery("SELECT repair_tb.id_stick, clock_tb.model, master_tb.name_master, repair_tb.date_start, repair_tb.note_repair FROM repair_tb, master_tb, clock_tb WHERE repair_tb.id_clock = clock_tb.id_clock AND repair_tb.id_master = master_tb.id_master AND repair_tb.indicator = '1'  ;");

            while (tabl.next())
            {
                String A1 = tabl.getString(1);
                String A2 = tabl.getString(2);
                String A3 = tabl.getString(3);
                String A4 = tabl.getString(4);
                String A5 = tabl.getString(5);
                End_Data.add(new Date_end(A1,A2,A3,A4,A5));
            }

        }catch (SQLException e){e.printStackTrace();}

    }

    public void BD_PAY_TABLE() //выборка
    {
         try {
            ResultSet tabl = statement.executeQuery("SELECT repair_tb.id_stick, clock_tb.model, master_tb.name_master, repair_tb.date_start, repair_tb.date_end, repair_tb.note_repair, client_tb.name_client, client_tb.phone_client, repair_tb.value, repair_tb.payment FROM repair_tb, master_tb, clock_tb, client_tb WHERE repair_tb.id_clock = clock_tb.id_clock AND repair_tb.id_master = master_tb.id_master AND clock_tb.id_client = client_tb.id_client AND repair_tb.indicator = '2';");

            while (tabl.next())
            {
                String A1 = tabl.getString(1);
                String A2 = tabl.getString(2);
                String A3 = tabl.getString(3);
                String A4 = tabl.getString(4);
                String A5 = tabl.getString(5);
                String A6 = tabl.getString(6);
                String A7 = tabl.getString(7);
                String A8 = tabl.getString(8);
                Integer A9 = tabl.getInt(9);
                Integer A10 = tabl.getInt(10);
                Pay_Data.add(new Pay_End(A1,A2,A3,A4,A5,A6,A7,A8,A9,A10));
            }

        }catch (SQLException e){e.printStackTrace();}

    }

    public void BD_WRITE_END(String DAT, String STIC)
    {
        try {
            String A1 = DAT;
            String A2 = STIC;

            String strit_end = "UPDATE repair_tb SET date_end = ?, indicator = '2' WHERE id_stick = ?";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(strit_end);
            prep.setString(1,A1);
            prep.setString(2,A2);

            prep.executeUpdate();

        } catch (SQLException e){e.printStackTrace();}
    }

    public void BD_PAY_WRITE(String DATES_LOCAL, String DATES, Integer PAY, String STIK)
    {
        try{
            String A1 = DATES_LOCAL;
            String A2 = DATES;
            Integer A3 = PAY;
            String A4 = STIK;

            String pay_end = "UPDATE repair_tb SET date_pay = ?, guarantee = ? , payment = ?, indicator = '3' WHERE id_stick = ?;";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(pay_end);
            prep.setString(1,A1);
            prep.setString(2,A2);
            prep.setInt(3,A3);
            prep.setString(4,A4);
            prep.executeUpdate();
        }
        catch (SQLException e){e.printStackTrace();}

    }

    public void UP_PLUS_NOTE(String STIK, String NOTE)
    {

        try {
            String A1 = NOTE;
            String A2 = STIK;

            String plus = "UPDATE repair_tb SET note_repair = CONCAT (note_repair , ? ) WHERE id_stick = ?";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(plus);
            prep.setString(1,A1);
            prep.setString(2,A2);
            prep.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}
    }

    public void BD_BACK_TABLE() //выборка
    {
        try {
            ResultSet tabl = statement.executeQuery("SELECT repair_tb.id_stick, clock_tb.model, master_tb.name_master, repair_tb.date_start, repair_tb.date_end, repair_tb.note_repair, client_tb.name_client, client_tb.phone_client, client_tb.adres , repair_tb.value, repair_tb.payment , client_tb.note_client  FROM repair_tb, master_tb, clock_tb, client_tb WHERE repair_tb.id_clock = clock_tb.id_clock AND repair_tb.id_master = master_tb.id_master AND clock_tb.id_client = client_tb.id_client AND repair_tb.indicator = '1' || repair_tb.indicator = '2'");

            while (tabl.next())
            {
                String A1 = tabl.getString(1);
                String A2 = tabl.getString(2);
                String A3 = tabl.getString(3);
                String A4 = tabl.getString(4);
                String A5 = tabl.getString(5);
                String A6 = tabl.getString(6);
                String A7 = tabl.getString(7);
                String A8 = tabl.getString(8);
                String A9 = tabl.getString(9);
                Integer A10 = tabl.getInt(10);
                Integer A11 = tabl.getInt(11);
                String A12 = tabl.getString(12);
                BACK_Data.add(new Order_back(A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12));
            }

        }catch (SQLException e){e.printStackTrace();}

    }

    public void UP_CL_BED(String PHON, String NOTE)
    {
        try {
            String A1 = NOTE;
            String A2 = PHON;
            String plus = "UPDATE client_tb SET note_client = CONCAT (note_client , ?) WHERE phone_client = ?;";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(plus);
            prep.setString(1,A1);
            prep.setString(2,A2);
            prep.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}
    }

    public void UP_BACK_BED(String STIK, String NOTE)
    {
        try {
            String A1 = NOTE;
            String A2 = STIK;
            String plus = "UPDATE repair_tb SET date_pay = ?, payment = '0', indicator='4' WHERE id_stick = ?;";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(plus);
            prep.setString(1,A1);
            prep.setString(2,A2);
            prep.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}
    }

    public void UP_BACK_NOTE()
    {
        try {
            ResultSet pluss = statement.executeQuery("SELECT id_stick, images  FROM repair_tb");
            while (pluss.next())
            {
                String A1 = pluss.getString(1);
                InputStream A2 = pluss.getBinaryStream(2);
                fotoData.add(new FOTOCLASS(A1,A2));

            }

        }catch (SQLException e){e.printStackTrace();}
    }



    public void WRITE_MASTER(String NAME_MAS, Integer PH_MAS, String NOTE_MAS) // добавеление мастера
    {
        try {
            ArrayList id = new ArrayList();

            ResultSet id_client = statement.executeQuery("select * from master_tb");
            while (id_client.next()) {
                int i = id_client.getInt("id_master");
                id.add(i);
            }
            Integer maxKey = 0;
            if (!id.isEmpty())
            {
                maxKey = (Integer) Collections.max(id);
            }
            Integer Id_Master = maxKey + 1;
            String name_master = NAME_MAS;
            int phone_master = PH_MAS;
            String note_master = NOTE_MAS;

            String stri = "insert into master_tb values(?,?,?,?)";
            PreparedStatement prep = (PreparedStatement) connection.prepareStatement(stri);
            prep.setInt(1, Id_Master);
            prep.setString(2, name_master);
            prep.setInt(3, phone_master);
            prep.setString(4,note_master);


            prep.execute();
        } catch (SQLException e){e.printStackTrace();}

    }


}
