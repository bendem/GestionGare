package be.beneterwan.gestiongare.applicgare;

import java.io.File;
import java.net.URL;

/**
 * @author bendem
 */
public class ApplicGare {

    public static void main(String[] args) {
        ApplicGareFrame.main(args);
    }

    public static URL getRessourceFolder() {
        return ApplicGare.class.getClassLoader().getResource("");
    }

    public static File getRessourceFile(String filename) {
        return new File(getRessourceFolder().getFile() + File.separator + filename);
    }

}
