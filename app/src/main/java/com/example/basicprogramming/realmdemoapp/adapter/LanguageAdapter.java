package com.example.basicprogramming.realmdemoapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicprogramming.realmdemoapp.R;
import com.example.basicprogramming.realmdemoapp.app.EditActivity;
import com.example.basicprogramming.realmdemoapp.model.LanguageList;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.Holder> {

    private Context context;
    private Realm realm;
    private RealmResults<LanguageList> realmResults;
    private LayoutInflater inflater;
    private LanguageList languageList;

    public LanguageAdapter(Context context, Realm realm, RealmResults<LanguageList> realmResults) {
        this.context = context;
        this.realm = realm;
        this.realmResults = realmResults;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.language_list_layout, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        languageList = realmResults.get(position);
        viewHolder.setLanguageList(languageList, position);
        viewHolder.setListener();
    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private int position;
        private TextView name_tv, description_tv;
        private ImageView edit_image_view, delete_image_view;

        public Holder(View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.language_name_text_view);
            description_tv = itemView.findViewById(R.id.language_description_text_view);
            edit_image_view = itemView.findViewById(R.id.edit_image_view);
            delete_image_view = itemView.findViewById(R.id.delete_image_view);
        }

        public void setLanguageList(LanguageList languageList, int position) {

            this.position = position;

            name_tv.setText(languageList.getName());
            description_tv.setText(languageList.getDescription());

        }

        public void setListener() {

            name_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    languageList = realmResults.get(position);

                    Dialog dialog = new Dialog(context, R.style.appDialog);
                    dialog.setTitle("Details Of " + languageList.getName());
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setContentView(R.layout.details_list_layout);

                    TextView tv1 = dialog.findViewById(R.id.detail_name_text_view);
                    TextView tv2 = dialog.findViewById(R.id.detail_developer_text_view);
                    TextView tv3 = dialog.findViewById(R.id.detail_description_text_view);
                    TextView tv4 = dialog.findViewById(R.id.detail_version_text_view);

                    tv1.setText("Language Name \n\n" + languageList.getName() + "\n");
                    tv2.setText("Design By \n\n" + languageList.getDeveloped() + "\n");
                    tv3.setText("Description \n\n" + languageList.getDescription() + "\n");
                    tv4.setText("Latest Version \n\n" + languageList.getLatest());

                    dialog.show();

                }
            });

            edit_image_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);


                }
            });

            delete_image_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            realmResults.deleteFromRealm(position);

                            Toasty.warning(context,
                                    "Language delete from list",
                                    Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, realmResults.size());
                        }
                    });

                }
            });

        }
    }
}
