package com.thedevelopershome.taskbrochill.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.thedevelopershome.taskbrochill.R;
import com.thedevelopershome.taskbrochill.model.PostModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{
    private Context context;
    private List<PostModel> postModels;

    public PostAdapter(Context context, List<PostModel> postModels) {
        this.context = context;
        this.postModels = postModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {




        Glide
                .with(context)
                .load(postModels.get(position).getPost())
                .asBitmap()
                .dontAnimate()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.post);

       holder.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Bitmap bitmap = ((BitmapDrawable)holder.post.getDrawable()).getBitmap();
                    File cachePath = new File(context.getCacheDir(), "images");
                    cachePath.mkdirs(); // don't forget to make the directory
                    FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    stream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                File imagePath = new File(context.getCacheDir(), "images");
                File newFile = new File(imagePath, "image.png");
                Uri contentUri = FileProvider.getUriForFile(context, "com.thedevelopershome.taskbrochill.fileprovider", newFile);

                if (contentUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// temp permission for receiving app to read this file

                    shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/path-to-your-image.jpg"));
                    shareIntent.setPackage("com.whatsapp");

                    shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
                    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                    context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
                }

            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bitmap bitmap = ((BitmapDrawable)holder.post.getDrawable()).getBitmap();
                    File cachePath = new File(context.getCacheDir(), "images");
                    cachePath.mkdirs(); // don't forget to make the directory
                    FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    stream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                File imagePath = new File(context.getCacheDir(), "images");
                File newFile = new File(imagePath, "image.png");
                Uri contentUri = FileProvider.getUriForFile(context, "com.thedevelopershome.taskbrochill.fileprovider", newFile);

                if (contentUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                    shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
                    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                    context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        PorterShapeImageView post;

        TextView whatsapp,share;
        public MyViewHolder(View itemView) {
            super(itemView);

            post = itemView.findViewById(R.id.postimage);
            whatsapp = itemView.findViewById(R.id.whatsapp);
            share = itemView.findViewById(R.id.share);

        }

    }


}
