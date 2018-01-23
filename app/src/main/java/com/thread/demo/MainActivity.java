package com.thread.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SyncThread syncThread = new SyncThread() ;

        //SyncThread2:0
        //SyncThread2:1
        //SyncThread2:2
        //SyncThread2:3
        //SyncThread2:4
        //SyncThread1:5
        //SyncThread1:6
        //SyncThread1:7
        //SyncThread1:8
        //SyncThread1:9
//        Thread thread1 = new Thread(syncThread , "SyncThread1") ;
//        Thread thread2 = new Thread(syncThread , "SyncThread2") ;

        //SyncThread1:0
        //SyncThread2:0
        //SyncThread1:1
        //SyncThread2:1
        //SyncThread1:2
        //SyncThread2:2
        //SyncThread1:3
        //SyncThread2:3
        //SyncThread1:4
        //SyncThread2:4
        Thread thread1 = new Thread(new SyncThread() , "SyncThread1") ;
        Thread thread2 = new Thread(new SyncThread() , "SyncThread2") ;
        thread1.start();
        thread2.start();
    }


    class SyncThread implements Runnable{
        private int count ;

        public SyncThread(){
            count = 0 ;
        }

        @Override
        public void run() {
            /**
             * 第一种：修饰一个代码块
             *        当两个线程thread1和thread2访问同一个syncThread对象中的synchronized代码块，
             *        同一时刻只能一个执行线程，另一个受阻，必须等当前线程执行完这个代码块后才能执行
             *        thread1在执行synchronized代码块时会锁定当前对象，只有执行完该代码块后才能释放对象锁，下一个线程才能执行并锁定该对象
             */
            synchronized (this){
                for (int i = 0; i < 5; i++) {
                    try {
                        //SyncThread2:0
                        //SyncThread2:1
                        //SyncThread2:2
                        //SyncThread2:3
                        //SyncThread2:4
                        //SyncThread1:5
                        //SyncThread1:6
                        //SyncThread1:7
                        //SyncThread1:8
                        //SyncThread1:9
                        Log.d("mainActivity-->", Thread.currentThread().getName() + ":" + (count++)) ;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        public int getCount(){
            return count ;
        }
    }


}
