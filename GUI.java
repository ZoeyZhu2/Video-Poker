/*****************************************
 * GUI class
 * Zoey Zhu
 ****************************************/ 

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {

    //private static final int BUTTON_TEXT_SIZE = 36;
    //private static final Font BUTTON_FONT = new Font("Serif", Font.BOLD, BUTTON_TEXT_SIZE);
    
    private double bankroll;
    private Game game;
    private Player player;
    private Deck deck;
    private int betAmount;
    ArrayList<JToggleButton> selectedCards = new ArrayList<>();
    ArrayList<Integer> selectedIndices = new ArrayList<>();

    //Constructor
    public GUI() {
        //making the window with a title 
        //implicit call to super
        //because GUI is a subclass of JFrame, we can use JFrame methods directly
        setTitle("Video Poker");
        setSize(1940,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adding contents to the window
        //initializing components first

        //JLabel displays text or images
        //JButton makes a button
        //JSpinner makes a spinner
        JLabel welcomeLabel = new JLabel("Welcome to Video Poker!");
        JLabel narrationLabel = new JLabel("Click 'Start Game' to begin playing.");
        JButton startButton = new JButton("Start Game");
        JLabel bankrollLabel = new JLabel("Bankroll: " + bankroll + " Tokens");
        JButton betButton = new JButton("Place Bet");
        JSpinner betSpinner = new JSpinner(new SpinnerNumberModel(1,1,5,1));
        JButton exchangeButton = new JButton("Exchange Cards");
        JButton nextRoundButton = new JButton("Next Round");
        JButton endButton = new JButton("End Game");



        //making the player's hand
        JToggleButton[] cardButtons = new JToggleButton[5];
        JPanel handPanel = new JPanel(new GridLayout(1,5));
        for (int i = 0; i < 5; i++) {
            cardButtons[i] = new JToggleButton("Card " + (i+1));
            handPanel.add(cardButtons[i]);
            final int index = i;
            cardButtons[index].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (cardButtons[index].isSelected()) {
                        if (!selectedCards.contains(cardButtons[index])) {
                            selectedCards.add(cardButtons[index]);
                            selectedIndices.add(index);
                        }
                    } else {
                        selectedCards.remove(cardButtons[index]);
                        selectedIndices.remove(Integer.valueOf(index));}
                }
            });
        }

        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 48));

        narrationLabel.setHorizontalAlignment(JLabel.CENTER);
        narrationLabel.setFont(new Font("Serif", Font.PLAIN, 24));

        bankrollLabel.setFont(new Font("Serif", Font.BOLD, 24));
        bankrollLabel.setHorizontalAlignment(JLabel.LEFT);

        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font("Serif", Font.BOLD, 36));

        betButton.setBackground(Color.WHITE);
        betButton.setForeground(Color.BLACK);
        betButton.setFont(new Font("Serif", Font.BOLD, 36));

        betSpinner.setToolTipText("Select your bet amount (1-5)");
        JComponent betSpinnerEditor = betSpinner.getEditor();
        JFormattedTextField betSpinnerTextField = ((JSpinner.DefaultEditor) betSpinnerEditor).getTextField();
        betSpinnerTextField.setFont(new Font("Serif", Font.BOLD, 36));
        betSpinnerTextField.setHorizontalAlignment(JTextField.CENTER);

        exchangeButton.setBackground(Color.WHITE);
        exchangeButton.setForeground(Color.BLACK);
        exchangeButton.setFont(new Font("Serif", Font.BOLD, 36));

        nextRoundButton.setBackground(Color.WHITE);
        nextRoundButton.setForeground(Color.BLACK);
        nextRoundButton.setFont(new Font("Serif", Font.BOLD, 36));


        endButton.setBackground(Color.WHITE);
        endButton.setForeground(Color.BLACK);
        endButton.setFont(new Font("Serif", Font.BOLD, 36));

        //Connecting Buttons to Functionality
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                narrationLabel.setText("Game started! Place your bet to begin.");
                game = new Game();
                player = game.getPlayer();
                deck = game.getDeck();
                bankroll = player.getBankroll();
                bankrollLabel.setText("Bankroll: " + (int)bankroll + " Tokens");
                betSpinner.setEnabled(true);
                betButton.setEnabled(true);
                for (int i = 0; i < 5; i++) {
                    cardButtons[i].setEnabled(true);
                }
                exchangeButton.setEnabled(false);
                nextRoundButton.setEnabled(false);
                endButton.setEnabled(true);
            }
        });

        betButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (betAmount > player.getBankroll()) {
                    narrationLabel.setText("Insufficient tokens! Please place a smaller bet.");
                    return;
                }
                betSpinner.setEnabled(false);
                narrationLabel.setText("You placed a bet of " + betAmount + " tokens. Click 'Exchange Cards' to exchange cards.");
                player.bets(betAmount);
                bankrollLabel.setText("Bankroll: " + player.getBankroll() + " Tokens");
                betButton.setEnabled(false);
                exchangeButton.setEnabled(true);
                for (int i = 0; i < 5; i++) {
                    cardButtons[i].setIcon(getCardIcon(player.getHand().get(i)));
                    cardButtons[i].setText("");
                }

            }
        });

        betSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                betAmount = (int) betSpinner.getValue();
            }
        });

        exchangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 5; i++) {
                    cardButtons[i].setEnabled(false);
                }
                exchangeButton.setEnabled(false);
                for (int i = 0; i < selectedIndices.size(); i++) {
                    int ogIndex = selectedIndices.get(i);
                    player.exchangeCard(ogIndex, deck.deal());
                    cardButtons[ogIndex].setIcon(getCardIcon(player.getHand().get(ogIndex)));
                }

                //checking results now
                String result = game.checkHand(player.getHand());
                // narrationLabel.setText("You got " + result + "!");
                double multiplier = game.getMultiplier(result);
                double payout = betAmount * multiplier;
                player.winnings(multiplier);
                // narrationLabel.setText("Payout: " + (int)payout + " tokens");
                bankrollLabel.setText("Bankroll: " + player.getBankroll() + " Tokens");
                // narrationLabel.setText("Click 'Next Round' to play again or 'End Game' to quit.");
                if ((int)player.getBankroll() <= 0) {
                    narrationLabel.setText("Cards exchanged! You got " + result + "! Your payout is " + (int)payout + " tokens. You are out of tokens :(. Game over! Please click 'End Game' to quit.");
                }
                else
                {
                    narrationLabel.setText("Cards exchanged! You got " + result + "! Your payout is " + (int)payout + " tokens. Click 'Next Round' to play again or 'End Game' to quit.");
                    nextRoundButton.setEnabled(true);
                }
            }
        });

        nextRoundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextRoundButton.setEnabled(false);
                player.clearHand();
                while (selectedCards.size() > 0) {
                    selectedCards.remove(0).setSelected(false);
                }
                while (selectedIndices.size() > 0) {
                    selectedIndices.remove(0);
                }
                deck = new Deck();
                deck.shuffle();
                for (int i = 0; i < 5; i++) {
                    player.addCard(deck.deal());
                }
                for (int i = 0; i < 5; i++) {
                    cardButtons[i].setIcon(null);
                    cardButtons[i].setText("Card " + (i+1));
                }
                betSpinner.setEnabled(true);
                betButton.setEnabled(true);
                for (int i = 0; i < 5; i++) {
                    cardButtons[i].setEnabled(true);
                }
                narrationLabel.setText("New round started! Place your bet to begin.");
            }
        });

        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                narrationLabel.setText("Game ended. Thanks for playing!");
                //disable all buttons except start button
                betButton.setEnabled(false);
                betSpinner.setEnabled(false);
                exchangeButton.setEnabled(false);
                nextRoundButton.setEnabled(false);
                endButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });

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

        //Disabling all unnecessary buttons at the start
        betSpinner.setEnabled(false);
        betButton.setEnabled(false);
        for (int i = 0; i < 5; i++) {
            cardButtons[i].setEnabled(false);
        }
        exchangeButton.setEnabled(false);
        nextRoundButton.setEnabled(false);
        endButton.setEnabled(false);

        //making the window visible
        setVisible(true);
    }

    //main method to launch the window
    public static void main(String[] args) {
        GUI gui = new GUI();
    }

    public ImageIcon getCardIcon(Card c) {
        String name = c.toString();
        String fileName = "Card Images/" + name + ".png";
        return new ImageIcon(fileName);
    }


}
