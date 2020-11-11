package com.destinyapp.puskomdik.Activity.menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.BuildConfig;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class PPDBActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    LinearLayout LTanggalLahir;
    TextView TanggalLahir;
    String tanggal;
    Destiny destiny;
    Button uploadAkta,uploadKK,uploadDataPribadi,uploadFoto;
    //Jenis Kelamin
    RadioButton Laki,Perempuan;
    Boolean Kelamin = false;
    //Akta
    RadioButton AdaAkta,TidakAdaAkta;
    Boolean Akta = false;
    LinearLayout lGambar1;
    ImageView ivGambar1;
    TextView tvGambar1;
    //KK
    RadioButton AdaKK,TidakAdaKK;
    Boolean KK = false;
    LinearLayout lGambar2;
    ImageView ivGambar2;
    TextView tvGambar2;
    //Data Pribadi
    RadioButton AdaDataPribadi,TidakAdaDataPribadi;
    Boolean DataPribadi = false;
    LinearLayout lGambar3;
    ImageView ivGambar3;
    TextView tvGambar3;
    //Foto
    RadioButton AdaFoto,TidakAdaFoto;
    Boolean Foto = false;
    LinearLayout lGambar4;
    ImageView ivGambar4;
    TextView tvGambar4;

    //Dellaroy Logic
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;


    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    String postFoto1= "";
    String postFoto2= "";
    String postFoto3= "";
    String postFoto4= "";
    //ONCLICK
    Boolean Gambar1 = false;
    Boolean Gambar2 = false;
    Boolean Gambar3 = false;
    Boolean Gambar4 = false;
    RelativeLayout Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_p_d_b);
        destiny = new Destiny();
        LTanggalLahir = findViewById(R.id.linearTanggalLahir);
        TanggalLahir = findViewById(R.id.tvTanggalLahir);
        uploadAkta = findViewById(R.id.btnUploadAkta);
        uploadKK = findViewById(R.id.btnUploadKK);
        uploadDataPribadi = findViewById(R.id.btnUploadDataPribadi);
        uploadFoto = findViewById(R.id.btnUploadFoto);
        Laki = findViewById(R.id.radioLakilaki);
        Perempuan = findViewById(R.id.radioPerempuan);
        AdaAkta = findViewById(R.id.radioAdaAkta);
        TidakAdaAkta = findViewById(R.id.radioTidakAdaAkta);
        AdaKK = findViewById(R.id.radioAdaKK);
        TidakAdaKK = findViewById(R.id.radioTidakAdaKK);
        AdaDataPribadi = findViewById(R.id.radioAdaDataPribadi);
        TidakAdaDataPribadi = findViewById(R.id.radioTidakAdaDataPribadi);
        AdaFoto = findViewById(R.id.radioAdaFoto);
        TidakAdaFoto = findViewById(R.id.radioTidakAdaFoto);
        //Akta
        lGambar1 = findViewById(R.id.linearGambarAkta);
        ivGambar1 = findViewById(R.id.ivGambarAkta);
        tvGambar1 = findViewById(R.id.tvGambarAkta);
        //KK
        lGambar2 = findViewById(R.id.linearGambarKK);
        ivGambar2 = findViewById(R.id.ivGambarKK);
        tvGambar2 = findViewById(R.id.tvGambarKK);
        //Data Siswa
        lGambar3 = findViewById(R.id.linearGambarDataPribadi);
        ivGambar3 = findViewById(R.id.ivGambarDataPribadi);
        tvGambar3 = findViewById(R.id.tvGambarDataPribadi);
        //Foto
        lGambar4 = findViewById(R.id.linearGambarFoto);
        ivGambar4 = findViewById(R.id.ivGambarFoto);
        tvGambar4 = findViewById(R.id.tvGambarFoto);
        Back = findViewById(R.id.relativeBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.Back(PPDBActivity.this);
            }
        });
        uploadAkta.setVisibility(View.GONE);
        uploadKK.setVisibility(View.GONE);
        uploadDataPribadi.setVisibility(View.GONE);
        uploadKK.setVisibility(View.GONE);
        LTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        TanggalLahir.setText(destiny.thisDay());
        LogicKelamin();
        LogicAkta();
        LogicKK();
        LogicDataPribadi();
        LogicFoto();
        uploadAkta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(PPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar1 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar1.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar1 = true;
                                        captureImage();
                                        lGambar1.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        uploadKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(PPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar2 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar2.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar2 = true;
                                        captureImage();
                                        lGambar2.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        uploadDataPribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(PPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar3 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar3.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar3 = true;
                                        captureImage();
                                        lGambar3.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        uploadFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(PPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar4 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar4.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar4 = true;
                                        captureImage();
                                        lGambar4.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        destiny.Back(PPDBActivity.this);
    }

    private void LogicKelamin(){
        Laki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kelamin=true;
            }
        });
        Perempuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kelamin=false;
            }
        });
    }
    private void LogicAkta(){
        AdaAkta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Akta = true;
                uploadAkta.setVisibility(View.VISIBLE);
                if (!tvGambar1.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar1.setVisibility(View.VISIBLE);
                }else{
                    lGambar1.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaAkta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Akta = false;
                uploadAkta.setVisibility(View.GONE);
                lGambar1.setVisibility(View.GONE);
            }
        });
    }
    private void LogicKK(){
        AdaKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KK = true;
                uploadKK.setVisibility(View.VISIBLE);
                if (!tvGambar2.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar2.setVisibility(View.VISIBLE);
                }else{
                    lGambar2.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KK = false;
                uploadKK.setVisibility(View.GONE);
                lGambar2.setVisibility(View.GONE);
            }
        });
    }
    private void LogicDataPribadi(){
        AdaDataPribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPribadi = true;
                uploadDataPribadi.setVisibility(View.VISIBLE);
                if (!tvGambar3.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar3.setVisibility(View.VISIBLE);
                }else{
                    lGambar3.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaDataPribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPribadi = false;
                uploadDataPribadi.setVisibility(View.GONE);
                lGambar3.setVisibility(View.GONE);
            }
        });
    }
    private void LogicFoto(){
        AdaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Foto = true;
                uploadFoto.setVisibility(View.VISIBLE);
                if (!tvGambar4.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar4.setVisibility(View.VISIBLE);
                }else{
                    lGambar4.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Foto = false;
                uploadFoto.setVisibility(View.GONE);
                lGambar4.setVisibility(View.GONE);
            }
        });
    }
    private void showDatePicker(){
        DatePickerDialog dialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year+"-"+month+"-"+dayOfMonth;
        TanggalLahir.setText(destiny.DateChanges(String.valueOf(year),String.valueOf(month),String.valueOf(dayOfMonth)));
        tanggal = date;
    }
    //Dellaroy Logic
    private void captureImage() {
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            // We give some instruction to the intent to save the image
            File photoFile = null;

            try {
                // If the createImageFile will be successful, the photo file will have the address of the file
                photoFile = createImageFile();
                // Here we call the function that will try to catch the exception made by the throw function
            } catch (IOException e) {
                Logger.getAnonymousLogger().info("Exception error in generating the file");
                e.printStackTrace();
            }
            // Here we add an extra file to the intent to put the address on to. For this purpose we use the FileProvider, declared in the AndroidManifest.
            Uri outputUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            // The following is a new line with a trying attempt
            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Logger.getAnonymousLogger().info("Calling the camera App by intent");

            // The following strings calls the camera app and wait for his file in return.
            startActivityForResult(callCameraApplicationIntent, CAMERA_PIC_REQUEST);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        }


    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("TEST", "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }
    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        // Here we specify the environment location and the exact path where we want to save the so-created file
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();

        // Here we create the file using a prefix, a suffix and a directory
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
            if (data != null) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // Set the Image in ImageView for Previewing the Media

//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if(Gambar1) {
                    postFoto1 = mediaPath;
                    String filename = postFoto1.substring(postFoto1.lastIndexOf("/") + 1);
                    lGambar1.setVisibility(View.VISIBLE);
                    ivGambar1.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar1.setText(filename);
                    Gambar1 = false;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }else if(Gambar2){
                    postFoto2 = mediaPath;
                    String filename = postFoto2.substring(postFoto2.lastIndexOf("/") + 1);
                    lGambar2.setVisibility(View.VISIBLE);
                    ivGambar2.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar2.setText(filename);
                    Gambar2 = false;
                }else if(Gambar3){
                    postFoto3 = mediaPath;
                    String filename = postFoto3.substring(postFoto3.lastIndexOf("/") + 1);
                    lGambar3.setVisibility(View.VISIBLE);
                    ivGambar3.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar3.setText(filename);
                    Gambar3 = false;
                }else if(Gambar4){
                    postFoto4 = mediaPath;
                    String filename = postFoto4.substring(postFoto4.lastIndexOf("/") + 1);
                    lGambar4.setVisibility(View.VISIBLE);
                    ivGambar4.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar4.setText(filename);
                    Gambar4 = false;
                }
            }
        }else if (requestCode == CAMERA_PIC_REQUEST){
            if (Gambar1){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar1);
                    postFoto1 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar1);
                    postFoto1 = fileUri.getPath();
                }
                String filename=postFoto1.substring(postFoto1.lastIndexOf("/")+1);
                lGambar1.setVisibility(View.VISIBLE);
                tvGambar1.setVisibility(View.VISIBLE);
                tvGambar1.setText(filename);
                Gambar1=false;
            }else if(Gambar2){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar2);
                    postFoto2 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar2);
                    postFoto2 = fileUri.getPath();
                }
                String filename=postFoto2.substring(postFoto2.lastIndexOf("/")+1);
                lGambar2.setVisibility(View.VISIBLE);
                tvGambar2.setVisibility(View.VISIBLE);
                tvGambar2.setText(filename);
                Gambar2=false;
            }else if(Gambar3){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar3);
                    postFoto3 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar3);
                    postFoto3 = fileUri.getPath();
                }
                String filename=postFoto3.substring(postFoto3.lastIndexOf("/")+1);
                lGambar3.setVisibility(View.VISIBLE);
                tvGambar3.setVisibility(View.VISIBLE);
                tvGambar3.setText(filename);
                Gambar3=false;
            }else if(Gambar4){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar4);
                    postFoto4 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar4);
                    postFoto4 = fileUri.getPath();
                }
                String filename=postFoto4.substring(postFoto4.lastIndexOf("/")+1);
                lGambar4.setVisibility(View.VISIBLE);
                tvGambar4.setVisibility(View.VISIBLE);
                tvGambar4.setText(filename);
                Gambar4=false;
            }
        }
    }

}