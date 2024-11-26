import java.util.Scanner;

public class ProjectLogic {
    private Note pattern;
    private Scanner scan;
    private int count, score, highscore, length;
    private String mode;

    public ProjectLogic() {
        scan = new Scanner(System.in);
        count = 0;
        score = 0;
        highscore = 0;
        length = 5;
        mode = "hard";
        pattern = new Note(length, mode);
    }

    //for setting initial highscore
    public ProjectLogic(int hs) {
        scan = new Scanner(System.in);
        count = 0;
        score = 0;
        highscore = hs;
        length = 5;
        mode = "hard";
        pattern = new Note(length, mode);
    }

    public void start() {

        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Welcome to the 'Taiko no Tatsujin' pattern reading simulator, " + name + ". Would you like a tutorial? (y/n) ");
        String tutor = scan.nextLine();
        if (tutor.equals("y")) {
            instructions(name);
        } else {
            System.out.println("Alright");
        }

        int choice = 0;
        while (choice != 5) {
            System.out.println("\n*********** Menu ***********");
            System.out.println("1. Play");
            System.out.println("2. Change mode");
            System.out.println("3. Change length");
            System.out.println("4. Change keys");
            System.out.println("5. Exit");
            System.out.println("****************************");
            System.out.print("Enter option: ");
            choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                String again = "y";
                while (again.equals("y")) {
                    play();
                    System.out.print("Play again? (y/n) ");
                    again = scan.nextLine();
                }
            } else if (choice == 2) {
                changeMode();
            } else if (choice == 3) {
                changeLength();
            } else if (choice == 4) {
                changeKeys();
            } else if (choice != 5){
                System.out.println("Invalid input");
            }
        }
        System.out.print("\nGoodbye, ");
        if (highscore > 99) {
            System.out.println("drum master");
        } else {
            System.out.println(name);
        }

    }

    private void play() {
        count = 0;
        score = 0;
        pattern.setLives(mode);

        boolean alive = true;
        while (alive) {
            pattern.newStream();
            System.out.println(pattern.display());
            String user = scan.nextLine();
            if (pattern.check(user)) {
                count++;
                score += length;
            } else {
                alive = false;
            }
        }
        //since easy mode has 3 lives and still increments score and count
        //when a pattern is entered incorrectly, we have to subtract count by 2
        //and score by 2 * length when the user inevitably loses
        if (mode.equals("easy")) {
            count -= 2;
            score -= 2 * length;
        }

        System.out.println("Streams completed: " + count);
        if (score > highscore) {
            System.out.println("New highscore: " + score + " (from " + highscore + ")");
            highscore = score;
        } else {
            System.out.println("Score: " + score + " (Highscore: " + highscore + ")");
        }

    }

    private void instructions(String name) {
        Note tutorial = new Note();
        if (Math.random() > .9) {
            tutorial.setLength(name.length()*5);
        }
        tutorial.newStream();
        System.out.println("How to play: ");
        System.out.println("The goal of this game is to train your pattern reading abilities in the hit rhythm game 'Taiko No Tatsujin'");
        System.out.println("Each pattern requires you to press the red and blue key buttons in a specified order");
        System.out.println("The default red keys are ',' and '.'");
        System.out.println("The default blue keys are '[' and ']'");
        System.out.println("Now type out the pattern " + tutorial.display() + " in the correct order and press enter: ");
        if (tutorial.check(scan.nextLine())) {
            System.out.println("Nicely done");
        } else {
            System.out.println("Not quite, but you'll figure it out soon enough...");
        }
    }

    private void changeKeys() {
            System.out.println("\nEnter new keybindings below (cannot be 0 or 1):");
            System.out.print("d1: ");
            String d1 = scan.nextLine();
            System.out.print("d2: ");
            String d2 = scan.nextLine();
            System.out.print("k1: ");
            String k1 = scan.nextLine();
            System.out.print("k2: ");
            String k2 = scan.nextLine();
            pattern.setKeys(d1, d2, k1, k2);
    }

    private void changeMode() {
        String choice = "n/a";
        while (choice.equals("n/a")) {
            System.out.print("\nMode? (easy/hard) ");
            choice = scan.nextLine();
            if (choice.equals("easy") || choice.equals("hard")) {
                mode = choice;
            } else {
                System.out.println("Try again");
                choice = "n/a";
            }
        }
    }

    private void changeLength() {
        System.out.print("\nNew length: ");
        length = scan.nextInt();
        scan.nextLine();
        pattern.setLength(length);
    }
}
