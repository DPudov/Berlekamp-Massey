import model.binary.BinaryArchiver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * GUI of the application
 */
public class BMArchiverGUI extends JFrame {

    private JButton archivateButton;
    private JButton dearchivateButton;
    private JPanel mainPanel;
    private JMenuBar menuBar;

    public BMArchiverGUI() {

        this.setTitle("BMArchiver");
        createMenuBar();
        archivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Архивировать файл");
                if (ret == JFileChooser.APPROVE_OPTION) {

                    BinaryArchiver binaryArchiver = new BinaryArchiver();
                    try {
                        binaryArchiver.archiveFile(fileopen.getSelectedFile().getPath());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(dearchivateButton.getParent(),
                                "Невозможно архивировать данный файл!",
                                "Ошибка исполнения",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        dearchivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Деархивировать файл");
                if (ret == JFileChooser.APPROVE_OPTION) {

                    BinaryArchiver binaryArchiver = new BinaryArchiver();
                    try {
                        binaryArchiver.dearchiveFile(fileopen.getSelectedFile().getPath());
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(dearchivateButton.getParent(),
                                "Невозможно разархивировать данный файл!",
                                "Ошибка исполнения",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void createMenuBar() {

        JMenu file = new JMenu("About");
        file.setMnemonic(KeyEvent.VK_A);
        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dearchivateButton.getParent(), "BMArchiver - архиватор на основе алгоритма Берлекэмпа-Месси. Проект для конкурса \"Шаг в будущее\"", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuBar.add(file);
        setJMenuBar(menuBar);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("BMArchiverGUI");
        frame.setContentPane(new BMArchiverGUI().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(250, 250, 250, 250);
        frame.setVisible(true);
    }


}
