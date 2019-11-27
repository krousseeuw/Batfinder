package com.kru.batfinder2.ui.batdetail;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.R;
import com.kru.batfinder2.ui.home.HomeViewModel;

public class BatDetailFragment extends Fragment {
    public String language = "EN";

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
        mViewModel = ViewModelProviders.of(this.getActivity()).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getSelectedBatId().observe(this, item -> {
            displayBat(mViewModel.getBatDetails(item));
        });
    }

    private void displayBat(BatDTO batDTO) {
        Glide.with(this)
                .load(batDTO.getImage_url())
                .into(mImageView);

        if(language.equals("EN")) {
            mCommonNameView.setText(batDTO.getCommon_name_en());
            mDescription.setText(batDTO.getDescription_en());
        } else if (language.equals("NL")){
            mCommonNameView.setText(batDTO.getCommon_name_nl());
            mDescription.setText(batDTO.getDescription_nl());
        }

        mScientificNameView.setText(batDTO.getScientific_name());
        mBodyLengthView.setText(batDTO.getBodyLengthToString());
        mPhotoCredit.setText(batDTO.getImage_creditString());
    }
}
