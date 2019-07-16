package com.example.umbrellaweatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.umbrellaweatherapp.model.forecastresponse.Forecast;

import java.util.List;


public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {
    List<Forecast> forecastList;

    public ForecastListAdapter(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.forecast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Forecast forecast = forecastList.get(position);
        String imageUrl = forecast.getWeatherIconUrl();

        Glide.with(holder.itemView.getContext()).load(imageUrl).into(holder.ivDailyIcon);
        holder.tvDay.setText(forecast.getDay());
        holder.tvDescription.setText(forecast.getWeatherDescription());
        holder.tvHighTemp.setText("High: " + String.valueOf(forecast.getHighTemp()) + "º");
        holder.tvLowTemp.setText("Low: " + String.valueOf(forecast.getLowTemp()) + "º");

        holder.setForecast(forecast);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivDailyIcon;
        TextView tvDay, tvDescription, tvHighTemp, tvLowTemp;
        Forecast forecast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDailyIcon = itemView.findViewById(R.id.ivDailyIcon);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvHighTemp = itemView.findViewById(R.id.tvHighTemp);
            tvLowTemp = itemView.findViewById(R.id.tvLowTemp);

            itemView.setOnClickListener(this);
        }

        public Forecast getForecast() {
            return forecast;
        }

        public void setForecast(Forecast forecast) {
            this.forecast = forecast;
        }

        @Override
        public void onClick(View view) {

//            Intent intent = new Intent(view.getContext(), ViewFlickr.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("item", item);
//            intent.putExtras(bundle);
//            view.getContext().startActivity(intent);
        }
    }
}
