package be.beneterwan.gestiongare.commons.trains;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author bendem & Curlybear
 */
public class HoraireComparator implements Comparator<HoraireTrain>, Serializable {

    @Override
    public int compare(HoraireTrain t, HoraireTrain t1) {
        return Integer.compare(t.getDepart(), t1.getDepart());
    }

}
