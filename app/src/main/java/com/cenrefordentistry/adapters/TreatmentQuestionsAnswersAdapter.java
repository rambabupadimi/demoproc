package com.cenrefordentistry.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cenrefordentistry.R;
import com.cenrefordentistry.models.TreatmentQuestionsAndAnswers;

import java.util.List;

/**
 * Created by Ramu on 01-08-2017.
 */

public class TreatmentQuestionsAnswersAdapter extends RecyclerView.Adapter<TreatmentQuestionsAnswersAdapter.ViewHolder> {
    List<TreatmentQuestionsAndAnswers> treatmentQuestionsAndAnswersList;
    Context mContext;
    boolean is_collapsed = true;
    int colorIs;

    public TreatmentQuestionsAnswersAdapter(Context context, List<TreatmentQuestionsAndAnswers> list,int colorIs)
    {
        this.treatmentQuestionsAndAnswersList = list;
        this.mContext = context;
        this.colorIs = colorIs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.treatment_details_questions, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TreatmentQuestionsAndAnswers treatmentQuestionsAndAnswers = treatmentQuestionsAndAnswersList.get(position);

        holder.collapse_parent_layout.setBackgroundColor(colorIs);
        holder.question.setText(treatmentQuestionsAndAnswers.getTreatmentQuestion());
        holder.answer.setText(treatmentQuestionsAndAnswers.getTreatmentAnswer());
        holder.collapse_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(is_collapsed) {
                    holder.collapse_expand.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
                    holder.collapse_expand.setColorFilter(ContextCompat.getColor(mContext, R.color.colorWhite));
                    //holder.answer.setVisibility(View.VISIBLE);
                    is_collapsed = false;
                    expand(holder.answer);
                }
                else
                {
                    holder.collapse_expand.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
                    //holder.answer.setVisibility(View.GONE);
                    is_collapsed = true;
                    collapse(holder.answer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return treatmentQuestionsAndAnswersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public TextView answer;
        public ImageView collapse_expand;
        RelativeLayout collapse_parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.question);
            answer = (TextView) itemView.findViewById(R.id.answer);
            collapse_expand = (ImageView) itemView.findViewById(R.id.collapsable_icon);
            collapse_parent_layout = (RelativeLayout) itemView.findViewById(R.id.collapse_parent_layout);
        }
    }

    public static void expand(final View v) {
        v.measure(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ActionBar.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}
