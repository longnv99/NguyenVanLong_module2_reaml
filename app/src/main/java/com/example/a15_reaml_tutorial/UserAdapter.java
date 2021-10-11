package com.example.a15_reaml_tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public
class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> listUser;
    private
    Context context;
    public
    UserAdapter(Context context) {
        this.context=context;
    }

    public void setData(List<User> list){
        this.listUser = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public
    UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
        return new UserHolder(v);
    }

    @Override
    public
    void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = listUser.get(position);
        if(user == null)
            return;
        holder.iMaSV.setText("MSV : "+user.getMaSinhVien());
        holder.iTen.setText("Tên : "+user.getHoTen());
        holder.iLop.setText("Lớp : "+user.getLop());
        holder.iGioiTinh.setText(user.getGioiTinh());
        holder.iToan.setText("Điểm Toán : "+user.getDiemToan());
        holder.iLy.setText("Điểm Lý : "+user.getDiemLy());
        holder.iHoa.setText("Điểm Hóa : "+user.getDiemHoa());
    }

    @Override
    public
    int getItemCount() {
        if(listUser!=null)
            return listUser.size();
        return 0;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        private
        TextView iMaSV, iTen, iLop, iGioiTinh, iToan, iLy, iHoa;
        public
        UserHolder(@NonNull View v) {
            super(v);

            iMaSV=v.findViewById(R.id.iMsv);
            iTen=v.findViewById(R.id.iName);
            iLop=v.findViewById(R.id.iClass);
            iGioiTinh=v.findViewById(R.id.iSex);
            iToan=v.findViewById(R.id.iToan);
            iLy=v.findViewById(R.id.iLy);
            iHoa=v.findViewById(R.id.iHoa);
        }
    }
}
