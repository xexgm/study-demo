package com.gm.study.designPattern.composite;

/**
 * @Author: xexgm
 */
public class Main {
    public static void main(String[] args) {
        Province fj = new Province();
        City fuzhou = new City();
        City xiamen = new City();

        District cangshan = new District("cangshan", 123);
        District gushan = new District("gushan", 234);
        fuzhou.addDistrict(cangshan);
        fuzhou.addDistrict(gushan);

        fj.addCity(fuzhou);

        System.out.println(fj.computePopulation());
    }
}
