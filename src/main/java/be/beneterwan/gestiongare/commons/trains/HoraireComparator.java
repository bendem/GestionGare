package be.beneterwan.gestiongare.commons.trains;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author bendem & Curlybear
 */
public class HoraireComparator implements Comparator<HoraireTrain>, Serializable {

    @Override
    public int compare(HoraireTrain t, HoraireTrain t1) {
        if(t == null) {
            return 1;
        }
        if(t1 == null) {
            return -1;
        }
        return Integer.compare(t.getArrivee(), t1.getArrivee());
    }

}
