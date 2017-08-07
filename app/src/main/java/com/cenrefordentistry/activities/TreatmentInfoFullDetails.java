package com.cenrefordentistry.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.TreatmentQuestionsAnswersAdapter;
import com.cenrefordentistry.fragments.TreatmentInfo;
import com.cenrefordentistry.models.TreatmentQuestionsAndAnswers;

import java.util.ArrayList;
import java.util.List;

public class TreatmentInfoFullDetails extends AppCompatActivity implements AppConstants {

    private static final String TAG = "TreatmentDetails";
    private TextView title, description;
    private ImageView back, treatment_image;
    private String Title, Description, Image_text, Question, Answer, QuesAnswerCount;
    private int count;
    private RecyclerView recyclerView;
    private TreatmentQuestionsAnswersAdapter treatmentQuestionsAnswersAdapter;
    private List<TreatmentQuestionsAndAnswers> list;

    LinearLayout tap_here_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_info_full_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_appointment_dairy);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tap_here_layout = (LinearLayout) findViewById(R.id.tap_here_layout);

        int colorIs = 0;
        Log.i("tag","colorcode"+getIntent().getStringExtra(AppConstants.COLOR_CODE));

        if(getIntent().getStringExtra(AppConstants.COLOR_CODE).equalsIgnoreCase("LGC"))
        {
           colorIs = getResources().getColor(R.color.colorPrimaryDarker);
        }
        else if(getIntent().getStringExtra(AppConstants.COLOR_CODE).equalsIgnoreCase("BLUE"))
        {
            colorIs = getResources().getColor(R.color.colorBlue);
        }else if(getIntent().getStringExtra(AppConstants.COLOR_CODE).equalsIgnoreCase("DPINK"))
        {
           colorIs = getResources().getColor(R.color.colorPink);
        }
        else if(getIntent().getStringExtra(AppConstants.COLOR_CODE).equalsIgnoreCase("PINK"))
        {
            colorIs = getResources().getColor(R.color.colorPink);
        }else if(getIntent().getStringExtra(AppConstants.COLOR_CODE).equalsIgnoreCase("VOILET"))
        {
            colorIs = getResources().getColor(R.color.colorPrimary);
        }else if(getIntent().getStringExtra(AppConstants.COLOR_CODE).equalsIgnoreCase("DGC"))
        {
            colorIs = getResources().getColor(R.color.colorRed);
        }

        toolbar.setBackgroundColor(colorIs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorIs);
        }

        tap_here_layout.setBackgroundColor(colorIs);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        title = (TextView) findViewById(R.id.treatment_details_title);
        description = (TextView) findViewById(R.id.treatment_details_text);
        treatment_image = (ImageView) findViewById(R.id.treatment_details_image);

        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.questions_answers);

        Title = getIntent().getStringExtra(AppConstants.TITLE);
        Description = getIntent().getStringExtra(AppConstants.DESCRIPTION);
        Image_text = getIntent().getStringExtra(AppConstants.IMAGE);

        Question = Title.replace("_title", "_question_");
        Answer = Title.replace("_title", "_answer_");
        QuesAnswerCount = Title.replace("_title", "_count");

        Log.w(TAG, "Title: " + Title);
        Log.w(TAG, "Question: " + Question);
        Log.w(TAG, "Answer: " + Answer);
        Log.w(TAG, "Count: " + QuesAnswerCount);

        if (Title.equalsIgnoreCase("bridgesandpartialdentures_title") || Title.equalsIgnoreCase("childrendental_title")) {
            title.setTextSize(16);
        }


        if (Title.equalsIgnoreCase("drillfreedentistry_title") || Title.equalsIgnoreCase("scaleanpolish_title")) {}else{
            Log.w(TAG, "Count_String: " + getResources().getText(getResources().getIdentifier(QuesAnswerCount, "string", getPackageName())).toString());
            count = Integer.parseInt(getResources().getText(getResources().getIdentifier(QuesAnswerCount, "string", getPackageName())).toString());
        }
        Log.w(TAG, "Count: " + count);

        title.setText(getResources().getText(getResources().getIdentifier(Title, "string", getPackageName())));
        description.setText(getResources().getText(getResources().getIdentifier(Description, "string", getPackageName())));
        try {
            treatment_image.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(Image_text.toLowerCase(), "drawable", getPackageName())));
        }catch (Exception e)
        {
            treatment_image.setImageDrawable(getResources().getDrawable(R.drawable.logo));
            e.printStackTrace();
        }
        try {
            for (int i = 1; i <= count; i++) {
                if (!Title.equalsIgnoreCase("drillfreedentistry_title") || !Title.equalsIgnoreCase("scaleanpolish_title")) {
                    if (getResources().getText(getResources().getIdentifier(Question + i, "string", getPackageName())).toString() != null && getResources().getText(getResources().getIdentifier(Answer + i, "string", getPackageName())).toString() != null) {
                        Log.w(TAG, "Question: " + getResources().getText(getResources().getIdentifier(Question + i, "string", getPackageName())));
                        Log.w(TAG, "Answer: " + getResources().getText(getResources().getIdentifier(Answer + i, "string", getPackageName())));

                        TreatmentQuestionsAndAnswers treatmentQuestionsAndAnswers =
                                new TreatmentQuestionsAndAnswers(getResources().getText(getResources().getIdentifier(Question + i, "string", getPackageName())).toString(),
                                        getResources().getText(getResources().getIdentifier(Answer + i, "string", getPackageName())).toString());
                        list.add(treatmentQuestionsAndAnswers);
                    }
                }
            }
        }
        catch(Exception e)
        {
            Log.e(TAG,""+e);
        }
        finally {
            Log.w(TAG,"List size: "+list.size());
            treatmentQuestionsAnswersAdapter = new TreatmentQuestionsAnswersAdapter(TreatmentInfoFullDetails.this, list,colorIs);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(treatmentQuestionsAnswersAdapter);
        }

/*
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TreatmentInfoFullDetails.this);
                LayoutInflater inflater = TreatmentInfoFullDetails.this.getLayoutInflater();
                View dialogView = View.inflate(TreatmentInfoFullDetails.this, R.layout.reader_dialog, null);
                dialogView.setPadding(20,20,20,20);
                dialogBuilder.setView(dialogView);
                Button yes_button = (Button) dialogView.findViewById(R.id.yes_button);
                Button no_button = (Button) dialogView.findViewById(R.id.no_button);

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();

                yes_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(TreatmentInfoFullDetails.this);
                        builder.setMessage(" Thanks for showing your interest. CFD PCT will contact you soon !")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                        alertDialog.dismiss();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

                no_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        },30000);

    **/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home) {
        finish();
        }
        return super.onOptionsItemSelected(item);

    }
}



