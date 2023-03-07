package fr.uavignon.ceri.tp2.data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@Database(entities = {Book.class}, version = 1)
public abstract class BookRoomDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
private static volatile BookRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        private Executor databaseWriteExecutor;

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            this.databaseWriteExecutor.execute(() -> {
                //BookDao bookDao = INSTANCE.bookDao();
            });
        }
    };
}

