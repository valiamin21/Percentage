package ir.proglovving.percentage.controllers;

import android.content.Context;

import java.text.DecimalFormat;

import ir.proglovving.percentage.R;
import ir.proglovving.percentage.models.ExamHistoryModel;
import ir.proglovving.percentage.models.ExamModel;
import ir.proglovving.percentage.open_helpers.ExamsHistoryOpenHelper;
import ir.proglovving.percentage.views.ExamBaseView;

public class ExamController {
    private ExamBaseView baseView;
    private ExamModel model;

    private DecimalFormat decimalFormat = new DecimalFormat("0.##");

    public ExamController(ExamBaseView baseView, ExamModel model) {
        this.baseView = baseView;
        this.model = model;
    }

    public void setData(int questionNo, int rightNo, int wrongNo) {
        model.setQuestionsNo(questionNo);
        model.setRightNo(rightNo);
        model.setWrongNo(wrongNo);
    }

    public void calculatePercent(boolean showErrorMessages) {
        if (!isExamValid(model)) {
            if (showErrorMessages)
                baseView.showErrorToast("لطفا اطلاعات‌را درست وارد کنید!");
            baseView.updateUi("---", "---", "---");
        } else {
            baseView.updateUi(decimalFormat.format(model.calculateMainPercent()) + "%"
                    , decimalFormat.format(model.calculatePercentWithoutWrongs()) + "%"
                    , decimalFormat.format(model.calculatePercentIfWrongsWasRight()) + "%"
            );
        }
    }

    public boolean isExamValid(ExamModel examModel) {
        return examModel.getQuestionsNo() >= model.getRightNo() + model.getWrongNo() && model.getQuestionsNo() != 0;
    }

    public boolean isExamValid() {
        return model.getQuestionsNo() >= model.getRightNo() + model.getWrongNo() && model.getQuestionsNo() != 0;
    }

    public void onNumPickersValueChanged() {
        baseView.updateUi("---", "---", "---");
    }

    public void saveExam(ExamsHistoryOpenHelper examsHistoryOpenHelper, String examName, int questionNo, int rightNo, int wrongNo) {
        ExamHistoryModel examHistoryModel = new ExamHistoryModel();
        examHistoryModel.setExamName(examName);
        examHistoryModel.setQuestionsNo(questionNo);
        examHistoryModel.setRightNo(rightNo);
        examHistoryModel.setWrongNo(wrongNo);

        if (examHistoryModel.getExamName().length() == 0) {
            baseView.showErrorToast(R.string.please_enter_exam_name);
        } else {
            examsHistoryOpenHelper.addExam(examHistoryModel);
            baseView.dismissDialog();
            baseView.clearDialog();
            baseView.refreshExamHistoryRecyclerView();
        }
    }
}