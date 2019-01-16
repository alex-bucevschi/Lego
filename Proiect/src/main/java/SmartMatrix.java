import java.util.Vector;

public class SmartMatrix<T> {

    private Vector<Vector<T>>  matrix;
    private int lines=0, columns=0;

    public SmartMatrix(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;
        matrix = new Vector<Vector<T>>();
        initMatrix();
    }

    protected void initMatrix(){
        for(int i = 0; i < lines; i++){
            Vector<T> vector = new Vector<T>();
            for(int j = 0; j < columns; j++){
                vector.add(null);
            }
            matrix.add(vector);
        }
    }


    public void initMatrixWithElem(T elem){
        for(int i = 0; i < lines; i++){
            Vector<T> vector = new Vector<T>();
            for(int j = 0; j < columns; j++){
                vector.add(elem);
            }
            matrix.add(vector);
        }
    }

    public Vector<T> getLine(Integer line){
        if(line >= lines || line < 0) return null;
        return matrix.get(line);
    }

    public Vector<T> getColumn(Integer column){
        if(column >= columns || column < 0) return null;
        Vector<T> aux = new Vector<>();
        for(int j = 0; j < lines; j++){
            aux.add(matrix.get(j).get(column));
        }
        return aux;
    }

    public boolean add(int line, int column, T element){
        if(line > this.lines || column > this.columns || element == null)
            return false;
        Vector<T> vector = matrix.get(line);
        vector.add(column, element);
        return true;
    }

    public boolean update(int line, int column, T element){
        if(line > this.lines || column > this.columns)
            return false;
        Vector<T> vector = matrix.get(line);
        vector.set(column,element);
        return true;
    }

    public T get(int line, int column){
        if(line > this.lines || column > this.columns || line < 0 || column < 0 || line >= lines || column >= columns)
            return null;
        return matrix.get(line).get(column);
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public void printWithIndex(){
        System.out.println("Lines :" + lines + " - Columns: " + columns);
        for(int i = 0; i < lines; i++){
            for(int j = 0; j < columns; j++){
                System.out.println("Matrix["+i+"]["+j+"] = "+matrix.get(i).get(j));
            }
        }
    }

    public void print(){
        for(int i = 0; i < lines; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "SmartMatrix{" +
                "matrix=" + matrix +
                ", lines=" + lines +
                ", columns=" + columns +
                '}';
    }
}
