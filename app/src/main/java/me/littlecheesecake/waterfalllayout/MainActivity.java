package me.littlecheesecake.waterfalllayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.littlecheesecake.waterfalllayout.view.StretchyImageView;
import me.littlecheesecake.waterfalllayoutview.MultiColumnListView;

public class MainActivity extends AppCompatActivity {
    private WFAdapter mAdapter;
    private MultiColumnListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (MultiColumnListView) findViewById(R.id.list);
        List<Integer> imageList = new ArrayList<>();
        Integer[] list = new Integer[]{
                R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3,
                R.mipmap.pic4, R.mipmap.pic5, R.mipmap.pic6,
                R.mipmap.pic7, R.mipmap.pic8, R.mipmap.pic9
        };
        Collections.addAll(imageList, list);
        mAdapter = new WFAdapter(this, imageList);
        listView.setAdapter(mAdapter);
    }

    private static class WFAdapter extends ArrayAdapter<Integer> {
        private Activity context;
        private List<Integer> imageList;

        public WFAdapter(Activity context, List<Integer> imageList) {
            super(context, R.layout.sample_item, imageList);
            this.imageList = imageList;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                view = inflater.inflate(R.layout.sample_item, null);

                final Holder holder = new Holder();
                holder.imageView = (StretchyImageView) view.findViewById(R.id.sample_item_image);

                view.setTag(holder);
            } else {
                view =convertView;
            }

            Holder holder = (Holder) view.getTag();

            Picasso.with(context)
                    .load(imageList.get(position))
                    .placeholder(R.drawable.empty_image)
                    .tag(context)
                    .into(holder.imageView);

            return view;
        }

        private class Holder {
            public StretchyImageView imageView;
        }
    }
}
