package com.example.victorina;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    private final LayoutInflater inflater;
    private static List<String> answerList;

    public AnswerAdapter(Context context, List<String> answerList) {
        this.inflater = LayoutInflater.from(context);
        this.answerList = answerList;
    }



    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.AnswerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String answ  = answerList.get(position);
        Log.d("vic777" , answ);
        holder.answer.setText(answ);
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public List<String> getAnswerList(){
        return answerList;
    }

   public static class AnswerViewHolder extends RecyclerView.ViewHolder {



        EditText answer;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.answer = itemView.findViewById(R.id.item_answer);

            answer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() != 0){
                        answerList.set(getAdapterPosition(), charSequence.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }


    }
}