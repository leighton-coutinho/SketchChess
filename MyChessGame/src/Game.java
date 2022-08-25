import com.sun.net.httpserver.Authenticator;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Game {
    Board b;
    Stack<String> moveHistory = new Stack<String>();
    Side currentTurn;
    boolean botcheck = false;

    public Game(){
        // todo: write a constructor that initializes the game with a new board
        b = new Board();
        this.currentTurn = Side.WHITE;
    }

    public static String getName() {
        return "SketchChess♔";
    }


    public boolean canMove(int x, int y, int destX, int destY, Side s){
       // TODO write a method that checks if a piece at coordinates x,y can move to coordinates destX,destY
        // Checking general requirements
        if ((x == destX) && (y == destY)) {
            return false;
        }
        if ((x < 0 || x > 7) || (y < 0 || y > 7) || (b.get(x,y) == null || ((destX < 0 || destX > 7) || (destY < 0 || destY > 7)))) {
            return false;
        }
        Piece curpiece = b.get(x,y);
        if ((curpiece.isCaptured()) || (curpiece.getSide() != this.currentTurn)) {
            return false;
        }
        Piece destpiece = b.get(destX,destY);
        if (destpiece != null) {
            if (destpiece.getSide() == currentTurn) {
                return false;
            }
        }


            //pawn
        if (curpiece.getSymbol() == "♟" || curpiece.getSymbol() == "♙") {
            return curpiece.canMove(destX,destY);
        } // knight
        else if (curpiece.getSymbol() == "♞" || curpiece.getSymbol() == "♘") {
            return curpiece.canMove(destX,destY);
        } // Every other piece
        else {
            return (curpiece.canMove(destX,destY) && isVisible(x,y,destX,destY));
        }
    }



    private boolean isVisible(int x, int y, int destX, int destY) {
        int diffX = destX - x;
        int diffY = destY - y;

        boolean validCheck = (diffX == 0 || diffY == 0) || (Math.abs(diffX) == Math.abs(diffY));
        if (!validCheck) {
            try {
                throw new Exception("The 'path' between the squares (" + x + ", " + y + ") and (" + destX + ", " + destY +") is undefined");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Diagonal
        if (Math.abs(diffX) == Math.abs(diffY) && Math.abs(diffX) > 1) {
            //Determine direction of move
            int dirX = diffX > 0 ? 1 : -1;
            int dirY = diffY > 0 ? 1 : -1;
            for (int i = x + dirX, j = y + dirY; i != destX && j != destY; i += dirX, j += dirY) {
                if (b.get(i, j) != null) {
                    return false;
                }
            }
        }

        //Linear
        if ((diffX == 0 && Math.abs(diffY) > 1) || (diffY == 0 && Math.abs(diffX) > 1)) {
            if (diffX == 0) {
                int dirY = diffY > 0 ? 1 : -1;
                for (int j = y + dirY; j != destY; j += dirY) {
                    if (b.get(x, j) != null) {
                        return false;
                    }
                }
            } else {
                int dirX = diffX > 0 ? 1 : -1;
                for (int i = x + dirX; i != destX; i += dirX) {
                    if (b.get(i, y) != null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void appendCheckToHistory(Side side){
        moveHistory.push(side.toString()+ " is in check");
    }

    private void appendWinToHistory(Side side){
        moveHistory.push(side.toString()+ " has won");
    }

    private void appendMoveToHistory(int x, int y, int destX, int destY, Side side){
        Piece toMove = b.get(x,y);
        Piece toCapture = b.get(destX, destY);
        if(toCapture == null){
            moveHistory.push(side.toString() + toMove.getSymbol() + " at "+ x + ", " + y + " to " + destX + ", " + destY);
        }else{
            moveHistory.push(side.toString() + toMove.getSymbol() + " at " + x + ", " + y + " captures " + toCapture.getSymbol() + " at " + destX + ", " + destY);
        }
    }

    public boolean move(int x, int y, int destX, int destY){

        if (canMove(x,y,destX,destY,currentTurn)) {
            Piece curpiece = b.get(x,y);
            appendMoveToHistory(x,y,destX,destY,currentTurn);
            curpiece.move(destX,destY);
            currentTurn = Side.negate(currentTurn);
            return true;
        }
        return false;
    }

    public boolean isInCheck(Side side) {
        // TODO write this method
        Piece sideking = b.getKing(side);
        List<Piece> othersidespieces = b.getPieces(Side.negate(side));
        for (Piece i : othersidespieces) {
            if (i.getSymbol() == "♟" || i.getSymbol() == "♙") {
                if (i.canMove(sideking.x, sideking.y)) {
                    return true;
                }
            } // knight
            else if (i.getSymbol() == "♞" || i.getSymbol() == "♘") {
                if (i.canMove(sideking.x, sideking.y)) {
                    return true;
                }
            } // Every other piece
            else {
                if ((i.canMove(sideking.x, sideking.y) && isVisible(i.x,i.y, sideking.x, sideking.y))){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Ensures that the game is in the exact same state as a new game
     */
    public void reset(){
        while(!moveHistory.empty()){
            System.out.println(moveHistory.pop());
        }
        b.fillBoard();
        currentTurn = Side.WHITE;
    }


    public static void main(String[] args){


    }

    public String[] moveHistory(){
        String[] out = new String[moveHistory.size()];
        for(int i = 0; i < moveHistory.size(); i++){
            out[i] = moveHistory.get(i);
        }
        return out;
    }

    public Side getCurrentTurn(){
        return currentTurn;
    }

}
