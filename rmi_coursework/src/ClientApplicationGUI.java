import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public class ClientApplicationGUI extends javax.swing.JFrame {

    private static javax.swing.JTextArea server_display_text;
    private final TestInfo testInfo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton rules_btn;
    private javax.swing.JLabel rules_label;
    private javax.swing.JScrollPane scrollpane_server_text;
    private javax.swing.JButton start_btn;
    private javax.swing.JLabel text_area_label;
    private javax.swing.JLabel title_label;


    public ClientApplicationGUI(TestInfo testInfo) {

        initComponents();
        this.testInfo = testInfo;
    }

    public static void display_server_message(String message) {
        server_display_text.append(message);
    }

    public static void main(String[] args) throws UnknownHostException, ServerNotActiveException, RemoteException,
            MalformedURLException, NotBoundException {
        args = new String[]{"rmi://127.0.0.1/rmi_coursework"};

        if (args.length != 1) {
            System.out.println("usage: java ClientApplication rmi://<machine>/rmi_coursework");
            System.out.println("note: use 127.0.0.1 if running the client on the same computer with the server.");
            return;
        }
        String host = String.valueOf(InetAddress.getLocalHost());

        // Connect to the server

        String serverAddress = args[0].trim();

        TestInfo testInfo = (TestInfo) Naming.lookup(serverAddress);
        testInfo.identify_client(host);
        String message = testInfo.hello_client(host);

        // Start the client

        ClientApplicationGUI clientApplicationGUI = new ClientApplicationGUI(testInfo);
        clientApplicationGUI.setVisible(true);
        display_server_message(message);

    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title_label = new javax.swing.JLabel();
        text_area_label = new javax.swing.JLabel();
        scrollpane_server_text = new javax.swing.JScrollPane();
        server_display_text = new javax.swing.JTextArea();
        start_btn = new javax.swing.JButton();
        rules_btn = new javax.swing.JButton();
        rules_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome Client");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(204, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        title_label.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setText("Hello there! Welcome to the English Test!");
        title_label.setToolTipText("");
        title_label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        title_label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        text_area_label.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        text_area_label.setForeground(new java.awt.Color(255, 255, 255));
        text_area_label.setText("Server message:");

        server_display_text.setEditable(false);
        server_display_text.setColumns(20);
        server_display_text.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        server_display_text.setLineWrap(true);
        server_display_text.setRows(5);
        server_display_text.setWrapStyleWord(true);
        server_display_text.setAlignmentX(1.0F);
        server_display_text.setAlignmentY(1.0F);
        scrollpane_server_text.setViewportView(server_display_text);

        start_btn.setText("Start Test");
        start_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    start_btnActionPerformed(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        });
        rules_btn.setText("Rules");
        rules_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rules_btnActionPerformed(evt);
            }

        });

        rules_label.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rules_label.setForeground(new java.awt.Color(255, 255, 255));
        rules_label.setText("First time? Check out the rules of the test!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(text_area_label, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(title_label)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(rules_label)
                                                                .addComponent(scrollpane_server_text, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addComponent(start_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(rules_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)))))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(title_label)
                                .addGap(31, 31, 31)
                                .addComponent(text_area_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollpane_server_text, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rules_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(start_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rules_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void start_btnActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {
        dispose();
        EnglishTestFrame englishTestFrame = new EnglishTestFrame(testInfo);
        englishTestFrame.setVisible(true);
    }

    private void rules_btnActionPerformed(java.awt.event.ActionEvent evt) {
        String instructions = "There are 4 multiple choices questions, " +
                "you have to select an answer." + "\n" +
                "At the end, click on View Results and the server will display your score\n";
        display_server_message(instructions);
    }


}
