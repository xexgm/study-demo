package com.gm.study.designPattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xexgm
 */
public class Province implements PopulationNode{

    private String name;

    // 肚子里也有一堆城市
    List<PopulationNode> cityList = new ArrayList<>();

    public void addCity(City city) {
        this.cityList.add(city);
    }

    @Override
    public int computePopulation() {
        return cityList.stream().mapToInt(PopulationNode::computePopulation).sum();
    }
}
