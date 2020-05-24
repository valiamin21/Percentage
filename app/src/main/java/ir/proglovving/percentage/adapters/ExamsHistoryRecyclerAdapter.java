package ir.proglovving.percentage.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import ir.proglovving.percentage.R;
import ir.proglovving.percentage.models.ExamHistoryModel;
import ir.proglovving.percentage.open_helpers.ExamsHistoryOpenHelper;

public class ExamsHistoryRecyclerAdapter extends RecyclerView.Adapter<ExamsHistoryRecyclerAdapter.ExamViewHolder> {

    private final Context context;
    private final List<ExamHistoryModel> examHistoryModelList;

    private DecimalFormat decimalFormat = new DecimalFormat("0.##");

    public ExamsHistoryRecyclerAdapter(Context context, List<ExamHistoryModel> examHistoryModelList){
        this.context = context;
        this.examHistoryModelList = examHistoryModelList;
        Collections.reverse(this.examHistoryModelList);
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exam_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ExamViewHolder holder, int position) {
        holder.examNameTextView.setText(examHistoryModelList.get(position).getExamName());
        holder.examPercentTextView.setText(decimalFormat.format(examHistoryModelList.get(position).calculateMainPercent()) + "%");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExamHistoryModelDialog(examHistoryModelList.get(holder.getAdapterPosition()));
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExamsHistoryOpenHelper.getInstance(context).deleteExamHistoryModel(examHistoryModelList.get(holder.getAdapterPosition()).getId());
                examHistoryModelList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return examHistoryModelList.size();
    }

    public void setExamHistoryModelList(List<ExamHistoryModel> examHistoryModelList){
        this.examHistoryModelList.clear();
        this.examHistoryModelList.addAll(examHistoryModelList);
        Collections.reverse(this.examHistoryModelList);
        notifyDataSetChanged();
    }

    private void showExamHistoryModelDialog(ExamHistoryModel examHistoryModel){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_exam_history_model);
        TextView examNameTextView, questionsNoTextView, rightsNoTextView, wrongsNoTextView, mainPercentTextView, percentWithoutWrongsTextView, ifWrongsWasRightTextView;
        examNameTextView = dialog.findViewById(R.id.tv_examName);
        questionsNoTextView = dialog.findViewById(R.id.tv_questionsNo);
        rightsNoTextView = dialog.findViewById(R.id.tv_rightsNo);
        wrongsNoTextView = dialog.findViewById(R.id.tv_wrongsNo);
        mainPercentTextView = dialog.findViewById(R.id.tv_mainPercent);
        percentWithoutWrongsTextView = dialog.findViewById(R.id.tv_percentWithoutWrongs);
        ifWrongsWasRightTextView = dialog.findViewById(R.id.tv_percentIfWrongsWasRight);

        Button gotItButton = dialog.findViewById(R.id.gotIt_button);

        examNameTextView.setText(examHistoryModel.getExamName());
        questionsNoTextView.setText(String.valueOf(examHistoryModel.getQuestionsNo()));
        rightsNoTextView.setText(String.valueOf(examHistoryModel.getRightNo()));
        wrongsNoTextView.setText(String.valueOf(examHistoryModel.getWrongNo()));
        mainPercentTextView.setText(decimalFormat.format(examHistoryModel.calculateMainPercent()) + "%");
        percentWithoutWrongsTextView.setText(decimalFormat.format(examHistoryModel.calculatePercentWithoutWrongs()) + "%");
        ifWrongsWasRightTextView.setText(decimalFormat.format(examHistoryModel.calculatePercentIfWrongsWasRight()) + "%");

        gotItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public class ExamViewHolder extends RecyclerView.ViewHolder{
        private TextView examPercentTextView, examNameTextView;
        private ImageButton deleteButton;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            examPercentTextView = itemView.findViewById(R.id.tv_examPercent);
            examNameTextView = itemView.findViewById(R.id.tv_examName);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
