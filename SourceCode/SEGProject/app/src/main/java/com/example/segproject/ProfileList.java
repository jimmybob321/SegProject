
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

public class ProfileList extends ArrayAdapter<Profile> {
    private Activity context;
    private List<Profile> profiles;

    public ProfileList(Activity context, List<Profile> profiles) {
        super(context, R.layout.layout_profile_list, profiles);
        this.context = context;
        this.profiles = profiles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_profile_list, null, true);

        TextView txtName = (TextView) listViewItem.findViewById(R.id.txtName);
        TextView txtScore = (TextView) listViewItem.findViewById(R.id.txtScore);

        Profile profile = profiles.get(position);
        txtName.setText(new StringBuilder("Name: ").append(profile.get_name()));
        txtScore.setText(new StringBuilder("Score: ").append(profile.get_score()));
        return listViewItem;
    }

}
