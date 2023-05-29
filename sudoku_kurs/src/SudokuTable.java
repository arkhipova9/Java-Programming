import java.util.Random;

public class SudokuTable {

    /**
     *    проверяет введеное число на корректность В РЯДУ
     */
    static boolean isValidInRow(int row, int number) {
        for (int col = 0; col < 9; col++) {
            if (GUI1.textField[row][col].getText().equals(String.valueOf(number))) {
                return false;
            }
        }
        return true;
    }

    /**
     *    проверяет введеное число на корректность В СТОЛБЦЕ
     */
    static boolean isValidInColumn(int col, int number) {
        for (int row = 0; row < 9; row++) {
            if (GUI1.textField[row][col].getText().equals(String.valueOf(number))) {
                return false;
            }
        }
        return true;
    }

    /**
     *     проверяет введеное число на корректность В КВАДРАНТЕ 3*3
     */
    static boolean isValidInBox(int startRow, int startCol, int number) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (GUI1.textField[row + startRow][col + startCol].getText().equals(String.valueOf(number))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *         объединяет в себе проверку всех 3 методов
     */
    static boolean isValidNumber(int row, int col, int number) {
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        return isValidInRow(row, number) && isValidInColumn(col, number) && isValidInBox(boxRow, boxCol, number);
    }


    /**
     *     основной метод данного класса, который отвечает за решение судоку по введеным пользователем
     *     значениям: если пользователь ввел число меньше 1 и больше 9, то выводится
     *     сообщение об ошибке
     *
     *     суть решения судоку состоит в том, чтобы заполнить поле 9*9 числами от 1 до 9 так,
     *     чтобы в строках, столбцах и квадрантах 3*3 числа не повторялись
*/
    static boolean solveSudoku() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (GUI1.textField[row][col].getText().isEmpty()) {
                    for (int number = 1; number <= 9; number++) {
                        if (isValidNumber(row, col, number)) {
                            GUI1.textField[row][col].setText(String.valueOf(number));
                            if (solveSudoku()) {
                                return true;
                            } else {
                                GUI1.textField[row][col].setText("");
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    /**
     *     стирает количество чисел, зависящее от уровня сложности, чтобы пользователь мог
     *     дорешать судоку
     */

    public static void removeKDigits(int count1) {
        Random rand = new Random();
        for (int i = 0; i < count1; i++) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            GUI1.textField[row][col].setText("");
        }
    }
}
