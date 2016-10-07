package com.chess;

/**
 * @author: DorotaJanicka
 * @since: 06.10.2016
 */
public class CanGo {


    public CanGo(){

    }

    public boolean White(int i, int j, int iNext, int jNext, GameObject[][] fields){ /** sprawdzam czy jest to plansza na którą mogę się ruszyć */


        System.out.println("Funkcja cangowhite");
        System.out.println(fields[(4)][(4)].pawn);
        System.out.println("index2[0]: "+ i+" index2[1]: "+ j);
        System.out.println("index[0]: "+iNext+" index[1]: "+jNext);
        if ((i+1) == iNext){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((j-1) == jNext) {
                System.out.println("Po drugim ifie indeksowym (index2[0]-1) == index[0])");
                return true;
            }
            else if ((j+1) ==jNext){
                System.out.println("Po drugim ifie indeksowym (index2[0]+1) ==index[0])");
                return true;
            }
        }
        else if(i+2==iNext){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((j-2) == jNext) {
                System.out.println("Po drugim ifie indeksowym (index2[0]-2) == index[0])");
                fields[(i+1)][(j-1)].pawn=null;
                return true;
            }
            else if ((j+2) ==jNext){
                System.out.println("Po drugim ifie indeksowym (index2[0]+2) ==index[0])");
                fields[(i+1)][(j+1)].pawn=null;
                return true;
            }
        }
        System.out.println("Coś w ifach poszło nie tak");
        return false;
    }

    public boolean Black(int i, int j, int iNext, int jNext, GameObject[][] fields){ /** sprawdzam czy jest to plansza na którą mogę się ruszyć */
        System.out.println("Funkcja cangoblack");
        System.out.println("index2[0]: "+ i+" index2[1]: "+ j);
        System.out.println("index[0]: "+iNext+" index[1]: "+jNext);
        if ((i-1) == iNext){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((j+1) == jNext || (j-1) ==jNext)
                System.out.println("Po drugim ifie indeksowym");
            return true;
        }
        else if(i-2==iNext){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((j-2) == jNext) {
                System.out.println("Po drugim ifie indeksowym (index2[0]-2) == index[0])");
                System.out.println(fields[(i-1)][(j-1)].pawn);
                System.out.println("i-1" + (i-1) + "j - 1" + (j-1));
                fields[(i-1)][(j-1)].pawn=null;
                System.out.println(fields[(i-1)][(j-1)].pawn);
                return true;
            }
            else if ((j+2) ==jNext){
                System.out.println("Po drugim ifie indeksowym (index2[0]+2) ==index[0])");
                System.out.println(fields[(i-1)][(j+1)].pawn);
                System.out.println("i-1" + (i-1) + "j + 1" + (j+1));
                fields[(i-1)][(j+1)].pawn=null;
                System.out.println(fields[(i-1)][(j+1)].pawn);
                return true;
            }
        }

        System.out.println("Coś w ifach poszło nie tak");
        return false;
    }

}
