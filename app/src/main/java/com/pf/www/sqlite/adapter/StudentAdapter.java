package com.pf.www.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pf.www.sqlite.R;
import com.pf.www.sqlite.bean.Student;

import java.util.List;

/**
 * Created by Leo on 2016/12/30.
 */

public class StudentAdapter extends BaseAdapter {

    private List<Student> students;
    private Context context;

    public StudentAdapter(Context context,List<Student> students) {
        super();
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        }
        ImageView imageView= (ImageView) view.findViewById(R.id.image);
        TextView tvName= (TextView) view.findViewById(R.id.name);
        TextView tvGrade= (TextView) view.findViewById(R.id.grade);

        if (students.get(i).getId()%2==0){
            imageView.setImageResource(R.mipmap.girl1);
        }else {
            imageView.setImageResource(R.mipmap.boy2);
        }
        tvName.setText(" 姓名 "+students.get(i).getName());
        tvGrade.setText(" 分数 "+students.get(i).getGrade());
        return view;
    }
}
