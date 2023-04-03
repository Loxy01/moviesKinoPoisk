package com.loxy01.test_start_new_project.ui.profile;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.loxy01.test_start_new_project.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private static final int SELECT_PICTURE = 222;
    private FragmentProfileBinding binding;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    boolean isIcon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.UserNameProfile.setText("Email: "+mAuth.getCurrentUser().getEmail());
        Log.d("name", mAuth.getCurrentUser().getDisplayName());
        accountPhotoClick();
        backgroundImgClick();
        Log.d("Fragments", "This is profile fragment");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the uri of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    if(isIcon){
                        binding.UserPhotoProfile.setImageURI(selectedImageUri);
                    } else {
                        binding.UserBackgroundImgProfile.setImageURI(selectedImageUri);
                    }
                }
            }
        }
    }

    public void accountPhotoClick() {
        binding.UserPhotoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIcon = true;
                imageChooser();
            }
        });
    }

    public void backgroundImgClick() {
        binding.UserBackgroundImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIcon = false;
                imageChooser();
            }
        });
    }

    // this function is triggered when
    // the Select Image Button is clicked
    private void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
}