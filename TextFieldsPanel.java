package fitnessAppfinal;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class TextFieldsPanel extends JPanel {
        public JTextField textField1, textField2, textField3, textField4;
        public JLabel label1, label2, label3, label4;
        public JPanel panel;

        static String[] textfieldsLabels = {
                        "Distance/m",
                        "Repetition",
                        "Sets",
                        "Calories Burnt"
        };

        TextFieldsPanel() {
                panel = new JPanel();
                panel.setBorder(
                                BorderFactory.createCompoundBorder(panel.getBorder(),
                                                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
                // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setLayout(new GridLayout(4, 1));
                add(panel);

                label1 = new JLabel("Distance/m" + ":", JLabel.LEFT);
                label1.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
                label2 = new JLabel("Repetition" + ":", JLabel.LEFT);
                label2.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
                label3 = new JLabel("Sets" + ":", JLabel.LEFT);
                label3.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
                label4 = new JLabel("Calories Burnt" + ":", JLabel.LEFT);
                label4.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

                textField1 = new JTextField(10);
                textField1.setBorder(BorderFactory.createCompoundBorder(textField1.getBorder(),
                                BorderFactory.createEmptyBorder(6, 6, 6, 6)));

                textField2 = new JTextField(10);
                textField2.setBorder(BorderFactory.createCompoundBorder(textField2.getBorder(),
                                BorderFactory.createEmptyBorder(6, 6, 6, 6)));
                textField3 = new JTextField(10);
                textField3.setBorder(BorderFactory.createCompoundBorder(textField3.getBorder(),
                                BorderFactory.createEmptyBorder(6, 6, 6, 6)));
                textField4 = new JTextField(10);
                textField4.setBorder(BorderFactory.createCompoundBorder(textField4.getBorder(),
                                BorderFactory.createEmptyBorder(6, 6, 6, 6)));

                panel.add(label1);
                panel.add(textField1);

                panel.add(label2);
                panel.add(textField2);

                panel.add(label3);
                panel.add(textField3);

                panel.add(label4);
                panel.add(textField4);

        }
}
