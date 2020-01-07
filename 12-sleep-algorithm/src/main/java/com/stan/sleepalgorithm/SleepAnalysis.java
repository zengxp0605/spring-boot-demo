package com.stan.sleepalgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


/**
 * 睡眠分析
 */
public class SleepAnalysis {


    public static void main(String[] args) {
        new SleepAnalysis().test();
    }

    public static final Logger logger = LoggerFactory.getLogger(SleepAnalysis.class);

    private List<HeartRatePO> rawHrList = new ArrayList<>();
    // 测量时间 小->大排序
    private List<HeartRatePO> sortedTimeHrList = new ArrayList<>();
    // 心率值 小->大排序
    private List<HeartRatePO> sortedValHrList = new ArrayList<>();
    // 每个心率值出现的次数
    private Map<Integer, Integer> hrValCounterMap = new HashMap<>();

    // 最大心率值,测量时间
    private int maxHrValue;
    private int maxHrMeasureTime;

    // 最小心率值,测量时间
    private int minHrValue;
    private int minHrMeasureTime;

    // 心率平均值
    private int avgHrValue;
    // 心率中位数
    private int medianHrValue;

    // TODO: 极限心率 220-年龄
    private int extremeHrValue;

    public void test() {
        logger.info("SleepAnalysis 分析睡眠数据");

        String fileName = "hr-demo.txt";
        initListByFile(fileName);

        logger.info("当前心率数量: {}, {}", rawHrList.size());

//        for(int i =1; i<rawHrList.size(); i++){
//            HeartRatePO prevHR = rawHrList.get(i-1);
//            HeartRatePO curHR = rawHrList.get(i);
//            System.out.println(curHR.getMeasureTime() - prevHR.getMeasureTime());
//        }
        logger.info("排序前,第一个值: {}, {}", rawHrList.get(0));

        // 拷贝对象
        sortedValHrList = new ArrayList<>(rawHrList);
        sortedTimeHrList = new ArrayList<>(rawHrList);

        Collections.sort(sortedValHrList, (HeartRatePO h1, HeartRatePO h2) -> h1.getHeartRate() > h2.getHeartRate() ? 1 : -1);
        Collections.sort(sortedTimeHrList, (HeartRatePO h1, HeartRatePO h2) -> h1.getMeasureTime() > h2.getMeasureTime() ? 1 : -1);

        logger.info("排序后,第一个值: {}, {}", sortedValHrList.get(0), sortedTimeHrList.get(0));

        HeartRatePO minItem = sortedValHrList.get(0);
        HeartRatePO maxItem = sortedValHrList.get(sortedValHrList.size() - 1);
        minHrMeasureTime = minItem.getMeasureTime();
        minHrValue = minItem.getHeartRate();
        maxHrMeasureTime = maxItem.getMeasureTime();
        maxHrValue = maxItem.getHeartRate();

        int totalVal = 0;
        int totalCount = sortedValHrList.size();
        for (HeartRatePO item : sortedValHrList) {
            int hr = item.getHeartRate();
            totalVal += hr;

            int curCount = 1;
            if (hrValCounterMap.containsKey(hr)) {
                curCount += hrValCounterMap.get(hr);
            }

            hrValCounterMap.put(hr, curCount);
        }

        avgHrValue = totalVal / totalCount;
        medianHrValue = calcMedianHrValue();

        logger.info("计算的基础数据: minHrMeasureTime={}, minHrValue={}, maxHrMeasureTime={}, maxHrValue={}, avgHrValue={}, medianHrValue={}",
                minHrMeasureTime,
                minHrValue,
                maxHrMeasureTime,
                maxHrValue,
                avgHrValue,
                medianHrValue);

        logger.info("心率各个值的统计: {}", hrValCounterMap);


    }

    /**
     * 计算心率中位数
     *
     * @return
     */
    private int calcMedianHrValue() {
        int totalCount = sortedValHrList.size();
        // 总数为奇数
        if (totalCount % 2 == 1) {
            int midIndex = (int) Math.ceil(totalCount / 2);
            return sortedValHrList.get(midIndex).getHeartRate();
        }

        // 总数为偶数
        int midIndex = (int) Math.ceil(totalCount / 2);
        int totalVal = sortedValHrList.get(midIndex).getHeartRate() + sortedValHrList.get(midIndex + 1).getHeartRate();
        return (int) Math.floor(totalVal / 2);
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    private void initListByFile(String fileName) {
        String path = "D:\\work\\workspace\\mydemo\\12-sleep-algorithm\\target\\classes\\debugData\\" + fileName;

        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                line++;

                String[] tmpArr = tempString.split(",");
                HeartRatePO heartRatePO = new HeartRatePO();
                heartRatePO.setMeasureTime(Integer.valueOf(tmpArr[0]));
                heartRatePO.setHeartRate(Integer.valueOf(tmpArr[1]));
                rawHrList.add(heartRatePO);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
