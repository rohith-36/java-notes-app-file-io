# Java Notes App - File I/O Implementation

A command-line notes application demonstrating Java file I/O operations using FileWriter, FileReader, and BufferedReader with try-with-resources.

## Features
- **Add Notes**: Create new notes with title and content
- **List Notes**: Display all saved notes with timestamps
- **Search Notes**: Find notes by keyword in title or content
- **Delete Notes**: Remove notes by ID
- **Persistent Storage**: Notes are saved to `notes.txt` file
- **CLI Interface**: Clean, menu-driven command-line interface

## Technologies Demonstrated
- **FileWriter**: For writing notes to file
- **FileReader** with **BufferedReader**: For reading notes from file
- **Try-with-resources**: Proper resource management and automatic cleanup
- **File I/O Operations**: Reading and writing text files in Java
- **Error Handling**: Proper exception handling for I/O operations

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line/terminal access

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/rohith-36/java-notes-app-file-io.git
   cd java-notes-app-file-io
   ```

2. Compile the Java program:
   ```bash
   javac NotesApp.java
   ```

3. Run the application:
   ```bash
   java NotesApp
   ```

4. Follow the menu options:
   - Choose option 1 to add a new note
   - Choose option 2 to list all notes
   - Choose option 3 to search notes
   - Choose option 4 to delete a note
   - Choose option 5 to exit

### Sample Usage

```
=== Welcome to NotesApp ===
A simple CLI notes application with file persistence

=== Notes Menu ===
1. Add Note
2. List All Notes
3. Search Notes
4. Delete Note
5. Exit
Enter your choice (1-5): 1

=== Add New Note ===
Enter note title: My First Note
Enter note content: This is the content of my first note.
Note added successfully with ID: NOTE-1723567890123
```

## File Structure
- `NotesApp.java` - Main application file containing all functionality
- `notes.txt` - Data file where notes are persistently stored (created automatically)
- `.gitignore` - Git ignore file for Java projects
- `LICENSE` - MIT License
- `README.md` - This documentation file

## Code Structure

### Main Components
1. **NotesApp Class**: Main application class with CLI interface
2. **Note Class**: Inner class representing a note object
3. **File I/O Methods**:
   - `saveNote()`: Writes notes using FileWriter with try-with-resources
   - `loadNotes()`: Reads notes using FileReader and BufferedReader
   - `deleteNoteById()`: Removes notes and rewrites file

### Key Features Demonstrated
- **Try-with-resources**: Automatic resource management for file operations
- **BufferedReader**: Efficient line-by-line file reading
- **Error Handling**: Comprehensive IOException handling
- **Data Persistence**: Notes survive application restarts
- **CRUD Operations**: Complete Create, Read, Update, Delete functionality

## Learning Outcomes

After studying this project, you will understand:

1. **Java File I/O**: How to read and write files in Java
2. **Resource Management**: Proper handling of system resources using try-with-resources
3. **Exception Handling**: How to handle I/O exceptions gracefully
4. **Data Persistence**: Implementing simple file-based data storage
5. **CLI Applications**: Creating user-friendly command-line interfaces
6. **Code Organization**: Structuring Java applications with classes and methods

## Future Enhancements
- JSON or XML format for better data structure
- Search with regular expressions
- Note categories and tags
- Export functionality
- GUI interface using JavaFX or Swing
- Database integration (SQLite, MySQL)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Feel free to fork this project and submit pull requests for improvements!

---
**Author**: Rohith  
**Purpose**: Demonstrate Java File I/O operations with try-with-resources  
**Date**: August 2025
