
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.server.RemoteServer;
import java.sql.Timestamp;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class ClientApplication extends JFrame implements ActionListener {

    private TestInfo testInfo;

    private JLabel questionLabel = new JLabel("Question...", SwingConstants.CENTER);
    private JRadioButton[] optionsRadioButton = new JRadioButton[4];
    private ButtonGroup radioButtonsGroup = new ButtonGroup();

    private int currentQuestion = 0;
    private int totalQuestions = 0;
    private int correctAnswers = 0;

    /**
     * Create the client application
     */
    public ClientApplication(TestInfo testInfo) {
        setTitle("Client");
        setSize(500, 45 * 3);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBackground(Color.BLACK);
        questionLabel.setOpaque(true);
        add(BorderLayout.NORTH, questionLabel);

        JPanel optionsPanel = new JPanel(new GridLayout(1, optionsRadioButton.length));
        optionsPanel.setBackground(Color.WHITE);
        add(BorderLayout.CENTER, optionsPanel);

        for (int i = 0; i < optionsRadioButton.length; i++) {
            optionsRadioButton[i] = new JRadioButton();
            optionsRadioButton[i].setBackground(Color.WHITE);
            optionsRadioButton[i].setOpaque(true);
            radioButtonsGroup.add(optionsRadioButton[i]);
            optionsPanel.add(optionsRadioButton[i]);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(BorderLayout.SOUTH, submitButton);

        this.testInfo = testInfo;
        //getNextQuestion();
    }

    /**
     * Load the next question to the user interface
     */
    private void getNextQuestion() {
        try {
            String[] question = testInfo.getQuestion(currentQuestion);

            // Game's over. No more questions
            if (question == null) {
                JOptionPane.showMessageDialog(this, "Exam finished. You got " + correctAnswers + " out of " + totalQuestions + ".");
                System.exit(0);
            }

            totalQuestions++;
            questionLabel.setText(question[0]);
            
            radioButtonsGroup.clearSelection();

            for (int i = 0; i < optionsRadioButton.length; i++) {
                optionsRadioButton[i].setText(question[i + 1]);
                optionsRadioButton[i].setSelected(false);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(0);
        }
    }

    /**
     * Handle the button events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // When the submit button is clicked, make sure that a choice
        // has been selected
        int index = -1;

        for (int i = 0; i < optionsRadioButton.length; i++) {
            if (optionsRadioButton[i].isSelected()) {
                index = i + 1;
                break;
            }
        }

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a choice.");
            return;
        }

        // Send the server the answer for validation
        try {
            if (testInfo.submitAnswer(currentQuestion, index)) {
                JOptionPane.showMessageDialog(this, "Correct!");
                correctAnswers++;
            } else {
                JOptionPane.showMessageDialog(this, "Wrong!");
            }

            currentQuestion++;
            getNextQuestion();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.exit(0);
        }
    }

    /**
     * Entry point of the program
     */
    public static void main(String[]  args) throws Exception {
        args = new String[] { "rmi://127.0.0.1/exam" };

        if (args.length != 1) {
            System.out.println("usage: java ClientApplication rmi://<machine>/exam");
            System.out.println("note: use 127.0.0.1 if running the client on the same computer with the server.");
            return;
        }
        String host = String.valueOf(InetAddress.getLocalHost());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Connect to the server
        String serverAddress = args[0].trim();

        TestInfo testInfo = (TestInfo) Naming.lookup(serverAddress);
        testInfo.identifyClient(host, String.valueOf(timestamp));
        // Start the client
        ClientApplication clientApplication = new ClientApplication(testInfo);
        clientApplication.setVisible(true);
    }
}
