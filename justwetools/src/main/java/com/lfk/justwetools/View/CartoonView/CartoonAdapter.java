package com.lfk.justwetools.View.CartoonView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lfk.justwetools.R;
import com.lfk.justwetools.Utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by liufengkai on 16/2/22.
 */
public class CartoonAdapter extends BaseAdapter {
    private ArrayList<String> userList;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private Context mContext;
    private int width, height;

    public CartoonAdapter(Context context, ArrayList<String> userList) {
        this.userList = userList;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mImageLoader = ImageLoader.getInstance(mContext,4, ImageLoader.Type.LIFO);
    }

    private class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.cartoon_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.cartoon_img);

            WindowManager manager = (android.view.WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(manager.getDefaultDisplay().getWidth(),
                    manager.getDefaultDisplay().getHeight());

            holder.imageView.setLayoutParams(params);

            holder.textView = (TextView) convertView.findViewById(R.id.cartoon_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(String.valueOf(position));

        mImageLoader.loadImage(userList.get(position),
                holder.imageView);

        holder.textView.setText("");

        return convertView;
    }
}
