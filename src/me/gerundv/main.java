package me.gerundv;

import java.util.Scanner;


public class main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int tempMenu = 0;
        menu(tempMenu,scanner);

        switch (tempMenu){
            case 1:
                game();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                menu(tempMenu,scanner);
                break;
        }
    }

    static void menu(int tempMenu, Scanner scanner){
        System.out.println("Choose 1 for play\n" + "Choose 2 for exit");
        tempMenu = scanner.nextInt();
        switch (tempMenu){
            case 1:
                game();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                tempMenu = 0;
                menu(tempMenu,scanner);
                break;
        }
    }

    static void game(){
        System.out.println("How many rounds do you want to play?");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        System.out.println("The game is started!");

        Thread first = new AffableThread();
        Thread machine = new AffableThread();

    }

    static class AffableThread extends Thread
    {
        @Override
        public void run()	//Этот метод будет выполнен в побочном потоке
        {
            double temp = Math.random() * 3;
        }
    }

}
