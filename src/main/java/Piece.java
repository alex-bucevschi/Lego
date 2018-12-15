public class Piece {

    private int id;
    private int x;
    private int y;
    private int z;
    private String color;
    private int orientation;

    public Piece(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        id = 0;
        color = "#000000";
        orientation = 0;
    }

    public Piece(int x, int y, int z, String color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
        id = 0;
        orientation = 0;
    }

    public Piece(int id, int x, int y, int z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        color = "#000000";
        orientation = 0;
    }

    public Piece(int id, int x, int y, int z, int orientation) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = orientation;
    }

    public Piece(int id, int x, int y, int z, String color) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;

    }

    public Piece(int id, int x, int y, int z, String color, int orientation) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
        this.orientation = orientation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return  x + "," + z + "," + y + "("+id+","+orientation+")";
    }

    public String toPrint(){
        if(id == -1) return null;
//        if(id >= 6)
//            return id + ", " + x + ", " + y + ", " + (z+1) + ", " + color + ", " +orientation;
        return id + ", " + x + ", " + y + ", " + z + ", " + color + ", " +orientation;
    }
}
