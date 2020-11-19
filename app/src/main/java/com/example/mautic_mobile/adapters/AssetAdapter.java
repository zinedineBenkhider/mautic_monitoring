package com.example.mautic_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.home.presenter.interfaces.AssetActionInterface;
import com.example.mautic_mobile.api.entities.Asset;
import com.example.mautic_mobile.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.AssetViewHolder> {
    private List<Asset> assets;
    private AssetActionInterface assetActionInterface;

    public AssetAdapter(AssetActionInterface assetActionInterface) {
        this.assets = new ArrayList<>();
        this.assetActionInterface = assetActionInterface;
    }

    @Override
    public AssetAdapter.AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_asset_item, parent, false);
        AssetAdapter.AssetViewHolder assetViewHolder = new AssetAdapter.AssetViewHolder(v);
        return assetViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AssetViewHolder holder, int position) {
        holder.updateAsset(assets.get(position), assetActionInterface);
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }

    public void bindViewModel(Asset asset) {
        this.assets.add(asset);
        notifyDataSetChanged();
    }

    public static class AssetViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateAddedTextView;
        private TextView extensionTextView;
        private View view;

        public AssetViewHolder(View v) {
            super(v);
            this.view = v;
            titleTextView = v.findViewById(R.id.title_asset_item);
            dateAddedTextView = v.findViewById(R.id.date_added_asset_item);
            extensionTextView = v.findViewById(R.id.extension_asset_item);
        }

        public void updateAsset(final Asset asset, AssetActionInterface assetActionInterface) {
            titleTextView.setText(asset.getTitle());
            dateAddedTextView.setText(Utils.getDateFormat(asset.getDateAdded(),view.getContext()));
            if (asset.getExtension() != null) {
                extensionTextView.setText(asset.getExtension().toUpperCase());
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    assetActionInterface.onFormClick(asset);
                }
            });
        }
    }
}