package io.github.strivestruggleyou.enums;

public enum FeatureEnum {

    COUNT(1, "count"),

    X_MIN(2, "x_min"),

    X_MAX(3, "x_max"),

    X_RATIO(4, "x_ratio"),

    //y坐标的最小值
    Y_MIN(5, "y_min"),

    //y坐标的最大值
    Y_MAX(6, "y_max"),

    //x坐标一阶差分后标准差
    X_DIFF_STD(7, "xDiffStd"),

    //x坐标一阶差分后的最大值
    X_DIFF_MAX(8, "x_diff_max"),

    //x坐标一阶差分后的最小值
    X_DIFF_MIN(9, "x_diff_min"),

    //x坐标一阶差分后的偏度
    X_DIFF_SKEW(10, "x_diff_skew"),

    //y坐标一阶差分后的平均值
    Y_DIFF_STD(11, "y_diff_std"),

    //x坐标回退标记
    X_BACK_NUM(12, "x_back_num"),

    //时间轴t差分后的均值
    T_DIFF_MEAN(13, "t_diff_mean"),

    //时间进行一阶差分后的标准差

    T_DIFF_STD(14, "t_diff_std"),

    //总时间比上x轴总路程
    DURATION_MEAN(15, "duration_mean"),

    //走一半路花的时间
    TIME_HALF(16, "timeHalf"),

    //所有相邻样本点距离最大值
    XY_DIFF_MAX(17, "xy_diff_max"),

    //相邻样本点速度的标准差
    VXY_STD(18, "vxy_std"),

    //相邻样本点速度的平均值
    VXY_MEAN(19, "vxy_mean"),

    //轨迹最后两个样本点的速度
    VXY_LAST(20, "vxy_last"),

    //轨迹最开始两个样本点的速度
    VXY_FIRST(21, "vxy_first"),

    //速度序列一阶差分的中值
    VXY_DIFF_MEDIAN(22, "vxy_diff_median"),

    //速度序列一阶差分的平均值
    VXY_DIFF_MEAN(23, "vxy_diff_mean"),

    //速度序列一阶差分的最大值
    VXY_DIFF_MAX(24, "vxy_diff_max"),

    //速度序列一阶差分的标准差
    VXY_DIFF_STD(25, "vxy_diff_std"),

    //相邻点构成的角度序列的标准差
    ANGLE_STD(26, "angle_std"),

    //相邻轨迹点的角度序列的峰度
    ANGLE_KURT(27, "angle_kurt"),

    //角度序列一阶差分的均值
    ANGLE_DIFF_MEAN(28, "angle_diff_mean"),

    //角度序列一阶差分的标准差
    ANGLE_DIFF_STD(29, "angle_diff_std"),


    //xy 坐标重复次数
    XY_DUPLICATE_CNT(32, "xy_duplicate_cnt");


    FeatureEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }


    int index;

    String desc;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}