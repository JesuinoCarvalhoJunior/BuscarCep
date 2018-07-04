package com.jcarvalhojr.buscarcep.Helpers;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
/**
 * Created by Junior_Carvalho on 27/06/2018.
 */
public class ShowHideKeyboard {

    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

    }

    public static void showKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }
}
