package ir.proglovving.percentage;

public interface ExamBaseView {
    void updateUi(String mainPercent, String percentWithoutWrongs, String percentIfWrongsWasRight);
    void showErrorToast(String message);
}
