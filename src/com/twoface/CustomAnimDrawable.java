package com.twoface;

import java.lang.reflect.Field;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;

public class CustomAnimDrawable extends AnimationDrawable {
    private final String TAG = "xmp";
    private AnimationDrawable mOriAnim;
    private AnimationDrawable mSelf;
    private Handler mHandler;
    private boolean mStarted;
    private AnimEndListenerRunnable mEndRunnable;
    private AnimationDrawableListener mListener;

    public CustomAnimDrawable(AnimationDrawable anim) {
        mOriAnim = anim;
        initialize();
    }

    private void initialize() {
        mSelf = this;
        mStarted = false;
        mHandler = new Handler();
        mEndRunnable = new AnimEndListenerRunnable();
        for (int i = 0; i < mOriAnim.getNumberOfFrames(); i++) {
            mSelf.addFrame(mOriAnim.getFrame(i), mOriAnim.getDuration(i));
        }
    }

    @Override
    public void start() {
        mOriAnim.start();
        mStarted = true;
        mHandler.post(mEndRunnable);
        if (mListener != null) {
            mListener.onAnimationStart(mSelf);
        }
        Log.v(TAG, "------CustomAnimDrawable------>start");
    }
    

    class AnimEndListenerRunnable implements Runnable {
        @Override
        public void run() {

            if (!mStarted) {
                return;
            }

            if (!isEnd()) {

                mHandler.postDelayed(mEndRunnable,1000);
                return;
            }
            Log.v(TAG, "----------->over");
            if (mListener != null) {
                mStarted = false;
                mListener.onAnimationEnd(mSelf);
            }
        }
    }

    private boolean isEnd(){
        Class<AnimationDrawable> animClass = AnimationDrawable.class;
        try{  

            Field field = animClass.getDeclaredField("mCurFrame");
            field.setAccessible(true);
            
            int currFrameNum = field.getInt(mOriAnim);
            int totalFrameNum = mOriAnim.getNumberOfFrames();
            if((currFrameNum == totalFrameNum - 1)||
               (currFrameNum == -1)){
                return true;
            }
        }
        catch (Exception e) {
            Log.v(TAG,"-------->Exception");
        }
        
        return false;
    }

    public void setAnimationListener(AnimationDrawableListener listener) {
        mListener = listener;
    }
    
    public interface AnimationDrawableListener {
        /**
         * Notifies the start of the animation
         * @param animation
         */
        public void onAnimationStart(AnimationDrawable animation);
        /**
         * Notifies the end of the animation
         * @param animation
         */
        public void onAnimationEnd(AnimationDrawable animation);
    }
}
