package com.example.constellation.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constellation.R;
import com.example.constellation.activity.EditActivity;
import com.example.constellation.enbity.Wish;
import com.example.constellation.db.WishDbOpenHelper;
import com.example.constellation.utils.ToastUtil;

import java.util.List;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.WishViewHolder> {
    private List<Wish> mBeanList;
    private Toolbar toolbar;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private WishDbOpenHelper mWishDbOpenHelper;

    public WishAdapter(Context context, List<Wish> mBeanList){
        this.mBeanList = mBeanList;
        this.mContext = context;
        mWishDbOpenHelper = new WishDbOpenHelper(mContext);
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=mLayoutInflater.inflate(R.layout.list_item_layout,parent,false);
        WishViewHolder wishViewHolder = new WishViewHolder(view);
        return wishViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        Wish wish = mBeanList.get(position);
        holder.wishcontent.setText(wish.getWishcontent());

        holder.rlContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按弹出弹窗 删除或者编辑
                Dialog dialog = new Dialog(mContext, android.R.style.ThemeOverlay_Material_Dialog_Alert);
                View dialogView = mLayoutInflater.inflate(R.layout.list_item_dialog_layout, null);
                TextView tvDelete = dialogView.findViewById(R.id.delete);
                TextView tvEdit = dialogView.findViewById(R.id.edit);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int row = mWishDbOpenHelper.deleteFromDbById(wish.getId());
                        if (row > 0) {
                            removeData(holder.getAdapterPosition());

                            ToastUtil.toastShort(mContext,"删除成功！");
                        }else {
                            ToastUtil.toastShort(mContext,"删除失败！");
                        }
                        dialog.dismiss();
                    }
                });
                tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, EditActivity.class);
                        intent.putExtra("wish", wish);
                        mContext.startActivity(intent);
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(dialogView);

                dialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }
    public void refreshData(List<Wish> wish) {
        this.mBeanList = wish;
        notifyDataSetChanged();
    }
    public void removeData(int pos) {
        mBeanList.remove(pos);
        notifyItemRemoved(pos);
    }


    class WishViewHolder extends RecyclerView.ViewHolder{

        TextView wishcontent;
        ViewGroup rlContainer;
        public WishViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wishcontent = itemView.findViewById(R.id.wishcontent);
            this.rlContainer = itemView.findViewById(R.id.rl_item_container);

        }
    }
}
