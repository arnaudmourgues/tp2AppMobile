package fr.uavignon.ceri.tp2.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BookDao{
    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Insert
    void insert(Book book);

    @Query("SELECT * FROM books")
    Book[] getAllBooks();

    @Query("SELECT * FROM books WHERE id = :id")
    Book getBook(long id);
}
