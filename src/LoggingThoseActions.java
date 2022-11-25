import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class LoggingThoseActions {

    ArrayList<String> lines = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    File log = new File("Log.txt");


    public static void main(String[] args) throws IOException {
        new LoggingThoseActions().Run();
    }

    void Run() throws IOException {

        String[] menuItems = new String[]{
                "1. Add line",
                "2. View lines",
                "3. Delete line",
                "9. Quit"
        };
        Menu menu = new Menu("MENU:", "Please choose option: ", menuItems);
        boolean keepGoing = true;
        do {
            menu.printMenu();
            int choice = menu.readChoice();
            switch (choice) {
                case 1:
                    addToLog();
                    break;
                case 2:
                    readArrayList();
                    break;
                case 3:
                    deleteLog();
                    break;
                case 9:
                    quit();
                    keepGoing = false;
                    break;
            }

        } while (keepGoing);
    }

    private void deleteLog() {
        System.out.println("Please write the index of the string you wish to delete: ");
        int choice1;
        choice1 = readInt();
        try {
            try (FileWriter f = new FileWriter("Log.txt", true)) {
                f.write(date + " Line removed: " + choice1 + ": " + "´" + lines.get(choice1) + "´" + "\n");
            }
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
        lines.remove(choice1);
    }


    public void saveFile() {
        try {
            PrintStream ps = new PrintStream("Log.txt");
            for (int i = 0; i <= lines.size() - 1; i++) {
                ps.println("Line added: " + lines.get(i));
                ps.println("Date: " + date);
                ps.close();
            }
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.getMessage());
        }
    }

    public void readFile() throws FileNotFoundException {
        try {
            Scanner myReader = new Scanner(log);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                System.out.println(" ");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readArrayList() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(i + " String: " + lines.get(i));
        }
        try {
            try (FileWriter f = new FileWriter("Log.txt", true)) {
                f.write(date + " Viewed lines" + "\n");
            }
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }

    public void addToLog() {
        System.out.println("Please write the string you wish to log: ");
        try {
            try (FileWriter f = new FileWriter("Log.txt", true)) {
                String s;
                s = readString();
                lines.add(s);
                f.write(date + " Line added: " + s + "\n");

            }
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }

    public String readString() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        String choice2 = " ";
        while (!validChoice) {
            System.out.print(" ");
            if (scanner.hasNextLine()) {
                choice2 = scanner.nextLine();
                validChoice = true;
            } else {
                System.err.println("Please input a string");
                scanner.nextLine();
            }
        }
        return choice2;
    }

    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        int choice = -1;
        while (!validChoice) {
            System.out.print(" ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validChoice = true;
            } else {
                System.err.println("Please input a number");
                scanner.nextLine();
            }
        }
        return choice;
    }

    @Override
    public String toString() {
        return " ";
    }

    public void quit() {
        System.out.println("Goodbye!");
        try {
            try (FileWriter f = new FileWriter("Log.txt", true)) {
                f.write(date + " Quit the program " + "\n");

            }
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }
}


