package com.example.verpflichtemicht.ui.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.verpflichtemicht.Klassen.Alarmhelper;
import com.example.verpflichtemicht.Klassen.NotificationKlasse;
import com.example.verpflichtemicht.R;
import com.example.verpflichtemicht.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    Button btn;
    Button btn2;
    Button btn3;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void macheNot(View view){

        //NotificationKlasse not = new NotificationKlasse();
        //not.makenotification(getActivity(),"Titel-Ich komme vom Bootreciver", "Boot hat funktioniert",3);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn = (Button) getActivity().findViewById(R.id.TesteNot);
        btn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Log.d("logapi", "Lebe bis jetzt");
NotificationKlasse not = new NotificationKlasse();
                not.makenotification(getActivity(),"Titel-Ich komme vom Bootreciver", "Boot hat funktioniert",3);


            }
        });

        btn2 = (Button) getActivity().findViewById(R.id.Alarmfenster);
        btn2.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                /*
                Intent i = new Intent(getContext(), AlarmFenster.class);
                startActivity(i);
                */

                Alarmhelper alarmhelper = new Alarmhelper();
                alarmhelper.AlarmSetzen(getContext());



            }
        });
        btn3 = (Button) getActivity().findViewById(R.id.Scan);
        btn3.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                /*
                Intent i = new Intent(getContext(), AlarmFenster.class);
                startActivity(i);
                */




            }
        });
       // webView = (WebView) getActivity().findViewById(R.id.webviewHome);
    }
}