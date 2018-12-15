import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Main {

    public static void main(String argv[]){
        //generateForm(3, "form.csv");
        startBKT();
    }

    private static void probe(){
        SmartMatrix<Integer> m = new SmartMatrix<>(10,10);
        m.initMatrixWithElem(0);
        for(int i=3; i< 6; i++){
            for(int j=2; j< 8; j++){
                m.add(i,j, 1);
            }
        }
        m.print();
        System.out.println('\n');
        Vector<Integer> column = m.getColumn(1);
        for(int i=0; i<10; i++){
            System.out.println(column.get(i));
        }
    }

    public static void startBKT(){
        //generateForm(2, "form.csv");
        Form form = SolutionWriter.readForm("form.csv");
        if(form == null) return;
        form.print(1);
        Map<Integer, Integer> formTypes = new HashMap<>();
        formTypes.put(1,100);
        formTypes.put(2,100);
        formTypes.put(3,100);
        formTypes.put(4,100);
        formTypes.put(5,100);

        SmartBCM smartBCM = new SmartBCM(form, formTypes);
        smartBCM.solve();
        smartBCM.fillMatrix();
        Form solvedFormOne = smartBCM.getResult();


        SmartBCM smartBKT_1 = new SmartBCM(solvedFormOne, formTypes);
        smartBKT_1.solve();
        Form solvedForm = smartBKT_1.getResult();
        //solvedForm.print(solvedForm.getLength() - 2);
        solvedForm.print(1);
        SolutionWriter.formWrite("input", solvedForm);
    }


    public static void generateForm(int levels, String filename){
        try {
            FileWriter fw = new FileWriter(new File("form.csv"));
        }catch (Exception e){}

        if(levels >= 1) {
            //LEVEL 1
                SolutionWriter.manualWrite(filename, 1, 6, 9, 28, 19, "#FF0000");
                SolutionWriter.manualWrite(filename, 1, 0, 17, 0, 19, "#FFFF00");
                SolutionWriter.manualWrite(filename, 1, 4, 17, 4, 19, "#FFFF00");
                SolutionWriter.manualWrite(filename, 1, 30, 17, 30, 19, "#FFFF00");
                SolutionWriter.manualWrite(filename, 1, 34, 17, 34, 19, "#FFFF00");
        }

        if(levels >= 2) {
            //LEVEL 2
            SolutionWriter.manualWrite(filename, 2, 6, 9, 28, 19, "#FF0000");
            SolutionWriter.manualWrite(filename, 2, 0, 16, 5, 17, "#FFFF00");
            SolutionWriter.manualWrite(filename, 2, 30, 16, 34, 17, "#FFFF00");

            SolutionWriter.manualWrite(filename, 2, 0, 18, 0, 20, "#FFFF00");
            SolutionWriter.manualWrite(filename, 2, 4, 18, 4, 20, "#FFFF00");

            SolutionWriter.manualWrite(filename, 2, 30, 18, 30, 20, "#FFFF00");
            SolutionWriter.manualWrite(filename, 2, 34, 18, 34, 20, "#FFFF00");
        }

        if(levels >= 3) {
            //LEVEL 3
            SolutionWriter.manualWrite(filename, 3, 6, 9, 28, 19, "#FF0000");

            SolutionWriter.manualWrite(filename, 3, 1, 15, 4, 18, "#FFFF00");
            SolutionWriter.manualWrite(filename, 3, 30, 15, 4, 18, "#FFFF00");

            SolutionWriter.manualWrite(filename, 3, 0, 17, 0, 19, "#FFFF00");
            SolutionWriter.manualWrite(filename, 3, 34, 17, 34, 19, "#FFFF00");

            SolutionWriter.manualWrite(filename, 3, 2, 14, 3, 14, "#FFFF00");
            SolutionWriter.manualWrite(filename, 3, 31, 14, 32, 14, "#FFFF00");

            SolutionWriter.manualWrite(filename, 3, 5, 19, 5, 19, "#FFFF00");
            SolutionWriter.manualWrite(filename, 3, 30, 19, 30, 19, "#FFFF00");
        }

    }

    public static void manualGenerator(){
        Form form = new Form(3);

        SmartMatrix<Piece> level1 = new SmartMatrix<Piece>(10,10);
        SmartMatrix<Piece> level2 = new SmartMatrix<Piece>(10,10);
        SmartMatrix<Piece> level3 = new SmartMatrix<Piece>(10,10);

        level1.add(1,1,new Piece(1,1,1));
        level1.add(1,2,new Piece(1,2,1));
        level1.add(1,3,new Piece(1,3,1));
        level1.add(1,4,new Piece(1,4,1));
        level1.add(1,7,new Piece(1,7,1));
        level1.add(1,8,new Piece(1,8,1));

        level1.add(2,2,new Piece(2,2,1));
        level1.add(2,3,new Piece(2,3,1));
        level1.add(2,4,new Piece(2,4,1));
        level1.add(2,5,new Piece(2,5,1));
        level1.add(2,7,new Piece(2,7,1));
        level1.add(2,8,new Piece(2,8,1));

        level1.add(3,4,new Piece(3,4,1));
        level1.add(3,6,new Piece(3,6,1));

        level1.add(4,2,new Piece(4,2,1));
        level1.add(4,3,new Piece(4,3,1));
        level1.add(4,4,new Piece(4,4,1));
        level1.add(4,5,new Piece(4,5,1));

        level1.add(5,5,new Piece(5,5,1));
        level1.add(5,6,new Piece(5,6,1));
        level1.add(5,7,new Piece(5,7,1));
        level1.add(5,8,new Piece(5,8,1));

        level1.add(6,4,new Piece(6,4,1));
        level1.add(6,5,new Piece(6,5,1));


        ////////////////////////////////////////////////////////

        level2.add(1,7, new Piece(1,7,2, "#FF0000"));
        level2.add(1,8, new Piece(1,8,2, "#FF0000"));

        level2.add(2,2, new Piece(2,2,2, "#FF0000"));
        level2.add(2,3, new Piece(2,3,2, "#FF0000"));
        level2.add(2,4, new Piece(2,4,2, "#FF0000"));
        level2.add(2,5, new Piece(2,5,2, "#FF0000"));
        level2.add(2,6, new Piece(2,6,2, "#FF0000"));
        level2.add(2,7, new Piece(2,7,2, "#FF0000"));


        level2.add(4,3, new Piece(4,3,2, "#FF0000"));
        level2.add(4,4, new Piece(4,4,2, "#FF0000"));
        level2.add(4,5, new Piece(4,5,2, "#FF0000"));

        level2.add(5,5, new Piece(5,5,2, "#FF0000"));
        level2.add(5,6, new Piece(5,6,2, "#FF0000"));
        level2.add(5,7, new Piece(5,7,2, "#FF0000"));
        level2.add(5,8, new Piece(5,8,2, "#FF0000"));

        ////////////////////////////////////////////////////////

        level3.add(1,7, new Piece(1,7,3, "#0000FF"));
        level3.add(2,7, new Piece(2,7,3, "#0000FF"));

        level3.add(4,5, new Piece(4,5,3, "#0000FF"));
        level3.add(5,5, new Piece(5,5,3, "#0000FF"));


        form.addLevel(0,level1);
        form.addLevel(1,level2);
        form.addLevel(2,level3);

        SolutionWriter.formWrite("input",form);
    }

}
