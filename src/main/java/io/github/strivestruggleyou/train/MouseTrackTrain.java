package io.github.strivestruggleyou.train;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;

public class MouseTrackTrain {

    //根据指定文件信息生成特征数据信息

    public static boolean checkPredicts(float[][] fPredicts, float[][] sPredicts) {
        if (fPredicts.length != sPredicts.length) {
            return false;
        }

        for (int i = 0; i < fPredicts.length; i++) {
            if (!Arrays.equals(fPredicts[i], sPredicts[i])) {
                return false;
            }
        }

        return true;
    }

    public static void saveDumpModel(String modelPath, String[] modelInfos) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(modelPath, "UTF-8");
            for (int i = 0; i < modelInfos.length; ++i) {
                writer.print("booster[" + i + "]:\n");
                writer.print(modelInfos[i]);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws XGBoostError {
        DMatrix trainMat = new DMatrix(
                "/Users/manager/yunpian-blackcat/src/main/resources/text/train40.txt");
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("eta", 1.0);
        params.put("max_depth", 2);
        params.put("silent", 1);
        params.put("objective", "binary:logistic");

        HashMap<String, DMatrix> watches = new HashMap<String, DMatrix>();
        watches.put("train", trainMat);

        int round = 2;

        //train a boost model
        Booster booster = XGBoost.train(trainMat, params, round, watches, null, null);

        File file = new File("./model");
        if (!file.exists()) {
            file.mkdirs();
        }

        String modelPath = "./model/xgb.model";
        booster.saveModel(modelPath);

    }

}
