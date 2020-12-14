package it.unibo.oop.lab.mvcio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first Java GUI");

    /**
     * @param controller
     *              the controller that permits to save file
     */
    public SimpleGUI(final Controller controller) {
        /*
         * Set up the basic elements
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea text = new JTextArea();
        final JPanel panel1 = new JPanel();
        final LayoutManager layout = new BorderLayout();
        panel1.setLayout(layout);
        /*
         * Add save button
         */
        final JButton save = new JButton("Save");
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
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.show();
    }

}
