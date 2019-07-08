package com.example.eventr_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.eventr_app.Model.Events;
import com.example.eventr_app.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class EventDetailsActivity extends AppCompatActivity {

    private Button addToCartButton;
    private ImageView eventImage;
    private TextView eventCategory, eventDate, eventLimit, eventName, eventPlace, eventPrice;
    private String eventID = "";
    private ElegantNumberButton numberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventID = getIntent().getStringExtra("pid");
        addToCartButton = (Button) findViewById(R.id.event_add_to_cart_button);
        eventImage = (ImageView ) findViewById(R.id.event_image_details);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_btn);
        eventName = findViewById(R.id.event_name_details);
        eventCategory = findViewById(R.id.event_category_details);
        eventLimit = findViewById(R.id.event_limit_details);
        eventPlace = findViewById(R.id.event_place_details);
        eventPrice = findViewById(R.id.event_price_details);
        eventDate = findViewById(R.id.event_date_details);

        getEventDetails(eventID);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addToCartList();
            }
        });
    }

    private void addToCartList()
    {
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("eid",eventID);
        cartMap.put("ename",eventName.getText().toString());
        cartMap.put("price",eventPrice.getText().toString());
        cartMap.put("date",saveCurrentTime);
        cartMap.put("time",saveCurrentDate);
        cartMap.put("category",eventCategory.getText().toString());
        cartMap.put("eDate",eventDate.getText().toString());
        cartMap.put("quantity",numberButton.getNumber());
        cartMap.put("place",eventPlace.getText().toString());
        cartMap.put("limit",eventLimit.getText().toString());

        cartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone())
                .child("Events").child(eventID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            cartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhone())
                                    .child("Events").child(eventID)
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(EventDetailsActivity.this, "Dodano do karty", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(EventDetailsActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                            }

                                        }
                                    });
                        }
                    }
                });
    }

    private void getEventDetails(String eventID)
    {
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference().child("Events");

        eventsRef.child(eventID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists()){

                    Events events = dataSnapshot.getValue(Events.class);

                    eventName.setText("Nazwa wydarzenia: " + events.getName());
                    eventCategory.setText("Kategoria: " + events.getCategory());
                    eventLimit.setText("Limit miejsc: " + events.getLimit());
                    eventPlace.setText("Miejsce: " + events.getPlace());
                    eventPrice.setText("Cena biletu: " + events.getPrice() + " zł");
                    eventDate.setText("Data: " + events.geteDate());
                    Picasso.get().load(events.getImage()).placeholder(R.drawable.logo).into(eventImage);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

}
