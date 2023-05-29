import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GUI1 {
    public static int K;
    static JPanel mainPanel = new JPanel(new GridBagLayout());
    public static final JFrame frame1 = new JFrame();
    public static final JFrame frame2 = new JFrame();
    static JButton buttonEasy, buttonMedium, buttonHard;
    static JLabel labelDisplay1, labelDisplay2, labelDisplay3, sky;
    static Icon clearIcon, generateIcon, solveIcon, returnIcon;
    static final JTextField[][] textField;
    private static final GridPanel gridPanel;
    public static GridBagConstraints gridBagConstraints;
    static JButton exampleButton, returnButton, solveButton, clearButton, checkButton;
    public static Timer timer1;
    public static long stopWatch;
    static JLabel stopWatchDisplay;
    static int minutes, seconds;
    static ImageIcon kubok;

    /**
     * GUI1() - конструктор, в котором создаются:
     *     - основное окно, на котором размещаются все элементы;
     *     - фон окна;
     *     - текст приветствия;
     *     - 3 кнопки, отвечающие за уровни сложности судоку
     */
    GUI1() {
        labelDisplay1 = new JLabel();
        labelDisplay2 = new JLabel();
        labelDisplay3 = new JLabel();

        sky = new JLabel();
        sky.setVisible(true);
        sky.setBounds(0,0,600,395);
        frame1.add(sky);
        Icon icon1 = new ImageIcon("images/sky.png");
        sky.setIcon(icon1);

        labelDisplay1.setText("Good afternoon!");
        labelDisplay2.setText("Today we're going to develop your mind!");
        labelDisplay3.setText("Please, choose the level!");

        labelDisplay1.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        labelDisplay1.setForeground(Color.GRAY);
        labelDisplay1.setVisible(true);
        labelDisplay1.setBounds(200, 240, 550, 30);
        frame1.add(labelDisplay1);

        labelDisplay2.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        labelDisplay2.setForeground(Color.GRAY);
        labelDisplay2.setVisible(true);
        labelDisplay2.setBounds(55, 275, 550, 35);
        frame1.add(labelDisplay2);

        labelDisplay3.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        labelDisplay3.setForeground(Color.GRAY);
        labelDisplay3.setVisible(true);
        labelDisplay3.setBounds(160, 310, 550, 30);
        frame1.add(labelDisplay3);

        buttonEasy = new JButton("EASY");
        buttonEasy.setVisible(true);
        buttonEasy.setBounds(150, 370, 300, 50);
        buttonEasy.setFocusPainted(false);
        buttonEasy.setBackground(Color.WHITE);
        buttonEasy.setForeground(Color.GRAY);
        buttonEasy.setBorderPainted(true);
        frame1.add(buttonEasy);

        buttonMedium = new JButton("MEDIUM");
        buttonMedium.setVisible(true);
        buttonMedium.setBounds(150, 430, 300, 50);
        buttonMedium.setFocusPainted(false);
        buttonMedium.setBackground(Color.WHITE);
        buttonMedium.setForeground(Color.GRAY);
        buttonMedium.setBorderPainted(true);
        frame1.add(buttonMedium);

        buttonHard = new JButton("HARD");
        buttonHard.setVisible(true);
        buttonHard.setBounds(150, 490, 300, 50);
        buttonHard.setFocusPainted(false);
        buttonHard.setBackground(Color.WHITE);
        buttonHard.setForeground(Color.GRAY);
        buttonHard.setBorderPainted(true);
        frame1.add(buttonHard);

        returnIcon = new ImageIcon("images/return.jpg");
        generateIcon = new ImageIcon("images/Write.jpg");
        clearIcon = new ImageIcon("images/Erase.jpg");
        solveIcon = new ImageIcon("images/Generate.jpg");

        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame1.setSize(600, 600);
        frame1.getContentPane().add(mainPanel);
        frame1.setLocationRelativeTo(null);
        mainPanel.setBackground(Color.WHITE);
        frame1.setVisible(true);
        frame1.setResizable(false);
        frame1.setTitle("Sudoku Solver");
        buttonEasy.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI1.buttonEasyClicked();
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }

            }
        });
        buttonMedium.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI1.buttonMediumClicked();
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }

            }
        });
        buttonHard.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI1.buttonHardClicked();
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }

            }
        });

        for(int y = 0; y < 9; ++y) {
            for(int x = 0; x < 9; ++x) {
                textField[x][y] = new JTextField();
                textField[x][y].setForeground(Color.BLACK);
                textField[x][y].setHorizontalAlignment(JTextField.CENTER);
                textField[x][y].setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
                gridPanel.add(textField[x][y]);
            }
        }

    }


    /**
     * проверяет корректность введенного текста - он должен быть целым числом от 1 до 9
     *
     */

    static boolean checkText() {
        for(int y = 0; y < 9; ++y) {
            for(int x = 0; x < 9; ++x) {
                if (!textField[x][y].getText().equals("")) {
                    try {
                        int digit = Integer.parseInt(textField[x][y].getText());
                        if (digit <= 0 || digit >= 10) {
                            return false;
                        }
                    } catch (NumberFormatException var3) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * очищает поле судоку
     */
    public static void clearGrid() {
        for(int y = 0; y < 9; ++y) {
            for(int x = 0; x < 9; ++x) {
                textField[x][y].setText("");
                textField[x][y].setForeground(Color.BLACK);
            }
        }

    }

    /**
     * запускает всю программу в классе SudokuStart
     */
    public void createGUI() {
    }

    /**
     * buttonEasyClicked(), buttonMediumClicked(), buttonHardClicked():
     *     в каждом методе присваивается соответствующее значение К - числу скрытых ячеек,
     *     которое зависит от уровня сложности;
     *     запускается метод, создающий судоку
     */
    public static void buttonEasyClicked() {
        K = 10;
        createSUDOKU();
    }

    public static void buttonMediumClicked() {
        K = 40;
        createSUDOKU();
    }

    public static void buttonHardClicked() {
        K = 50;
        createSUDOKU();
    }


    /**
     * запускает секундомер, считает количество секунд, пршедших с клика по кнопке Generate Example,
     *     конвертирует секунды в минуты и выводит в поле секундомера
     */
    private static void startStopWatch() {
        if (timer1!=null) {
            timer1.stop();
        }

        stopWatch = System.currentTimeMillis();
        timer1 = new Timer(10, e -> {
            long elapsedTime = System.currentTimeMillis() - stopWatch;
            minutes = (int) (elapsedTime / 60000);
            seconds = (int) ((elapsedTime / 1000) % 60);

            String countTime = String.format("%02d:%02d", minutes, seconds);
            stopWatchDisplay.setText(countTime);
        });
        timer1.start();
    }

    /**
     * останавливает секундомер
     */

    public static void stopStopWatch() {
        if (timer1 != null) {
            timer1.stop();
        }
    }

    /**
     * основной метод данного класса, в котором создается сетка 9*9 из текстовых полей, секундомер и
     *     5 кнопок, отвечающих за операции:
     *     - сгенерировать пример;
     *     - очистить поле судоку;
     *     - проверить решение
     *     - решить программой
     *     - вернуться в меню
     *     при этом, пользователь может сам вводить любое количество чисел в произвольные ячейки,
     *     и если их положение будет корректным, при нажатии кнопки Generate Example,
     *     сгенерируется пример судоку с некотором количеством удаленных чисел, зависящим от уровня сложности
     *
     *     пользователь может дорешать судоку и нажать кнопку Check the Result, либо не дорешивать
     *     и нажать кнопку Help - программа сама правильно заполнит ячейки
     *
     *     существует несколько вариантов ввода: в случае некорректного ввода выводится сообщение
     *     о соответсвуеющей ошибке. если пользоваетль правильно решил пример, выводится сообщение о победе
     *     с количесвом времени, за которое он справился. в противном случае, выводится сообщение,
     *     что пользователь совершил ошибки
     *
     */

    public static void createSUDOKU() {
        gridBagConstraints = new GridBagConstraints();

        stopWatchDisplay = new JLabel("00:00");                     // секундомер
        stopWatchDisplay.setOpaque(false);
        stopWatchDisplay.setForeground(Color.BLACK);
        stopWatchDisplay.setBackground(Color.white);
        stopWatchDisplay.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        stopWatchDisplay.setVisible(true);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.05;
        gridBagConstraints.fill = 1;
        stopWatchDisplay.setOpaque(true);
        stopWatchDisplay.setBackground(Color.WHITE);
        mainPanel.add(stopWatchDisplay, gridBagConstraints);

        gridBagConstraints.gridx = 0;                             // сетка
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 10;
        mainPanel.add(gridPanel, gridBagConstraints);

        gridBagConstraints.anchor = 10;
        gridBagConstraints.weighty = 0.1;
        returnButton = new JButton("Back to Menu");             // кнопка возврата
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.ipadx = 40;
        returnButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
        returnButton.setIcon(returnIcon);
        returnButton.setBackground(Color.WHITE);
        returnButton.setFocusPainted(false);
        mainPanel.add(returnButton, gridBagConstraints);
        returnButton.addActionListener(e -> {
            clearGrid();
            stopWatchDisplay.setText("00:00");
            frame2.setVisible(false);
        });

        exampleButton = new JButton("Generate Example");                 // кнопка генерации
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.ipadx = 40;
        exampleButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
        exampleButton.setIcon(generateIcon);
        exampleButton.setBackground(Color.WHITE);
        exampleButton.setFocusPainted(false);
        mainPanel.add(exampleButton, gridBagConstraints);
        exampleButton.addActionListener(e -> {
            if (!checkText()) {
                JOptionPane.showMessageDialog(GUI1.frame2, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                SudokuTable.solveSudoku();
                if (SudokuTable.solveSudoku()) {
                    int count1 = K;
                    SudokuTable.removeKDigits(count1);
                }
            }
            stopWatchDisplay.setText("00:00");

        });

        clearButton = new JButton("Clear Table");                       // кнопка очистки
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 0;
        clearButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
        clearButton.setIcon(clearIcon);
        clearButton.setBackground(Color.WHITE);
        clearButton.setFocusPainted(false);
        mainPanel.add(clearButton, gridBagConstraints);
        clearButton.addActionListener(e -> {
            GUI1.clearGrid();
            stopWatchDisplay.setText("00:00");
        });

        solveButton = new JButton("Help");                       // кнопка решения программой
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        mainPanel.add(solveButton, gridBagConstraints);
        solveButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
        solveButton.setIcon(solveIcon);
        solveButton.setBackground(Color.WHITE);
        solveButton.setFocusPainted(false);
        solveButton.addActionListener(e -> {
            if (!checkText()) {
                JOptionPane.showMessageDialog(GUI1.frame2, "Invalid input. What do you enter?", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                SudokuTable.solveSudoku();
            }

        });

        checkButton = new JButton("Check the Result");                 // кнопка проверки
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.ipadx = 40;
        checkButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        checkButton.setIcon(generateIcon);
        checkButton.setBackground(Color.white);
        checkButton.setFocusPainted(false);
        mainPanel.add(checkButton, gridBagConstraints);
        checkButton.addActionListener(e -> {

          if (checkText() && SudokuTable.solveSudoku()) {
              for (int row = 0; row < 9; row++) {
                  for (int col = 0; col < 9; col++) {
                      for (int number = 1; number <= 9; number++) {
                          if (SudokuTable.isValidNumber(row, col, number)) {
                              kubok = new ImageIcon("images/kubok.png");
                          }
                      }
                  }
              }
              if (minutes == 0) {
                  JOptionPane.showMessageDialog(GUI1.frame2, "GREAT! You solved the sudoku in " + seconds + " seconds!", "WIN", JOptionPane.INFORMATION_MESSAGE, kubok);
              } else if (minutes == 1) {
                  JOptionPane.showMessageDialog(GUI1.frame2, "GREAT! You solved the sudoku in " + minutes + " minute " + seconds + " seconds!", "WIN", JOptionPane.INFORMATION_MESSAGE, kubok);
              } else {
                  JOptionPane.showMessageDialog(GUI1.frame2, "GREAT! You solved the sudoku in " + minutes + " minutes " + seconds + " seconds!", "WIN", JOptionPane.INFORMATION_MESSAGE, kubok);
              }
          } else {
              JOptionPane.showMessageDialog(GUI1.frame2, "OH NOOOO! You made mistakes(", "LOSS", JOptionPane.ERROR_MESSAGE);
          }
            stopWatchDisplay.setText("00:00");
        });


        exampleButton.addActionListener(e -> startStopWatch());
        solveButton.addActionListener(e -> stopStopWatch());
        returnButton.addActionListener(e -> stopStopWatch());

        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame2.setSize(600, 600);
        frame2.setTitle("Sudoku Solver");
        frame2.getContentPane().add(mainPanel);
        frame2.setLocationRelativeTo(null);
        frame2.setResizable(false);
        frame2.setVisible(true);
    }

    static {
        textField = new JTextField[9][9];
        gridPanel = new GridPanel(new GridLayout(9, 9, 1, 1));
    }

    public static class GridPanel extends JPanel {
        GridPanel(GridLayout layout) {
            super(layout);
        }

        /**
         *  расчерчивает границы в поле судоку, чтобы создавались квадранты 3*3
         */
        public void paintComponent(Graphics g){
            g.fillRect(getWidth()/3 - 1,0,3,getHeight());
            g.fillRect(2*getWidth()/3 - 1,0,3,getHeight());
            g.fillRect(0,getHeight()/3 - 1,getWidth(),3);
            g.fillRect(0,2*getHeight()/3 - 2,getWidth(),3);
        }
    }
}
