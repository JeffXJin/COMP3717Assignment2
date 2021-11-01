//package ca.bcit.comp3717assignment2;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
//    private Context _context;
//    private ArrayList<Contributor> contributorArrayList;
//    private String[] captions;
//
//    public RecyclerAdapter(Context c, ArrayList<Contributor> contributors) {
//        _context = c;
//        contributorArrayList = contributors;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private CardView cardView;
//
//        public ViewHolder(CardView v) {
//            super(v);
//            cardView = v;
//        }
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        CardView cv = (CardView) LayoutInflater.from(_context)
//                .inflate(R.layout.card_item, parent, false);
//
//        return new ViewHolder(cv);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        final CardView cardView = holder.cardView;
//
//        Contributor currentItem = contributorArrayList.get(position);
//
//        TextView tvName = cardView.findViewById(R.id.loginName);
//        ImageView ivPictureUrl = cardView.findViewById(R.id.image_view);
//
//        tvName.setText(currentItem.getLoginName());
//
//        //uses asynchronous class to download picture
//        if (currentItem.getAvatarURL() != null) {
//            Picasso.with(_context)
//                    .load(currentItem.getAvatarURL())
//                    .fit()
//                    .centerInside()
//                    .into(ivPictureUrl);
//
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return contributorArrayList.size();
//    }
//}
