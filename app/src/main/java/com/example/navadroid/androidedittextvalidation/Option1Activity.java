package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Option1Activity extends AppCompatActivity {

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        bindView();
        initView();
    }

    private void bindView(){
        etName = (EditText) findViewById(R.id.et_name1);
        etPwd = (EditText) findViewById(R.id.et_pwd1);
        etEmail = (EditText) findViewById(R.id.et_email1);
        etPhone = (EditText) findViewById(R.id.et_phone1);
    }

    private void initView(){
        findViewById(R.id.btn_validate1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEditText()) {
                    Toast.makeText(Option1Activity.this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
                    // SnackBar?
                }
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This can be ignored
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This can be ignored
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEditText(); // OR validation can be specific (only for this EditText)
            }
        });

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: add your Password validation here
            }
        });

//        etPhone.addTextChangedListener(...);
//        etEmail.addTextChangedListener(...);
    }
    private boolean validateEditText(){
        boolean isValidated = true;
        if(!isValidName(etName.getText().toString())){
            etName.setError("ระบุเฉพาะตัวอักษรเท่านั้น!");
            isValidated = false;
        }
        if (etName.getText().toString().length() == 0) {
            etName.setError("กรุณาระบุชื่อ");
            isValidated = false;
        }
        if(!isValidEmail(etEmail.getText().toString())){
            etEmail.setError("ระบุไม่ตรงรูปแบบ!");
            isValidated = false;
        }
        if (etEmail.getText().toString().length() == 0) {
            etEmail.setError("กรุณาระบุอีเมล์");
            isValidated = false;
        }
        if (etPwd.getText().toString().length() == 0) {
            etPwd.setError("กรุณาระบุรหัสผ่าน");
            isValidated = false;
        }
        if(!isValidPhone(etPhone.getText().toString())){
            etPhone.setError("ระบุหมายเลขโทรศัพท์ไม่ถูกต้อง!");
            isValidated = false;
        }
        if (etPhone.getText().toString().length() == 0) {
            etPhone.setError("กรุณาระบุเบอร์โทรศัพท์");
            isValidated = false;
        }
        if (etPhone.getText().toString().length() < 10 || etPhone.getText().toString().length() > 10) {
            etPhone.setError("กรุณาระบุเบอร์โทรศัพท์ 10 หลัก");
            isValidated = false;
        }
        // TODO: add your EditText validation here

        return isValidated;
    }
    private boolean isValidName(String name) {
        String NAME_PATERN="[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(NAME_PATERN);
        Matcher matcher=pattern.matcher(name);
        return matcher.matches();
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidPhone(String phone) {
        String PHONE_PATERN="[0-9]+";
        Pattern pattern = Pattern.compile(PHONE_PATERN);
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }
    // To validate all EditTexts
    /*private boolean validateEditText(){
        boolean isValidated = true;
        if (etName.getText().toString().length() == 0) {
            etName.setError("Required");
            isValidated = false;
        }
        // TODO: add your EditText validation here

        return isValidated;
    }*/
}
