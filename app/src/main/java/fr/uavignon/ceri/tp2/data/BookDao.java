package fr.uavignon.ceri.tp2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Insert
    void insert(Book book);

    @Query("SELECT * FROM books")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM books WHERE bookTitle = :name")
    List<Book> findByName(String name);

    @Query("Select * from books where bookId = :id")
    List<Book> findById(long id);

    @Query("DELETE FROM books")
    void deleteAll();
    @Query("SELECT * FROM books WHERE bookId = :id")
    Book getBook(long id);
}
