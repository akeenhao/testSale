package com.mycompany.myapp.service.dto;

import java.util.List;

public class BusiInfo {
    String code;
    List<String> xData;
    List<Float> yHighData;
    List<Float> yLowData;
    List<Float> yOpenData;
    List<Float> yCloseData;
    Float yMaxData;
    Float yMinData;

    public List<Float> getyLowData() {
        return yLowData;
    }

    public void setyLowData(List<Float> yLowData) {
        this.yLowData = yLowData;
    }

    public List<Float> getyOpenData() {
        return yOpenData;
    }

    public void setyOpenData(List<Float> yOpenData) {
        this.yOpenData = yOpenData;
    }

    public List<Float> getyCloseData() {
        return yCloseData;
    }

    public void setyCloseData(List<Float> yCloseData) {
        this.yCloseData = yCloseData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getxData() {
        return xData;
    }

    public void setxData(List<String> xData) {
        this.xData = xData;
    }

    public List<Float> getyHighData() {
        return yHighData;
    }

    public void setyHighData(List<Float> yHighData) {
        this.yHighData = yHighData;
    }

    public Float getyMaxData() {
        return yMaxData;
    }

    public void setyMaxData(Float yMaxData) {
        this.yMaxData = yMaxData;
    }

    public Float getyMinData() {
        return yMinData;
    }

    public void setyMinData(Float yMinData) {
        this.yMinData = yMinData;
    }
}
