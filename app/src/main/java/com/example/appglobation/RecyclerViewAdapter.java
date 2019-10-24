package com.example.appglobation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appglobation.model.Servicio;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    List<Servicio> servicios;
    private View.OnClickListener listener;

    public RecyclerViewAdapter(Context context, List<Servicio> servicios) {
        this.context = context;
        this.servicios = servicios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list, parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);

        viewHolder.yourPhone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {

                String url = "tel:"+viewHolder.txtPhone.getText().toString();

                context.startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse(url)));

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Servicio servicio = servicios.get(position);
        holder.txtServiceName.setText(servicio.getServicio());
        holder.txtServiceType.setText(servicio.getTipo());
        holder.txtServicePrice.setText(servicio.getPrecio());
        holder.txtPhone.setText(servicio.getTelefono());
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }
public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
}

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtServiceName;
        public TextView txtServiceType;
        public TextView txtServicePrice;
        public TextView txtPhone;
        private TextView yourPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtServiceName = itemView.findViewById(R.id.textServiceName);
            txtServiceType = itemView.findViewById(R.id.textServiceType);
            txtServicePrice = itemView.findViewById(R.id.textPrice);
            txtPhone = itemView.findViewById(R.id.textPhone);
            yourPhone = itemView.findViewById(R.id.textViewPhone);
        }

    }
}
