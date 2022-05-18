package com.example.victorina;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;



public class Dialog {

    //метод создания диалогового окна

   static AlertDialog createDialog(String title, String message, Context context){

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        dialog.setTitle(title)

                .setMessage(message)

                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }

                });

        return dialog.create();

    }
}
