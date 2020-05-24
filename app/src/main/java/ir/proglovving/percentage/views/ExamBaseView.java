package ir.proglovving.percentage.views;

import androidx.annotation.StringRes;

public interface ExamBaseView {
    void updateUi(String mainPercent, String percentWithoutWrongs, String percentIfWrongsWasRight);
    void showErrorToast(String message);
    void showErrorToast(@StringRes int message);
    void dismissDialog();
    void clearDialog();
    void refreshExamHistoryRecyclerView();
}
