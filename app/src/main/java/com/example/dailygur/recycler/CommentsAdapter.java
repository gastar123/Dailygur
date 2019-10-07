package com.example.dailygur.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dailygur.R;
import com.example.dailygur.dto.Comment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentHolder> {

    private final List<Comment> commentList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void changeData(List<Comment> commentList) {
        this.commentList.clear();
        this.commentList.addAll(commentList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
        final CommentHolder commentHolder = new CommentHolder(view);
        return commentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.bindHolder(commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        private TextView tvAuthor;
        private TextView tvComment;
        private TextView tvChildren;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvComment = itemView.findViewById(R.id.tvComment);
            tvChildren = itemView.findViewById(R.id.tvChildren);
        }

        public void bindHolder(Comment comment) {
            tvAuthor.setText(comment.getAuthor());
            tvComment.setText(comment.getComment());
            if (comment.getChildren() != null && !comment.getChildren().isEmpty()) {
                tvChildren.setText(String.valueOf(comment.getChildren().size()));
            } else {
                tvChildren.setText("");
            }
            ((ViewGroup.MarginLayoutParams)itemView.getLayoutParams()).leftMargin = comment.getLvl() * 20;

            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    List<Comment> childrenList = comment.getChildren();
                    if (comment.isOpen()) {
                        int count = closeComment(childrenList);
                        notifyItemRangeRemoved(getAdapterPosition() + 1, count);
                        comment.setOpen(false);
                    } else {
                        openComment(childrenList, comment.getLvl() + 1);
                        comment.setOpen(true);
                    }
                }
            });
        }

        private void openComment(List<Comment> childrenList, int lvl) {
            for (Comment child : childrenList) {
                child.setLvl(lvl);
            }
            CommentsAdapter.this.commentList.addAll(getAdapterPosition() + 1, childrenList);
            notifyItemRangeInserted(getAdapterPosition() + 1, childrenList.size());
        }

        private int closeComment(List<Comment> childrenList) {
            int childrenCount = childrenList.size();
            commentList.removeAll(childrenList);
            for (Comment child : childrenList) {
                if (child.isOpen()) {
                    child.setOpen(false);
                    childrenCount += closeComment(child.getChildren());
                }
            }
            return childrenCount;
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Comment comment);
    }
}
