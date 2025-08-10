import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple Notes Application with File I/O functionality
 * Features:
 * - Add new notes
 * - View all notes
 * - Save notes to file
 * - Load notes from file
 * - Delete notes
 * - Search notes
 */
public class NotesApp {
    private static final String NOTES_FILE = "notes.txt";
    private List<Note> notes;
    private Scanner scanner;

    public NotesApp() {
        this.notes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadNotesFromFile();
    }

    // Inner class to represent a Note
    static class Note {
        private int id;
        private String title;
        private String content;
        private LocalDateTime timestamp;

        public Note(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.timestamp = LocalDateTime.now();
        }

        public Note(int id, String title, String content, LocalDateTime timestamp) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.timestamp = timestamp;
        }

        // Getters and setters
        public int getId() { return id; }
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public LocalDateTime getTimestamp() { return timestamp; }

        public void setTitle(String title) { this.title = title; }
        public void setContent(String content) { this.content = content; }

        @Override
        public String toString() {
            return String.format("ID: %d | Title: %s | Created: %s\nContent: %s\n",
                    id, title, timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), content);
        }

        // Method to convert note to file format
        public String toFileFormat() {
            return id + "|" + title + "|" + content + "|" + timestamp.toString();
        }

        // Static method to create note from file format
        public static Note fromFileFormat(String line) {
            String[] parts = line.split("\\|", 4);
            if (parts.length == 4) {
                return new Note(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        LocalDateTime.parse(parts[3])
                );
            }
            return null;
        }
    }

    // Main menu method
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Java Notes App ===");
            System.out.println("1. Add Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Search Notes");
            System.out.println("4. Delete Note");
            System.out.println("5. Save Notes");
            System.out.println("6. Load Notes");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: addNote(); break;
                    case 2: viewAllNotes(); break;
                    case 3: searchNotes(); break;
                    case 4: deleteNote(); break;
                    case 5: saveNotesToFile(); break;
                    case 6: loadNotesFromFile(); break;
                    case 7:
                        saveNotesToFile(); // Auto-save before exit
                        System.out.println("Thank you for using Notes App!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Add a new note
    private void addNote() {
        System.out.print("Enter note title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }

        System.out.print("Enter note content: ");
        String content = scanner.nextLine().trim();
        if (content.isEmpty()) {
            System.out.println("Content cannot be empty!");
            return;
        }

        int newId = getNextId();
        Note newNote = new Note(newId, title, content);
        notes.add(newNote);
        System.out.println("Note added successfully! (ID: " + newId + ")");
    }

    // Get next available ID
    private int getNextId() {
        return notes.stream().mapToInt(Note::getId).max().orElse(0) + 1;
    }

    // View all notes
    private void viewAllNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        }

        System.out.println("\n=== All Notes ===");
        for (Note note : notes) {
            System.out.println(note);
            System.out.println("-".repeat(50));
        }
    }

    // Search notes by title or content
    private void searchNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes to search.");
            return;
        }

        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();
        if (searchTerm.isEmpty()) {
            System.out.println("Search term cannot be empty!");
            return;
        }

        List<Note> matchingNotes = notes.stream()
                .filter(note -> note.getTitle().toLowerCase().contains(searchTerm) ||
                               note.getContent().toLowerCase().contains(searchTerm))
                .collect(ArrayList::new, (list, note) -> list.add(note), (list1, list2) -> list1.addAll(list2));

        if (matchingNotes.isEmpty()) {
            System.out.println("No notes found matching '" + searchTerm + "'");
        } else {
            System.out.println("\n=== Search Results ===");
            for (Note note : matchingNotes) {
                System.out.println(note);
                System.out.println("-".repeat(50));
            }
        }
    }

    // Delete a note by ID
    private void deleteNote() {
        if (notes.isEmpty()) {
            System.out.println("No notes to delete.");
            return;
        }

        viewAllNotes();
        System.out.print("Enter the ID of the note to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = notes.removeIf(note -> note.getId() == id);
            if (removed) {
                System.out.println("Note deleted successfully!");
            } else {
                System.out.println("No note found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid ID number.");
        }
    }

    // Save notes to file
    private void saveNotesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTES_FILE))) {
            for (Note note : notes) {
                writer.write(note.toFileFormat());
                writer.newLine();
            }
            System.out.println("Notes saved to " + NOTES_FILE + " successfully!");
        } catch (IOException e) {
            System.err.println("Error saving notes: " + e.getMessage());
        }
    }

    // Load notes from file
    private void loadNotesFromFile() {
        File file = new File(NOTES_FILE);
        if (!file.exists()) {
            System.out.println("Notes file not found. Starting with empty notes.");
            return;
        }

        notes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            int loadedCount = 0;
            while ((line = reader.readLine()) != null) {
                Note note = Note.fromFileFormat(line.trim());
                if (note != null) {
                    notes.add(note);
                    loadedCount++;
                }
            }
            System.out.println("Loaded " + loadedCount + " notes from " + NOTES_FILE);
        } catch (IOException e) {
            System.err.println("Error loading notes: " + e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {
        System.out.println("Welcome to Java Notes App with File I/O!");
        NotesApp app = new NotesApp();
        app.showMenu();
    }
}
