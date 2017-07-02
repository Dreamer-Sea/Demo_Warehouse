package com.example.webmusictest.fragments.BingPicture;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.webmusictest.R;
import com.example.webmusictest.beans.BingPicture.PictureItem;
import com.example.webmusictest.threads.ReceiveJSON;
import com.example.webmusictest.utils.DownloadUtils;
import com.example.webmusictest.utils.ParseJsonWithGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2017/6/21.
 */

public class PictureFragment extends Fragment {

    private List<PictureItem> pictureList = new ArrayList<PictureItem>();
    private final String URL = "http://route.showapi.com/1377-1";
    private PictureAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bing_picture, container, false);
        RecyclerView pictureRecyclerView = (RecyclerView) view.findViewById(R.id.bingpicture_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        pictureRecyclerView.setLayoutManager(layoutManager);

        try {
            ReceiveJSON rx = new ReceiveJSON(URL);
            rx.start();
            rx.join();
            pictureList.addAll(ParseJsonWithGson.parseBingPictureJson(rx.getRes()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new PictureAdapter(pictureList);
        pictureRecyclerView.setAdapter(adapter);

        //下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.bingpicture_swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    ReceiveJSON rx = new ReceiveJSON(URL);
                    rx.start();
                    rx.join();
                    pictureList = ParseJsonWithGson.parseBingPictureJson(rx.getRes());
                    adapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder>{

        private DownloadUtils downloadUtils;
        private List<PictureItem> mPictureList;

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView bingImage;

            public ViewHolder(View itemView) {
                super(itemView);
                bingImage = (ImageView) itemView.findViewById(R.id.picture_image);
            }
        }

        public PictureAdapter(List<PictureItem> mPictureList) {
            this.mPictureList = mPictureList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);

            holder.bingImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    downloadUtils = new DownloadUtils(getContext());
                    int position = holder.getAdapterPosition();
                    PictureItem p = mPictureList.get(position);
                    String[] t = p.getPic().split("/");
                    String name = t[t.length-1];
                    downloadUtils.downloadFile(p.getPic(), name);
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            PictureItem pictureItem = mPictureList.get(position);
            //使用Glide库，从指定URL中读取图片，并放入Holder的bingImage成员变量中
            Glide.with(getContext()).load(Uri.parse(pictureItem.getPic())).into(holder.bingImage);
        }

        @Override
        public int getItemCount() {
            return mPictureList.size();
        }
    }
}

