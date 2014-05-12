package be.beneterwan.gestiongare.launcher;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.applicpostes.ApplicPostes;

/**
 * @author bendem & Curlybear
 */
public class Launcher {
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApplicGare.main(new String[] {});
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApplicDepot.main(new String[] {});
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApplicPostes.main(new String[] {});
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApplicPostes.main(new String[] {});
            }
        }).start();
    }
}
