package be.beneterwan.gestiongare.commons;

import java.net.URL;

/**
 * @author bendem et Curlybear
 */
public class ResourceManager {

    public static URL getResourceFile(String filename) {
        return ResourceManager.class.getClassLoader().getResource(filename);
    }

}