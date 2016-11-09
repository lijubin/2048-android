package com.example.lyl.a2048;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public enum Orientation {
        TOP,BOTTOM,LEFT,RIGHT;
    }
    public ArrayList<View> viewLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewLists();
        resetAllView();//初始化

        ((Button) findViewById(R.id.topId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveByOrientation(Orientation.TOP);
            }
        });
        ((Button) findViewById(R.id.leftId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveByOrientation(Orientation.LEFT);
            }
        });
        ((Button) findViewById(R.id.bottomId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveByOrientation(Orientation.BOTTOM);
            }
        });
        ((Button) findViewById(R.id.rightId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveByOrientation(Orientation.RIGHT);
            }
        });
    }

    //初始化视图列表
    private void initViewLists() {
        viewLists = new ArrayList<View>();
        TextView textView00 = (TextView )findViewById(R.id.btn00);
        TextView textView01 = (TextView )findViewById(R.id.btn01);
        TextView textView02 = (TextView )findViewById(R.id.btn02);
        TextView textView03 = (TextView )findViewById(R.id.btn03);
        TextView textView10 = (TextView )findViewById(R.id.btn10);
        TextView textView11 = (TextView )findViewById(R.id.btn11);
        TextView textView12 = (TextView )findViewById(R.id.btn12);
        TextView textView13 = (TextView )findViewById(R.id.btn13);
        TextView textView20 = (TextView )findViewById(R.id.btn20);
        TextView textView21 = (TextView )findViewById(R.id.btn21);
        TextView textView22 = (TextView )findViewById(R.id.btn22);
        TextView textView23 = (TextView )findViewById(R.id.btn23);
        TextView textView30 = (TextView )findViewById(R.id.btn30);
        TextView textView31 = (TextView )findViewById(R.id.btn31);
        TextView textView32 = (TextView )findViewById(R.id.btn32);
        TextView textView33 = (TextView )findViewById(R.id.btn33);
        viewLists.add(textView00);
        viewLists.add(textView01);
        viewLists.add(textView02);
        viewLists.add(textView03);
        viewLists.add(textView10);
        viewLists.add(textView11);
        viewLists.add(textView12);
        viewLists.add(textView13);
        viewLists.add(textView20);
        viewLists.add(textView21);
        viewLists.add(textView22);
        viewLists.add(textView23);
        viewLists.add(textView30);
        viewLists.add(textView31);
        viewLists.add(textView32);
        viewLists.add(textView33);
    }

    //清空数据初始化
    private void resetAllView() {
        for (View view : viewLists) {
            TextView textView = (TextView )view;
            textView.setText("");
        }
        //首次进入生成两个随机数
        setShowNumber();
        setShowNumber();
//        TextView textView = (TextView)findViewById(R.id.btn00);
//        textView.setText("2");
//        TextView textView1 = (TextView)findViewById(R.id.btn10);
//        textView1.setText("4");
//        TextView textView2 = (TextView)findViewById(R.id.btn20);
//        textView2.setText("4");
//        TextView textView3 = (TextView)findViewById(R.id.btn30);
//        textView3.setText("2");
    }

    //获取随机数
    String getRandomStr() {
        String[] randomLists = {"2","2","2","2","2","2","2","2","4","4"};
        Random random = new Random();
        int result = random.nextInt(10);
        return randomLists[result];
    }

    //设置随机数值
    void setShowNumber() {
        String number = getRandomStr();
        ArrayList<Integer> tmpOkArray = new ArrayList();
        for (int i = 0;i < viewLists.size();i++) {
            TextView textView = (TextView) viewLists.get(i);
            String text = (String) textView.getText();
            if (text.isEmpty() || text == "") {
                tmpOkArray.add(i);
            }
        }
        if (tmpOkArray.size() == 0) {
            Log.e("game over:","game over");
            gameOver();
            return;
        }

        Random random = new Random();
        int resultRandom = random.nextInt(tmpOkArray.size());
        TextView textView = (TextView) viewLists.get(tmpOkArray.get(resultRandom));
        textView.setText(getRandomStr());
    }

    //上下左右移动
    void moveByOrientation (Orientation orientation) {
        boolean isMove = false;
        int colums = 4;
        int startValue = 0;//起始值
        int endValue = 0;
        int directionVar = 0;//自增数
        int directionMul = 0;//自增数倍数
        boolean isAdd = true;//自增,还是自减
        if (orientation == Orientation.TOP) {
            startValue = 12;
            endValue = 16;
            directionVar = 4;
            directionMul = 1;
            isAdd = false;
        } else if (orientation == Orientation.BOTTOM) {
            startValue = 0;
            endValue = 4;
            directionVar = 4;
            directionMul = 1;
            isAdd = true;
        } else if (orientation == Orientation.LEFT) {
            startValue = 3;
            endValue = 16;
            directionVar = 1;
            directionMul = 4;
            isAdd = false;
        } else if (orientation == Orientation.RIGHT) {
            startValue = 0;
            endValue = 16;
            directionVar = 1;
            directionMul = 4;
            isAdd = true;
        }

        for (int i = startValue;i < endValue;i+=directionMul) {
            int tmpIncrementStart = i;
            int tmpIncrementEnd = i;
            int moveNumber = 0;//移动次数
            ArrayList<Integer> tmpNeedArray = new ArrayList();//需要转换的数组
            ArrayList<Integer> tmpIndexArray = new ArrayList();//能改转换的下标 0 1
            do {
                tmpNeedArray.add(tmpIncrementEnd);
                tmpIndexArray.add(0);
                tmpIncrementEnd = isAdd?(tmpIncrementEnd + directionVar):(tmpIncrementEnd - directionVar);
                moveNumber++;
            }while (moveNumber < colums);
            moveNumber = 0;
            tmpIncrementStart = colums - 2;
            int j = 0;
            do {
                tmpIncrementEnd = tmpIncrementStart + 1;
                TextView textViewStart = (TextView) viewLists.get(tmpNeedArray.get(tmpIncrementStart));
                TextView textViewEnd = (TextView) viewLists.get(tmpNeedArray.get(tmpIncrementEnd));
                String startStr = (String) textViewStart.getText();
                String endStr = (String) textViewEnd.getText();
                if (!"".equals(startStr) || !"".equals(endStr)) {
                    if (startStr.equals(endStr)
                            && tmpIndexArray.get(tmpIncrementStart) == 0
                            && tmpIndexArray.get(tmpIncrementEnd) == 0) {
                        textViewEnd.setText((Integer.parseInt(startStr == ""?"0":startStr) + Integer.parseInt(endStr==""?"0":endStr)) + "");
                        textViewStart.setText("");
                        isMove = true;
                        moveNumber += 1;
                        tmpIndexArray.set(tmpIncrementEnd,1);
                    } else if (!"".equals(startStr) && "".equals(endStr)) {
                        textViewStart.setText(endStr);
                        textViewEnd.setText(startStr);
                        isMove = true;
                        moveNumber += 1;
                        Integer tmpValue = tmpIndexArray.get(tmpIncrementStart);
                        tmpIndexArray.set(tmpIncrementStart,tmpIndexArray.get(tmpIncrementEnd));
                        tmpIndexArray.set(tmpIncrementEnd,tmpValue);
                    }
                }
                tmpIncrementStart--;
                j++;
                if (j >= colums - 1) {//移动完一周
                    if (moveNumber > 0) {
                        j = 0;
                        tmpIncrementStart = colums - 2;
                        moveNumber = 0;
                    } else {
                        break;
                    }
                }
            }while (j < colums - 1);
        }
        if (isMove) {
            setShowNumber();
        }
    }

    //游戏结束
    void gameOver() {
        Toast.makeText(getApplicationContext(), "游戏结束！",
                Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(MainActivity.this).setTitle("游戏结束")
                .setMessage("重新开始游戏!")
                .setPositiveButton("开始游戏",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        resetAllView();
                    }
                }).show();//在按键响应事件中显示此对话框
    }
}
