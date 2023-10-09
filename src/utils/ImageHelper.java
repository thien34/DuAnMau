package utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHelper {

//    public static Image getAppIcon() {
//        String file = "/src/image/fpt.png";
//        URL url = ImageHelper.class.getResource(file);
//        return new ImageIcon(url).getImage();
//    }
//    public static Image getAppIcon() {
//        try (InputStream stream = ImageHelper.class.getResourceAsStream("\\src\\image\\fpt.ico")) {
//            return ImageIO.read(stream);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public static Image getAppIcon() {
        try {
            return ImageIO.read(new File("/src/image/fpt.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void saveLogo(File file) {
        File dir = new File("img", file.getName());
        // Tạo thư mục nếu chưa tồn tại 
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại) 
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(dir.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon readLogo(String fileName) {
        File path = new File("img", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(207, 313, Image.SCALE_DEFAULT));
    }
}
