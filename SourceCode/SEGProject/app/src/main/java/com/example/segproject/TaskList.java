package com.example.segproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Jarod Lee on 2017-12-01.
 */

public class TaskList extends ArrayAdapter<Task> {
    private Activity context;
    List<Task> tasks;

    public TaskList(Activity context, List<Task> tasks) {
        super(context, R.layout.layout_task_list, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_task_list, null, true);

        TextView txtName = (TextView) listViewItem.findViewById(R.id.txtName);
        TextView txtDate = (TextView) listViewItem.findViewById(R.id.txtDate);
        TextView txtReward = (TextView) listViewItem.findViewById(R.id.txtReward);

        Task task = tasks.get(position);
        txtName.setText(new StringBuilder("Name: ").append(task.getTitle()));
        txtDate.setText(new StringBuilder("Due Date:").append(task.getDate()));
        txtReward.setText(new StringBuilder("Reward: ").append(task.getReward()));
        return listViewItem;
    }

}
