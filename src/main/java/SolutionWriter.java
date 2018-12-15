import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SolutionWriter {

    public static boolean formWrite(String filename, Form form){
        if(filename == null || filename.length()<1 || form == null)
            return false;
        try{
            FileWriter fw;

            fw = new FileWriter(new File(filename+".csv"));
            for(int i = 1; i <= form.getLength(); i++){
                SmartMatrix<Piece> matrix = form.getLevel(i);
                for(int j = 0; j < matrix.getLines(); j++){
                    for(int z = 0; z < matrix.getColumns(); z++){
                        if(matrix.get(j, z) != null){
                            if(matrix.get(j, z).toPrint() != null)
                            {
                                fw.write(matrix.get(j, z).toPrint());
                                fw.write('\n');
                            }
                        }
                    }
                }
            }
            fw.write('\n');


            fw.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static boolean manualWrite(String filename, int level, int startX, int startY, int endX, int endY, String color){
        if(filename == null || filename.length()<1 )
            return false;
        try{
            FileWriter fw;
            fw = new FileWriter(new File(filename), true);
            for(int i = startX; i<= endX; i++){
                for(int j = startY; j<= endY; j++){
                    fw.write("0, " + String.valueOf(i)  + ", " +String.valueOf(level) + ", " + String.valueOf(j) + ", "+color+", 0");
                    fw.write('\n');
                }
            }

            fw.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static Form readForm(String filename) {
        Form form = new Form(1);
        try {
            File file = new File(filename);

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null){
                String[] listOfParams = st.replace(" ", "").split(",");
                try{
                    int x=Integer.valueOf(listOfParams[1]),z=Integer.valueOf(listOfParams[2]), y=Integer.valueOf(listOfParams[3]);

                    String color;
                    if(x>5 && x<29 && y>8 && y<20){
                        color = "#FF0000";
                    }else if(z < 4){
                        color = "#FFFF00";
                    }else{
                        color = "#0000FF";
                    }
                    Piece piece = new Piece(0, x, z,y, color, 0);
                   // if(z < 8)
                        form.addPiece(Integer.valueOf(listOfParams[2]), piece);
                }catch (Exception e){
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            return form;
        }
        catch(Exception e){}
        return null;
    }
}
