import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Form {

    private Map<Integer, SmartMatrix<Piece>> form;
    private int length;

    public Form(int length) {
        this.length = length;
        this.form = new HashMap<Integer, SmartMatrix<Piece>>();
    }

    public void addLevel(Integer level, SmartMatrix<Piece> matrix){
        form.put(level, matrix);
    }

    public void addPiece(Integer level, Piece piece){
        if(piece == null)return;
        if(form.get(level) == null){
            if(level > length) length = level;
            form.put(level ,new SmartMatrix<Piece>(35,35));
        }
        form.get(level).add(piece.getX(), piece.getZ(), piece);
    }

    public SmartMatrix<Piece> getLevel(Integer level){
        if(level > form.size()) return null;
        return form.get(level);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void print(int level){
        if(level > length) return;
        SmartMatrix<Piece> m = form.get(level);
        m.print();
    }

    @Override
    public String toString() {
        return "Form{" +
                "form=" + form +
                ", length=" + length +
                '}';
    }
}
