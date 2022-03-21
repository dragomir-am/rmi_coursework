import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class ResultsFrame extends javax.swing.JFrame {

    // Variables declaration - do not modify
    private javax.swing.JLabel correct_label;
    private javax.swing.JTextArea user_text_area;
    private javax.swing.JPanel results_panel;
    private javax.swing.JLabel your_label;
    private javax.swing.JTextArea default_text_area;
    public ResultsFrame(ArrayList<String> user_answers, ArrayList<String> right_wrong, int user_score, TestInfo testInfo) throws RemoteException {

        initComponents();

        for (int i = 0; i < 4; i++) {
            user_text_area.append(Arrays.toString(testInfo.get_question(i)) + " " + user_answers.get(i) + "\n" +
                    right_wrong.get(i) + "\n");
        }
        String message = "";
        if (user_score < 3) {
            message = "Study more next time!";
        } else {
            message = "Congratulations!!!!";
        }

        user_text_area.append("Total score is " + user_score + "/4\n" + message + "\n");


    }

    private void initComponents() {

        results_panel = new javax.swing.JPanel();
        correct_label = new javax.swing.JLabel();
        user_text_area = new javax.swing.JTextArea();
        default_text_area = new javax.swing.JTextArea();
        your_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Results");

        results_panel.setBackground(new java.awt.Color(0, 204, 204));

        correct_label.setBackground(new java.awt.Color(255, 255, 255));
        correct_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        correct_label.setForeground(new java.awt.Color(255, 255, 255));
        correct_label.setText("Correct Answers:");

        user_text_area.setEditable(false);
        user_text_area.setBackground(new java.awt.Color(0, 102, 153));
        user_text_area.setColumns(20);
        user_text_area.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        user_text_area.setForeground(new java.awt.Color(255, 255, 255));
        user_text_area.setLineWrap(true);
        user_text_area.setRows(5);
        user_text_area.setWrapStyleWord(true);

        default_text_area.setEditable(false);
        default_text_area.setBackground(new java.awt.Color(0, 153, 153));
        default_text_area.setColumns(20);
        default_text_area.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        default_text_area.setForeground(new java.awt.Color(255, 255, 255));
        default_text_area.setLineWrap(true);
        default_text_area.setRows(5);
        default_text_area.setText("Q1: Good sleep is necessary (D. for) good health.\n\nQ2: I bought him (C. up) with great difficulty.\n\nQ3: Please remove the (A. reed).\n\nQ4: The train is (C. pulling) out of the platform now.");
        default_text_area.setWrapStyleWord(true);

        your_label.setBackground(new java.awt.Color(255, 255, 255));
        your_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        your_label.setForeground(new java.awt.Color(255, 255, 255));
        your_label.setText("Your Answers:");

        javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(results_panel);
        results_panel.setLayout(resultsPanelLayout);
        resultsPanelLayout.setHorizontalGroup(
                resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                .addGroup(resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                                .addGroup(resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                                                .addGap(145, 145, 145)
                                                                .addComponent(correct_label))
                                                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(default_text_area, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                                                .addGap(150, 150, 150)
                                                                .addComponent(your_label)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultsPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(user_text_area, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        resultsPanelLayout.setVerticalGroup(
                resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(correct_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(default_text_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(your_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(user_text_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(results_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(results_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    // End of variables declaration
}
