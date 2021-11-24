package ru.vsu.sc.uliyanov_n_s;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.vsu.sc.uliyanov_n_s.error.ErrorMessage;
import ru.vsu.sc.uliyanov_n_s.utils.ArrayUtils;
import ru.vsu.sc.uliyanov_n_s.utils.JTableUtils;
import ru.vsu.sc.uliyanov_n_s.utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableOutput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonArrayTransform;
    private JButton buttonSaveOutputInFile;
    private JButton buttonInputRandom;
    private JButton buttonSaveInputInFile;
    private JTable tableInput;
    private JTextField textFieldForRow;
    private JLabel label;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    public FrameMain() {
        this.setTitle("Task 8");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(700, 100, 300, 300);
        this.setResizable(false);
        this.pack();

        ArrayTransform arrayTransform = new ArrayTransform();

        JTableUtils.initJTableForArray(tableInput, 30, true, true, true, true, 25, 15);
        JTableUtils.initJTableForArray(tableOutput, 30, true, true, true, true, 25, 15);
        tableInput.setRowHeight(30);
        tableOutput.setRowHeight(30);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        buttonLoadInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        assert arr != null;
                        JTableUtils.writeArrayToJTable(tableInput, arr, "%d");
                    }
                } catch (Exception e) {
                    ErrorMessage.printErrorMessage(1);
                }
            }
        });

        buttonSaveInputInFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        Integer[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput, Integer.class, Integer::parseInt, false, 0);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    ErrorMessage.printErrorMessage(2);
                }
            }
        });

        buttonSaveOutputInFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        Integer[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput, Integer.class, Integer::parseInt, false, 0);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    ErrorMessage.printErrorMessage(2);
                }
            }
        });

        buttonArrayTransform.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable1(tableInput);
                    int numRaw = Integer.parseInt(textFieldForRow.getText());
                    try {
                        assert matrix != null;
                        arrayTransform.transformArray(matrix, numRaw);
                    } catch (Exception e) {
                        ErrorMessage.printErrorMessage(4);
                    }
                    JTableUtils.writeArrayToJTable(tableOutput, matrix, "%d");
                } catch (Exception e) {
                    ErrorMessage.printErrorMessage(0);
                }
            }
        });

        buttonInputRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = ArrayUtils.createRandomIntMatrix(
                            tableInput.getRowCount(), tableInput.getColumnCount(), 0, 100);
                    JTableUtils.writeArrayToJTable(tableInput, matrix, "%d");
                } catch (Exception e) {
                    ErrorMessage.printErrorMessage(3);
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(5, 1, new Insets(10, 10, 10, 10), 10, 10));
        panelMain.setBackground(new Color(-1));
        panelMain.setMaximumSize(new Dimension(1000, 1000));
        panelMain.setMinimumSize(new Dimension(700, 500));
        panelMain.setPreferredSize(new Dimension(700, 700));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        panel1.setForeground(new Color(-10162));
        panelMain.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonLoadInputFromFile = new JButton();
        Font buttonLoadInputFromFileFont = this.$$$getFont$$$(null, -1, 14, buttonLoadInputFromFile.getFont());
        if (buttonLoadInputFromFileFont != null) buttonLoadInputFromFile.setFont(buttonLoadInputFromFileFont);
        buttonLoadInputFromFile.setText("Выбрать из файла");
        panel1.add(buttonLoadInputFromFile, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonInputRandom = new JButton();
        Font buttonInputRandomFont = this.$$$getFont$$$(null, -1, 14, buttonInputRandom.getFont());
        if (buttonInputRandomFont != null) buttonInputRandom.setFont(buttonInputRandomFont);
        buttonInputRandom.setText("Заполнить случайными значениями");
        panel1.add(buttonInputRandom, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonSaveInputInFile = new JButton();
        Font buttonSaveInputInFileFont = this.$$$getFont$$$(null, -1, 14, buttonSaveInputInFile.getFont());
        if (buttonSaveInputInFileFont != null) buttonSaveInputInFile.setFont(buttonSaveInputInFileFont);
        buttonSaveInputInFile.setText("Сохранить в файл");
        panel1.add(buttonSaveInputInFile, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1));
        Font panel2Font = this.$$$getFont$$$(null, -1, 18, panel2.getFont());
        if (panel2Font != null) panel2.setFont(panel2Font);
        panel2.setForeground(new Color(-10162));
        panelMain.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonArrayTransform = new JButton();
        Font buttonArrayTransformFont = this.$$$getFont$$$(null, -1, 14, buttonArrayTransform.getFont());
        if (buttonArrayTransformFont != null) buttonArrayTransform.setFont(buttonArrayTransformFont);
        buttonArrayTransform.setText("Выполнить преобразование");
        panel2.add(buttonArrayTransform, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label = new JLabel();
        label.setBackground(new Color(-13421773));
        label.setFocusCycleRoot(true);
        Font labelFont = this.$$$getFont$$$(null, -1, 16, label.getFont());
        if (labelFont != null) label.setFont(labelFont);
        label.setForeground(new Color(-13421773));
        label.setText("Номер строки для сортировки:");
        panel2.add(label, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldForRow = new JTextField();
        Font textFieldForRowFont = this.$$$getFont$$$(null, -1, 18, textFieldForRow.getFont());
        if (textFieldForRowFont != null) textFieldForRow.setFont(textFieldForRowFont);
        textFieldForRow.setText("1");
        textFieldForRow.setToolTipText("");
        panel2.add(textFieldForRow, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(1, 1), new Dimension(45, 30), new Dimension(45, 30), 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setForeground(new Color(-13421773));
        panelMain.add(scrollPane1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableOutput = new JTable();
        scrollPane1.setViewportView(tableOutput);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1));
        panel3.setForeground(new Color(-10162));
        panelMain.add(panel3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonSaveOutputInFile = new JButton();
        Font buttonSaveOutputInFileFont = this.$$$getFont$$$(null, -1, 14, buttonSaveOutputInFile.getFont());
        if (buttonSaveOutputInFileFont != null) buttonSaveOutputInFile.setFont(buttonSaveOutputInFileFont);
        buttonSaveOutputInFile.setText("Сохранить в файл");
        panel3.add(buttonSaveOutputInFile, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel3.add(spacer5, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel3.add(spacer6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panelMain.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableInput = new JTable();
        scrollPane2.setViewportView(tableInput);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
