package sparsearr;

import java.io.*;

public class SparseArr1 {

    public static void main(String[] args) {
        //1. create original array chessArr
        int[][] chessArr = new int[11][11];

        //2. assignment
        // 1 means black pieces 2 means while pieces//cheese
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        //3. count the number of elements
        int sum = 0;
        for (int[] row : chessArr) {
            for (int pieces : row) {
                if(pieces !=0){
                    sum++;
                }

                System.out.print(pieces + "  ");
            }
            System.out.println();
        }

        System.out.println("------------------------");

        //create sparse array
        int[][] spareArr = new int[sum+1][3];

        //assignment to spareArr[0] to record the row,list and sum;
        spareArr[0][0] = chessArr.length;
        spareArr[0][1] = chessArr[0].length;
        spareArr[0][2] = sum;


        //row of sparse array
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    spareArr[count][0] = i;
                    spareArr[count][1] = j;
                    spareArr[count][2] = chessArr[i][j];
                }
            }
        }

        for (int[] row : spareArr) {
            for (int pieces : row) {
                System.out.print(pieces + "  ");
            }
            System.out.println();
        }
        System.out.println("------------------------");

        int[][] chessArr2 = new int[spareArr[0][0]][spareArr[0][1]];
        for (int i = 1; i<spareArr.length;i++){
            chessArr2[spareArr[i][0]][spareArr[i][1]] = spareArr[i][2];
        }

        for (int[] row : chessArr2) {
            for (int pieces : row) {
                System.out.print(pieces + "  ");
            }
            System.out.println();
        }

        System.out.println(" --- test save and read as Char --- ");
        File file = new File("./src/sparsearr/mapA.data");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveChar(spareArr,file);
        int[][] chessArr3 = readChar(file);
        for (int[] ints : chessArr3) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.println(" --- test save and read as byte --- ");
        File file1 = new File("./src/sparsearr/mapBytes.data");
        int[][] chessArr4 = null;
        writeByte(spareArr,file1);
        chessArr4 = readBytes(file1);
        for (int[] ints : chessArr4) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }

    }

    public static void saveChar(int[][] x, File file){
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[i].length; j++) {
                    br.write(x[i][j] + "\t");
                }
                br.write("\r\n");
            }
        } catch (IOException e) {
            System.out.println("数据保存有误");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("数据保存有误");
                }
            }
        }
    }

    public static int[][] readChar(File file){
        BufferedReader br = null;
        int[][] b = new int[0][];
        try {
            br = new BufferedReader(new FileReader(file));
            String str  = null;
            b = null;
            int row =0;
            while ((str = br.readLine())!=null){
                String[] temp = str.split("\t");
                row++;
                if (row==1){
                    b = new int[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];
                }else{
                    b[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = Integer.parseInt(temp[2]);
                }
            }
            return b;
        } catch (IOException e) {
            System.out.println("数据恢复有误-未成功读取文件数据");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("数据恢复有误-未关闭输入流");
                }
            }
        }
        throw new RuntimeException("数据恢复有误");
    }

    public static void writeByte(int [][] x , File file){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[i].length; j++) {
                    int a  = x[i][j];
                    fos.write((String.valueOf(a)+",").getBytes());
                }
                fos.write("\n".getBytes());
            }
        } catch (IOException e) {
            System.out.println("数据存储有误");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println("流关闭有误");
                }
            }
        }
    }

    public static int[][] readBytes(File file){
        BufferedReader ns = null;
        try {
            ns = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str = null;
            int row = 0;
            int[][] b = null;
            while ((str = ns.readLine())!=null){
                String[] temp = str.split(",");
                row++;
                if (row == 1){
                    b = new int[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];
                }else{
                    b[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = Integer.parseInt(temp[2]);
                }
            }
            return b;
        } catch (IOException e) {
            System.out.println("数据读出有误");
        }finally {
            if (ns != null) {
                try {
                    ns.close();
                } catch (IOException e) {
                    System.out.println("流关闭有误");
                }
            }
        }
        throw new RuntimeException("数据读出失败");
    }
}
