package com.tech.salesapp.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.tech.salesapp.Exception.ExceptionLog;
import com.tech.salesapp.R;

public class AppProgress {
    private static Dialog dialog=null;
    Activity activity;
  // public static ProgressDialog progressDialog;

   /* public static void showProgress(Activity activity) {

        try {
            if (dialog==null) {
                dialog = new Dialog(activity, android.R.style.Theme_Black);
                View view = LayoutInflater.from(activity).inflate(R.layout.app_progress, null);
                ImageView logo = view.findViewById(R.id.img_logo);
                RotateAnimation rotate = new RotateAnimation(
                        0, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                rotate.setDuration(1000);
                rotate.setRepeatCount(Animation.INFINITE);
                logo.startAnimation(rotate);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.transparent);
                dialog.setCancelable(false);
                dialog.setContentView(view);
                if (dialog.isShowing() == false) {
                    dialog.show();
                }
            }
            else
            {
                if (dialog.isShowing() == false) {
                    dialog.show();
                }
            }
        }catch (Exception ex)
        {
            ExceptionLog.catchException(ex);
        }
    }

    public static Dialog showProgress(FragmentActivity activity) {

        try {
            if (dialog==null) {
                dialog = new Dialog(activity, android.R.style.Theme_Black);
                View view = LayoutInflater.from(activity).inflate(R.layout.app_progress, null);
                ImageView logo = view.findViewById(R.id.img_logo);
                RotateAnimation rotate = new RotateAnimation(
                        0, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                rotate.setDuration(1000);
                rotate.setRepeatCount(Animation.INFINITE);
                logo.startAnimation(rotate);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.transparent);
                dialog.setCancelable(false);
                dialog.setContentView(view);
                if (dialog.isShowing() == false) {
                    dialog.show();
                }
            }
            else
            {
                if (dialog.isShowing() == false) {
                    dialog.show();
                }
            }
        }catch (Exception ex)
        {
            ExceptionLog.catchException(ex);
        }
        return dialog;
    }

    public static void hideProgress(Dialog  dialog) {
        try {
            if (dialog!=null)
            {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    dialog=null;
                }
            }

        }catch (Exception ex)
        {
            dialog=null;
            ExceptionLog.catchException(ex);
        }
    }*/

    public static ProgressDialog showProgress(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        try {
            progressDialog.setCancelable(false);
           // progressDialog.setTitle();
            progressDialog.setMessage(context.getResources().getString(R.string.please_wait));
           // progressDialog.create();
            progressDialog.show();

        }catch (Exception ex)
        {
            ExceptionLog.catchException(ex);
        }
        return  progressDialog;
    }


    public static void hideProgress(ProgressDialog progressDialog) {
        try {

                progressDialog.dismiss();


        }catch (Exception ex)
        {
            ExceptionLog.catchException(ex);
        }
    }


}
