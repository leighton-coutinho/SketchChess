import java.util.List;

public class Rating {
    static int pawnBoard[][] = {//attribute to http://chessprogramming.wikispaces.com/Simplified+evaluation+function
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    static int pawnBoardB[][] = {//attribute to http://chessprogramming.wikispaces.com/Simplified+evaluation+function
            {0, 0, 0, 0, 0, 0, 0, 0},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    static int rookBoard[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {5, 10, 10, 10, 10, 10, 10, 5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {0, 0, 0, 5, 5, 0, 0, 0}};
    static int rookBoardB[][] = {
            {0, 0, 0, 5, 5, 0, 0, 0},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {5, 10, 10, 10, 10, 10, 10, -5},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    static int knightBoard[][] = {
            {-50, -40, -30, -30, -30, -30, -40, -50},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 0, 15, 20, 20, 15, 0, -30},
            {-30, 5, 10, 15, 15, 10, 5, -30},
            {-40, -20, 0, 5, 5, 0, -20, -40},
            {-50, -40, -30, -30, -30, -30, -40, -50}};
    static int knightBoardB[][] = {
            {-50, -40, -30, -30, -30, -30, -40, -50},
            {-40, -20, 0, 5, 5, 0, -20, -40},
            {-30, 5, 10, 15, 15, 10, 5, -30},
            {-30, 0, 15, 20, 20, 15, 0, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-50, -40, -30, -30, -30, -30, -40, -50}};
    static int bishopBoard[][] = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 5, 0, 0, 0, 0, 5, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}};
    static int bishopBoardB[][] = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}};
    static int queenBoard[][] = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}};
    static int queenBoardB[][] = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}};
    static int kingMidBoard[][] = {
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-20, -30, -30, -40, -40, -30, -30, -20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {20, 30, 10, 0, 0, 10, 30, 20}};
    static int kingMidBoardB[][] = {
            {20, 30, 10, 0, 0, 10, 20, 20},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {-20, -20, -30, -40, -40, -30, -30, -20},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30}};
    static int kingEndBoard[][] = {
            {-50, -40, -30, -20, -20, -30, -40, -50},
            {-30, -20, -10, 0, 0, -10, -20, -30},
            {-30, -10, 20, 30, 30, 20, -10, -30},
            {-30, -10, 30, 40, 40, 30, -10, -30},
            {-30, -10, 30, 40, 40, 30, -10, -30},
            {-30, -10, 20, 30, 30, 20, -10, -30},
            {-30, -30, 0, 0, 0, 0, -30, -30},
            {-50, -30, -30, -30, -30, -30, -30, -50}};
    static int kingEndBoardB[][] = {
            {-50, -40, -30, -20, -20, -30, -40, -50},
            {-30, -30, 0, 0, 0, 0, -30, -30},
            {-30, -10, 20, 30, 30, 20, -10, -30},
            {-30, -10, 30, 40, 40, 30, -10, -30},
            {-30, -10, 30, 40, 40, 30, -10, -30},
            {-30, -10, 20, 30, 30, 20, -10, -30},
            {-30, -30, -10, 0, 0, -10, -30, -30},
            {-50, -30, -30, -30, -30, -30, -30, -50}};

    public static int rating(int list, int depth) {

        int counter = 0, material = rateMaterial(GameApp.g.currentTurn);
        counter += rateAttack(GameApp.g.currentTurn);
        counter += material;
        counter += rateMovability(list, depth, material);
        counter += ratePositional(material, GameApp.g.currentTurn);
        GameApp.g.currentTurn = Side.negate(GameApp.g.currentTurn);
        material = rateMaterial(GameApp.g.currentTurn);
        counter -= rateAttack(GameApp.g.currentTurn);
        counter -= material;
        counter -= rateMovability(list, depth, material);
        counter -= ratePositional(material, GameApp.g.currentTurn);
        GameApp.g.currentTurn = Side.negate(GameApp.g.currentTurn);
        return -(counter + (depth * 50));
    }

    public static int rateAttack(Side s) {
        int value = 0;
        int counter = 0;
        Side Temp = GameApp.g.currentTurn;
        List<Piece> mypieces = GameApp.g.b.getPieces(s);
        GameApp.g.currentTurn = Side.negate(s);
        List<Piece> enemypieces = GameApp.g.b.getPieces(GameApp.g.currentTurn);
        for (Piece mypiece : mypieces) {
            switch (mypiece.getSymbol()) {
                case "♟": // we essentially pretend our piece is a king and check if it is being
                    value = -64;                  // positive values for black pieces
                    break;                  // to increase rating i.e worse
                case "♜":
                    value = -500;
                    break;
                case "♞":
                    value = -300;
                    break;
                case "♝":
                    value = -300;
                    break;
                case "♛":
                    value = -900;
                    break;

                case "♙": // we essentially pretend our piece is a king and check if it is being
                    value = -64;     // negative values when white pieces can be taken
                    break;             // to reduce rating
                case "♖":
                    value = -500;
                    break;
                case "♘":
                    value = -300;
                    break;
                case "♗":
                    value = -300;
                    break;
                case "♕":
                    value = -900;
                    break;
            }
            for (Piece enemypiece : enemypieces) {
                if (GameApp.g.canMove(enemypiece.x, enemypiece.y, mypiece.x, mypiece.y, GameApp.g.currentTurn)) {
                    counter += value;
                    break; // leave inner loop and move onto next piece
                }
            }
        }
        GameApp.g.currentTurn = Temp;
        return counter / 2; // half of having the pieces being actually attacked

    }

    public static int rateMaterial(Side s) {
        int counter = 0, bishopCounter = 0; // here we rate pieces on the board and their worth
        List<Piece> mypieces = GameApp.g.b.getPieces(s);
        for (Piece mypiece : mypieces) {
            switch (mypiece.getSymbol()) {
                case "♕":
                    counter += 900;
                    break;
                case "♘":
                    counter += 300;
                    break;
                case "♗":
                    bishopCounter += 1;
                    break;
                case "♙":
                    counter += 100;
                    break;
                case "♖":
                    counter += 500;
                    break;

                case "♟":
                    counter += 100;
                    break;
                case "♜":
                    counter += 500;
                    break;
                case "♞":
                    counter += 300;
                    break;
                case "♝":
                    bishopCounter += 1;
                    break;
                case "♛":
                    counter += 900;
                    break;
            }
        }
        if (bishopCounter >= 2) {
            counter += 300 * bishopCounter;
        } else {
            if (bishopCounter == 1) {
                counter += 250;
            }
        }
        return counter;
    }


    public static int rateMovability(int listLength, int depth, int material) {
        int counter = 0;                                       // this rates how open and free we are
        String mylist = NewGameEngine.posibleMoves(GameApp.g);
        counter += mylist.length();
        // 5 points are added per valid move as this means we are more flexible
        if (listLength == 0) { // means we are at checkmate or stalemate
            if (GameApp.g.isInCheck(GameApp.g.currentTurn)) { // means checkmate
                counter += -200000 * depth;
            } else { // stalemate
                counter += -150000 * depth;
            }
        }
        return counter;
    }


   public static int ratePositional(int material, Side s) {
        int counter=0;  // based on each pieces position on the board
        int myx;
        int myy;
        List<Piece> mypieces = GameApp.g.b.getPieces(s);
        for (Piece mypiece : mypieces) {
            myx = mypiece.x;
            myy = mypiece.y;
            switch (mypiece.getSymbol()) {
                case "♙":
                    counter += pawnBoard[myy][myx];
                    break;
                case "♖":
                    counter += rookBoard[myy][myx];
                    break;
                case "♘":
                    counter += knightBoard[myy][myx];
                    break;
                case "♗":
                    counter += bishopBoard[myy][myx];
                    break;
                case  "♕":
                    counter += queenBoard[myy][myx];
                    break;
                case "♔":
                    if (material >= 1750) {
                        counter += kingMidBoard[myy][myx];
                    } else {
                        counter += kingEndBoard[myy][myx];
                    }
                    break;


                case "♟":
                    counter += pawnBoardB[myy][myx];
                    break;
                case "♜":
                    counter += rookBoardB[myy][myx];
                    break;
                case "♞":
                    counter += knightBoardB[myy][myx];
                    break;
                case "♝":
                    counter += bishopBoardB[myy][myx];
                    break;
                case  "♛":
                    counter += queenBoardB[myy][myx];
                    break;
                case "♚":
                    if (material >= 1750) {
                        counter += kingMidBoardB[myy][myx];
                    } else {
                        counter += kingEndBoardB[myy][myx];
                    }
                    break;
                }
        }
        return counter;
    }
}
