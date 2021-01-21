package hacine.mohamed.abdelhakim.taskmanager;


import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private NoteDao noteDao;
    private NoteDataBase mDb;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, NoteDataBase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        noteDao = mDb.noteDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetNote() throws Exception {
        Note word = new Note("word","haha",1);
        Note word2 = new Note("word2","haha2",2);
        noteDao.insert(word);
        noteDao.insert(word2);


        List<Note> noteList = LiveDataUtil.getValue(noteDao.getAllNotes());
        assertEquals(noteList.get(0).getTitle(), word.getTitle());
        assertEquals(noteList.get(0).getDescription(), word.getDescription());
        assertEquals(noteList.get(0).getPriority(), word.getPriority());


    }

    @Test
    public void sizetest() throws Exception {
        Note word = new Note("word","haha",1);
        Note word2 = new Note("word2","haha2",2);
        noteDao.insert(word);
        noteDao.insert(word2);
        List<Note> noteList = LiveDataUtil.getValue(noteDao.getAllNotes());

        assertEquals(2, noteList.size());


    }

}
