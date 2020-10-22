package com.example.projectsdetails;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<Project> projList;
    Context ct;
    static int id;

    ProjectAdapter(List<Project> projList, Context ct){
        this.projList = projList;
        this.ct = ct;
    }

    @NonNull
    @Override
//    public ProjectAdapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_view,parent,false);
        ProjectViewHolder proHolder = new ProjectViewHolder(view);//,mCtx);
        return proHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, final int position) {
        Project project = projList.get(position);
        id = project.getPid();
        holder.tx_name.setText(projList.get(position).getpName());
        holder.tx_time.setText(projList.get(position).getpDurat());
        holder.tx_date.setText(""+projList.get(position).getpDate());

        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ct,"Project: "+projList.get(position).getpName().toUpperCase(),
                        Toast.LENGTH_LONG).show();
            }
        });

//        holder.ln.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(ct,"Project: "+projList.get(position).getpName().toUpperCase(),
//                        Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return projList.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView tx_name,tx_time,tx_date;
        LinearLayout ln;
        public ProjectViewHolder(@NonNull View iv) {
            super(iv);
            tx_name = iv.findViewById(R.id.tv_nm);
            tx_time = iv.findViewById(R.id.tv_hr);
            tx_date = iv.findViewById(R.id.tv_date);
            ln = iv.findViewById(R.id.linear);
            ln.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Options:");
            menu.add(this.getAdapterPosition(),121,0,"DELETE ?");
            menu.add(this.getAdapterPosition(),122,1,"CANCEL !");
        }
    }
}
