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

    // Fecha o teclado virtual se aberto (view com foque)
    public static boolean closeVirtualKeyboard(Context context, View view) {
        // Fecha o teclado virtual
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            boolean b = imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return b;
        }
        return false;
    }



}
