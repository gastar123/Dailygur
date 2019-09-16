package com.example.spidertest.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spidertest.R;
import com.example.spidertest.dto.Comment;

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
            }
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Comment comment);
    }
}
