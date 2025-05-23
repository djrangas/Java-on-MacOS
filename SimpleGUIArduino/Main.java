import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import com.fazecast.jSerialComm.*;

public class Main {
    private static SerialPort arduinoPort;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test One");
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Control LED with Arduino");
        frame.add(label);
        JButton onButton = new JButton("Turn ON LED");
        frame.add(onButton);
        JButton offButton = new JButton("Turn OFF LED");
        frame.add(offButton);

        onButton.addActionListener(e -> sendCommandToArduino("1"));
        offButton.addActionListener(e -> sendCommandToArduino("0"));

        setupArduinoConnection();
        frame.setVisible(true);
    }

    private static void setupArduinoConnection() {
        arduinoPort = SerialPort.getCommPort("/dev/tty.usbmodem11201");
        arduinoPort.setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (!arduinoPort.openPort()) {
            System.out.println("Failed to open port!");
        } else {
            System.out.println("Connected to Arduino.");
        }
    }

    private static void sendCommandToArduino(String command) {
        if (arduinoPort != null && arduinoPort.isOpen()) {
            try (OutputStream outputStream = arduinoPort.getOutputStream()) {
                outputStream.write(command.charAt(0));
                outputStream.flush();
                System.out.println("Sent command: " + command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Arduino is not connected.");
        }
    }
}