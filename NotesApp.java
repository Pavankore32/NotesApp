import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Notes App ---");
            System.out.println("1) Add note");
            System.out.println("2) View notes");
            System.out.println("3) Clear notes");
            System.out.println("4) Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1": addNote(sc); break;
                case "2": viewNotes(); break;
                case "3": clearNotes(); break;
                case "4": System.out.println("Bye"); sc.close(); return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void addNote(Scanner sc) {
        System.out.println("Enter note (single line) :");
        String note = sc.nextLine();
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println(note);
            System.out.println("Saved.");
        } catch (IOException e) {
            System.err.println("Error writing note: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewNotes() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            System.out.println("No notes yet.");
            return;
        }
        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr)) {
            System.out.println("\n--- Saved notes ---");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading notes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME, false)) {
            // overwrites file with nothing
            System.out.println("All notes cleared.");
        } catch (IOException e) {
            System.err.println("Error clearing notes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
