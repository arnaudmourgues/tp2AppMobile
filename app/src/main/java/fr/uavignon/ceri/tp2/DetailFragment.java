package fr.uavignon.ceri.tp2;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import fr.uavignon.ceri.tp2.data.Book;
import fr.uavignon.ceri.tp2.data.DetailViewModel;

public class DetailFragment extends Fragment {

    private EditText textTitle, textAuthors, textYear, textGenres, textPublisher;

    private DetailViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get selected book
        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        //Book book = Book.books[(int)args.getBookNum()];
        DetailViewModel model = new DetailViewModel(requireActivity().getApplication());
        Book book = model.getBook((int)args.getBookNum()).getValue();

        textTitle = (EditText) view.findViewById(R.id.nameBook);
        textAuthors = (EditText) view.findViewById(R.id.editAuthors);
        textYear = (EditText) view.findViewById(R.id.editYear);
        textGenres = (EditText) view.findViewById(R.id.editGenres);
        textPublisher = (EditText) view.findViewById(R.id.editPublisher);

        assert book != null;
        textTitle.setText(book.getTitle());
        textAuthors.setText(book.getAuthors());
        textYear.setText(book.getYear());
        textGenres.setText(book.getGenres());
        textPublisher.setText(book.getPublisher());

        viewModel = new DetailViewModel(requireActivity().getApplication());
        viewModel.setBook(book);

        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(fr.uavignon.ceri.tp2.DetailFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        view.findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book(textTitle.getText().toString(), textAuthors.getText().toString(), textYear.getText().toString(), textGenres.getText().toString(), textPublisher.getText().toString());
                viewModel.updateBook(book);
                NavHostFragment.findNavController(fr.uavignon.ceri.tp2.DetailFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                //add a notification

            }
        });
    }
}