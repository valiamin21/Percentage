package ir.proglovving.percentage.open_helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ir.proglovving.percentage.models.ExamHistoryModel;

public class ExamsHistoryOpenHelper extends SQLiteOpenHelper {
    private static final String EXAM_HISTORY_DB_NAME = "exams_history_database";
    private static final String EXAM_HISTORY_TABLE_NAME = "exams_history_table";
    private static final int EXAM_HISTORY_DB_VERSION = 1;

    private static final String COL_ID = "id";
    private static final String COL_EXAM_NAME = "exam_name";
    private static final String COL_EXAM_QUESTIONS_NO = "questions_no";
    private static final String COL_EXAM_WRONGS_NO = "wrongs_no";
    private static final String COL_EXAM_RIGHTS_NO = "rights_no";

    private static final String createTableCommand = "CREATE TABLE " + EXAM_HISTORY_TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COL_EXAM_NAME + " TEXT," +
            COL_EXAM_QUESTIONS_NO + " INTEGER," +
            COL_EXAM_WRONGS_NO + " INTEGER," +
            COL_EXAM_RIGHTS_NO + " INTEGER);";

    private static ExamsHistoryOpenHelper instance;

    public static ExamsHistoryOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new ExamsHistoryOpenHelper(context);
        }

        return instance;
    }

    public ExamsHistoryOpenHelper(@Nullable Context context) {
        super(context, EXAM_HISTORY_DB_NAME, null, EXAM_HISTORY_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addExam(ExamHistoryModel examHistoryModel){
        ContentValues cv = new ContentValues();
        cv.put(COL_EXAM_NAME,examHistoryModel.getExamName());
        cv.put(COL_EXAM_QUESTIONS_NO,examHistoryModel.getQuestionsNo());
        cv.put(COL_EXAM_WRONGS_NO,examHistoryModel.getWrongNo());
        cv.put(COL_EXAM_RIGHTS_NO,examHistoryModel.getRightNo());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(EXAM_HISTORY_TABLE_NAME,null,cv);
        db.close();
    }

    public List<ExamHistoryModel> getExamHistoryModelList(){
        List<ExamHistoryModel> examHistoryModelList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EXAM_HISTORY_TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do{
                ExamHistoryModel examHistoryModel = new ExamHistoryModel();
                examHistoryModel.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                examHistoryModel.setExamName(cursor.getString(cursor.getColumnIndex(COL_EXAM_NAME)));
                examHistoryModel.setQuestionsNo(cursor.getInt(cursor.getColumnIndex(COL_EXAM_QUESTIONS_NO)));
                examHistoryModel.setWrongNo(cursor.getInt(cursor.getColumnIndex(COL_EXAM_WRONGS_NO)));
                examHistoryModel.setRightNo(cursor.getInt(cursor.getColumnIndex(COL_EXAM_RIGHTS_NO)));

                examHistoryModelList.add(examHistoryModel);
            }while(cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return examHistoryModelList;
    }

    public void deleteExamHistoryModel(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(EXAM_HISTORY_TABLE_NAME,COL_ID + " = ?" , new String[]{String.valueOf(id)});
        db.close();
    }
}
