package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Simple GUI");
    private final Controller controller;

    /**
     * @param controller is the controller used by SimpleGUI
     */
    public SimpleGUI(final SimpleController controller) {
        this.controller = controller;
        /*
         * Set up the basic elements
         */
        final JTextField stringField = new JTextField();
        final JTextArea stringArea = new JTextArea();
        final JButton printButton = new JButton("Print");
        final JButton showHistoryButton = new JButton("Show history");
        final JPanel canvas = new JPanel();
        final JPanel buttonsPanel = new JPanel();
        final LayoutManager layout = new BorderLayout();

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                SimpleGUI.this.controller.setNextStringToPrint(stringField.getText());
                SimpleGUI.this.controller.printCurrentString();
            }
        });

        showHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final StringBuilder textBuilder = new StringBuilder();
                final List<String> history = SimpleGUI.this.controller.getPrintedStrings();
                for (final String print : history) {
                    textBuilder.append(print);
                    textBuilder.append('\n');
                }
                if (history.isEmpty()) {
                    textBuilder.deleteCharAt(textBuilder.length() - 1);
                }
                stringArea.setText(textBuilder.toString());
            }
        });
        /*
         * GUI assemblage
         */
        canvas.setLayout(layout);
        stringField.setBackground(Color.LIGHT_GRAY);
        canvas.add(stringField, BorderLayout.NORTH);
        stringArea.setEditable(false);
        canvas.add(stringArea, BorderLayout.CENTER);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        canvas.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.add(printButton);
        buttonsPanel.add(showHistoryButton);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Frame size
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        /*
         * Frame location
         */
        frame.setLocationByPlatform(true);
    }
    /**
     * 
     */
    private void show() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        new SimpleGUI(new SimpleController()).show();
    }

}
