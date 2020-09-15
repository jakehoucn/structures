package recursion.labyrince;

import org.junit.Test;

/**
 * @author shkstart
 * @create 2020-09-08 18:51
 */
public class Labyrinth {

    /**
     * 逻辑:1为墙壁,0为未尝试的地方,如果最后一个元素为2那么证明游戏成功
     *如果一个点为0,那么先赋值2,继续走其它的点,如果其他的点都走不通,那么给该点赋值3,返回false
     *如果一个点不为0那么返回false
     *
     * */
    @Test
    public void testWay(){
        int[][] map = new int[8][8];
        for (int i = 0; i < map.length; i++) {
            map[i][0]=1;
            map[i][7]=1;
            map[0][i]=1;
            map[7][i]=1;
        }

        map[3][1]=1;
        map[2][3]=1;
        map[3][2]=1;
        map[3][3]=1;

        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }

        System.out.println("------------------------");

        setWay(map,1,1);
        int count1=0;

        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
                if (anInt==2){
                count1++;
                }
            }
            System.out.println();
        }

        System.out.println("---------------------------------------");

        int[][] map2 = new int[8][8];
        for (int i = 0; i < map.length; i++) {
            map2[i][0]=1;
            map2[i][7]=1;
            map2[0][i]=1;
            map2[7][i]=1;
        }

        map2[3][1]=1;
        map2[2][3]=1;
        map2[3][2]=1;
        map2[3][3]=1;

        for (int[] ints : map2) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }

        System.out.println("---------------------------------------");

        setWay2(map2,1,1);
        int count2 = 0;
        for (int[] ints : map2) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
                if (anInt==2){
                    count2++;
                }
            }
            System.out.println();
        }

        System.out.println(count1+"    "+ count2);
    }


    public boolean setWay(int[][] map, int i, int j) {
        int x = map.length - 2;
        int y = map[1].length - 2;
        if (i>x||j>y){
            return false;
        }

        if (map[x][y] == 2) {
            return true;
        }else{
            if (map[i][j]==0){
                map[i][j] = 2;
                if (setWay(map, i, j+1)) {
                    return true;
                } else if (setWay(map, i+1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else if (setWay(map, i- 1, j )) {
                    return true;
                } else {
                    map[i][j]=3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    public boolean setWay2(int[][] map, int i, int j) {
        int x = map.length - 2;
        int y = map[1].length - 2;
        if (i>x||j>y){
            return false;
        }

        if (map[x][y] == 2) {
            return true;
        }else{
            if (map[i][j]==0){
                map[i][j] = 2;
                if (setWay2(map, i+1, j)) {
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i -1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j]=3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
