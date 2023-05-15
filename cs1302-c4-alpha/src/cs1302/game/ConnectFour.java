package cs1302.game;

import cs1302.gameutil.GamePhase;
import cs1302.gameutil.Token;
import cs1302.gameutil.TokenGrid;

/**
 * {@code ConnectFour} represents a two-player connection game involving a two-dimensional grid of
 * {@linkplain cs1302.gameutil.Token tokens}. When a {@code ConnectFour} game object is
 * constructed, several instance variables representing the game's state are initialized and
 * subsequently accessible, either directly or indirectly, via "getter" methods. Over time, the
 * values assigned to these instance variables should change so that they always reflect the
 * latest information about the state of the game. Most of these changes are described in the
 * project's <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">
 * functional requirements</a>.
 */
public class ConnectFour {

    //----------------------------------------------------------------------------------------------
    // INSTANCE VARIABLES: You should NOT modify the instance variable declarations below.
    // You should also NOT add any additional instance variables. Static variables should
    // also NOT be added.
    //----------------------------------------------------------------------------------------------

    private int rows;        // number of grid rows
    private int cols;        // number of grid columns
    private Token[][] grid;  // 2D array of tokens in the grid
    private Token[] player;  // 1D array of player tokens (length 2)
    private int numDropped;  // number of tokens dropped so far
    private int lastDropRow; // row index of the most recent drop
    private int lastDropCol; // column index of the most recent drop
    private GamePhase phase; // current game phase


    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    /**
     * Constructs a {@link cs1302.game.ConnectFour} game with a grid that has {@code rows}-many
     * rows and {@code cols}-many columns. All of the game's instance variables are expected to
     * be initialized by this constructor as described in the project's
     * <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">functional
     * requirements</a>.
     *
     * @param rows the number of grid rows
     * @param cols the number of grid columns
     * @throws IllegalArgumentException if the value supplied for {@code rows} or {@code cols} is
     *     not supported. The following values are supported: {@code 6 <= rows <= 9} and
     *     {@code 7 <= cols <= 9}.
     */
    public ConnectFour(int rows, int cols)  {
        if (rows <= 5 || rows > 9 || cols <= 6 || cols > 9) {
            throw new IllegalArgumentException("Dimensions are invalid");
        }  else {
            this.rows = rows;
            this.cols = cols;
        } //else

        player = new Token[2];
        grid = new Token[rows][cols];
        numDropped = 0;
        lastDropRow = -1;
        lastDropCol = -1;
        phase = GamePhase.NEW;

    } // ConnectFour

    //----------------------------------------------------------------------------------------------
    // INSTANCE METHODS
    //----------------------------------------------------------------------------------------------

//    private void debugTester(){
    //      System.out.println(rows);
    //  System.out.println(cols);
    //  System.out.println(grid);
    //  System.out.println(player);
    //  System.out.println(numDropped);
    //  System.out.println(lastDropRow);
    //  System.out.println(lastDropCol);
    //  System.out.println(phase);
    // }//debugTester
    /**
     * Return the number of rows in the game's grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        //
        // replace the entire contents of this method with your implementation
        return rows;
    } // getRows

    /**
       n     * Return the number of columns in the game's grid.
       *
       * @return the number of columns
       */
    public int getCols() {
        //
        // replace the entire contents of this method with your implementation

        return cols;
    } // getCols

    /**
     * Return whether {@code row} and {@code col} specify a location inside this game's grid.
     *
     * @param row the position's row index
     * @param col the positions's column index
     * @return {@code true} if {@code row} and {@code col} specify a location inside this game's
     *     grid and {@code false} otherwise
     */
    public boolean isInBounds(int row, int col) {
        //
        // replace the entire contents of this method with your implementation
        if ((row >= 0) && (row < this.rows) && (col >= 0) && (col < this.cols)) {
            return true;
        } else {
            return false;
        } // else
    } // isInBounds

    /**
     * Return the grid {@linkplain cs1302.gameutil.Token token} located at the specified position
     * or {@code null} if no token has been dropped into that position.
     *
     * @param row the token's row index
     * @param col the token's column index
     * @return the grid token located in row {@code row} and column {@code col}, if it exists;
     *     otherwise, the value {@code null}
     * @throws IndexOutOfBoundsException if {@code row} and {@code col} specify a position that is
     *     not inside this game's grid.
     */

