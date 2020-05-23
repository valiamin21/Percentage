package ir.proglovving.percentage.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import ir.proglovving.percentage.OnBottomSheetStateChanged;
import ir.proglovving.percentage.controllers.ExamController;
import ir.proglovving.percentage.models.ExamModel;
import ir.proglovving.percentage.R;
import ir.proglovving.percentage.Utilities;

public class MainActivity extends AppCompatActivity implements ExamBaseView {
    private NumberPicker questionsNumPicker, wrongsNumPicker, rightsNumPicker;
    private TextView mainPercentTextView, percentWithoutWrongsTextView, percentIfWrongsWasRightTextView;
    private Button calculateButton;
    private LinearLayout bottomSheetParent;
    private ImageButton bottomSheetOpenCloseButton;
    private TextView bottomSheetTitle;
    private BottomSheetBehavior bottomSheetBehavior;

    private ExamController controller;

    private NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            controller.onNumPickersValueChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        ExamModel model = new ExamModel();
        controller = new ExamController(this, model);
        controller.setData(0, 0, 0);
        controller.calculatePercent(false);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.setData(questionsNumPicker.getValue(), rightsNumPicker.getValue(), wrongsNumPicker.getValue());
                controller.calculatePercent(true);
            }
        });

        bottomSheetOpenCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        bottomSheetTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetOpenCloseButton.callOnClick();
            }
        });

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetParent);
        bottomSheetBehavior.addBottomSheetCallback(new OnBottomSheetStateChanged() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        rotateBottomSheetOpenCloseImage(180);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        rotateBottomSheetOpenCloseImage(0);
                        break;
                }
            }
        });
    }

    private void rotateBottomSheetOpenCloseImage(float endAngle){
        float startAngle = bottomSheetOpenCloseButton.getRotation();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startAngle, endAngle);
        valueAnimator.setDuration(150);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bottomSheetOpenCloseButton.setRotation((float) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    private void setupViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Utilities.applyFontForAViewGroup(toolbar, this);

        questionsNumPicker = findViewById(R.id.np_questions);
        setupNumPickers(questionsNumPicker);

        wrongsNumPicker = findViewById(R.id.np_wrongs);
        setupNumPickers(wrongsNumPicker);

        rightsNumPicker = findViewById(R.id.np_rights);
        setupNumPickers(rightsNumPicker);

        mainPercentTextView = findViewById(R.id.tv_mainPercent);
        percentWithoutWrongsTextView = findViewById(R.id.tv_percentWithoutWrongs);
        percentIfWrongsWasRightTextView = findViewById(R.id.tv_percentIfWrongsWasRight);

        calculateButton = findViewById(R.id.btn_calculate);

        bottomSheetParent = findViewById(R.id.bottom_sheet_patent);
        // Setting onclickListener as null prevents user interacts with underlying views
        bottomSheetParent.setOnClickListener(null);

        bottomSheetOpenCloseButton = findViewById(R.id.imgBtn_close_open_bottom_sheet);
        bottomSheetTitle = findViewById(R.id.txt_bottomSheetTitle);
    }

    private void setupNumPickers(NumberPicker numberPicker) {
        numberPicker.setValue(0);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(1000);
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener(onValueChangeListener);
    }

    @Override
    public void updateUi(String mainPercent, String percentWithoutWrongs, String percentIfWrongsWasRight) {
        mainPercentTextView.setText(String.valueOf(mainPercent));
        percentWithoutWrongsTextView.setText(String.valueOf(percentWithoutWrongs));
        percentIfWrongsWasRightTextView.setText(String.valueOf(percentIfWrongsWasRight));
    }

    @Override
    public void showErrorToast(String message) {
        Utilities.showErrorToast(this, message, Toast.LENGTH_SHORT);
    }
}