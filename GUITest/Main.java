import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUITest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test GUI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Hello, World!");
        JButton button = new JButton("Click Me");
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button Clicked!"));
        frame.add(label);
        frame.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
