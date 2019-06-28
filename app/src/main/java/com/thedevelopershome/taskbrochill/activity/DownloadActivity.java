package com.thedevelopershome.taskbrochill.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.thedevelopershome.taskbrochill.R;


import java.io.File;

public class DownloadActivity extends AppCompatActivity {

    PorterShapeImageView post;
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        int Data=getIntent().getIntExtra("var",0);

        post=findViewById(R.id.postimage);
        download=findViewById(R.id.download);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),12);

        }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2)
    {
        try
        {
            Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(),  bmp1.getConfig());
            Canvas canvas = new Canvas(bmOverlay);
            canvas.drawBitmap(bmp1, new Matrix(), null);
            canvas.drawBitmap(bmp2, 0, 0, null);
            return bmOverlay;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == 12) {
                    Uri selectedImageUri = data.getData();
                    // Get the path from the Uri
                    final String path = getPathFromURI(selectedImageUri);

                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
                        Drawable drawableFore = getResources().getDrawable(R.drawable.a);
                        Bitmap bitmapBack = BitmapFactory.decodeFile(selectedImageUri.toString());

                        Bitmap bitmapFore = ((BitmapDrawable) drawableFore).getBitmap();


                        Bitmap scaledBitmapFore = Bitmap.createScaledBitmap(bitmapFore, 35, 35, true);
                        Bitmap scaledBitmapBack = Bitmap.createScaledBitmap(bitmapBack, 45, 45, true);

                        Bitmap combineImages = overlay(scaledBitmapBack, scaledBitmapFore);

                        post.setImageBitmap(combineImages);

                    }
                    // Set the image in ImageView
                  //  ImageView((ImageView) findViewById(R.id.imgView)).setImageURI(selectedImageUri);
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }



    public String getPathFromURI(Uri contentUri)
    {
        String[] proj = { MediaStore.Audio.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
