package com.example.mautic_mobile.api.entities;

public class AssetResponse {
    private Asset asset;

    public AssetResponse(Asset asset) {
        this.asset = asset;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
