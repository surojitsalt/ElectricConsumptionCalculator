package com.example.electricconsumptioncalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class UIUtility {

    public static void showAlertDialog(final Context pContext, final String pMessage,
                                       final String pLabel, final String pTitle) {
        final AlertDialog.Builder vAdb = new AlertDialog.Builder(pContext);
        showAlertDialog(vAdb, pMessage, pLabel, pTitle);
    }

    public static void showAlertDialog(final AlertDialog.Builder pADB, final String pMessage,
                                       final String pLabel, final String pTitle) {

        final AlertDialog vAlertDialog = pADB.create();
        vAlertDialog.setMessage(pMessage);
        vAlertDialog.setTitle(pTitle);
        vAlertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        vAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, pLabel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog,
                                        final int id) {
                        dialog.cancel();

                    }
                });

        vAlertDialog.show();
        vAlertDialog.setCanceledOnTouchOutside(false);
    }


}
