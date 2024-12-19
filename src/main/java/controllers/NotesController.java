package controllers;
import Manager_models.NotesManager;
import database.NotesDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.time.LocalDateTime;

public class NotesController {
    @FXML private TableView<NotesManager> notesTableView;
    @FXML private TableColumn<NotesManager, Integer> idColumn;
    @FXML private TableColumn<NotesManager, String> titleColumn;
    @FXML private TableColumn<NotesManager, String> createdAtColumn;

    @FXML private TextField titleField;
    @FXML private TextArea contentField;

    private ObservableList<NotesManager> notesList = FXCollections.observableArrayList();
    private NotesManager selectedNote;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        createdAtColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCreatedAt().toString())
        );

        loadNotes();
        notesTableView.setOnMouseClicked(event -> populateFields());
    }

    private Object getCreatedAt() {
        return null;
    }

    private void loadNotes() {
        notesList.setAll(NotesDAO.getAllNotes());
        notesTableView.setItems(notesList);
    }

    private void populateFields() {
        selectedNote = notesTableView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            titleField.setText(selectedNote.getTitle());
            contentField.setText(selectedNote.getContent());
        }
    }

    @FXML
    public void addNote() {
        NotesManager note = new NotesManager(0, titleField.getText(), contentField.getText(), LocalDateTime.now());
        if (NotesDAO.addNote(note)) {
            loadNotes();
            clearFields();
        }
    }

    @FXML
    public void updateNote() {
        if (selectedNote != null) {
            selectedNote.setTitle(titleField.getText());
            selectedNote.setContent(contentField.getText());
            if (NotesDAO.updateNote(selectedNote)) {
                loadNotes();
                clearFields();
            }
        }
    }

    @FXML
    public void deleteNote() {
        if (selectedNote != null && NotesDAO.deleteNote(selectedNote.getId())) {
            loadNotes();
            clearFields();
        }
    }

    private void clearFields() {
        titleField.clear();
        contentField.clear();
        selectedNote = null;
    }
}
