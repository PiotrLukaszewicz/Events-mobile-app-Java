package com.example.eventr_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity
{
    private ImageView sport, camping, cinema, cycling, concerts, theater, fireworks, eSport, massEvents, clubs, meetings, health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        sport = (ImageView) findViewById(R.id.sports);
        camping = (ImageView) findViewById(R.id.camping);
        cinema = (ImageView) findViewById(R.id.cinema);
        cycling = (ImageView) findViewById(R.id.cycling);
        concerts = (ImageView) findViewById(R.id.concerts);
        theater = (ImageView) findViewById(R.id.drama);
        fireworks = (ImageView) findViewById(R.id.fireworks);
        eSport = (ImageView) findViewById(R.id.gamepad);
        massEvents = (ImageView) findViewById(R.id.imprezy_masowe);
        clubs = (ImageView) findViewById(R.id.kluby);
        meetings = (ImageView) findViewById(R.id.talks);
        health = (ImageView) findViewById(R.id.uroda);


        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Sport");
                startActivity(intent);
            }
        });

        camping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Camping");
                startActivity(intent);
            }
        });

        cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Cinema");
                startActivity(intent);
            }
        });

        cycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Cycling");
                startActivity(intent);
            }
        });

        concerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Concerts");
                startActivity(intent);
            }
        });

        theater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Theater");
                startActivity(intent);
            }
        });

        fireworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Fireworks");
                startActivity(intent);
            }
        });

        eSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","E-Sport");
                startActivity(intent);
            }
        });

        massEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Mass Events");
                startActivity(intent);
            }
        });

        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Clubs");
                startActivity(intent);
            }
        });

        meetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Meetings");
                startActivity(intent);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewEventActivity.class);
                intent.putExtra("category","Health and Beauty");
                startActivity(intent);
            }
        });
    }
}
