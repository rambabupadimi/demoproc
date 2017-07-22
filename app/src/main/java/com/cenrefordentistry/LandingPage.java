package com.cenrefordentistry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cenrefordentistry.activities.AppointmentCalenderView;

/**
 * Created by Dineshgadu on 17-01-2017.
 */

public class LandingPage extends AppCompatActivity {


    Button tellMeMoreButton,contactCareTeamButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_layout);

        tellMeMoreButton    = (Button) findViewById(R.id.tellme_more_button);
        contactCareTeamButton   =   (Button)findViewById(R.id.contact_care_team_button);

        tellMeMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog();
            }
        });

        contactCareTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactCareDialog();
            }
        });
    }



    public void showContactCareDialog() {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.contact_care_team, null);
            Button cancel = (Button) view.findViewById(R.id.cct_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            Button callback = (Button)view.findViewById(R.id.cct_callback);
            callback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LandingPage.this,RegisterDob.class);
 //                   Intent intent = new Intent(LandingPage.this,AppointmentCalenderView.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            alertDialog.setView(view);
            alertDialog.show();
            alertDialog.setCancelable(false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    public void showInfoDialog() {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.tellme_more_layout, null);
            Button ok = (Button) view.findViewById(R.id.ok_button);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.setView(view);
            alertDialog.show();
            alertDialog.setCancelable(false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    // Detect when the back button is pressed
    private boolean _doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (_doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            this._doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to quit", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                _doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }
}
