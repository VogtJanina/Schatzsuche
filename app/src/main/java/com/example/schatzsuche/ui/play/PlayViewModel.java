package com.example.schatzsuche.ui.play;

import android.util.Log;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schatzsuche.FileIOScores;
import com.example.schatzsuche.Game;
import com.example.schatzsuche.R;
import com.example.schatzsuche.ScoreItem;

public class PlayViewModel extends ViewModel {

    private final MutableLiveData<Integer> mBtn;
    private Game game;
    private MutableLiveData<ScoreItem> mScoreItem = null;
    private ScoreItem scoreItem;


    public PlayViewModel() {
        mBtn = new MutableLiveData<>();
        mBtn.setValue(R.string.hideTreasure);
        game  = new Game();
        mScoreItem = new MutableLiveData<ScoreItem>();
        mScoreItem.setValue(scoreItem);

    }

    public LiveData<Integer> getText() {
        return mBtn;
    }

    public LiveData<ScoreItem> getScoreItem(){return mScoreItem; }

    public void startGame(){
        game.hideTreasureAndSeamonster();
        mBtn.setValue(R.string.searchTreasure);

    }
    public void checkForTreasureAndSeaMonster(ImageButton i_btn){
        scoreItem= game.checkForTreasureAndSeaMonster(i_btn);
        if (scoreItem != null){
            Log.d("ScoreItem", scoreItem.toString());
            mScoreItem.setValue(scoreItem);
            mBtn.setValue(R.string.hideTreasure);
        }
    }

}