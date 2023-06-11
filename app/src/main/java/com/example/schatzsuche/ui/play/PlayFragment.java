package com.example.schatzsuche.ui.play;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.schatzsuche.FileIOScores;
import com.example.schatzsuche.R;
import com.example.schatzsuche.ScoreItem;
import com.example.schatzsuche.databinding.FragmentPlayBinding;
import com.google.android.material.button.MaterialButton;

public class PlayFragment extends Fragment {

    private FragmentPlayBinding binding;
    private static final int MAX_ISLANDS = 15;
    private static final String fileName = "Scores.txt";
    private MaterialButton btnHideTreasure;
    private ConstraintLayout cl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PlayViewModel playViewModel =
                new ViewModelProvider(this).get(PlayViewModel.class);

        binding = FragmentPlayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FileIOScores fileIOScores = new FileIOScores(getActivity());

        //Listener for btnHideTreasure
        btnHideTreasure = binding.btnHideTreasure;
        playViewModel.getText().observe(getViewLifecycleOwner(), btnHideTreasure::setText);

        btnHideTreasure.setOnClickListener(v ->{
            btnHideTreasure.setEnabled(false);
            playViewModel.startGame();
            setImageButtonImage();
            setImageButtonState(true);
        });


        playViewModel.getScoreItem().observe(getViewLifecycleOwner(), new Observer<ScoreItem>() {
            @Override
            public void onChanged(ScoreItem scoreItem) {
                if (scoreItem != null) {
                    Log.d("SAVE ScoreItem", "ScoreItem to save: " + scoreItem);
                    fileIOScores.writeFile(fileName, scoreItem.toString());
                    fileIOScores.printFileContent(fileName);
                    setImageButtonState(false);
                    setMaterialButtonState();
                }
            }
        });

        cl = binding.fragPlayConstraintLayout;

        //Listener for ImageButtons
        for(int i=0; i<MAX_ISLANDS; i++) {
            ImageButton btn = (ImageButton) cl.getChildAt(i);
            btn.setEnabled(false);
            btn.setOnClickListener(v -> {
                playViewModel.checkForTreasureAndSeaMonster(btn);
            });
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setImageButtonState(boolean bool){
        for(int j=0; j<MAX_ISLANDS; j++){
            ImageButton i_btn = (ImageButton) cl.getChildAt(j);
            i_btn.setEnabled(bool);
        };
    }
    public void setMaterialButtonState(){
        btnHideTreasure.setEnabled(true);
    }
    public void setImageButtonImage(){
        for(int j=0; j<MAX_ISLANDS; j++){
            ImageButton i_btn = (ImageButton) cl.getChildAt(j);
            i_btn.setImageResource(R.mipmap.island);
        };
    }
}