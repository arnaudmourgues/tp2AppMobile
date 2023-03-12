package fr.uavignon.ceri.tp2.data;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private BookRepository repository;

    private LiveData<List<Book>> allBooks;

    private MutableLiveData<List<Book>> searchResults;


    public ListViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllbooks();
        searchResults = repository.getSearchResults();
    }

    public void insertBook(Book newbook) {
        repository.insertBook(newbook);
    }

    public void deletebook(String name) {
        repository.deletebook(name);
    }

    public void findbook(String name) {
        repository.findbook(name);
    }

    public LiveData<List<Book>> getAllbooks() {
        return allBooks;
    }

    public MutableLiveData<List<Book>> getSearchResults() {
        return searchResults;
    }


}
