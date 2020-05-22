package ir.proglovving.percentage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ExamBaseView {
    private static final String TAG = "MainActivity";
    private NumberPicker questionsNumPicker, wrongsNumPicker, rightsNumPicker;
    private TextView mainPercentTextView, percentWithoutWrongsTextView, percentIfWrongsWasRightTextView;
    private Button calculateButton;

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
        Utilities.showErrorToast(this,message,Toast.LENGTH_SHORT);
    }
}