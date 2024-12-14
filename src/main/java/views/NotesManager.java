package views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class NotesManager {
    private List<Note> notes;

    public NotesManager() {
        notes = new ArrayList<>();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void openNotesSection(Stage parentStage) {
        Stage notesStage = new Stage();
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20;");
        layout.getChildren().add(new Label("Раздел заметок пока пуст"));

        Scene scene = new Scene(layout, 300, 200);
        notesStage.setTitle("Заметки");
        notesStage.setScene(scene);
        notesStage.initOwner(parentStage);
        notesStage.show();
    }

    public static class Note {
        private int id;
        private String title;
        private String content;

        public Note(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
}
