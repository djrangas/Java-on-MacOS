import com.fazecast.jSerialComm.*;
import java.io.OutputStream;
import java.util.Scanner;

public class SimpleConsoleArduino {
    private SerialPort arduinoPort;

    public SimpleConsoleArduino() {
        setupArduinoConnection();
    }

    private void setupArduinoConnection() {
        arduinoPort = SerialPort.getCommPort("<port>");
        arduinoPort.setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (!arduinoPort.openPort()) {
            System.out.println("Failed to open port!");
        } else {
            System.out.println("Connected to Arduino.");
        }
    }

    private void sendCommandToArduino(String command) {
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

    public static void main(String[] args) {
        SimpleConsoleArduino console = new SimpleConsoleArduino();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter command (1 to turn ON LED, 0 to turn OFF LED, q to quit): ");
            command = scanner.nextLine();

            if (command.equals("1")) {
                console.sendCommandToArduino("1");
            } else if (command.equals("0")) {
                console.sendCommandToArduino("0");
            } else if (command.equalsIgnoreCase("q")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid command. Please enter 1, 0, or q.");
            }
        }

        scanner.close();
    }
}
