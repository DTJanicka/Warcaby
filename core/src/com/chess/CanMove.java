package com.chess;

public class CanMove {

    Board board;
    Field[][] fields;

    public CanMove() {
        fields = new Field[8][8];
        board = new Board();
    }

    public boolean white(int i, int j) { /**dostaję plansze */
        fields = board.send();
        System.out.println("Funkcja canmovewhite");
        /** index[0] = i; index[1] = j */

        System.out.println("Plansza po tablicy indeksów: " + i + j);
        if (j == 0) {
            System.out.println("Po 1 ifie");
            if (board.getPawn(fields[(i + 1)][1]) == null) {
                System.out.println("Może się ruszyć");
                return true;
            } else if (board.getPawn(fields[(i + 2)][(j + 2)]) == null) {
                System.out.println("Wchodzę do pętli: else if(index2[0]+2==index[0] && index2[1]+2==index[1])");
                if (board.getPawn(fields[(i + 1)][1]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        } else if (j > 0 && j < 7) {
            System.out.println("Po 2 ifie");
            if (board.getPawn(fields[(i + 1)][(j - 1)]) == null || board.getPawn(fields[(i + 1)][(j + 1)]) == null) {
                System.out.println("Może się ruszyć");
                return true;
            } else if (board.getPawn(fields[(i + 2)][(j - 2)]) == null) {
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]+2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(i + 1)][(j - 1)]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            } else if (board.getPawn(fields[(i + 2)][(j + 2)]) == null) {
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]+2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(i + 1)][(j + 1)]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        } else if (j == 7) {
            System.out.println("Po 3 ifie");
            if (board.getPawn(fields[(i + 1)][6]) == null) {
                System.out.println("Może się ruszyć");
                return true;
            } else if (board.getPawn(fields[(i + 2)][(j - 2)]) == null) {
                if (board.getPawn(fields[(i + 1)][6]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        System.out.println("Nie może się ruszyć");
        return false;
    }

    public boolean black(int i, int j) { /**dostaję plansze */
        fields = board.send();
        System.out.println("Funkcja canmoveblack");
        /** index[0] = i; index[1] = j */

        System.out.println("Plansza po tablicy indeksów: " + i + j);
        if (j == 0) {
            System.out.println("Po 1 ifie");
            if (board.getPawn(fields[(i - 1)][1]) == null) {
                System.out.println("Może się ruszyć");
                return true;
            } else if (board.getPawn(fields[(i - 2)][(j + 2)]) == null) {
                System.out.println("Wchodzę do pętli: else if(index2[0]-2==index[0] && index2[1]+2==index[1])");
                if (board.getPawn(fields[(i - 1)][1]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        } else if (j > 0 && j < 7) {
            System.out.println("Po 2 ifie");
            if (board.getPawn(fields[(i - 1)][(j - 1)]) == null || board.getPawn(fields[(i - 1)][(j + 1)]) == null) {
                System.out.println("Może się ruszyć");
                return true;
            } else if (board.getPawn(fields[(i - 2)][(j - 2)]) == null) {
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]-2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(i - 1)][(j - 1)]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            } else if (board.getPawn(fields[(i - 2)][(j + 2)]) == null) {
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]-2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(i - 1)][(j + 1)]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        } else if (j == 7) {
            System.out.println("Po 3 ifie");
            if (board.getPawn(fields[(i - 1)][6]) == null) {
                System.out.println("Może się ruszyć");
                return true;
            } else if (board.getPawn(fields[(i - 2)][(j - 2)]) == null) {
                if (board.getPawn(fields[(i - 1)][6]) != null) {
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        System.out.println("Nie może się ruszyć");
        return false;
    }

}
