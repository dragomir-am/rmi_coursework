import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class EnglishTestFrame extends javax.swing.JFrame {

    private final TestInfo testInfo;
    private final JRadioButton[] option_radio_btn = new JRadioButton[4];
    ArrayList<String> user_answers = new ArrayList<>();
    ArrayList<String> right_wrong = new ArrayList<>();
    private int current_question = 0;
    private int correct_answers = 0;
    private javax.swing.JPanel answers_panel;
    private javax.swing.ButtonGroup option_btns_group;
    private javax.swing.JButton confirm_btn;
    private javax.swing.JLabel option_label;
    private javax.swing.JPanel question_panel;
    private javax.swing.JTextArea question_text;

    public EnglishTestFrame(TestInfo testInfo) throws RemoteException {
        initComponents();

        this.testInfo = testInfo;
        get_questions();

    }

    private void initComponents() {

        option_btns_group = new ButtonGroup();
        question_panel = new JPanel();
        option_label = new JLabel();
        question_text = new JTextArea();
        answers_panel = new JPanel();
        for (int i = 0; i < option_radio_btn.length; i++) {
            option_radio_btn[i] = new JRadioButton();
            option_radio_btn[i].setBackground(new Color(0, 0, 102));
            option_radio_btn[i].setFont(new Font("Arial", 1, 14)); // NOI18N
            option_radio_btn[i].setForeground(new Color(255, 255, 255));
            option_radio_btn[i].setText("");
            option_radio_btn[i].setHorizontalAlignment(SwingConstants.LEFT);
            option_btns_group.add(option_radio_btn[i]);
        }

        confirm_btn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("English Test");
        setAlwaysOnTop(true);
        setBackground(new Color(255, 204, 204));
        setBounds(new Rectangle(0, 0, 1, 1));
        setResizable(false);

        question_panel.setBackground(new Color(0, 51, 102));

        option_label.setFont(new Font("Arial", 1, 14)); // NOI18N
        option_label.setForeground(new Color(255, 255, 255));
        option_label.setHorizontalAlignment(SwingConstants.CENTER);
        option_label.setText("Select an option:");

        question_text.setEditable(false);
        question_text.setBackground(new Color(0, 51, 102));
        question_text.setColumns(20);
        question_text.setFont(new Font("Arial", 0, 18)); // NOI18N
        question_text.setForeground(new Color(255, 255, 255));
        question_text.setLineWrap(true);
        question_text.setRows(5);
        question_text.setText("");
        question_text.setToolTipText("");
        question_text.setAutoscrolls(false);
        question_text.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        question_text.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        GroupLayout question_panelLayout = new GroupLayout(question_panel);
        question_panel.setLayout(question_panelLayout);
        question_panelLayout.setHorizontalGroup(
                question_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, question_panelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(question_text, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(question_panelLayout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(option_label, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        question_panelLayout.setVerticalGroup(
                question_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, question_panelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(question_text, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(option_label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        answers_panel.setBackground(new Color(0, 102, 204));

        confirm_btn.setText("Confirm");
        confirm_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                confirm_btnActionPerformed(evt);
            }
        });

        GroupLayout answers_panelLayout = new GroupLayout(answers_panel);
        answers_panel.setLayout(answers_panelLayout);
        answers_panelLayout.setHorizontalGroup(
                answers_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(answers_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, answers_panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(answers_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(option_radio_btn[2], GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(answers_panelLayout.createSequentialGroup()
                                                .addComponent(option_radio_btn[0], GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                .addGap(136, 136, 136)
                                                .addGroup(answers_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(option_radio_btn[3], GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(option_radio_btn[1], GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
                                .addGap(45, 45, 45))
                        .addGroup(GroupLayout.Alignment.TRAILING, answers_panelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(confirm_btn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145))

        );
        answers_panelLayout.setVerticalGroup(
                answers_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(answers_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(answers_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(option_radio_btn[1], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(option_radio_btn[0], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(answers_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(option_radio_btn[2], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(option_radio_btn[3], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(confirm_btn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(question_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(answers_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(question_panel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(answers_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void get_questions() throws RemoteException {
        try {
            String[] question = testInfo.get_question(current_question);
            if (question == null) {
                dispose();
                ResultsFrame resultsFrame = new ResultsFrame(user_answers, right_wrong, correct_answers, testInfo);
                resultsFrame.setVisible(true);
            } else {
                question_text.setText(question[0]);
                option_btns_group.clearSelection();
            }

            for (int i = 0; i < option_radio_btn.length; i++) {
                option_radio_btn[i].setText(question[i + 1]);
                option_radio_btn[i].setSelected(false);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            dispose();
        }
    }

    private void confirm_btnActionPerformed(ActionEvent evt) {
        // When the submit button is clicked, make sure that a choice
        // has been selected
        int index = -1;

        for (int i = 0; i < option_radio_btn.length; i++) {
            if (option_radio_btn[i].isSelected()) {
                index = i + 1;
                this.user_answers.add(option_radio_btn[i].getText());
                break;
            }
        }

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a choice.");
            return;
        }

        // Send the server the answer for validation
        try {
            if (testInfo.submit_user_answer(current_question, index)) {
                JOptionPane.showMessageDialog(this, "Correct!");
                correct_answers++;
                this.right_wrong.add("Correct!");
            } else {
                JOptionPane.showMessageDialog(this, "Wrong!");
                this.right_wrong.add("Wrong!");
            }
            current_question++;
            get_questions();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.exit(0);
        }
    }

}
