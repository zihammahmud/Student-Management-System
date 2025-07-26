import javax.swing.SwingUtilities;
import ui.HomePage;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage());
    }
}
