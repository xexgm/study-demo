package com.gm.study.designPattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xexgm
 * des: 城市有一个名字，有计算人口的方法
 */
public class City implements PopulationNode{

    // 城市有一个名字
    private String name;

    // 城市里有一堆的区
    List<PopulationNode> districtList = new ArrayList<>();

    // 城市要有能添加区的方法

    public void addDistrict(District district) {
        this.districtList.add(district);
    }

    @Override
    public int computePopulation() {
        return this.districtList.stream().mapToInt(PopulationNode::computePopulation).sum();
    }
}
