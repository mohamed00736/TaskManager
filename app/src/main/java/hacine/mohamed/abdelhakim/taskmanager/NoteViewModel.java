package hacine.mohamed.abdelhakim.taskmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private Noterepositoty repository;
    private LiveData<List<Note>> allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new Noterepositoty(application);
        allNotes = repository.getAllNotes();
    }
    public void insert(Note note) {
        repository.insert(note);
    }
    public void update(Note note) {
        repository.update(note);
    }
    public void delete(Note note) {
        repository.delete(note);
    }
//    public void deleteAllNotes() {
//        repository.deleteAllNotes();
//    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }}
