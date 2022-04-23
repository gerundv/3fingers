package me.gerundv;

import java.util.Scanner;


public class main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int tempMenu = 0;
        while(true) {
            menu(tempMenu, scanner);
        }
    }

    static void menu(int tempMenu, Scanner scanner){

        System.out.println("Enter 1 to play " +"\nEnter 2 for exit");
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

        int scoreFirstFinal = 0;
        int scoreSecondFinal = 0;
        int humanScore = 0;
        int scoreAll = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 if you want to play first, or 2 if you want to play second");
        int x = scanner.nextInt();
        System.out.println("How many rounds do you want to play?");
        int temp = scanner.nextInt();
        System.out.println("The game is started!");

        for(int i = 1; i <= temp; i++)
        {
            AffableThread machine = new AffableThread();
            machine.start();

            while( humanScore < 1 || humanScore > 3 ) {
                System.out.println("\nHow many fingers do you want to show?");
                humanScore = scanner.nextInt();
                if ( humanScore < 1 || humanScore > 3 ){
                    System.out.println("You entered an incorrect number");
                }
            }

            try{
                machine.join(1000);
            }catch(InterruptedException e){}

            System.out.println("The machine showed " + machine.getScore() + " fingers");
            scoreAll = humanScore + machine.getScore();
            System.out.println("Total fingers in round " + i +  " : " + scoreAll);

            if (scoreAll % 2 == 0){
                System.out.println("The second player won the round " + i);
                scoreSecondFinal += scoreAll;
                scoreFirstFinal -= scoreAll;
            }
            else {
                System.out.println("The first player won the round " + i);
                scoreFirstFinal += scoreAll;
                scoreSecondFinal -= scoreAll;
            }

            humanScore = 0;
            machine.interrupt();
        }

        System.out.println("\nScore first player: " + scoreFirstFinal + "\nScore second player: " + scoreSecondFinal + "\n");

        if (scoreFirstFinal > scoreSecondFinal && x == 1){
            System.out.println("YOU WIN");
        }
        else if (scoreFirstFinal < scoreSecondFinal && x == 1){
            System.out.println("YOU LOSE");
        }
        else if (scoreFirstFinal < scoreSecondFinal && x == 2){
            System.out.println("YOU WIN");
        }
        else if (scoreFirstFinal > scoreSecondFinal && x == 2){
            System.out.println("YOU LOSE");
        }

        System.out.println("\n");
    }

    static class AffableThread extends Thread
    {
        private volatile int score = 0;

        public int getScore(){
            return score;
        }

        @Override
        public void run()
        {
            score = 1 + (int) (Math.random() * 3);
        }
    }

}
