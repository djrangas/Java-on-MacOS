import com.fazecast.jSerialComm.SerialPort;

public class SerialTest {
    public static void main(String[] args) {
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Available COM Ports:");
        for (SerialPort port : ports) {
            System.out.println(" - " + port.getSystemPortName());
        }

        SerialPort arduinoPort = SerialPort.getCommPort("<port>");
        arduinoPort.setBaudRate(9600);

        if (!arduinoPort.openPort()) {
            System.out.println("Failed to connect to Arduino!");
            return;
        }

        System.out.println("Connected to Arduino on " + arduinoPort.getSystemPortName());
    }
}