package com.example.geo;
public class TrueFalse {
    private int mQuestion; // 更改变量名以符合命名规范
    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public boolean isTrueQuestion() { // 更改方法名以符合命名规范
        return mTrueQuestion;
    }
}