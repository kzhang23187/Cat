package com.example.cat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtils {
    public static void showPopUp(Context context
            , String title
            , String msg
            , String positiveBtnTxt
            , String negativeBtnTxt
            , DialogInterface.OnClickListener positiveBtnListener
            , DialogInterface.OnClickListener negativeBtnListener){

        final AlertDialog errorDialog;
        AlertDialog.Builder errorDialogBuilder = new AlertDialog.Builder(context, R.style.AppTheme);
        errorDialogBuilder.setTitle(title);
        errorDialogBuilder.setMessage(msg);
        errorDialogBuilder.setPositiveButton(positiveBtnTxt, positiveBtnListener);
        errorDialogBuilder.setNegativeButton(negativeBtnTxt, negativeBtnListener);
        errorDialog = errorDialogBuilder.create();
        errorDialog.show();
    }
}
