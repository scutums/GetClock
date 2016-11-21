import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Alexander on 18.11.2016.
 */
public class Webcam_order
{

    @FXML
    private ImageView foto;
    Webcam webcam = Webcam.getDefault();
    @FXML
    private Label error;
    public BufferedImage image;
    public InputStream fotos;

    @FXML
    private void initialize()
    {
        if (webcam != null) {
            error.setText("Webcam: " + webcam.getName());
        } else {
            error.setText("No webcam detected");
        }

    }

    @FXML
    private void prin() throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        webcam.open();
        image = webcam.getImage();
        ImageIO.write(image, "png", baos);

        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        webcam.close();
        WritableImage ima = SwingFXUtils.toFXImage(image,null);
        foto.setImage(ima);


        Order or = new Order();
        or.mas = is;
        System.out.print(or.mas);
    }
}
