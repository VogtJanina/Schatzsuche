package com.example.schatzsuche.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schatzsuche.databinding.FragmentPlayBinding;
import com.google.android.material.button.MaterialButton;

public class PlayFragment extends Fragment {

    private FragmentPlayBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PlayViewModel galleryViewModel =
                new ViewModelProvider(this).get(PlayViewModel.class);

        binding = FragmentPlayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final MaterialButton btnHideTreasure = binding.btnHideTreasure;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), btnHideTreasure::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}