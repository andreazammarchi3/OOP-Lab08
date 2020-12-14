package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("Simple GUI with file chooser");

    /**
     * @param controller
     *              the controller that permits to save file
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        /*
         * Set up the basic elements
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea text = new JTextArea();
        final JPanel panel1 = new JPanel();
        final JTextField filePath = new JTextField();
        filePath.setText(controller.getCurrentFilePath());
        final JButton browse = new JButton("Browse");
        final JButton save = new JButton("Save");
        final LayoutManager layout = new BorderLayout();
        panel1.setLayout(layout);

        /*
         * Buttons actions
         */
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                    final JFileChooser jfc = new JFileChooser("Choose where to save");
                    jfc.setSelectedFile(controller.getCurrentFile());
                    final int result = jfc.showSaveDialog(frame);
                    switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDest = jfc.getSelectedFile();
                        controller.setDestination(newDest);
                        filePath.setText(newDest.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "OPS!", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.save(text.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*
         * GUI assemblage
         */
        final JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(filePath, BorderLayout.CENTER);
        upperPanel.add(browse, BorderLayout.LINE_END);
        panel1.add(upperPanel, BorderLayout.NORTH);
        panel1.add(text, BorderLayout.CENTER);
        panel1.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel1);

        /*
         * Set up the view
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }

    /*
     * Method that makes visible the GUI window
     */
    private void show() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.show();
    }

}

