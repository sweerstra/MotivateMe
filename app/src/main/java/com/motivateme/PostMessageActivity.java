package com.motivateme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class PostMessageActivity extends AppCompatActivity {

    private final static int PICK_PHOTO_CODE = 0;
    private final static int CAMERA_REQUESTED = 2;
    private EditText postText;
    private EditText titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postmessage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        postText = (EditText) findViewById(R.id.editTextPost);
        titleText = (EditText) findViewById(R.id.editTextPostTitle);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        BitmapDrawable selectedImage = null;
        if (requestCode == CAMERA_REQUESTED && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            selectedImage = new BitmapDrawable(getResources(), (Bitmap) extras.get("data"));
        } else {
            try {
                selectedImage = new BitmapDrawable(getResources(), MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ImageView mPickPhoto = (ImageView) findViewById(R.id.imgViewPost);
        assert mPickPhoto != null;
        mPickPhoto.setBackground(selectedImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_picture:
                //make the user pick a specific photo
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    //bring up gallery to select a photo
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
                return true;
            case R.id.action_add_post:
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("text", postText.getText());
                intent1.putExtra("title", titleText.getText());
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
