package jmemorize.core.src.jmemorize.gui.swing.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JMemorizeWithShortcuts extends JFrame {

    private JTextArea textArea; // Text area to display flashcards and user interactions
    private boolean isFront = true; // Tracks the current state of the flashcard (front or back)
    private List<String> flashcards; // Placeholder for flashcards
    private int currentFlashcardIndex = 0; // Current flashcard indexx

    public JMemorizeWithShortcuts() {
        setTitle("jMemorize with Keyboard Shortcuts"); // Set the title of the window
        setSize(400, 300); // Set the size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Set default close operation

        textArea = new JTextArea(); // Create a JTextArea for displaying text
        JScrollPane scrollPane = new JScrollPane(textArea); // Add a scroll pane to the text area
        getContentPane().add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the frame's content pane

        // Initialize placeholder flashcards
        flashcards = new ArrayList<>();
        flashcards.add("Flashcard 1");
        flashcards.add("Flashcard 2");
        flashcards.add("Flashcard 3");

        // Create actions for different functionalities

        // Action to flip the flashcard
        Action flipAction = new AbstractAction("Flip Flashcard")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                flipFlashcard(); // Call the method to flip the flashcard
            }
        };
        // Ctrl + F to flip the flashcard
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK), "flipAction");
        textArea.getActionMap().put("flipAction", flipAction);
// Method to flip the flashcard
        private void flipFlashcard(){
            if (isFront) {
                textArea.setText("Back of the flashcard: " + flashcards.get(currentFlashcardIndex));
                isFront = false;
            } else {
                textArea.setText("Front of the flashcard: " + flashcards.get(currentFlashcardIndex));
                isFront = true;
            }
        }
    }
}