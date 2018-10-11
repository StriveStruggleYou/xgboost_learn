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
        DMatrix testMat = new DMatrix(
                "/Users/manager/yunpian-blackcat/src/main/resources/text/test.txt");

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("eta", 1.0);
        params.put("max_depth", 2);
        params.put("silent", 1);
        params.put("objective", "binary:logistic");

        HashMap<String, DMatrix> watches = new HashMap<String, DMatrix>();
        watches.put("train", trainMat);
//        watches.put("test", testMat);

        //set round
        int round = 2;

        //train a boost model
        Booster booster = XGBoost.train(trainMat, params, round, watches, null, null);

        //predict
        float[][] predicts = booster.predict(testMat);

        System.out.println("nihao:" + predicts);

        //save model to modelPath
        File file = new File("./model");
        if (!file.exists()) {
            file.mkdirs();
        }

        String modelPath = "./model/xgb.model";
        booster.saveModel(modelPath);

        //dump model with feature map
//        String[] modelInfos = booster.getModelDump("/Users/manager/yunpian-blackcat/src/main/resources/text/featmap.txt", false);
//        saveDumpModel("./model/dump.raw.txt", modelInfos);
//
//        //save dmatrix into binary buffer
//        testMat.saveBinary("./model/dtest.buffer");

        //reload model and data
        Booster booster2 = XGBoost.loadModel("./model/xgb.model");
        DMatrix testMat2 = new DMatrix("./model/dtest.buffer");

        float[][] predicts2 = booster2.predict(testMat2);

        System.out.println(predicts2);

        //check the two predicts
        System.out.println(checkPredicts(predicts, predicts2));
    }

}
