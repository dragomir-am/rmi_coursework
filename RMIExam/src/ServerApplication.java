

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.sql.Timestamp;


public class ServerApplication extends JFrame {

    private JTextArea logTextArea = new JTextArea();

    /**
     * Initialize the graphical components
     */
    public ServerApplication() {
        setTitle("Server");
        setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(BorderLayout.CENTER, new JScrollPane(logTextArea));

        try {
            File logFile = new File("logFile.txt");
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    /**
     * Put some log message on display for debugging purposes
     */
    public void logMessage(String message) {

        logTextArea.append(message + "\n");
        try {
            FileWriter filestream = new FileWriter("logFile.txt", true);
            BufferedWriter myWriter = new BufferedWriter(filestream);
            myWriter.write(message + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
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
        Naming.rebind("exam", testInfo);
        serverApplication.logMessage("Server has started...");


    }
}
