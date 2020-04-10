package com.abdulkarim.loginapplication;

import android.widget.EditText;

public class InputValidation {

    public boolean isEmptyEditText(EditText editText,String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            editText.setError(message);
            return false;
        }
        return true;
    }

    public boolean isValidEmail(EditText editText,String message) {
        String value = editText.getText().toString().trim();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            editText.setError(message);
            return false;
        }
        return true;
    }

    public boolean isPasswordMatches(EditText editText1, EditText editText2,String message) {
        String value1 = editText1.getText().toString().trim();
        String value2 = editText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            editText2.setError(message);
            return false;
        }
        return true;
    }

}
