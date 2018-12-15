import java.util.*;

public class SmartBCM {

    private Form form;
    private Map<Integer, Integer> formTypes;
    private Map<Integer, int[]> bricks;


    private void init(){
        bricks = new HashMap<>();
        int[] myIntArray = new int[]{1,1};
        bricks.put(0,myIntArray);

        myIntArray = new int[]{1,2};
        bricks.put(1,myIntArray);

        myIntArray = new int[]{1,3};
        bricks.put(2,myIntArray);

        myIntArray = new int[]{1,4};
        bricks.put(3,myIntArray);

        myIntArray = new int[]{1,6};
        bricks.put(4,myIntArray);

        myIntArray = new int[]{1,8};
        bricks.put(5,myIntArray);

        myIntArray = new int[]{2,2};
        bricks.put(6,myIntArray);

        myIntArray = new int[]{2,3};
        bricks.put(7,myIntArray);

        myIntArray = new int[]{2,4};
        bricks.put(8,myIntArray);

        myIntArray = new int[]{2,6};
        bricks.put(9,myIntArray);

        myIntArray = new int[]{2,8};
        bricks.put(10,myIntArray);
    }

    public SmartBCM(Form form, Map<Integer, Integer> formTypes) {
        this.form = form;
        this.formTypes = formTypes;
        init();
    }

    private Piece getMyReplacer(Integer level, int line, int column){
        Piece currentPiece = form.getLevel(level).get(line,column);
        if(currentPiece == null) return null;
            for(int i = 10; i>=1; i--){
                    int val = formCanBePlaced(level, currentPiece, i, line, column);
                    if (val == 0) {
                        currentPiece.setId(i);
                        currentPiece.setOrientation(0);
                        return currentPiece;
                    } else if (val == 1) {
                        currentPiece.setId(i);
                        currentPiece.setOrientation(1);
                        return currentPiece;
                    }
            }
        unu(level, line, column);
        return null;
    }

