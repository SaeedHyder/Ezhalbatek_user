package com.ingic.ezhalbatek.ui.binders;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.ingic.ezhalbatek.R;
import com.ingic.ezhalbatek.entities.SubServiceEnt;
import com.ingic.ezhalbatek.helpers.BasePreferenceHelper;
import com.ingic.ezhalbatek.interfaces.onDeleteImage;
import com.ingic.ezhalbatek.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.ingic.ezhalbatek.ui.views.AnyEditTextView;
import com.ingic.ezhalbatek.ui.views.AnyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 5/23/2017.
 */

public class SelectedJobBinder extends RecyclerViewBinder<SubServiceEnt> {
    BasePreferenceHelper preferenceHelper;
    private onDeleteImage onDeleteImage;

    public SelectedJobBinder(onDeleteImage onDeleteImage, BasePreferenceHelper preferenceHelper) {
        super(R.layout.row_item_selectedjobs);
        this.onDeleteImage = onDeleteImage;
        this.preferenceHelper = preferenceHelper;
    }


    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new SelectedJobsViewHolder(view);
    }

    @Override
    public void bindView(SubServiceEnt entity, final int position, Object viewHolder, Context context) {
        SelectedJobsViewHolder holder = (SelectedJobsViewHolder) viewHolder;
        holder.txtJobselectedtext.setText(entity.getTitle()+"");
        holder.txt_jobselectedAmount.setText(entity.getAmount()+"");

        holder.edtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    entity.setQuantity(holder.edtQuantity.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteImage.OnDeleteJobs(position);
            }
        });
    }


    public static class SelectedJobsViewHolder extends BaseViewHolder {
        @BindView(R.id.txt_jobselectedtext)
        AnyTextView txtJobselectedtext;
        @BindView(R.id.txt_jobselectedAmount)
        AnyTextView txt_jobselectedAmount;
        @BindView(R.id.delete_text)
        ImageView deleteText;
        @BindView(R.id.edtQuantity)
        AnyEditTextView edtQuantity;

        SelectedJobsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
