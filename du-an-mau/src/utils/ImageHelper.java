package utils;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class ImageHelper {

    public static Image logoApp() {
        File file = new File("src/image", "fpt.png");
        Image image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
        return image;
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
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon readLogo(String fileName) {
        File path = new File("img", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(207, 313, Image.SCALE_DEFAULT));
    }
}
