// PROVIDED AS BLANK WITH HEADERS
public abstract class Piece {
    protected int x, y;     //Accessible within package and to subclasses
    protected Board board;
    private boolean captured;
    private Side side;

    public Piece(int x, int y, Side side, Board b) {
        this.x = x;
        this.y = y;
        this.side = side;
        captured = false;
        this.board = b;
    }

    public abstract boolean canMove(int destX, int destY);

    public abstract String getSymbol();

    public boolean move(int destX, int destY){ // moves the piece
        if(canMove(destX,destY)){
            if(board.get(destX,destY) != null){
                board.get(destX,destY).capture();
            }
            board.set(this.x, this.y, null);
            board.set(destX,destY,this);
            this.x = destX;
            this.y = destY;
            return true;
        }
        return false;
    }

    public void capture(){ // means the piece has been captured
        this.x = -1;
        this.y = -1;
        captured = true;
    }

    public Side getSide() {
        return side;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
