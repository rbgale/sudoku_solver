public class Board {

    private final int GRID_SIZE = 9;
    private final int[][] board;

    public Board(int[][] board){
        this.board = board;
    }

    /**
     * Solves sudoku board if a solution exists,
     * otherwise does not modify the board.
     *
     * @return true if the board can be solved;
     * false otherwise
     */
    public boolean solver(){
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j] == 0) {
                    for (int k = 1; k < 10; k++) {
                        if (valid(i, j, k)) {
                            board[i][j] = k;

                            if (solver()) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rowCheck(int row, int num){
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean colCheck(int col, int num){
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean boxCheck(int row, int col, int num){
        int cornerRow = row - (row % 3);
        int cornerCol = col - (col % 3);

        for (int i = cornerRow; i < (cornerRow + 3); i++) {
            for (int j = cornerCol; j < (cornerCol + 3); j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Takes a location on the board and a number and
     * returns if that number can be placed at that location.
     *
     * @param row row to be checked
     * @param col column to be checked
     * @param num number to check
     * @return true if the number can be placed;
     * false otherwise
     */
    public boolean valid(int row, int col, int num) {
        return !rowCheck(row, num) &&
                !colCheck(col, num) &&
                !boxCheck(row, col, num);
    }

    /**
     * Returns a string representation of the board
     *
     * @return string representation of board
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                str.append(board[i][j]).append(" ");
            }
            str.append("\n");
        }

        return str.toString();
    }
}
