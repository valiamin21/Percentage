package ir.proglovving.percentage;

import java.text.DecimalFormat;

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
        if (model.getQuestionsNo() < model.getRightNo() + model.getWrongNo() || model.getQuestionsNo() == 0) {
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

    public void onNumPickersScroll(){
        baseView.updateUi("---", "---", "---");
    }

}