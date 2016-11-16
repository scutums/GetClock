/**
 * Created by Alexander on 16.11.2016.
 */
public class Date_end {

    private String stick_end;
    private String model_end;
    private String master_end;
    private String dat_end;
    private String note_end;

    public Date_end(String stick_end,String model_end,String master_end,String dat_end,String note_end)
    {
        this.stick_end=stick_end;
        this.model_end=model_end;
        this.master_end=master_end;
        this.dat_end=dat_end;
        this.note_end=note_end;
    }

    public Date_end(){}

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


}
