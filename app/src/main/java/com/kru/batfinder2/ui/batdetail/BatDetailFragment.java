package com.kru.batfinder2.ui.batdetail;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kru.batfinder2.database.Bat;
import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.R;
import com.kru.batfinder2.ui.home.HomeViewModel;

import java.util.Locale;

public class BatDetailFragment extends Fragment {
    private HomeViewModel mViewModel;
    private ImageView mImageView;
    private TextView mCommonNameView;
    private TextView mScientificNameView;
    private TextView mBodyLengthView;
    private TextView mDescription;
    private TextView mPhotoCredit;

    public static BatDetailFragment newInstance() {
        return new BatDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bat_detail_fragment, container, false);
        setHasOptionsMenu(true);

        mImageView = root.findViewById(R.id.image_bat_big);
        mCommonNameView = root.findViewById(R.id.text_bat_common_name);
        mScientificNameView = root.findViewById(R.id.text_scientific_name);
        mBodyLengthView = root.findViewById(R.id.text_bat_bodylength);
        mDescription = root.findViewById(R.id.text_description);
        mPhotoCredit = root.findViewById(R.id.text_photocredit);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this.getActivity()).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getSelectedBat().observe(getViewLifecycleOwner(), this::displayBat);
    }

    private void displayBat(Bat bat) {
        Glide.with(this)
                .load(bat.getImageUrl())
                .into(mImageView);

        if(Locale.getDefault().getLanguage().equals(getString(R.string.dutchLanguageCode))) {
            mCommonNameView.setText(bat.getCommonNameNl());
            mDescription.setText(bat.getDescriptionNl());
        } else {
            mCommonNameView.setText(bat.getCommonNameEn());
            mDescription.setText(bat.getDescriptionEn());
        }

        mDescription.setMovementMethod(new ScrollingMovementMethod());
        mScientificNameView.setText(bat.getScientificName());
        mBodyLengthView.setText(getString(R.string.bodyLengthString, Integer.toString(bat.getMinBodyLength()), Integer.toString(bat.getMaxBodyLength())));
        mPhotoCredit.setText(getString(R.string.photoCreditString, bat.getImageAuthorName()));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.bat_detail_menu, menu);
    }
}
