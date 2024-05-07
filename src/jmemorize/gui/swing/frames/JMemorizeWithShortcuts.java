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
    // Action to navigate to the next flashcard
    Action nextAction = new AbstractAction("Next Flashcard") {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextFlashcard(); // Call the method to navigate to the next flashcard
        }
    };  // Ctrl + N to navigate to the next flashcard
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), "nextAction");
        textArea.getActionMap().put("nextAction", nextAction);
    // Method to navigate to the next flashcard
    private void nextFlashcard() {
        currentFlashcardIndex = (currentFlashcardIndex + 1) % flashcards.size();
        textArea.setText("Front of the flashcard: " + flashcards.get(currentFlashcardIndex));
        isFront = true;
    }
    // Action to navigate to the previous flashcard
    Action prevAction = new AbstractAction("Previous Flashcard") {
        @Override
        public void actionPerformed(ActionEvent e) {
            prevFlashcard(); // Call the method to navigate to the previous flashcard
        }
    }; // Ctrl + P to navigate to the previous flashcard
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK), "prevAction");
        textArea.getActionMap().put("prevAction", prevAction);// Method to navigate to the previous flashcard
    private void prevFlashcard() {
        currentFlashcardIndex = (currentFlashcardIndex - 1 + flashcards.size()) % flashcards.size();
        textArea.setText("Front of the flashcard: " + flashcards.get(currentFlashcardIndex));
        isFront = true;
    }
    // Action to save flashcards
    Action saveAction = new AbstractAction("Save Flashcards") {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveFlashcards(); // Call the method to save flashcards
        }
    };  // Ctrl + S to save flashcards
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "saveAction");
        textArea.getActionMap().put("saveAction", saveAction);
    // Method to save flashcards
    private void saveFlashcards() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("flashcards.txt"))) {
            for (String flashcard : flashcards) {
                writer.println(flashcard);
            }
            textArea.append("Flashcards saved!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
