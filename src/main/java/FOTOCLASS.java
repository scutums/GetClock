import java.io.InputStream;

/**
 * Created by Alexander on 22.11.2016.
 */
public class FOTOCLASS {
    private String stick_end;
    private InputStream foto;

    public FOTOCLASS(String stick_end, InputStream foto)
    {
        this.stick_end=stick_end;
        this.foto=foto;
    }

    public FOTOCLASS(){}

    public String getStick_end()
    {
        return stick_end;
    }
    public void setStick_end(String stick_end)
    {
        this.stick_end=stick_end;
    }

    public InputStream foto()
    {
        return foto;
    }
    public void setFoto(InputStream foto)
    {
        this.foto=foto;
    }


}