    private boolean ifSubLevelOfMyPieceIsNullOrMyPieceIsReallyShittyAndCannotBePlacedPleaseAddPiecesOnBaseLevelSoICanPlaceThisShitCuzImTiredForNameOfGodIsElevenPMFuckMyLife(Integer level, int x, int y){
        try {
            if (level < 2) return false;
            for (int i = level - 1; i >= 1; i--) {
                System.out.println("SUB LEVEL ( " + level + " ) -> " + x + " " + i + " " + y);
                Piece piece = form.getLevel(i).get(x, y);
                if (piece != null) {
                    return true;
                } else {
                    //form.getLevel(i).update(x,y, new Piece(0,x, i, y, "#0000FF", 0));
                    form.getLevel(i).update(x, y, new Piece(0, x, i, y, form.getLevel(level).get(x, y).getColor(), 0));

                }
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    private boolean unu(Integer level, int x, int y){
        return false;
        //return ifSubLevelOfMyPieceIsNullOrMyPieceIsReallyShittyAndCannotBePlacedPleaseAddPiecesOnBaseLevelSoICanPlaceThisShitCuzImTiredForNameOfGodIsElevenPMFuckMyLife(level, x, y);
    }


    private int formCanBePlaced(Integer level, Piece currentPiece, int form, int line, int column){

        boolean needsDifOrientation = false;
        if(level>1 && this.form.getLevel(level-1) != null && this.form.getLevel(level-1).get(line,column) != null && this.form.getLevel(level-1).get(line,column).getId() == form){
            needsDifOrientation = true;
        }
        int orientation = -1;
        //needsDifOrientation = false;
        if(needsDifOrientation){
            Piece aiaDeJos = this.form.getLevel(level-1).get(line,column);
            if(aiaDeJos.getId() != -1) {
                orientation = aiaDeJos.getOrientation();

                if (orientation == 0)
                    orientation = 1;
                else
                    orientation = 0;
            }
        }

        //System.out.println("\n\nAICI -> " + needsDifOrientation + " " + orientation + " : " + this.form.getLevel(level).get(line,column).getOrientation() + "\n\n");
        int[] myForm = bricks.get(form);
        if(myForm[0] == 1){

            // Verificare pe linie
                if(orientation == -1 || orientation == 0) {
                    Vector<Piece> currentLine = this.form.getLevel(level).getLine(line);
                    List<Piece> lineList = currentLine.subList(currentPiece.getZ(), currentPiece.getZ() + myForm[1]);
//                    System.out.println(currentLine);
//                    System.out.println(lineList);
//                    System.out.println('\n');
                    int cnt = 0;
                    for (Piece p : lineList) {

                        if (p == null || p.getId() == -1) return -1;;
                        cnt++;
                    }
                    if (cnt == lineList.size()) {
                        for (int i = currentPiece.getZ() + 1; i < currentPiece.getZ() + myForm[1]; i++) {
                            Piece piece = this.form.getLevel(level).get(currentPiece.getX(), i);
                            if (piece != null) piece.setId(-1);
                            this.form.getLevel(level).update(currentPiece.getX(), i, piece);
                            unu(level, currentPiece.getX(), i);
                        }
                        return 0;
                    }
                }

            // Verificare pe coloana

                if(orientation == -1 || orientation == 1) {
                    Vector<Piece> currentColumn = this.form.getLevel(level).getColumn(column);
                    if(currentPiece.getX()+ myForm[1] >= currentColumn.size()) return -1;
                    List<Piece> columnList = currentColumn.subList(currentPiece.getX(), currentPiece.getX() + myForm[1]);
//                    System.out.println(currentColumn);
//                    System.out.println(columnList);
//                    System.out.println('\n');
                    int cnt = 0;
                    cnt = 0;
                    for (Piece p : columnList) {
                        if (p == null || p.getId() == -1) return -1;;
                        cnt++;
                    }
                    if (cnt == columnList.size()) {
                        for (int z = currentPiece.getX(); z < currentPiece.getX() + myForm[1]; z++) {
                            Piece piece = this.form.getLevel(level).get(z, currentPiece.getZ());
                            if (piece != null) piece.setId(-1);
                            this.form.getLevel(level).update(z, currentPiece.getZ(), piece);
                            unu(level, z, currentPiece.getZ());
                        }
                        return 1;
                    }
                }

        }else if(myForm[0] == 2){

            // Verificare pe linie
                if(orientation == -1 || orientation == 0) {
                    Vector<Piece> currentLine = this.form.getLevel(level).getLine(line);
                    Vector<Piece> nextLine = this.form.getLevel(level).getLine(line + 1);
                    if (nextLine == null) return -1;
//                    System.out.println(currentPiece.getZ() + " " + (currentPiece.getZ() + myForm[1]) + " -> " + myForm[0] + " " + myForm[1]);
//                    System.out.println(currentLine);
//                    System.out.println(nextLine);
                    List<Piece> lineList = currentLine.subList(currentPiece.getZ(), currentPiece.getZ() + myForm[1]);

                    List<Piece> nextLineList = nextLine.subList(currentPiece.getZ(), currentPiece.getZ() + myForm[1]);

//                    System.out.println(currentLine);
//                    System.out.println(nextLine);
//                    System.out.println(lineList);
//                    System.out.println(nextLineList);
//                    System.out.println('\n');
                    int cnt1 = 0, cnt2 = 0;
                    for (Piece p : lineList) {
                        if (p == null || p.getId() == -1) return -1;;
                        cnt1++;
                    }
                    for (Piece p : nextLineList) {
                        if (p == null || p.getId() == -1) return -1;;
                        cnt2++;
                    }
                    if (cnt1 == lineList.size() && cnt2 == nextLineList.size() && cnt1 == cnt2) {
                        for (int i = currentPiece.getZ() + 1; i < currentPiece.getZ() + myForm[1]; i++) {
                            Piece piece = this.form.getLevel(level).get(currentPiece.getX(), i);
                            Piece piece2 = this.form.getLevel(level).get(currentPiece.getX() + 1, i);
                            if (piece != null) piece.setId(-1);
                            if (piece2 != null) piece2.setId(-1);
                            this.form.getLevel(level).update(currentPiece.getX(), i, piece);
                            this.form.getLevel(level).update(currentPiece.getX() + 1, i, piece2);
                            unu(level, currentPiece.getX(), i);
                            unu(level, currentPiece.getX() + 1, i);
                        }
                        Piece piece = this.form.getLevel(level).get(currentPiece.getX() + 1, currentPiece.getZ());
                        if (piece != null) piece.setId(-1);
                        this.form.getLevel(level).update(currentPiece.getX() + 1, currentPiece.getZ(), piece);
                        return 0;
                    }
                }

            // Verificare pe coloana

                if(orientation == -1 || orientation == 1) {
                    int cnt1 = 0, cnt2 = 0;
                    Vector<Piece> currentColumn = this.form.getLevel(level).getColumn(column);
                    Vector<Piece> nextCurrentColumn = this.form.getLevel(level).getColumn(column + 1);
//                    System.out.println("MUIE " + currentColumn);
//                    System.out.println("MUIE " + nextCurrentColumn);
//                    System.out.println(currentPiece.getX()+ " " +  myForm[1] + "  " + currentColumn.size());
                    if (nextCurrentColumn == null) return -1;
                    if(currentPiece.getX()+ myForm[1] >= currentColumn.size()) return -1;
                    List<Piece> columnList = currentColumn.subList(currentPiece.getX(), currentPiece.getX() + myForm[1]);
                    List<Piece> nextColumnList = nextCurrentColumn.subList(currentPiece.getX(), currentPiece.getX() + myForm[1]);

//                    System.out.println(currentColumn);
//                    System.out.println(nextCurrentColumn);
//                    System.out.println(columnList);
//                    System.out.println(nextColumnList);
//                    System.out.println('\n');
                    cnt1 = 0;
                    cnt2 = 0;
                    for (Piece p : columnList) {
                        if (p == null || p.getId() == -1) return -1;;
                        cnt1++;
                    }
                    for (Piece p : nextColumnList) {
                        if (p == null || p.getId() == -1) return -1;
                        cnt2++;
                    }
                    if (cnt1 == columnList.size() && cnt2 == nextColumnList.size() && cnt1 == cnt2) {
                        for (int z = currentPiece.getX(); z < currentPiece.getX() + myForm[1]; z++) {
                            Piece piece = this.form.getLevel(level).get(z, currentPiece.getZ());
                            Piece piece2 = this.form.getLevel(level).get(z, currentPiece.getZ() + 1);
                            if (piece != null) piece.setId(-1);
                            if (piece2 != null) piece2.setId(-1);
                            this.form.getLevel(level).update(z, currentPiece.getZ(), piece);
                            this.form.getLevel(level).update(z, currentPiece.getZ() + 1, piece2);
                            unu(level, z, currentPiece.getZ());
                            unu(level, z, currentPiece.getZ() + 1);
                        }
                        Piece piece = this.form.getLevel(level).get(currentPiece.getX(), currentPiece.getZ() + 1);
                        if (piece != null) piece.setId(-1);
                        this.form.getLevel(level).update(currentPiece.getX(), currentPiece.getZ() + 1, piece);
                        return 1;
                    }
                }

        }

        return -1;
    }

    private Integer getForm(Integer wanted){
        if(formTypes.get(wanted) > 0){
            Integer count = formTypes.get(wanted);
            formTypes.put(wanted, count - 1);
            return wanted;
        }
        return -1;
    }

    private int getColorOcc(String color, List<Piece> L1, List<Piece> L2, List<Piece> L3){
        int occ = 0;
        for(int i=0; i<Math.min(Math.min(L1.size(), L2.size()), L3.size()); i++){
            if(L1.get(i) != null){
                if(L1.get(i).getColor().equals(color))occ++;
            }
        }
        return occ;
    }

    private boolean fillEmptySpaces(Integer level, int x, int y){

        if(form.getLevel(level) == null) return false;
        if(y<2 || y> form.getLevel(level).getColumns() -2) return false;
        Vector<Piece> precedentLine = this.form.getLevel(level).getLine(x - 1);
        Vector<Piece> currentLine = this.form.getLevel(level).getLine(x);
        Vector<Piece> nextLine = this.form.getLevel(level).getLine(x + 1);
        if(precedentLine == null || currentLine == null || nextLine == null) return false;
        List<Piece> precedentThree = precedentLine.subList(y-1, y+2);
        List<Piece> currentThree = currentLine.subList(y-1, y+2);
        List<Piece> nextThree = nextLine.subList(y-1, y+2);
        if(precedentThree == null || currentThree == null || nextThree == null) return false;
        int oPL = Collections.frequency(precedentThree, null);
        int oCL = Collections.frequency(currentThree, null);
        int oNL = Collections.frequency(nextThree, null);

        int redOcc=0, yellOcc=0, blueOcc=0;
        redOcc = getColorOcc("#FF0000", precedentThree, currentThree, nextThree);
        yellOcc = getColorOcc("#FFFF00", precedentThree, currentThree, nextThree);
        blueOcc = getColorOcc("#0000FF", precedentThree, currentThree, nextThree);

        String color = "";
        if(redOcc > yellOcc && redOcc > blueOcc){
            color = "#FF0000";
        }else if (yellOcc > redOcc && yellOcc > blueOcc){
            color = "#FFFF00";
        }else{
            color = "#0000FF";
        }

        if(oCL == 3 || oPL == 3 || oNL == 3) return false;

        if(oPL == 0 || oNL == 0 || (oPL + oNL) > 4) {
            unu(level, x, y);
            form.getLevel(level).update(x,y,new Piece(0,x, level, y, color, 0));
            return true;
        }

        return false;
    }

    private void computeBCM(int level){
        if(level > form.getLength()) return;
        for(int x = 0; x < form.getLevel(level).getLines(); x++){
            for(int y = 0; y < form.getLevel(level).getColumns(); y++){

                Piece currentPiece = form.getLevel(level).get(x, y);
                if(currentPiece != null && currentPiece.getId() != -1){
                    Piece replacer = getMyReplacer(level, x, y);
                    if(replacer != null){
                        unu(level, x, y);
                        form.getLevel(level).update(x,y,replacer);
                    }
                }
            }
        }
    }

    public void fillMatrix(){
        for(int i = 0; i<= form.getLength();i++){
            int level = i;
            for(int x = 0; x < form.getLevel(level).getLines(); x++){
                for(int y = 0; y < form.getLevel(level).getColumns(); y++){
                    Piece currentPiece = form.getLevel(level).get(x, y);
                    if(currentPiece == null ){
                        fillEmptySpaces(level, x, y);
                    }
                }
            }
        }

    }

    public void solve(){
        for(int i = 0; i<= form.getLength();i++){
            computeBCM(i);
        }
    }

    public Form getResult(){
        return this.form;
    }
}
