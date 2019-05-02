package com.example.androidgezginapp.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidgezginapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private static final int IMAGE_REQUEST = 111;
    ProgressDialog progressDialog;
    StorageReference mStorageRef;
    private Uri filePath;
    ImageView img;
    ImageButton btnImg;
    Button btnSave;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        img = view.findViewById(R.id.image_profile);
        btnImg = view.findViewById(R.id.img_btn_camera);
        btnSave = view.findViewById(R.id.btn_save_photo);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectPhoto();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePhoto();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Picasso.with(getActivity()).load(filePath).fit().centerCrop().into(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private void selectPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser
                (intent, "Resim Seçiniz"), IMAGE_REQUEST);
    }

    public void savePhoto() {
        if (filePath != null) {
            showProgressDialog();
            mStorageRef.child("userprofilephoto").putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            dismissProgressDialog();
                            Toast.makeText(getActivity(),
                                    "Fotoğraf başarılı bir şekilde kaydedildi.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    dismissProgressDialog();
                    Toast.makeText(getActivity(), "Fotoğraf Kaydedilemedi", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }


}
