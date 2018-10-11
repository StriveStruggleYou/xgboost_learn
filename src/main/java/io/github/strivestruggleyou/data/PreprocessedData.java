package io.github.strivestruggleyou.data;

import io.github.strivestruggleyou.entity.ListDataCover;
import io.github.strivestruggleyou.enums.FeatureEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PreprocessedData {
    public static void main(String args[]) {

        File file = new File(
                "/Users/manager/yunpian-blackcat/src/main/resources/text/dsjtzs_txfz_training.txt");

        File newFile = new File(
                "/Users/manager/yunpian-blackcat/src/main/resources/text/tezheng33.txt");

        FileOutputStream fileOutputStream=null;

        BufferedReader reader = null;
        try {
            fileOutputStream = new FileOutputStream(newFile);

            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));
            String str = reader.readLine();
            while (str != null) {
                ListDataCover listDataCover = new ListDataCover();
//                String str = "1 353,2607,349;367,2607,376;388,2620,418;416,2620,442;500,2620,493;584,2620,547;675,2620,592;724,2620,643;780,2620,694;822,2620,742;850,2633,793;885,2633,844;934,2633,895;983,2633,946;1060,2633,1006;1144,2633,1063;1235,2633,1093;1284,2633,1144;1312,2633,1210;1326,2633,1243;1333,2633,1354;1354,2633,1408;1375,2646,1450;1452,2659,1492;1473,2672,1543;1480,2672,1954;1487,2672,2050;1494,2672,2233;1501,2672,2245;1515,2672,2293;1515,2659,2347;1522,2659,2554;1529,2659,2722;1543,2659,2773;1550,2659,2794;1564,2659,2842;1578,2659,2893;1592,2659,2944;1599,2659,2992;1606,2659,3043;1613,2659,3100;1620,2659,3178;1634,2659,3220;1648,2659,3244;1669,2659,3301;1676,2646,3445;1690,2646,3490;1718,2633,3547;1732,2633,3592;1739,2633,3646;1732,2633,4930;1725,2633,4981;1718,2620,6466;1704,2620,6562;1704,2607,6598;1697,2607,7237; 1420.5,202 1";

                //字符进行处理
                String[] strs = str.split(" ");
                if (strs.length != 4) {
                    return;
                }
                listDataCover.setLable(strs[3]);

                String points[] = strs[1].split(";");

                if (points.length == 0) {
                    return;
                }

                List<Double> xList = new ArrayList<>();

                List<Double> yList = new ArrayList<>();

                List<Double> tList = new ArrayList<>();

                for (String point : points) {
                    String mouses[] = point.split(",");
                    if (mouses.length != 3) {
                        continue;
                    }
                    xList.add(Double.valueOf(mouses[0]));
                    yList.add(Double.valueOf(mouses[1]));
                    tList.add(Double.valueOf(mouses[2]));
                }

                listDataCover.setXList(xList);

                listDataCover.setYList(yList);

                listDataCover.setTList(tList);

                StringBuffer sb = new StringBuffer();

                //设置 lable;
                sb.append(listDataCover.getLable()).append(" ");
                //设置 count
                buildStringBuffer(sb, FeatureEnum.COUNT.getIndex(), listDataCover.getCount());

                buildStringBuffer(sb, FeatureEnum.X_MIN.getIndex(), listDataCover.getXMin());

                buildStringBuffer(sb, FeatureEnum.X_MAX.getIndex(), listDataCover.getXMax());

                buildStringBuffer(sb, FeatureEnum.X_RATIO.getIndex(), listDataCover.getXRatio());

                buildStringBuffer(sb, FeatureEnum.Y_MIN.getIndex(), listDataCover.getYMin());

                buildStringBuffer(sb, FeatureEnum.Y_MAX.getIndex(), listDataCover.getYMax());

                buildStringBuffer(sb, FeatureEnum.X_DIFF_STD.getIndex(),
                        listDataCover.getXDiffStd());

                buildStringBuffer(sb, FeatureEnum.X_DIFF_MAX.getIndex(),
                        listDataCover.getXDiffMax());

                buildStringBuffer(sb, FeatureEnum.X_DIFF_MIN.getIndex(),
                        listDataCover.getXDiffMin());

                buildStringBuffer(sb, FeatureEnum.X_DIFF_SKEW.getIndex(),
                        listDataCover.getXDiffSkew());

                buildStringBuffer(sb, FeatureEnum.Y_DIFF_STD.getIndex(),
                        listDataCover.getYDiffStd());

                buildStringBuffer(sb, FeatureEnum.X_BACK_NUM.getIndex(),
                        listDataCover.getXBackNum());

                buildStringBuffer(sb, FeatureEnum.T_DIFF_MEAN.getIndex(),
                        listDataCover.getTDiffMean());

                buildStringBuffer(sb, FeatureEnum.T_DIFF_STD.getIndex(),
                        listDataCover.getTDiffStd());

                buildStringBuffer(sb, FeatureEnum.DURATION_MEAN.getIndex(),
                        listDataCover.getDurationMean());
                buildStringBuffer(sb, FeatureEnum.TIME_HALF.getIndex(),
                        listDataCover.getTimeHalf());

                buildStringBuffer(sb, FeatureEnum.XY_DIFF_MAX.getIndex(),
                        listDataCover.getXYDiffMax());

                buildStringBuffer(sb, FeatureEnum.VXY_STD.getIndex(), listDataCover.getVxyStd());

                buildStringBuffer(sb, FeatureEnum.VXY_MEAN.getIndex(), listDataCover.getVxyMean());

                buildStringBuffer(sb, FeatureEnum.VXY_LAST.getIndex(), listDataCover.getVxyLast());

                buildStringBuffer(sb, FeatureEnum.VXY_FIRST.getIndex(),
                        listDataCover.getVxyFirst());

                buildStringBuffer(sb, FeatureEnum.VXY_DIFF_MEDIAN.getIndex(),
                        listDataCover.getVxyDiffMedian());

                buildStringBuffer(sb, FeatureEnum.VXY_DIFF_MEAN.getIndex(),
                        listDataCover.getVxyDiffMean());

                buildStringBuffer(sb, FeatureEnum.VXY_DIFF_MAX.getIndex(),
                        listDataCover.getVxyDiffMax());

                buildStringBuffer(sb, FeatureEnum.VXY_DIFF_STD.getIndex(),
                        listDataCover.getVxyDiffStd());

                buildStringBuffer(sb, FeatureEnum.ANGLE_STD.getIndex(),
                        listDataCover.getAngleStd());

                buildStringBuffer(sb, FeatureEnum.ANGLE_KURT.getIndex(),
                        listDataCover.getAngleKurt());

                buildStringBuffer(sb, FeatureEnum.ANGLE_DIFF_MEAN.getIndex(),
                        listDataCover.getAngleDiffMean());

                buildStringBuffer(sb, FeatureEnum.ANGLE_DIFF_STD.getIndex(),
                        listDataCover.getAngleDiffStd());

                buildStringBuffer(sb, FeatureEnum.XY_DUPLICATE_CNT.getIndex(),
                        listDataCover.getXyDuplicateCnt());

                sb.append("\r\n");

                fileOutputStream.write(sb.toString().getBytes());

                System.out.println(sb.toString());

                str = reader.readLine();

            }
            fileOutputStream.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void buildStringBuffer(StringBuffer sb, int index, double result) {
        sb.append(index).append(":").append(result).append(" ");
    }
}
