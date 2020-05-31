package com.example.compendio2.camara;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.compendio2.R;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Lo primero que haremos es modificar el archivo AndroidManifest.xml para permitir a la
 * aplicación almacenar archivos en la memoria externa:
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 */

public class Camara extends AppCompatActivity {


    static final int CAPTURA_IMAGEN_THUMBNAIL = 1;
    static final int CAPTURA_IMAGEN_TAMAÑO_REAL = 2;
    ImageView imageView;
    String fotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    // onClick botón obtener thumbnail
    public void hacerFotoThumbnail (View view){
        Intent hacerFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hacerFotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(hacerFotoIntent, CAPTURA_IMAGEN_THUMBNAIL);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURA_IMAGEN_THUMBNAIL && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
        else if (requestCode == CAPTURA_IMAGEN_TAMAÑO_REAL && resultCode == RESULT_OK) {
            Toast toast = Toast.makeText(this, "La imagen se ha guardado en: " + fotoPath , Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    // CREATE A COLLISION-RESISTANT NAME
    // Código de Android Developer
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        fotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    public void guardarFotoTamañoReal(View view){
        dispatchTakePictureIntent();
    }

    // SAVE THE FULL-SIZE PHOTO with a COLLISION-RESISTANT NAME
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                //...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, CAPTURA_IMAGEN_TAMAÑO_REAL);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
