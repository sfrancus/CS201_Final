package project.projectfiles;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CarController {
    private CarUnit car;
    public CarController(CarUnit e)
    {
        this.car = e;
    }
    public void actionOnClick()
    {
        JDialog carDialogPane = new JDialog();
        carDialogPane.setSize(new Dimension(400, 400));
        carDialogPane.setTitle("Car Information");
        carDialogPane.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JPanel dialogPanel = new JPanel();
        JLabel speed = new JLabel("Speed: " + this.car.speed + " mph ");
        dialogPanel.add(speed);
        carDialogPane.add(dialogPanel);
        carDialogPane.setLocationRelativeTo(null);
        carDialogPane.setVisible(true);
    }
}
