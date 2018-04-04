package com.demo.user;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.MainActivity;
import com.demo.R;
import com.demo.database.DbHelper;
import com.demo.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        init();
    }

    private void init() {
        //set Actionbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.login));
        }
        binding.btnLogin.setOnClickListener(this);
        binding.txtSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (isValid()) {
                    if (checkLogin()) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        showSnackBar(getString(R.string.error_login));
                    }
                }
                break;
            case R.id.txt_signup:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private boolean isValid() {
        if (binding.edtEmail.getText().toString().isEmpty()) {
            showSnackBar(getString(R.string.email_validation));
            return false;
        } else if (binding.edtPwd.getText().toString().isEmpty()) {
            showSnackBar(getString(R.string.password_validation));
            return false;
        }
        return true;
    }

    private boolean checkLogin() {
        DbHelper helper = new DbHelper(this);
        return helper.getUser(binding.edtEmail.getText().toString(), binding.edtPwd.getText().toString());
    }

    public void showSnackBar(String message) {
        Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
}