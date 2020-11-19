package com.example.mautic_mobile.api.entities;

import java.util.List;

public class AssetsResponse {
    private int total;
    private List<Asset> assets;

    public AssetsResponse(int total, List<Asset> assets) {
        this.total = total;
        this.assets = assets;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
