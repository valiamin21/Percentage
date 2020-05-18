package ir.proglovving.percentage;

public class ExamModel {
    private double questionsNo;
    private double wrongNo;
    private double rightNo;

    public void setQuestionsNo(int questionsNo){
        this.questionsNo = questionsNo;
    }

    public void setWrongNo(int wrongNo){
        this.wrongNo = wrongNo;
    }

    public void setRightNo(int rightNo){
        this.rightNo = rightNo;
    }

   public double calculateMainPercent(){
       return (((3 * rightNo) - wrongNo) / (3 * questionsNo)) * 100;
   }

   public double calculatePercentWithoutWrongs(){
        return (rightNo * 100) / questionsNo;
   }

   public double calculatePercentIfWrongsWasRight(){
       return ((rightNo + wrongNo) * 100) / questionsNo;
   }
}
