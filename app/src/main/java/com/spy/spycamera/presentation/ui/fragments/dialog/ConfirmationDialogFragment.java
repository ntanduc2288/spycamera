package com.spy.spycamera.presentation.ui.fragments.dialog;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.spy.spycamera.R;
import com.spy.spycamera.presentation.util.Constants;

import butterknife.BindView;

/**
 * @author Duc Nguyen
 * @version 1.0
 * @since 6/27/16
 */
@SuppressLint("ValidFragment")
public class ConfirmationDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    @BindView(R.id.title)
    AppCompatTextView lblTitle;
    @BindView(R.id.content)
    AppCompatTextView lblContent;
    @BindView(R.id.button_negative)
    AppCompatButton btnNegative;
    @BindView(R.id.button_positive)
    AppCompatButton btnPositive;
    @BindView(R.id.close)
    ImageButton btnClose;
    @BindView(R.id.customview_container)
    FrameLayout flCustomviewContainer;
    @BindView(R.id.view_container)
    LinearLayout lnContainer;
    @BindView(R.id.confirm_bottom_container)
    LinearLayout lnBottomContainer;

    View.OnClickListener positiveListenter;
    View.OnClickListener negativeListenter;
    View.OnClickListener closeButtonListenter;

    String title, content = Constants.EMPTY_STRING;
    private String positiveText;
    private String negativeText;
    private ViewGroup customView;
    private Drawable bgDrawable;
    private boolean isCancelable;
    private boolean hideCloseButton;

    @SuppressLint("ValidFragment")
    private ConfirmationDialogFragment(Builder builder) {
        content = builder.content;
        positiveListenter = builder.positiveListenter;
        negativeListenter = builder.negativeListenter;
        closeButtonListenter = builder.closeButtonListenter;
        title = builder.title;
        positiveText = builder.positiveText;
        negativeText = builder.negativeText;
        customView = builder.customView;
        isCancelable = builder.isCancelable;
        hideCloseButton = builder.hideCloseButton;

    }

    @Override
    public int getLayoutResource() {
        return R.layout.confirmation_dialog;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        if (negativeListenter == null) {
            btnNegative.setVisibility(View.GONE);
        } else {
            if (!TextUtils.isEmpty(negativeText))
                btnNegative.setText(negativeText);
            btnNegative.setOnClickListener(this);
            btnNegative.setVisibility(View.VISIBLE);
        }

        if (positiveListenter == null) {
            btnPositive.setVisibility(View.GONE);
        } else {
            if (!TextUtils.isEmpty(positiveText))
                btnPositive.setText(positiveText);
            btnPositive.setOnClickListener(this);
            btnPositive.setVisibility(View.VISIBLE);
        }

        if (positiveListenter == null && negativeListenter == null) {
            lnBottomContainer.setVisibility(View.GONE);
        } else {
            lnBottomContainer.setVisibility(View.VISIBLE);
        }

        lblContent.setText(content);

        if (!TextUtils.isEmpty(title)) {
            lblTitle.setText(title);
            lblTitle.setVisibility(View.VISIBLE);
        } else {
            lblTitle.setVisibility(View.GONE);
        }

        if (customView != null) {
            if (customView.getParent() != null) {
                ((ViewGroup) customView.getParent()).removeView(customView);
            }
            flCustomviewContainer.removeAllViews();
            flCustomviewContainer.addView(customView);
        }

        btnClose.setVisibility(hideCloseButton ? View.GONE : View.VISIBLE);
        btnClose.setOnClickListener(this);

        setCancelable(isCancelable);
    }

    @Override
    public boolean shouldHideDialogTitle() {
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_negative:
                dismiss();
                if (negativeListenter != null)
                    negativeListenter.onClick(v);

                break;
            case R.id.button_positive:
                if (positiveListenter != null)
                    positiveListenter.onClick(v);
                break;
            case R.id.close:
                if (closeButtonListenter != null) {
                    closeButtonListenter.onClick(v);
                } else {
                    dismiss();
                }
                break;
        }
    }

    public static IContent builder() {
        return new Builder();
    }

    public interface IBuild {

        IBuild withPositiveListener(View.OnClickListener positiveListener);

        IBuild withNegativeListener(View.OnClickListener negativeListener);

        IBuild withCloseButtonListener(View.OnClickListener closeButtonListenter);

        IBuild setPositiveText(String positiveText);

        IBuild setNegativeText(String negativeText);

        IBuild withTitle(String val);

        IBuild setCancelable(boolean cancelable);

        IBuild withCustomView(ViewGroup customView);

        IBuild withBgDrawable(Drawable bgDrawable);

        IBuild hideCloseButton(boolean hide);

        ConfirmationDialogFragment build();
    }

    public interface IContent {
        IBuild withContent(String val);
    }

    public static final class Builder implements IContent, IBuild {
        View.OnClickListener positiveListenter;
        View.OnClickListener negativeListenter;
        View.OnClickListener closeButtonListenter;
        private String positiveText;
        private String negativeText;
        private String title;
        private String content;
        private ViewGroup customView;
        private Drawable bgDrawable;
        private boolean isCancelable = true;
        private boolean hideCloseButton = false;

        private Builder() {
        }

        @Override
        public IBuild withContent(String val) {
            content = val;
            return this;
        }

        @Override
        public IBuild withPositiveListener(View.OnClickListener positiveListener) {
            this.positiveListenter = positiveListener;
            return this;
        }

        @Override
        public IBuild withNegativeListener(View.OnClickListener negativeListener) {
            this.negativeListenter = negativeListener;
            return this;
        }

        @Override
        public IBuild withCloseButtonListener(View.OnClickListener closeButtonListenter) {
            this.closeButtonListenter = closeButtonListenter;
            return this;
        }

        @Override
        public IBuild setPositiveText(String positiveText) {
            this.positiveText = positiveText;
            return this;
        }

        @Override
        public IBuild setNegativeText(String negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        @Override
        public IBuild withTitle(String val) {
            title = val;
            return this;
        }

        @Override
        public IBuild withCustomView(ViewGroup customView) {
            this.customView = customView;
            return this;
        }

        @Override
        public IBuild withBgDrawable(Drawable bgDrawable) {
            this.bgDrawable = bgDrawable;
            return this;
        }

        @Override
        public IBuild hideCloseButton(boolean hide) {
            this.hideCloseButton = hide;
            return this;
        }

        @Override
        public IBuild setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public ConfirmationDialogFragment build() {
            return new ConfirmationDialogFragment(this);
        }
    }

}