    public Token getTokenAt(int row, int col) {
        //
        // replace the entire contents of this method with your implementation
        if (!((row >= 0) && (row < this.rows) && (col >= 0) && (col < this.cols))) {

            throw new IndexOutOfBoundsException("Specified position is invalid.");
        }
        if (this.player[1] == grid[row][col]) {
            return this.player[1];
        }
        if (this.player[0] == grid[row][col]) {
            return this.player[0];
        } else {
            return null;
        }
    } // getTokenAt

    /**
     * Set the first player token and second player token to {@code token0} and {@code token1},
     * respectively. If the current game phase is {@link cs1302.gameutil.GamePhase#NEW}, then
     * this method changes the game phase to {@link cs1302.gameutil.GamePhase#READY}, but only
     * if no exceptions are thrown.
     *.
     * @param token0 token for first player
     * @param token1 token for second player
     * @throws NullPointerException if {@code token0} or {@code token1} is {@code null}.
     * @throws IllegalArgumentException if {@code token0 == token1}.
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#PLAYABLE} or {@link cs1302.gameutil.GamePhase#OVER}.
     */
    public void setPlayerTokens(Token token0, Token token1) {
        //
        // replace the entire contents of this method with your implementation
        //
        player[0] = token0;
        player[1] = token1;
        phase = GamePhase.READY;
        if (token0 == null || token1 == null) {
            throw new NullPointerException("Player token has in invalid value.");
        }
        if (token0 == token1) {
            throw new IllegalArgumentException("Player token needs a unique value.");
        }
        if (getPhase() == GamePhase.PLAYABLE || getPhase() == GamePhase.OVER) {
            throw new IllegalStateException("Cannot play until GamePhase.READY.");
        } // if
    } // setPlayerTokens

    /**
     * Return a player's token.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @return the token for the specified player
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW}.
     */
    public Token getPlayerToken(int player) {
        //
        // replace the entire contents of this method with your implementation
        if (this.player[player] == this.player[0] || this.player[player] == this.player[1]) {
            return this.player[player];
        }
        if ((this.player[player] != this.player[0]) || (this.player[player] != this.player[1])) {
            throw new IllegalArgumentException("Player does not exist.");
        }
        if (getPhase() == GamePhase.NEW) {
            throw new IllegalStateException("Cannot play in current game phase.");
        } // if
        return this.player[player];
    } // getPlayerToken

    /**
     * Return the number of tokens that have been dropped into this game's grid so far.
     *
     * @return the number of dropped tokens
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getNumDropped() {
        //
        // replace the entire contents of this method with your implementation
        //
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException("Cannot play in current game phase.");
        } // if
        return numDropped;
    } // getNumDropped

    /**
     * Return the row index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the row index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */

