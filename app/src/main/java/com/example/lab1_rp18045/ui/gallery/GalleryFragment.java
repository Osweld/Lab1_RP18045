package com.example.lab1_rp18045.ui.gallery;

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

import com.example.lab1_rp18045.databinding.FragmentGalleryBinding;

import java.text.DecimalFormat;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText salario = binding.salario;
        final Button calcular = binding.calcular;
        final TextView isss = binding.isss;
        final TextView afp = binding.afp;
        final TextView renta = binding.renta;
        final TextView bono = binding.bono;
        final TextView salarioNeto = binding.salarioNeto;


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(salario.getText().length() > 0){
                    // antes de aplicar la renta hay que quitarle las retenciones de isss y afp
                    try {
                        DecimalFormat f = new DecimalFormat("##.00");
                        Double salarioBruto = Double.parseDouble(salario.getText().toString());
                        Double saldoIsss = salarioBruto * 0.03;
                        isss.setText("ISSS: $"+f.format(saldoIsss)+"   (3%)");
                        Double saldoAfp = salarioBruto * 0.075;
                        afp.setText("AFP: $"+f.format(saldoAfp)+"   (7.5%)");
                        Double salarioConRetenciones = salarioBruto - saldoIsss - saldoAfp;
                        Double saldoRenta = 0.0;
                        if(salarioConRetenciones < 550){
                            renta.setText("Renta: No aplica");
                        }else if(salarioConRetenciones >= 550.01 && salarioConRetenciones < 800){
                            saldoRenta =  salarioConRetenciones * 0.10;
                            renta.setText("Renta: $"+ f.format(saldoRenta)+"   (10%)");
                        }else if(salarioConRetenciones >= 800.01 && salarioConRetenciones < 2500){
                            saldoRenta =  salarioConRetenciones * 0.20;
                            renta.setText("Renta: $"+ f.format(saldoRenta)+"   (20%)");
                        }else if(salarioConRetenciones >= 2500.01){
                            saldoRenta =  salarioConRetenciones * 0.30;
                            renta.setText("Renta: $"+ f.format(saldoRenta)+"   (30%)");
                        }
                        Double salarioConRenta = salarioConRetenciones - saldoRenta;
                        Double saldoBono = 0.0;
                        //Con el bono tenia dudas si era aplicado al salario bruto o neto
                        if(salarioBruto <= 250){
                            saldoBono = salarioBruto * 0.10;
                            bono.setText("Bono: $"+f.format(saldoBono)+"   (10%)");
                        }else{
                            bono.setText("Bono: No aplica");
                        }
                        Double saldoSalarioNeto = salarioConRenta + saldoBono;
                        salarioNeto.setText("El salario neto es : $"+f.format(saldoSalarioNeto));

                    }catch (Exception ex){
                        Toast.makeText(view.getContext(),"Ingrese una cantidad valida",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(view.getContext(),"Ingrese una cantidad",Toast.LENGTH_SHORT).show();
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