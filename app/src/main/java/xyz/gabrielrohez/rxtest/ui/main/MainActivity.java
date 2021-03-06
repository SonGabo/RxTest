package xyz.gabrielrohez.rxtest.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import xyz.gabrielrohez.rxtest.databinding.ActivityMainBinding;
import xyz.gabrielrohez.rxtest.ui.second.view.RxRetrofitActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpEvents();
    }

    private void setUpEvents() {
        binding.btnRxRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RxRetrofitActivity.class));
            }
        });
    }
}
