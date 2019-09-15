// Pakkaus
package hy.happoni.compremator3000.ui;

/**
 * Tekstikäyttöliittymästä huolehtiva luokka.
 */
public class UI {
    
    private String testi;
    private AppLogic logic;
    
    public UI() {
        this.logic = new AppLogic();
        this.testi = "testi";
    }
    
    public void startApp() {
        logic.runLZW();
        logic.runLZ();
        logic.runLZSS();
    }
}
