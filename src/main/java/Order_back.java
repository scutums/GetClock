/**
 * Created by Alexander on 19.11.2016.
 */
public class Order_back extends Pay_End{

    private String stick_end;
    private String model_end;
    private String master_end;
    private String start_end;
    private String dat_end;
    private String note_end;
    private String client;
    private String phone;
    private String adres;
    private Integer value;
    private Integer payment;
    private String note_cl;

   public Order_back(String stick_end ,String model_end, String master_end, String start_end, String dat_end, String note_end, String client, String phone,String adres, Integer value, Integer payment , String note_cl)
   {
       this.stick_end=stick_end;
       this.model_end=model_end;
       this.master_end=master_end;
       this.start_end=start_end;
       this.dat_end=dat_end;
       this.note_end=note_end;
       this.client=client; //
       this.phone=phone;
       this.adres=adres;
       this.value=value;
       this.payment=payment;
       this.note_cl=note_cl;
   }

    public Order_back(){}

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


    public String getStart_end()
    {
        return start_end;
    }
    public void setStart_end(String start_end)
    {
        this.start_end=start_end;
    }

    public String getDat_end()
    {
        return dat_end;
    }
    public void setDat_end(String dat_end)
    {
        this.dat_end=dat_end;
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

    public String getAdres()
    {
        return adres;
    }
    public void setAdres(String adres)
    {
        this.adres=adres;
    }

    public Integer getValue()
    {
        return value;
    }
    public void setValue(Integer value)
    {
        this.value=value;
    }

    public Integer getPayment()
    {
        return payment;
    }
    public void setPayment(Integer payment)
    {
        this.payment=payment;
    }

    public String getNote_cl()
    {
        return note_cl;
    }
    public void setNote_cl(String note_cl)
    {
        this.note_cl=note_cl;
    }

}
