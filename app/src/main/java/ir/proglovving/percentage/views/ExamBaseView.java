package ir.proglovving.percentage.views;

public interface ExamBaseView {
    void updateUi(String mainPercent, String percentWithoutWrongs, String percentIfWrongsWasRight);
    void showErrorToast(String message);
}
