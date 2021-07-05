package com.example.storecode_android.view.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.storecode_android.R;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterProductFragment extends Fragment {

    ImageButton btnReturn;
    int SELECT_PHOTO= 1;
    Uri uri;
    ImageView ivPhoto;
    Button btnChooseImage;




    public RegisterProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterProductFragment newInstance(String param1, String param2) {
        RegisterProductFragment fragment = new RegisterProductFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnReturn= view.findViewById(R.id.btnReturn);
        ivPhoto= view.findViewById(R.id.ivPhotoProduct);
        btnChooseImage = view.findViewById(R.id.btnChooseImage);

        btnChooseImage.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, SELECT_PHOTO);
        });

        btnReturn.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(RegisterProductFragmentDirections.toProfileLogedFragment());
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data!= null && data.getData()!= null){
            uri = data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ivPhoto.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}