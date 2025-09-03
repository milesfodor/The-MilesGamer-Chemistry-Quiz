import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Quiz extends JFrame {
    //UI elements
    private JPanel mainPanel;
    private JPanel titleScreenPanel;
    private JButton startButton;
    private JPanel quizPanel;
    private JLabel question;
    private JTextField answer;
    private JButton submitButton;
    private JLabel questionCounter;
    private JLabel tipText;
    private JPanel quizSelectorPanel;
    private JButton elementsToSymbols;
    private JButton symbolsToElements;
    private JLabel selectText;
    private JPanel endScreenPanel;
    private JLabel winOrLoseImage;
    private JButton tryAgainButton;
    private JLabel correctAnswersDisplay;
    private JSlider musicSlider;
    private JSlider sfxSlider;
    private JLabel musicText;
    private JLabel sfxText;

    //fields
    private final String[][] temp = {{"Hydrogen", "H"}, {"Helium", "He"}, {"Lithium", "Li"}, {"Beryllium", "Be"}, {"Boron", "B"},
            {"Carbon", "C"}, {"Nitrogen", "N"}, {"Oxygen", "O"}, {"Fluorine", "F"}, {"Neon", "Ne"}, {"Sodium", "Na"}, {"Magnesium", "Mg"},
            {"Aluminum", "Al"}, {"Silicon", "Si"}, {"Phosphorus", "P"}, {"Sulfur", "S"}, {"Chlorine", "Cl"}, {"Argon", "Ar"},
            {"Potassium", "K"}, {"Calcium", "Ca"}, {"Scandium", "Sc"}, {"Titanium", "Ti"}, {"Vanadium", "V"}, {"Chromium", "Cr"},
            {"Manganese", "Mn"}, {"Iron", "Fe"}, {"Cobalt", "Co"}, {"Nickel", "Ni"}, {"Copper", "Cu"}, {"Zinc", "Zn"},
            {"Gallium", "Ga"}, {"Germanium", "Ge"}, {"Arsenic", "As"}, {"Selenium", "Se"}, {"Bromine", "Br"}, {"Krypton", "Kr"}};
    private ArrayList<String[]> elements;
    private String currentElement;
    private int questionNumber;
    private int correctAnswers;
    private boolean firstTime = true;
    private final String[] songs = {"audio/pixel_peeker_polka_-_faster.wav", "audio/fluffing_a_duck.wav",
            "audio/sneaky_snitch.wav", "audio/amazing_plan.wav", "audio/hitman.wav",
            "audio/local_forecast_-_elevator.wav", "audio/scheming_weasel_faster_version.wav",
            "audio/darkest_child.wav", "audio/the_descent.wav", "audio/merry_go.wav",
            "audio/hyperfun.wav", "audio/carefree.wav", "audio/barroom_ballet.wav",
            "audio/cipher.wav", "audio/wallpaper.wav", "audio/investigations.wav",
            "audio/the_builder.wav", "audio/jaunty_gumption.wav", "audio/quirky_dog.wav",
            "audio/dance_of_the_sugar_plum_fairies.wav", "audio/if_i_had_a_chicken.wav", "audio/kevin_macleod_monkeys_spinning_monkeys.wav",
            "audio/kevin_macleod_meatball_parade.wav", "audio/kevin_macleod_run_amok.wav", "audio/kevin_macleod_music_to_delight.wav"};
    private boolean isElementsToSymbols;

    public Quiz() {
        setTitle("The MilesGamer Chemistry Quiz!");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 580);
        setVisible(true);
        setLocationRelativeTo(null);
        AudioPlayer musicPlayer = new AudioPlayer();
        AudioPlayer effectsPlayer = new AudioPlayer();
        musicPlayer.playAudioLoop("audio/its_showtime!.wav");
        titleScreenPanel.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleScreenPanel.setVisible(false);
                quizSelectorPanel.setVisible(true);
            }
        });

        elementsToSymbols.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isElementsToSymbols = true;

                //set ArrayList
                elements = new ArrayList<>(Arrays.asList(temp));

                //set questionNumber back to 1
                questionNumber = 1;
                questionCounter.setText("1/36");

                //set correctAnswers to 0
                correctAnswers = 0;

                //change panels
                quizSelectorPanel.setVisible(false);
                quizPanel.setVisible(true);

                //generate question for random element
                Random random = new Random();
                int randomNumber = random.nextInt(elements.size());
                question.setText("What is the symbol for " + elements.get(randomNumber)[0] + "?");

                //change font size
                Font currentFont = question.getFont();
                Font newFont = currentFont.deriveFont(Font.PLAIN, 25f);
                question.setFont(newFont);

                //remove element from List and store in currentElement
                currentElement = elements.get(randomNumber)[1];
                elements.remove(randomNumber);

                if (firstTime) {
                    tipText.setText("Tip: you can hit enter or the submit button to submit your answer!");
                    firstTime = false;
                } else {
                    tipText.setText("");
                }

                //switch songs
                int randomSong = random.nextInt(songs.length);
                musicPlayer.stopAudio();
                musicPlayer.playAudioLoop(songs[randomSong]);

                answer.setText("");
            }
        });

        symbolsToElements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isElementsToSymbols = false;

                //set ArrayList
                elements = new ArrayList<>(Arrays.asList(temp));

                //set questionNumber back to 1
                questionNumber = 1;
                questionCounter.setText("1/36");

                //set correctAnswers to 0
                correctAnswers = 0;

                //change panels
                quizSelectorPanel.setVisible(false);
                quizPanel.setVisible(true);

                //generate question for random element
                Random random = new Random();
                int randomNumber = random.nextInt(elements.size());
                question.setText("What is the element for " + elements.get(randomNumber)[1] + "?");

                //change font size
                Font currentFont = question.getFont();
                Font newFont = currentFont.deriveFont(Font.PLAIN, 25f);
                question.setFont(newFont);

                //remove element from List and store in currentElement
                currentElement = elements.get(randomNumber)[0];
                elements.remove(randomNumber);

                if (firstTime) {
                    tipText.setText("Tip: you can hit enter or the submit button to submit your answer!");
                    firstTime = false;
                } else {
                    tipText.setText("");
                }

                //switch songs
                int randomSong = random.nextInt(songs.length);
                musicPlayer.stopAudio();
                musicPlayer.playAudioLoop(songs[randomSong]);

                answer.setText("");
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answer.getText().equalsIgnoreCase(currentElement)) {
                    correctAnswers++;
                    tipText.setText("Correct!");
                }

                //increase the questionCounter
                questionNumber++;

                //change questions if quiz not finished yet
                if (questionNumber <= 36) {
                    if (answer.getText().equalsIgnoreCase(currentElement)) {
                        effectsPlayer.playAudio("audio/old_victory_sound_roblox.wav");
                    } else {
                        effectsPlayer.playAudio("audio/loud_incorrect_buzzer_lie__memesound_effect.wav");
                        tipText.setText("Incorrect! The correct answer was " + currentElement);
                    }

                    questionCounter.setText(questionNumber + "/36");

                    //generate question for random element
                    Random random = new Random();
                    int randomNumber = random.nextInt(elements.size());

                    //change question text + answers based on which quiz was selected
                    if (isElementsToSymbols) {
                        question.setText("What is the symbol for " + elements.get(randomNumber)[0] + "?");

                        //remove element from List and store in currentElement
                        currentElement = elements.get(randomNumber)[1];
                        elements.remove(randomNumber);

                        //clear text field
                        answer.setText("");
                    } else {
                        question.setText("What is the element for " + elements.get(randomNumber)[1] + "?");

                        //remove element from List and store in currentElement
                        currentElement = elements.get(randomNumber)[0];
                        elements.remove(randomNumber);

                        //clear text field
                        answer.setText("");
                    }
                } else { //if quiz is finished
                    if (correctAnswers == 36) {
                        displayWinScreen();
                        musicPlayer.stopAudio();
                        musicPlayer.playAudioLoop("audio/rick_and_jerry_dancing_while_pickle_rick_trap_remix_plays_red_green_yellow_blue_version_hd_4k.wav");
                    } else {
                        displayLoseScreen();
                        musicPlayer.stopAudio();
                        musicPlayer.playAudio("audio/sad_trombone_-_sound_effect_hd.wav");
                    }
                }
            }
        });

        answer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answer.getText().equalsIgnoreCase(currentElement)) {
                    correctAnswers++;
                    tipText.setText("Correct!");
                }

                //increase the questionCounter
                questionNumber++;

                //change questions if quiz not finished yet
                if (questionNumber <= 36) {
                    if (answer.getText().equalsIgnoreCase(currentElement)) {
                        effectsPlayer.playAudio("audio/old_victory_sound_roblox.wav");
                    } else {
                        effectsPlayer.playAudio("audio/loud_incorrect_buzzer_lie__memesound_effect.wav");
                        tipText.setText("Incorrect! The correct answer was " + currentElement);
                    }

                    questionCounter.setText(questionNumber + "/36");

                    //generate question for random element
                    Random random = new Random();
                    int randomNumber = random.nextInt(elements.size());

                    //change question text + answers based on which quiz was selected
                    if (isElementsToSymbols) {
                        question.setText("What is the symbol for " + elements.get(randomNumber)[0] + "?");

                        //remove element from List and store in currentElement
                        currentElement = elements.get(randomNumber)[1];
                        elements.remove(randomNumber);

                        //clear text field
                        answer.setText("");
                    } else {
                        question.setText("What is the element for " + elements.get(randomNumber)[1] + "?");

                        //remove element from List and store in currentElement
                        currentElement = elements.get(randomNumber)[0];
                        elements.remove(randomNumber);

                        //clear text field
                        answer.setText("");
                    }
                } else { //if quiz is finished
                    if (correctAnswers == 36) {
                        displayWinScreen();

                        //switch songs
                        musicPlayer.stopAudio();
                        musicPlayer.playAudioLoop("audio/rick_and_jerry_dancing_while_pickle_rick_trap_remix_plays_red_green_yellow_blue_version_hd_4k.wav");
                    } else {
                        displayLoseScreen();

                        //switch songs
                        musicPlayer.stopAudio();
                        musicPlayer.playAudio("audio/sad_trombone_-_sound_effect_hd.wav");
                    }
                }
            }
        });

        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endScreenPanel.setVisible(false);
                quizSelectorPanel.setVisible(true);
                musicPlayer.stopAudio();
                musicPlayer.playAudioLoop("audio/its_showtime!.wav");
            }
        });

        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!musicSlider.getValueIsAdjusting()) {
                    float volume = musicSlider.getValue() / 100.0f;
                    musicPlayer.setVolume(volume);
                }
            }
        });

        sfxSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!sfxSlider.getValueIsAdjusting()) {
                    float volume = sfxSlider.getValue() / 100.0f;
                    effectsPlayer.setVolume(volume);
                }
            }
        });
    }

    private void displayWinScreen() {
        //switch panels
        quizPanel.setVisible(false);
        endScreenPanel.setVisible(true);

        Icon winIcon = new ImageIcon(getClass().getResource("/images/a4w39g.gif"));
        winOrLoseImage.setIcon(winIcon);
        correctAnswersDisplay.setText("Congratulations! You got every question correct.");
    }

    private void displayLoseScreen() {
        //switch panels
        quizPanel.setVisible(false);
        endScreenPanel.setVisible(true);

        Icon loseIcon = new ImageIcon(getClass().getResource("/images/a4vue5.jpg")); //TO-DO: create loseIcon, add retry button and correctAnswers to endScreen
        winOrLoseImage.setIcon(loseIcon);
        correctAnswersDisplay.setText("You got " + correctAnswers + "/36 correct");
    }
}
