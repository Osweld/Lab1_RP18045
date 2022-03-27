package com.example.lab1_rp18045.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab1_rp18045.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText nombre = binding.nombre;
        final EditText carnet = binding.carnet;
        final Button verPerfil = binding.verPerfil;

        verPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().length() > 0 && carnet.getText().length() > 0 ){
                    Toast.makeText(view.getContext(),"El nombre es: "+nombre.getText()+" y su carnet es: "+carnet.getText(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(),"Los campos no pueden quedar vacios",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}