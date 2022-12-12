// Define a data class to represent a tic-tac-toe board
data class Board(val squares: MutableList<String>) {
    // Function to check if the game is over
    fun isGameOver(): Boolean {
        // Check all rows
        for (i in 0..2) {
            if (squares[i * 3] != "_" && squares[i * 3] == squares[i * 3 + 1] && squares[i * 3 + 1] == squares[i * 3 + 2]) {
                return true
            }
        }

        // Check all columns
        for (i in 0..2) {
            if (squares[i] != "_" && squares[i] == squares[i + 3] && squares[i + 3] == squares[i + 6]) {
                return true
            }
        }

        // Check diagonal from top left to bottom right
        if (squares[0] != "_" && squares[0] == squares[4] && squares[4] == squares[8]) {
            return true
        }

        // Check diagonal from top right to bottom left
        if (squares[2] != "_" && squares[2] == squares[4] && squares[4] == squares[6]) {
            return true
        }

        // If none of the above conditions are met, the game is not over
        return false
    }
}


// Function to print the current state of the board
fun printBoard(board: Board) {
    for (i in 0..2) {
        for (j in 0..2) {
            val index = i * 3 + j
            val square = board.squares[index]
            print("$square ")
        }
        println()
    }
}


private fun promptMove(): Int {
    print("Enter move: ")
    return readLine()!!.toInt()
}

fun main() {
    // Initialize the board with empty strings
    val board = Board(MutableList(9) { "_" })
    var move=-1;
    while(!board.isGameOver()) {
        // Print the current state of the board
        printBoard(board)

        // Prompt the current player to make a move
        move = promptMove()

        // Make the move and update the board
        board.squares[move] = "X"

        // Check if the game is over
        if (board.isGameOver()) {
            break
        }

        // Switch to the other player
        // (in this implementation, "X" always plays first)
        val otherPlayer = if (board.squares[move] == "X") "O" else "X"

        // Prompt the other player to make a move
        val otherMove = promptMove()

        // Make the move and update the board
        board.squares[otherMove] = otherPlayer
    }

    // Print the final state of the board
    printBoard(board)

    // Print a message to indicate who won the game (if anyone)
    val winner = if (board.isGameOver()) {
        // If isGameOver() is true, then one of the players must have won
        // We can determine the winner by looking at the value of the square
        // that caused the game to end
        board.squares[move]
    } else {
        // If isGameOver() is false, then the game must have ended in a draw
        "Nobody"
    }
    println("$winner wins!")
}

