package com.example.schatzsuche.ui.scores;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schatzsuche.FileIOScores;
import com.example.schatzsuche.MiniAdapter;
import com.example.schatzsuche.ScoreItem;
import com.example.schatzsuche.databinding.FragmentScoresBinding;

import java.util.ArrayList;


public class ScoresFragment extends Fragment {

    private FragmentScoresBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MiniAdapter adapter;
    private FileIOScores fileIOScores;
    private ArrayList<ScoreItem> scores;
    private ArrayList<String> scoreStrings = new ArrayList<String>();
    private static final String fileName = "Scores.txt";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScoresViewModel scoresViewModel =
                new ViewModelProvider(this).get(ScoresViewModel.class);

        binding = FragmentScoresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerView;
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        fileIOScores = new FileIOScores(getActivity());
        scores = fileIOScores.readScores(fileName);
        Log.i("Im ScoresView", "vor tv.settext");
        fileIOScores.printFileContent(fileName);

        adapter = new MiniAdapter(scoreStrings);
        recyclerView.setAdapter(adapter);
        for (ScoreItem si : scores){
            Log.d("ScoreItem", si.toString());
            adapter.add(si.toString());
        }
        Log.i("ScoreItemAmount", scoreStrings.toString());

        //final TextView textView = binding.textScores;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}