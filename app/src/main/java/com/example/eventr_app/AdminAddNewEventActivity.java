package com.example.eventr_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewEventActivity extends AppCompatActivity
{
    private String CategoryName, Name, Place, Time, Date, Limit, Price, saveCurrentDate, saveCurrentTime;
    private Button AddNewEventButton;
    private ImageView InputEventImage;
    private EditText InputEventName, InputEventPlace, InputEventTime, InputEventDate, InputEventLimit,InputEventPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String eventRandomKey, downloadImageUrl;
    private StorageReference EventImageRef;
    private DatabaseReference EventRef;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_event);

        CategoryName = getIntent().getExtras().get("category").toString();
        EventImageRef = FirebaseStorage.getInstance().getReference().child("Event Images");
        EventRef = FirebaseDatabase.getInstance().getReference().child("Events");

        AddNewEventButton = (Button) findViewById(R.id.add_new_event);
        InputEventImage = (ImageView) findViewById(R.id.select_event_image);
        InputEventName = (EditText) findViewById(R.id.txtNameOfEvent);
        InputEventPlace = (EditText) findViewById(R.id.txtPlace);
        InputEventTime = (EditText) findViewById(R.id.txtTime);
        InputEventDate = (EditText) findViewById(R.id.txtDate);
        InputEventLimit = (EditText) findViewById(R.id.txtAmountPpl);
        InputEventPrice = (EditText) findViewById(R.id.price);
        loadingBar = new ProgressDialog(this);

        InputEventImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OpenGallery();
            }
        });

        AddNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidateEventData();
            }
        });
    }



    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            InputEventImage.setImageURI(ImageUri);
        }
    }

    private void ValidateEventData()
    {
        Name = InputEventName.getText().toString();
        Place = InputEventPlace.getText().toString();
        Time = InputEventTime.getText().toString();
        Date = InputEventDate.getText().toString();
        Limit = InputEventLimit.getText().toString();
        Price = InputEventPrice.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Zdjęcie wydarzenia jest wymagane", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Name))
        {
            Toast.makeText(this, "Proszę wpisać nazwę wydarzenia", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(Place))
        {
            Toast.makeText(this, "Proszę wpisać miejsce wydarzenia", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(Time))
        {
            Toast.makeText(this, "Proszę wpisać godzinę ", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(Date))
        {
            Toast.makeText(this, "Proszę wpisać datę wydarzenia", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(Limit))
        {
            Toast.makeText(this, "Proszę wpisać limit miejsc", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Proszę wpisać cenę za wejście. W przypadku darmowych wydarzeń wpisz 0", Toast.LENGTH_SHORT).show();

        }
        else
        {
            StoreEventInformation();
        }
    }

    private void StoreEventInformation()
    {
        loadingBar.setTitle("Dodawanie nowego wydarzenia");
        loadingBar.setMessage("Prosze czekać, dodajemy nowe wydarzenie");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();

        //unikalny klucz dzięki dacie i czasie (zawsze inny dla każdego dodanego wydarzenia
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        eventRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = EventImageRef.child(ImageUri.getLastPathSegment() + eventRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AdminAddNewEventActivity.this, "Błąd: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AdminAddNewEventActivity.this, "Zdjęcie wydarzenia załadowane poprawnie " , Toast.LENGTH_SHORT).show();
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();
                            Toast.makeText(AdminAddNewEventActivity.this, "Zdjęcie wydarzenia zapisane poprawnie",Toast.LENGTH_SHORT).show();
                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });

    }

    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> eventMap = new HashMap<>();
        eventMap.put("pid", eventRandomKey);
        eventMap.put("category", CategoryName);
        eventMap.put("date", saveCurrentDate);
        eventMap.put("time", saveCurrentDate);
        eventMap.put("name", Name);
        eventMap.put("image", downloadImageUrl);
        eventMap.put("place", Place);
        eventMap.put("eTime", Time);
        eventMap.put("eDate", Date);
        eventMap.put("limit", Limit);
        eventMap.put("price", Price);

        EventRef.child(eventRandomKey).updateChildren(eventMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(AdminAddNewEventActivity.this, AdminCategoryActivity.class);
                            startActivity(intent);
                            loadingBar.dismiss();
                            Toast.makeText(AdminAddNewEventActivity.this,"Wydarzenie dodane poprawnie",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AdminAddNewEventActivity.this,"Błąd: " + message,Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
