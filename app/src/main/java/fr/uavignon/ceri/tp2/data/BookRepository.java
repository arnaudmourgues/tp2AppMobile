package fr.uavignon.ceri.tp2.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BookRepository {
    private MutableLiveData<List<Book>> searchResults =
            new MutableLiveData<>();
    private LiveData<List<Book>> allbooks;

    private BookDao bookDao;

    public void bookRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allbooks = bookDao.getAllbooks();
    }

    public void insertbook(book newbook) {
        databaseWriteExecutor.execute(() -> {
            bookDao.insertbook(newbook);
        });
    }

    public void deletebook(String name) {
        databaseWriteExecutor.execute(() -> {
            bookDao.deletebook(name);
        });
    }

    public void findbook(String name) {

        Future<List<book>> fbooks = databaseWriteExecutor.submit(() -> {
            return bookDao.findbook(name);
        });
        try {
            searchResults.setValue(fbooks.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<book>> getAllbooks() {
        return allbooks;
    }

    public MutableLiveData<List<book>> getSearchResults() {
        return searchResults;
    }
}
