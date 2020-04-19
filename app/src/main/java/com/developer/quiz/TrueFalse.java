package com.developer.quiz;

public class TrueFalse {
    private int mQuestionID;
    private boolean mAnswer;


    public TrueFalse(int questionResourceID,boolean trueORFalse)
    {
        mQuestionID = questionResourceID;
        mAnswer = trueORFalse;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
