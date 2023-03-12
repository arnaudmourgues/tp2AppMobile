package fr.uavignon.ceri.tp2.data;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class DetailViewModel extends AndroidViewModel {
    public BookDao bookRepository;
    private BookRepository repository;

    private MutableLiveData<Book> book;

    public DetailViewModel(Application application) {
        super(application);
        repository = new BookRepository(application);
        book = repository.getBook();
    }

    public void findbook(String name) {
        repository.findbook(name);
    }

    public MutableLiveData<Book> getBook(int bookNum) {
        return book;
    }

    public void setBook(Book book) {
        this.book.setValue(book);
    }

    public void updateBook(Book book) {
        repository.updateBook(book);
    }
}
