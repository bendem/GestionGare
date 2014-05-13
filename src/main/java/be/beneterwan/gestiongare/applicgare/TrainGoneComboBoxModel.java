package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 * @author bendem & Curlybear
 */
public class TrainGoneComboBoxModel extends AbstractListModel<HoraireTrain> implements MutableComboBoxModel<HoraireTrain> {

    private final List<HoraireTrain> trains;
    private HoraireTrain current;

    public TrainGoneComboBoxModel(List<HoraireTrain> trains) {
        this.trains = trains;
    }

    @Override
    public void addElement(HoraireTrain e) {
        trains.add(e);
    }

    @Override
    public HoraireTrain getElementAt(int i) {
        return trains.get(i);
    }

    @Override
    public Object getSelectedItem() {
        return current;
    }

    @Override
    public void setSelectedItem(Object o) {
        if(!(o instanceof HoraireTrain)) {
            throw new IllegalArgumentException("Setting incorrect element as selected");
        }
        current = (HoraireTrain) o;
    }

    @Override
    public int getSize() {
        return trains.size();
    }

    @Override
    public void insertElementAt(HoraireTrain e, int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeElement(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeElementAt(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update() {
        fireContentsChanged(this, 0, trains.size());
    }

}
