package sparsearr;

import java.io.*;

public class ArrIOTest {
    public static void main(String[] args) throws IOException {
        int[][] a = new int[2][3];
        a[0][0] = 10;
        a[0][1] = 10;
        a[0][2] = 1;
        a[1][0] = 2;
        a[1][1] = 2;
        a[1][2] = 5;

        File file = new File("./src/sparsearr/map.data");
        file.createNewFile();

        BufferedWriter bw  =  new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length;j++){
                bw.write(a[i][j] + "\t");
            }
            bw.write("\r\n");
        }

        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(file) );
        String line = null;
        int[][] b = null;
        int row = 0;
        while ((line = br.readLine())!= null){
            row++;
            String[] temp = line.split("\t");
            if (row == 1){
                b = new int[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];
            }else{
                b[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]= Integer.parseInt(temp[2]);
            }
        }

        for (int[] ints : b) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
    }
}
