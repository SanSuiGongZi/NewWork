package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.WithState;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterWith extends RecyclerView.Adapter<MyAdapterWith.ViewHolder> {

    private Context context;
    private ArrayList<WithState.ResultBean.ActivitiesBean> mList = new ArrayList<>();
    private MediaPlayer mPlayer;

    public MyAdapterWith(Context context) {
        this.context = context;
    }

    public void addList(List<WithState.ResultBean.ActivitiesBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_state, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final WithState.ResultBean.ActivitiesBean bean = mList.get(i);

        if (bean != null) {

            viewHolder.mTime.setText(bean.getDate());

            if (!TextUtils.isEmpty(bean.getAudioURL())) {
                viewHolder.mCardVoice.setVisibility(GridView.VISIBLE);
                viewHolder.mIvVoice.setVisibility(GridView.VISIBLE);
                viewHolder.mCardDynamic.setVisibility(GridView.GONE);
                viewHolder.mIvDynamic.setVisibility(GridView.GONE);

                viewHolder.mUpDataState.setText(context.getResources().getString(R.string.update_voice));
                viewHolder.audioTime.setText(bean.getAudioLength() + "〞");
                viewHolder.mTvComment.setText(bean.getReplyCount() + "");
                viewHolder.mTvPraise.setText(bean.getLikeCount() + "");

                if (bean.isIsLiked()) {
                    Glide.with(context).load(R.mipmap.praise).into(viewHolder.praise);
                } else {
                    Glide.with(context).load(R.mipmap.praise_unselected).into(viewHolder.praise1);
                }

                //开始监听
                viewHolder.mIv_Start_playing.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        try {
                            mPlayer = MediaPlayer.create(context, Uri.parse(bean.getAudioURL()));
                            mPlayer.start();
                            viewHolder.mIv_Start_playing.setVisibility(View.GONE);
                            viewHolder.mIv_Stop.setVisibility(View.VISIBLE);
                            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

                //停止监听
                viewHolder.mIv_Stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.mIv_Start_playing.setVisibility(View.VISIBLE);
                        viewHolder.mIv_Stop.setVisibility(View.GONE);
                        mPlayer.stop();
                        notifyItemChanged(i);
                    }
                });

            } else {
                viewHolder.mCardVoice.setVisibility(GridView.GONE);
                viewHolder.mIvVoice.setVisibility(GridView.GONE);
                viewHolder.mCardDynamic.setVisibility(GridView.VISIBLE);
                viewHolder.mIvDynamic.setVisibility(GridView.VISIBLE);

                viewHolder.mUpDataState.setText(context.getResources().getString(R.string.send_state));
                viewHolder.mTvStr.setText(bean.getContent());

                if (bean.getImages().size() > 0) {
                    Glide.with(context).load(bean.getImages().get(0)).into(viewHolder.mIvState);

                    viewHolder.mIvState.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           initpopup(i);
                        }
                    });

                }else {
                    viewHolder.mIvState.setVisibility(View.GONE);
                }

                viewHolder.mTvComment1.setText(bean.getReplyCount() + "");
                viewHolder.mTvPraise1.setText(bean.getLikeCount() + "");

            }

            if (i > 1) {
                viewHolder.mIvVoice.setVisibility(GridView.GONE);
                viewHolder.mIvDynamic.setVisibility(GridView.GONE);
            }

        }
    }

    private void initpopup(int position) {
        //加载popupwindow布局，布局中为下文控件（此控件类似于ImageView）
        View view = View.inflate(context, R.layout.item_popup, null);
        //找到布局中控件的id
        PhotoView image = view.findViewById(R.id.mPhoto);
        //给他设置初始化图片时是不进行缩放的
        image.enable();
        //将接口回调穿过来的Url给Popupwindow中的控件设置上
        Glide.with(context)
                .load(mList.get(position).getImages().get(0))
                .into(image);
        final PopupWindow pp = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        pp.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.c_60s)));
        pp.setOutsideTouchable(true);
        pp.showAtLocation(view, Gravity.CENTER, 0, 0);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pp.dismiss();
            }
        });

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mTime)
        TextView mTime;
        @BindView(R.id.mUpDataState)
        TextView mUpDataState;
        @BindView(R.id.audioTime)
        TextView audioTime;
        @BindView(R.id.mTv_praise)
        TextView mTvPraise;
        @BindView(R.id.mTv_comment)
        TextView mTvComment;
        @BindView(R.id.mCard_voice)
        CardView mCardVoice;
        @BindView(R.id.mTv_str)
        TextView mTvStr;
        @BindView(R.id.mIv_state)
        ImageView mIvState;
        @BindView(R.id.mTv_praise1)
        TextView mTvPraise1;
        @BindView(R.id.mTv_comment1)
        TextView mTvComment1;
        @BindView(R.id.mCard_dynamic)
        CardView mCardDynamic;
        @BindView(R.id.mIv_dynamic)
        ImageView mIvDynamic;
        @BindView(R.id.mIv_voice)
        ImageView mIvVoice;
        @BindView(R.id.praise)
        ImageView praise;
        @BindView(R.id.praise1)
        ImageView praise1;
        @BindView(R.id.mIv_Start_playing)
        ImageView mIv_Start_playing;
        @BindView(R.id.mIv_Stop)
        ImageView mIv_Stop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
