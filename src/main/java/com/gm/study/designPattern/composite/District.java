package com.gm.study.designPattern.composite;

/**
 * @Author: xexgm
 * des: 区
 */
public class District implements PopulationNode{

    // 区有一个名字
    private String name;

    // 区有固定的人口
    private int population;

    public District(String name, int population) {
        this.name = name;
        this.population = population;
    }

    @Override
    public int computePopulation() {
        return population;
    }
}
