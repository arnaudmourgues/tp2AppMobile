package fr.uavignon.ceri.tp2.data;

import static fr.uavignon.ceri.tp2.data.BookRoomDatabase.databaseWriteExecutor;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import fr.uavignon.ceri.tp2.data.Book;
import fr.uavignon.ceri.tp2.data.BookDao;
import fr.uavignon.ceri.tp2.data.BookRoomDatabase;

public class BookRepository {
    private MutableLiveData<List<Book>> searchResults =
            new MutableLiveData<>();
    private LiveData<List<Book>> allBooks;

    private BookDao bookDao;

    private MutableLiveData<Book> book;

    public BookRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    public void insertBook(Book newbook) {
        databaseWriteExecutor.execute(() -> {
            bookDao.insert(newbook);
        });
    }

    public void deletebook(String name) {
        databaseWriteExecutor.execute(() -> {
            bookDao.delete(bookDao.findByName(name).get(0));
        });
    }

    public void findbook(String name) {

        Future<List<Book>> fbooks = databaseWriteExecutor.submit(() -> {
            return bookDao.findByName(name);
        });
        try {
            searchResults.setValue(fbooks.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<Book>> getAllbooks() {
        return allBooks;
    }

    public MutableLiveData<List<Book>> getSearchResults() {
        return searchResults;
    }

    public MutableLiveData<Book> getBook() {
        return book;
    }

    public void updateBook(Book book) {
        databaseWriteExecutor.execute(() -> {
            bookDao.update(book);
        });
    }
}
