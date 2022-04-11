package com.youknowweather.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.youknowweather.android.R;

public class FutureWeaItemLayout extends LinearLayout {

    private ImageView weaImg;
    private TextView weaDesc;
    private TextView weaDate;
    private TextView weaTemp;

    public FutureWeaItemLayout(Context context,AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.seven_day_future_wea_item,this);

        weaImg = (ImageView) findViewById(R.id.future_wea_img);
        weaDesc = (TextView) findViewById(R.id.future_wea_desc);
        weaDate = (TextView) findViewById(R.id.future_wea_date);
        weaTemp = (TextView) findViewById(R.id.future_wea_temp);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.FutureWeaItemLayout);
//        setWeaImg(a.getString(R.styleable.FutureWeaItemLayout_future_img));
//        setWeaDesc(a.getString(R.styleable.FutureWeaItemLayout_future_desc));
//        setWeaDate(a.getString(R.styleable.FutureWeaItemLayout_future_date));
//        setWeaTemp(a.getFloat(0,R.styleable.FutureWeaItemLayout_future_temp_min),
//                a.getFloat(0,R.styleable.FutureWeaItemLayout_future_temp_max));
    }

    public void setWeaImg(String skycon) {
        Log.e("天气现象",skycon);
        this.weaImg.setImageResource(SkyEnum.valueOf(skycon).toDo().resID);
    }

    public void setWeaDate(String date) {
        this.weaDate.setText(date.substring(0,10));
    }
    public void setWeaTemp(int min,int max) {
        this.weaTemp.setText(String.format("%s/%s °C", max, min));
    }
//
    public void setWeaDesc(String skycon) {
        this.weaDesc.setText(SkyEnum.valueOf(skycon).toDo().desc);
    }

    public static class WeaImgAndDesc {
        private int resID;
        private String desc;
        WeaImgAndDesc(int resID,String desc) {
            this.desc = desc;
            this.resID = resID;
        }

        public int getResID() {
            return resID;
        }

        public void setResID(int resID) {
            this.resID = resID;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }



    enum SkyEnum {
        CLEAR_DAY {
            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_clear_day,"晴");
            }
        },
        CLEAR_NIGHT {
            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_clear_night,"晴");
            }
        },
        PARTLY_CLOUDY_DAY {
            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_partly_cloud_day,"多云");
            }
        },
        PARTLY_CLOUDY_NIGHT {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_partly_cloud_night,"多云");
            }
        },
        CLOUDY {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_cloudy,"阴");
            }
        },
        LIGHT_HAZE {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_light_haze,"轻度雾霾");
            }
        },
        MODERATE_HAZE {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_moderate_haze,"中度雾霾");
            }
        },
        HEAVY_HAZE {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_heavy_haze,"重度雾霾");
            }
        },
        LIGHT_RAIN {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_light_rain,"小雨");
            }
        },
        MODERATE_RAIN {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_moderate_rain,"中雨");
            }
        },
        HEAVY_RAIN {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_heavy_rain,"大雨");
            }
        },
        STORM_RAIN {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_storm_rain,"暴雨");
            }
        },
        FOG {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_fog,"雾");
            }
        },
        LIGHT_SNOW {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_light_snow,"小雪");
            }
        },

        MODERATE_SNOW {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_moderate_snow,"中雪");
            }
        },
        HEAVY_SNOW {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_heavy_snow,"大雪");
            }
        },
        STORM_SNOW {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_heavy_snow,"暴雪");
            }
        },
        DUST {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_fog,"浮尘");
            }
        },
        SAND {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_fog,"沙尘");
            }
        },
        WIND {

            public WeaImgAndDesc toDo() {
                return new WeaImgAndDesc(R.drawable.ic_wind,"大风");
            }
        };
        public abstract WeaImgAndDesc toDo();
    }
}

