import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewGameEngine {
    //most essential component : searching and rating method
    static String chessBoard[][]={
            {"r","k","b","q","a","b","k","r"},
            {"p","p","p","p","p","p","p","p"},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {"P","P","P","P","P","P","P","P"},
            {"R","K","B","Q","A","B","K","R"}};
    static int kingPositionC, kingPositionL;
    static int humanAsWhite=-1;//1=human as white, 0=human as black
    static int globalDepth=4;



    public static String alphaBeta(int depth, int beta, int alpha, String move, int player, Game g) {
        //return in the form of 1234b##########
        // black is minimizing player while white is maximising player
        String list=posibleMoves(g);
        // means we've reached the end of possible moves or the furthest depth so we return
        // the rating and the move
        if (depth==0 || list.length()==0) {
            return move+(Rating.rating(list.length(),depth)*(player*2-1));}
        if (g.currentTurn == Side.BLACK) { player = 1;} else {player = 0;}
        for (int i=0;i<list.length();i+=5) {
            makeMove(list.substring(i,i+5),g);
            String returnString=alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player,g);
            // uses recursion to go through the depth of the tree and find the ratings
            // of the move combinations
            int value=Integer.valueOf(returnString.substring(5));
            flipBoard(g);
            undoMove(list.substring(i,i+5),g);

            // setting the values for the next moves
            if (player==0) {
                // being less than beta would make it a good move
                if (value<=beta) {beta=value; if (depth==globalDepth) {move=returnString.substring(0,5);}}
            } else {
                if (value>alpha) {alpha=value; if (depth==globalDepth) {move=returnString.substring(0,5);}}
            } // stores the best move as move, using the returnString variable
            if (alpha>=beta) {
                if (player==0) {return move+beta;} else {return move+alpha;}
            }
        }
        if (player==0) {
            return move+beta;}
        else {
            return move+alpha;
        }

    }
    public static void flipBoard(Game g) {
        //negating the side
        g.currentTurn = g.currentTurn.negate(g.currentTurn);
    }
    public static boolean makeMove(String move, Game g) { // translates string moves to actual moves
        int originX = Character.getNumericValue(move.charAt(1)); // conversion
        int originY = Character.getNumericValue(move.charAt(0));
        int destx = Character.getNumericValue(move.charAt(3));
        int desty = Character.getNumericValue(move.charAt(2));
        return g.move(originX,originY,destx,desty);

    }
    public static void undoMove(String move, Game g) {

        int originX = Character.getNumericValue(move.charAt(1));
        int originY = Character.getNumericValue(move.charAt(0));
        int destx = Character.getNumericValue(move.charAt(3));
        int desty = Character.getNumericValue(move.charAt(2));
        Piece curpiece = g.b.get(destx,desty);
        // if no piece was captured we can just move back the pieces
        if (move.charAt(4) == ' ') {
            g.b.set(destx, desty, null);
            g.b.set(originX,originY,curpiece);
            curpiece.x = originX;
            curpiece.y = originY;
            g.moveHistory.pop();
        }
        else { // if a piece was captured we must recreate the piece and put it back
            Piece mypiece;
            g.b.set(destx, desty, null);
            g.b.set(originX,originY,curpiece);
            curpiece.x = originX;
            curpiece.y = originY;
            g.moveHistory.pop();
            switch (move.charAt(4)) {
                case '♕':
                    mypiece = new Queen(destx, desty, Side.WHITE, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♔':
                    mypiece = new King(destx, desty, Side.WHITE, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♘':
                    mypiece = new Knight(destx, desty, Side.WHITE, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♗':
                    mypiece = new Bishop(destx, desty, Side.WHITE, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♙':
                    mypiece = new Pawn(destx, desty, Side.WHITE, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♖':
                    mypiece = new Rook(destx, desty, Side.WHITE, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;

                case '♛':
                    mypiece = new Queen(destx, desty, Side.BLACK, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♚':
                    mypiece = new King(destx, desty, Side.BLACK, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♞':
                    mypiece = new Knight(destx, desty, Side.BLACK, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♝':
                    mypiece = new Bishop(destx, desty, Side.BLACK, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♟':
                    mypiece = new Pawn(destx, desty, Side.BLACK, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
                case '♜':
                    mypiece = new Rook(destx, desty, Side.BLACK, g.b);
                    g.b.set(destx, desty, mypiece);
                    break;
            }
        }
    }
    public static String posibleMoves(Game g) {
        String list="";
        List<Piece> mypieces = g.b.getPieces(g.currentTurn);
        String oriX;
        String oriY;
        String destX;
        String destY;
        for (Piece mypiece : mypieces) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece destpiece = g.b.get(i,j);
                    if (g.canMove(mypiece.x,mypiece.y,i,j,g.currentTurn)) {
                        oriX = String.valueOf(mypiece.x);
                        oriY = String.valueOf(mypiece.y);
                        destX = String.valueOf(i);
                        destY = String.valueOf(j);
                        if (destpiece != null) {
                            list += oriY + oriX + destY + destX + destpiece.getSymbol();
                        } else {
                            list += oriY + oriX + destY + destX + " ";
                        }
                    }
                }
            }
        }

        return list;
    }


    public static String sortMoves(String list, Game g) {
        int[] score=new int [list.length()/5];
        for (int i=0;i<list.length();i+=5) {
            makeMove(list.substring(i, i+5), g);
            score[i/5]=-Rating.rating(-1, 0);
            undoMove(list.substring(i, i+5),g);
        }
        // We will first add the best few moves to newlistA and then the rest afterwards
        // this is how it will be sorted
        String newListA="", newListB=list;
        for (int i=0;i<Math.min(6, list.length()/5);i++) {//first few moves only
            int max=-1000000, maxLocation=0;
            for (int j=0;j<list.length()/5;j++) {
                if (score[j]>max) {max=score[j]; maxLocation=j;}
            }
            score[maxLocation]=-1000000;
            newListA+=list.substring(maxLocation*5,maxLocation*5+5);
            newListB=newListB.replace(list.substring(maxLocation*5,maxLocation*5+5), "");
        }
        return newListA+newListB;
    }


}