    public int getLastDropRow() {
        //
        // replace the entire contents of this method with your implementation
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException("Cannot play in current game phase.");
        } // if
        return lastDropRow;
    } // getLastDropRow

    /**
     * Return the col index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the column index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropCol() {
        //
        // replace the entire contents of this method with your implementation
        //
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException("Cannot play in current game phase.");
        } // if
        return lastDropCol;
    } // getLastDropCol

    /**
     * Return the current game phase.
     *
     * @return current game phase
     */
    public GamePhase getPhase() {
        //
        // replace the entire contents of this method with your implementation
        //
        return phase;
    } // getPhase

    /**
     * Drop a player's token into a specific column in the grid. This method should not enforce turn
     * order -- that is the players' responsibility should they desire an polite and honest game.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @param col the grid column where the token will be dropped
     * @throws IndexOutOfBoundsException if {@code col} is not a valid column index
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} does not return
     *    {@link cs1302.gameutil.GamePhase#READY} or {@link cs1302.gameutil.GamePhase#PLAYABLE}
     * @throws IllegalStateException if the specified column in the grid is full
     */
    public void dropToken(int player, int col) {
        //
        // replace the entire contents of this method with your implementation
        //
        if ((col < 0 ) || (col > this.cols)) {
            throw new IndexOutOfBoundsException("Column is not in bounds.");
        } // if
        if (player != 1 && player != 0) {
            throw new IllegalArgumentException("Invalid player token.");
        } // if
        if (getPhase() != GamePhase.READY && getPhase() != GamePhase.PLAYABLE) {
            throw new IllegalStateException("Unable to drop token due to current game phase.");
        } // if
        if ((grid[0][col] == this.player[0]) || (grid[0][col] == this.player[1])) {
            throw new IllegalStateException("This column is full.");
        }
        lastDropCol = col;
        this.phase = GamePhase.PLAYABLE;
        for (int l = this.rows - 1 ; l >= 0 ; l--) {
            if ((grid[l][col] != this.player[1]) && (grid[l][col] != this.player[0])) {
                grid[l][col] = this.player[player];
                numDropped++;
                lastDropRow = l;
                break;
            } //if
        } //for
    } //dropToken



    /**
     * Return {@code true} if the last token dropped via {@link #dropToken} created a
     * <em>connect four</em>. A <em>connect four</em> is a sequence of four equal tokens (i.e., they
     * have the same color) -- this sequence can occur horizontally, vertically, or diagonally.
     * If the grid is full or the last drop created a <em>connect four</em>, then this method
     * changes the game's phase to {@link cs1302.gameutil.GamePhase#OVER}.
     *
     * <p>
     * <strong>NOTE:</strong> The only instance variable that this method might change, if
     * applicable, is ``phase``.
     *
     * <p>
     * <strong>NOTE:</strong> If you want to use this method to determin a winner, then you must
     * call it after each call to {@link #dropToken}.
     *
     * @return {@code true} if the last token dropped created a <em>connect four</em>, else
     *     {@code false}
     */
    public boolean isLastDropConnectFour() {
        if (numDropped == (this.cols * this.rows)) {
            this.phase = GamePhase.OVER;
        }

        // TEST FOR RIGHT TO LEFT DIAGONAL
        for (int i = 0; i < this.rows - 3; i++) {
            for (int j = this.cols - 1; j > 2; j--) {
                if (grid[i][j] != null) {
                    if (grid[i][j] == grid[i + 1][j - 1] && grid[i + 2][j - 2] == grid[i + 3][j - 3]
                        && grid[i + 1][j - 1] == grid[i + 2][j - 2]) {
                        phase = GamePhase.OVER;
                        return true;
                    }
                } //outer if
            }
        } //outer for

        // TEST FOR LEFT TO RIGHT DIAGONAL
        for (int i = 0; i < this.rows - 3; i++) {
            for (int j = 0; j < this.cols - 3; j++) {
                if (grid[i][j] != null) {
                    if (grid[i][j] == grid[i + 1][j + 1] && grid[i + 2][j + 2] == grid[i + 3][j + 3]
                        && grid[i + 1][j + 1] == grid[i + 2][j + 2]) {
                        phase = GamePhase.OVER;
                        return true;
                    }
                } // outer if
            }
        } // outer for

        // TEST FOR VERTICAL
        for (int j = 0; j < this.cols; j++) {
            for (int i = 0; i < this.rows - 3; i++) {
                if (grid[i][j] != null) {
                    if (grid[i][j] == grid[i + 1][j] && grid[i + 2][j] == grid[i + 3][j]
                        && grid[i + 1][j] == grid[i + 2][j]) {
                        phase = GamePhase.OVER;
                        return true;
                    }
                } //outer if
            }
        } //outer for

        // TEST FOR HORIZONTAL
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols - 3; j++) {
                if (grid[i][j] != null) {
                    if (grid[i][j] == grid[i][j + 1] && grid[i][j + 2] == grid[i][j + 3]
                        && grid[i][j + 1] == grid[i][j + 2]) {
                        phase = GamePhase.OVER;
                        return true;
                    }
                } //outer if
            }
        } // outer for

        return false;

    } //isLastDropConnectFour

    //----------------------------------------------------------------------------------------------
    // ADDITIONAL METHODS: If you create any additional methods, then they should be placed in the
    // space provided below.
    //----------------------------------------------------------------------------------------------



//----------------------------------------------------------------------------------------------
// DO NOT MODIFY THE METHODS BELOW!
//----------------------------------------------------------------------------------------------

/**
 * <strong>DO NOT MODIFY:</strong>
 * Print the game grid to standard output. This method assumes that the constructor
 * is implemented correctly.
 *
 * <p>
 * <strong>NOTE:</strong> This method should not be modified!
 */
    public void printGrid() {
        TokenGrid.println(this.grid);
    } // printGrid

} // ConnectFour
