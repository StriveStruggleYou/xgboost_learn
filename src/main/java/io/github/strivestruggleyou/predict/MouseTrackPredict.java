package io.github.strivestruggleyou.predict;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;

public class MouseTrackPredict {

    public static void main(String args[]) throws XGBoostError {

        DMatrix testMat = new DMatrix(
                "/Users/manager/xgboost_learn/src/main/resources/test.txt");

                Booster booster2 = XGBoost.loadModel("./model/xgb.model");
//        DMatrix testMat2 = new DMatrix("./model/dtest.buffer");

        float[][] predicts2 = booster2.predict(testMat);

        System.out.println(predicts2);
    }

}
