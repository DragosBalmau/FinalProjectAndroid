package com.example.traveljournal.Trip;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournal.R;

import java.io.IOException;
import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.triplistsql.REPLY";

    //TODO
    /*
    * Utilizatorul sa apara in stanga
    * Poza din galerie in baza de date
    *
    * Design
    *
    * Display pe recyclerview
    *
    * Share - pozele din baza de date
    *
    *
    * Poza cu camera + butoanele alea care nu merg
    * */

    private DatePickerDialog.OnDateSetListener onDateSetListenerStart;
    private DatePickerDialog.OnDateSetListener onDateSetListenerEnd;
    private EditText EditViewTripName;
    private EditText EditViewTripDestination;
    private TextView SliderValue;
    private RadioButton RadioButton1,RadioButton2,RadioButton3;
    private SeekBar Slider;
    private TextView startDate, endDate;
    private RatingBar RatingBar;
    private Button PhotoCamera;
    private ImageView ImagePreview;
    Bitmap bmpImage = null;
    String trip;
    String loc;
    private static final String TAG = "AddTripActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtrip);
        EditViewTripName = findViewById(R.id.editTextTripName);
        EditViewTripDestination = findViewById(R.id.editTextDestination);
        SliderValue = findViewById(R.id.textViewSliderValue);
        RadioButton1 = findViewById(R.id.radioButtonCityBreak);
        RadioButton2 = findViewById(R.id.radioButtonMountains);
        RadioButton3 = findViewById(R.id.radioButtonSeaSide);
        Slider = findViewById(R.id.sliderPrice);
        startDate = findViewById(R.id.textViewStartDate);
        endDate = findViewById(R.id.textViewEndDate);
        RatingBar = findViewById(R.id.ratingBar);
        PhotoCamera = findViewById(R.id.buttonPhoto);
       // PhotoGallery = findViewById(R.id.buttonOpenGallery);


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddTripActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth , onDateSetListenerStart, year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                //Log.d(TAG,"onDataSet: mm/dd/yyyy " + year + "/" + month + "/" + dayOfMonth );
                String date = year + "/" + month + "/" + dayOfMonth;
                startDate.setText(date);
            }
        };


        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddTripActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth , onDateSetListenerEnd, year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                //Log.d(TAG,"onDataSet: mm/dd/yyyy " + year + "/" + month + "/" + dayOfMonth );
                String date = year + "/" + month + "/" + dayOfMonth;
                endDate.setText(date);
            }
        };

        final Button button = findViewById(R.id.buttonSave);

        //NU MERGE
        if(RadioButton1.isChecked()) {
            loc = "City Break";
        }
        if(RadioButton2.isChecked()) {
            loc = "Mountains";
        }
        if(RadioButton3.isChecked()) {
            loc = "Sea Side";
        }

        Slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                SliderValue.setText(String.valueOf(progress) + "$");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(EditViewTripName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    trip = EditViewTripName.getText().toString() + "-" + EditViewTripDestination.getText().toString() + "-" +
                            loc + "-" + method(SliderValue.getText().toString()) + "-" + startDate.getText() + "-" + endDate.getText() + "-" + RatingBar.getRating();
                    /*Toast.makeText(
                            getApplicationContext(),
                            trip,
                            Toast.LENGTH_LONG).show();
                    replyIntent.putExtra(EXTRA_REPLY, trip);*/
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }

    public void cancelOnClick(View view) {
        finish();
    }

    static final int CAMERA_INTENT = 2;
    private int PICK_IMAGE_REQUEST = 1;
    public void takePicture(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePictureIntent, CAMERA_INTENT);

    }

    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImagePreview = findViewById(R.id.imagePreview);
        if (requestCode == CAMERA_INTENT && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImagePreview.setImageBitmap(imageBitmap);
        }


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                ImagePreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveImageFromGallery(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

}
