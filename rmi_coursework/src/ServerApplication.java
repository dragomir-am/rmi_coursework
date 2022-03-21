import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Timestamp;


public class ServerApplication extends JFrame {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private final JTextArea log_text_area = new JTextArea();

    /**
     * Initialize the graphical components
     */
    public ServerApplication() {
        setTitle("Server");
        setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(BorderLayout.CENTER, new JScrollPane(log_text_area));

        try {
            File log_file = new File("logFile.txt");
            if (log_file.createNewFile()) {
                System.out.println("File created: " + log_file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Entry point of the program
     */
    public static void main(String[] args) throws Exception {

        // Create the testInfo application
        ServerApplication serverApplication = new ServerApplication();
        serverApplication.setVisible(true);

        // Start the testInfo
        TestInfoImpl testInfo = new TestInfoImpl(serverApplication);

        try {

            // Try using port 1099
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            // If port 1099 fails, try using any current port
            LocateRegistry.getRegistry();
        }

        // Bind the testInfo
        Naming.rebind("rmi_coursework", testInfo);
        serverApplication.log_message(testInfo.time + "Server has started...");


    }

    /**
     * Put some log message on display for debugging purposes
     */
    public void log_message(String message) {

        log_text_area.append(message + "\n");
        try {
            FileWriter filestream = new FileWriter("logFile.txt", true);
            BufferedWriter my_writer = new BufferedWriter(filestream);
            my_writer.write(message + "\n");
            my_writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

    public String hello_client(String client) throws RemoteException {
        String format = "[" + timestamp + "]:";
        String instructions = "If you already know how to play, click Start, else click Rules";
        String message = format + "Hello " + client + "\n" + instructions + "\n";
        return message;
    }
}
