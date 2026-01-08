/*****************************************
 * GUI class
 * Zoey Zhu
 ****************************************/ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    //private static final int BUTTON_TEXT_SIZE = 36;
    //private static final Font BUTTON_FONT = new Font("Serif", Font.BOLD, BUTTON_TEXT_SIZE);
    
    private int bankroll;

    //need to eventually connect GUI to functionality


    //Constructor
    public GUI() {
        //making the window with a title 
        //implicit call to super
        //because GUI is a subclass of JFrame, we can use JFrame methods directly
        setTitle("Video Poker");
        setSize(1940,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adding contents to the window
        //JLabel displays text or images
        JLabel welcomeLabel = new JLabel("Welcome to Video Poker!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 48));

        //must add the JLabel to the JFrame to display it;

        //narration label
        JLabel narrationLabel = new JLabel("Click 'Start Game' to begin playing.");
        narrationLabel.setHorizontalAlignment(JLabel.CENTER);
        narrationLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        //To do: add listeners to the buttons. Update narration labels as buttons are clicked.

        //JButton makes a button
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font("Serif", Font.BOLD, 36));
        //make start button unclickable after clicked once to start game

        JLabel bankrollLabel = new JLabel("Bankroll: " + bankroll + " Tokens");
        bankrollLabel.setFont(new Font("Serif", Font.BOLD, 24));
        bankrollLabel.setHorizontalAlignment(JLabel.LEFT);


        JButton betButton = new JButton("Place Bet");
        betButton.setBackground(Color.WHITE);
        betButton.setForeground(Color.BLACK);
        betButton.setFont(new Font("Serif", Font.BOLD, 36));

        //JSpinner makes a spinner
        JSpinner betSpinner = new JSpinner(new SpinnerNumberModel(1,1,5,1));
        betSpinner.setToolTipText("Select your bet amount (1-5)");
        JComponent betSpinnerEditor = betSpinner.getEditor();
        JFormattedTextField betSpinnerTextField = ((JSpinner.DefaultEditor) betSpinnerEditor).getTextField();
        betSpinnerTextField.setFont(new Font("Serif", Font.BOLD, 36));
        betSpinnerTextField.setHorizontalAlignment(JTextField.CENTER);


        JButton exchangeButton = new JButton("Exchange Cards");
        exchangeButton.setBackground(Color.WHITE);
        exchangeButton.setForeground(Color.BLACK);
        exchangeButton.setFont(new Font("Serif", Font.BOLD, 36));

        //make it so that I click on the cards I want to exchange
        //make it so that exchange button becomes unclickable after one use per round

        JButton nextRoundButton = new JButton("Next Round");
        nextRoundButton.setBackground(Color.WHITE);
        nextRoundButton.setForeground(Color.BLACK);
        nextRoundButton.setFont(new Font("Serif", Font.BOLD, 36));
        //make it so that next round button cannot be clicked until the exchange button is clicked

        JButton endButton = new JButton("End Game");
        endButton.setBackground(Color.WHITE);
        endButton.setForeground(Color.BLACK);
        endButton.setFont(new Font("Serif", Font.BOLD, 36));

        //making the player's hand
        JToggleButton[] cardButtons = new JToggleButton[5];
        JPanel handPanel = new JPanel(new GridLayout(1,5));
        for (int i = 0; i < 5; i++) {
            cardButtons[i] = new JToggleButton("Card " + (i+1));
            handPanel.add(cardButtons[i]);
        }

        // Top panel: vertical BoxLayout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.NORTH);
        topPanel.add(bankrollLabel, BorderLayout.WEST);

        // In-game button panel
        JPanel inGameButtonPanel = new JPanel();
        inGameButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        inGameButtonPanel.add(betSpinner);
        inGameButtonPanel.add(betButton);
        inGameButtonPanel.add(exchangeButton);
        inGameButtonPanel.add(nextRoundButton);

        // Start/end button panel
        JPanel startEndButtonPanel = new JPanel();
        startEndButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startEndButtonPanel.add(startButton);
        startEndButtonPanel.add(endButton);

        // South panel: vertical BoxLayout
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        narrationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inGameButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startEndButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        southPanel.add(narrationLabel);
        southPanel.add(Box.createVerticalStrut(10));
        southPanel.add(inGameButtonPanel);
        southPanel.add(Box.createVerticalStrut(10));
        southPanel.add(startEndButtonPanel);

        // Main layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(handPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        //making the window visible
        setVisible(true);
    }

    //main method to launch the window
    public static void main(String[] args) {
        GUI gui = new GUI();
    }

    //method to get card icons for each card --- TO DO LATER ---
    public ImageIcon getCardIcon(Card c) {
        String name = c.toString();
        //somehow replace spaces with underscores
        String fileName = "Images/" + name + ".png";
        return new ImageIcon(fileName);
    }


}
