package com.example.verpflichtemicht.ui.home;

import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.verpflichtemicht.R;
import com.example.verpflichtemicht.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public WebView webView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
       //  = (WebView) rootView.findViewById(R.id.webview);
       // browser.loadUrl("http://192.168.178.32:80/verpflichtemich/index.php");

        //webview(browser);
        return root;
    }
    public  void  webview(WebView browser){
    }


    public void onActivityCreated() {
        onActivityCreated();
    }

    @Override

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView = (WebView) getActivity().findViewById(R.id.webviewHome);

        if (savedInstanceState != null)
            webView.restoreState(savedInstanceState);
        else {
            webView.getSettings().setUseWideViewPort(true);
            SharedPreferences sharedPref = getContext().getSharedPreferences("AppData",0);
            String Authentifizierungstoken = sharedPref.getString("Authentifizierungstokens",null);
            String Bootinfo = sharedPref.getString("Bootinfo","leereBootinfo");

            webView.loadUrl("https://hardcorewuschel.de/Authentifizierungstoken="+Authentifizierungstoken);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.setWebViewClient(new SSLTolerentWebViewClient());
        }
    }
    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            Log.d("logapi", "Error");

            handler.proceed(); // Ignore SSL certificate errors
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}