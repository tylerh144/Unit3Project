public class Note {
    private String stream;
    private String d1, d2, k1, k2;
    private int length, lives;

    //default constructor
    public Note() {
        stream = "";
        d1 = ",";
        d2 = ".";
        k1 = "[";
        k2 = "]";
        length = 5;
        setLives("hard");
    }
    //constructor with length input
    public Note(int len, String mode) {
        stream = "";
        d1 = ",";
        d2 = ".";
        k1 = "[";
        k2 = "]";
        length = len;
        setLives(mode);
    }

    public void setKeys(String d1, String d2, String k1, String k2) {
        this.d1 = d1;
        this.d2 = d2;
        this.k1 = k1;
        this.k2 = k2;
        System.out.println("New keys (ddkk): " + d1 + " " + d2 + " " + k1 + " " + k2);
    }

    public void setLength(int newLength) {
        length = newLength;
    }

    public void setLives(String mode) {
        if (mode.equals("easy")) {
            lives = 3;
        } else if (mode.equals("hard")) {
            lives = 1;
        }
    }

    //dynamic live setter
    public void setLives(int lives) {
        this.lives = lives;
    }

    public void newStream() {
        stream = "";
        for (int i = 0; i < length; i++) {
            stream += (int) (Math.random() * 2);
        }
    }

    public String display() {
        String display = stream;
        display = display.replace("0", "\uD83D\uDD34");
        display = display.replace("1", "\uD83D\uDD35");
        return display;
    }

    public boolean check(String userStream) {
        userStream = userStream.replace(d1, "0");
        userStream = userStream.replace(d2, "0");
        userStream = userStream.replace(k1, "1");
        userStream = userStream.replace(k2, "1");
        boolean correct = userStream.equals(stream);
        if (!correct && lives > 1) {
            lives--;
            System.out.println("-1 life, Lives remaining: " + lives);
            return true;
        } else {
            return correct;
        }
    }
}
