public class Queen extends Piece{
    public Queen(int x, int y, Side side, Board board) {
        // TODO: Call super constructor
        super(x,y,side,board);

    }

    @Override
    public boolean canMove(int destX, int destY) {

        //TODO: Check piecerules.md for the movement rule for this piece :)
        // need to also add conditon for when there is a piece in the way
        if ((Math.abs(this.x - destX) == Math.abs(this.y  - destY)) || (this.x == destX || this.y == destY)){
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return this.getSide() == Side.BLACK ? "♛" : "♕" ;
    }
}
