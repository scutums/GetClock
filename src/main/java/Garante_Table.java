import java.io.InputStream;

/**
 * Created by Alexander on 22.11.2016.
 */
public class Garante_Table {
    private String stick_end;//
    private String model_end;//
    private String master_end;//
    private String guarante;//
    private String note_end;//
    private String client;//
    private String phone;//
    private Integer payment;//
    private Integer id_client;//

    public Garante_Table(String stick_end ,String model_end, String master_end, String guarante, String note_end, String client, String phone,Integer payment, Integer id_client)
    {
        this.stick_end=stick_end;
        this.model_end=model_end;
        this.master_end=master_end;
        this.guarante=guarante;
        this.note_end=note_end;
        this.client=client; //
        this.phone=phone;
        this.payment=payment;
        this.id_client=id_client;

    }

    public Garante_Table(){}

    public String getStick_end()
    {
        return stick_end;
    }
    public void setStick_end(String stick_end)
    {
        this.stick_end=stick_end;
    }

    public String getModel_end()
    {
        return model_end;
    }
    public void setModel_end(String model_end)
    {
        this.model_end=model_end;
    }

    public String getMaster_end()
    {
        return master_end;
    }
    public void setMaster_end(String master_end)
    {
        this.master_end=master_end;
    }

    public String getGuarante()
    {
        return guarante;
    }
    public void setGuarante(String guarante)
    {
        this.guarante=guarante;
    }

    public String getNote_end()
    {
        return note_end;
    }
    public void setNote_end(String note_end)
    {
        this.note_end=note_end;
    }

    public String getClient()
    {
        return client;
    }
    public void setClient(String client)
    {
        this.client=client;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
    }

    public Integer getPayment()
    {
        return payment;
    }
    public void setPayment(Integer payment)
    {
        this.payment=payment;
    }

    public  Integer getId_client()
    {
        return id_client;
    }
    public void setId_client(Integer id_client)
    {
        this.id_client=id_client;
    }

}
