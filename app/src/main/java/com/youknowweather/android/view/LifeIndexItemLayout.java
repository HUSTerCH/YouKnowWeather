package com.youknowweather.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.youknowweather.android.R;

public class LifeIndexItemLayout extends LinearLayout {

    private ImageView indexImg;
    private TextView indexText;
    private TextView indexDesc;

    public LifeIndexItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.life_index_item,this);
        //获取布局中的控件
        indexImg = (ImageView) findViewById(R.id.life_index_item_img);
        indexText = (TextView) findViewById(R.id.life_index_item_text);
        indexDesc = (TextView) findViewById(R.id.life_index_item_desc);


        //加载属性文件
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LifeIndexItemLayout);

        setImageView(a.getResourceId(R.styleable.LifeIndexItemLayout_index_image,10000));
        setTextView(a.getString(R.styleable.LifeIndexItemLayout_index_desc));
        setIndexText(a.getString(R.styleable.LifeIndexItemLayout_index_text));
    }

    public void setImageView(int im_id) {
        this.indexImg.setImageResource(im_id);
    }
    public void setTextView(String indexDesc) {
        this.indexDesc.setText(indexDesc);
    }
    public void setIndexText(String indexText) {
        this.indexText.setText(indexText);
    }
}
