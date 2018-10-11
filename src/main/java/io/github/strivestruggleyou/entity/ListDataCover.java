package io.github.strivestruggleyou.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;


@Data
public class ListDataCover {


    List<Double> xList;

    List<Double> yList;

    List<Double> tList;

    String lable;


    public double getCount() {
        return xList.size();
    }

    public double getXMin() {
        double xMin = Integer.MAX_VALUE;
        for (Double x : xList) {
            if (x < xMin) {
                xMin = x;
            }
        }
        return xMin;
    }

    public double getXMax() {
        double xMax = Integer.MIN_VALUE;
        for (Double x : xList) {
            if (x > xMax) {
                xMax = x;
            }
        }
        return xMax;
    }

    // 轨迹x方向走一半花的时间比
    public double getXRatio() {
        double sumX = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            sumX = xList.get(i + 1) - xList.get(i);
        }
        int pos = 0;
        double halfSum = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            halfSum = xList.get(i + 1) - xList.get(i);
            if (halfSum >= (sumX / 2)) {
                pos = i;
            }
        }
        double tHalf = tList.get(pos);
        double tStart = tList.get(0);
        double tEnd = tList.get(tList.size() - 1);
        double xRatio = (tHalf - tStart) / (tEnd - tStart);
        return xRatio;
    }


    public double getYMin() {
        double yMin = Integer.MAX_VALUE;
        for (Double y : yList) {
            if (y < yMin) {
                yMin = y;
            }
        }
        return yMin;
    }


    public double getYMax() {
        double yMax = Integer.MIN_VALUE;
        for (Double y : yList) {
            if (y > yMax) {
                yMax = y;
            }
        }
        return yMax;
    }

    public double getXDiffStd() {
        List<Double> xDiffList = new ArrayList<>();
        double sumX = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            xDiffList.add(xList.get(i + 1) - xList.get(i));
            sumX += xList.get(i + 1) - xList.get(i);
        }
        //设置均值
        double xMean = sumX / xDiffList.size();
        double ss = 0;
        for (int i = 0; i < xDiffList.size(); i++) {
            ss += ((xDiffList.get(i) - xMean) * (xDiffList.get(i) - xMean));
        }
        return Math.sqrt(ss / (xDiffList.size() - 1));
    }


    public double getXDiffMax() {
        double xDiffMax = Integer.MIN_VALUE;
        for (int i = 0; i < xList.size() - 1; i++) {
            if (xDiffMax < (xList.get(i + 1) - xList.get(i))) {
                xDiffMax = xList.get(i + 1) - xList.get(i);
            }
        }
        return xDiffMax;
    }

    public double getXDiffMin() {
        double xDiffMin = Integer.MAX_VALUE;
        for (int i = 0; i < xList.size() - 1; i++) {
            if (xDiffMin > (xList.get(i + 1) - xList.get(i))) {
                xDiffMin = xList.get(i + 1) - xList.get(i);
            }
        }
        return xDiffMin;
    }

    public double getXDiffSkew() {
        List<Double> xDiffList = new ArrayList<>();
        double sumX = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            xDiffList.add(xList.get(i + 1) - xList.get(i));
            sumX += xList.get(i + 1) - xList.get(i);
        }
        //设置均值
        double xMean = sumX / xDiffList.size();
        double ss = 0;
        double mmm = 0;
        for (int i = 0; i < xDiffList.size(); i++) {
            ss += ((xDiffList.get(i) - xMean) * (xDiffList.get(i) - xMean));

            mmm += ((xDiffList.get(i) - xMean) * (xDiffList.get(i) - xMean) * (xDiffList.get(i)
                    - xMean));
        }
        double sss = Math.sqrt(ss / (xDiffList.size() - 1)) * Math.sqrt(ss / (xDiffList.size() - 1))
                * Math.sqrt(ss / (xDiffList.size() - 1));
        double nm = mmm / (xDiffList.size() - 1);
        return nm / sss;
    }


    public double getYDiffStd() {
        List<Double> yDiffList = new ArrayList<>();
        double sumY = 0;
        for (int i = 0; i < yList.size() - 1; i++) {
            yDiffList.add(yList.get(i + 1) - yList.get(i));
            sumY += yList.get(i + 1) - yList.get(i);
        }

        //设置均值
        double xMean = sumY / yDiffList.size();
        double ss = 0;
        for (int i = 0; i < yDiffList.size(); i++) {
            ss += ((yDiffList.get(i) - xMean) * (yDiffList.get(i) - xMean));
        }
        return Math.sqrt(ss / (yDiffList.size() - 1));
    }

    public double getXBackNum() {
        double xBackNum = 0;
        for (int i = 0; i < xList.size(); i++) {
            for (int j = 0; j < xList.size(); j++) {
                if (j > i && xList.get(j) < xList.get(i)) {
                    xBackNum += 1;
                }
            }
        }
        return xBackNum;
    }


    public double getTDiffMean() {
        List<Double> tDiff = new ArrayList<>();
        double tSum = 0;
        for (int i = 0; i < tList.size() - 1; i++) {
            tSum += (tList.get(i + 1) - tList.get(i));

            tDiff.add(tList.get(i + 1) - tList.get(i));
        }
        return tSum / tDiff.size();
    }

    public double getTDiffStd() {
        List<Double> tDiffList = new ArrayList<>();
        double sumT = 0;
        for (int i = 0; i < tList.size() - 1; i++) {
            tDiffList.add(tList.get(i + 1) - tList.get(i));
            sumT += tList.get(i + 1) - tList.get(i);
        }
        //设置均值
        double tMean = sumT / tDiffList.size();
        double ss = 0;
        for (int i = 0; i < tDiffList.size(); i++) {
            ss += ((tDiffList.get(i) - tMean) * (tDiffList.get(i) - tMean));
        }
        return Math.sqrt(ss / (tDiffList.size() - 1));
    }

    public double getDurationMean() {
        //所花时间
        double sumT = tList.get(tList.size() - 1) - tList.get(0);
        double duration = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            duration += result;
        }
        return sumT / duration;
    }


    public double getTimeHalf() {
        double duration = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            duration += result;

        }
        double half = 0;
        int pos = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            half += result;
            if (half > (duration / 2)) {
                pos = i;
                break;
            }
        }
        return (tList.get(pos) - tList.get(0));
    }


    public double getXYDiffMax() {
        double xyDiffMax = Integer.MIN_VALUE;
        for (int i = 0; i < xList.size() - 1; i++) {
            double duration =
                    Math.sqrt(xList.get(i + 1) - xList.get(i) * (xList.get(i + 1) - xList.get(i)))
                            + (yList.get(i + 1) - yList.get(i)) * (yList.get(i + 1) - yList.get(i));
            if (xyDiffMax < duration) {
                xyDiffMax = duration;
            }
        }
        return xyDiffMax;
    }


    //相邻样本点速度的标准差, （距离／时间差）
    public double getVxyStd() {
        List<Double> vList = new ArrayList<>();
        double sumV = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
            sumV += duration / costT;
        }
        //设置均值
        double vMean = sumV / vList.size();
        double ss = 0;
        for (int i = 0; i < vList.size(); i++) {
            ss += ((vList.get(i) - vMean) * (vList.get(i) - vMean));
        }
        return Math.sqrt(ss / (vList.size() - 1));

    }

    //
    public double getVxyMean() {
        List<Double> vList = new ArrayList<>();
        double sumV = 0;

        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
            sumV += duration / costT;
        }

        return sumV / vList.size();
    }


    public double getVxyLast() {
        List<Double> vList = new ArrayList<>();

        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
        }

        if (vList.size() > 0) {
            return vList.get(vList.size() - 1);
        } else {
            return 0;
        }

    }

    public double getVxyFirst() {
        List<Double> vList = new ArrayList<>();

        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
        }
        if (vList.size() > 0) {
            return vList.get(0);
        }
        return 0;
    }

    public double getVxyDiffMedian() {
        List<Double> vList = new ArrayList<>();
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
        }
        List<Double> vDiffList = new ArrayList<>();
        for (int i = 0; i < vList.size() - 1; i++) {
            vDiffList.add(vList.get(i + 1) - vList.get(i));
        }

        //排序取中值
        Collections.sort(vDiffList);
        if (vDiffList.size() > 0) {
            return vDiffList.get(vDiffList.size() / 2);
        }
        return 0;
    }

    public double getVxyDiffMean() {
        List<Double> vList = new ArrayList<>();
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
        }
        double sumVDiff = 0;
        List<Double> vDiffList = new ArrayList<>();
        for (int i = 0; i < vList.size() - 1; i++) {
            vDiffList.add(vList.get(i + 1) - vList.get(i));
            sumVDiff += vList.get(i + 1) - vList.get(i);
        }
        return sumVDiff / vDiffList.size();
    }

    public double getVxyDiffMax() {
        List<Double> vList = new ArrayList<>();
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
        }
        double maxVDiff = Integer.MIN_VALUE;
        for (int i = 0; i < vList.size() - 1; i++) {
            double vDiff = vList.get(i + 1) - vList.get(i);
            if (maxVDiff < vDiff) {
                maxVDiff = vDiff;
            }
        }
        return maxVDiff;
    }

    public double getVxyDiffStd() {
        List<Double> vList = new ArrayList<>();
        for (int i = 0; i < xList.size() - 1; i++) {
            double x = (xList.get(i + 1) - xList.get(i));
            double y = yList.get(i + 1) - yList.get(i);
            double result = Math.sqrt((x * x) + (y * y));
            double duration = result;
            double costT = tList.get(i + 1) - tList.get(i);
            vList.add(duration / costT);
        }
        List<Double> vDiffList = new ArrayList<>();
        double sumV = 0;

        for (int i = 0; i < vList.size() - 1; i++) {
            vDiffList.add(vList.get(i + 1) - vList.get(i));
            sumV += vList.get(i + 1) - vList.get(i);

        }
        //设置均值
        double vMean = sumV / vDiffList.size();
        double ss = 0;
        for (int i = 0; i < vDiffList.size(); i++) {
            ss += ((vDiffList.get(i) - vMean) * (vDiffList.get(i) - vMean));
        }
        return Math.sqrt(ss / (vDiffList.size() - 1));
    }


    public double getAngleStd() {
        List<Double> angleList = new ArrayList<>();
        double sumAngle = 0;
        for (int i = 0; i < xList.size() - 1; i++) {
            double y = yList.get(i + 1) - yList.get(i);
            double x = xList.get(i + 1) - xList.get(i);
            if (x == 0) {
                continue;
            }
            angleList.add(y / x);
            sumAngle += (y / x);
        }
        double vMean = sumAngle / angleList.size();
        double ss = 0;
        for (int i = 0; i < angleList.size(); i++) {
            ss += ((angleList.get(i) - vMean) * (angleList.get(i) - vMean));
        }
        return Math.sqrt(ss / (angleList.size() - 1));
    }


    public double getAngleKurt() {
        double maxAngle = Integer.MIN_VALUE;
        for (int i = 0; i < xList.size() - 1; i++) {
            double y = yList.get(i + 1) - yList.get(i);
            double x = xList.get(i + 1) - xList.get(i);
            if (x == 0) {
                continue;
            }

            double angle = y / x;
            if (angle > maxAngle) {
                maxAngle = angle;
            }
        }
        return maxAngle;
    }

    public double getAngleDiffMean() {
        List<Double> angleList = new ArrayList<>();
        for (int i = 0; i < xList.size() - 1; i++) {
            double y = yList.get(i + 1) - yList.get(i);
            double x = xList.get(i + 1) - xList.get(i);
            if (x == 0) {
                continue;
            }
            angleList.add(y / x);
        }
        List<Double> angleDiffList = new ArrayList<>();
        double sumAngle = 0;
        for (int i = 0; i < angleList.size() - 1; i++) {
            angleDiffList.add((angleList.get(i + 1) - angleList.get(i)));
            sumAngle += angleList.get(i + 1) - angleList.get(i);
        }

        double vMean = sumAngle / angleDiffList.size();
        return vMean;
    }


    public double getAngleDiffStd() {
        List<Double> angleList = new ArrayList<>();
        for (int i = 0; i < xList.size() - 1; i++) {
            double y = yList.get(i + 1) - yList.get(i);
            double x = xList.get(i + 1) - xList.get(i);
            if (x == 0) {
                continue;
            }
            angleList.add(y / x);
        }
        List<Double> angleDiffList = new ArrayList<>();
        double sumAngle = 0;
        for (int i = 0; i < angleList.size() - 1; i++) {
            angleDiffList.add((angleList.get(i + 1) - angleList.get(i)));
            sumAngle += angleList.get(i + 1) - angleList.get(i);
        }

        double vMean = sumAngle / angleDiffList.size();
        double ss = 0;
        for (int i = 0; i < angleDiffList.size(); i++) {
            ss += ((angleDiffList.get(i) - vMean) * (angleDiffList.get(i) - vMean));
        }
        return Math.sqrt(ss / (angleDiffList.size() - 1));
    }

    public double getXyDuplicateCnt() {
        double xyDuplicateCnt = 0;
        for (int i = 0; i < xList.size(); i++) {
            for (int j = 0; j < xList.size(); j++) {
                if (i != j) {
                    if (xList.get(i) == xList.get(j) && yList.get(i) == yList.get(j)) {
                        xyDuplicateCnt += 1;
                    }
                }
            }
        }
        return xyDuplicateCnt;
    }


}
