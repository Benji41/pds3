package com.equipoDinamita.covidAmigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.equipoDinamita.Interface.API;
import com.equipoDinamita.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profile extends AppCompatActivity {

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://covid-amigo-pds3.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button signout;
    //String email;
    //User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        signout = (Button) findViewById(R.id.signOut);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profile.this, MainActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView NameTextView = (TextView) findViewById(R.id.Name);
        final TextView ageTextView = (TextView) findViewById(R.id.age);
        final TextView emailTextView = (TextView) findViewById(R.id.email);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String Name = userProfile.getUs_f_name() + userProfile.getUs_l_name();
                    String email = userProfile.getId_us();
                    int age = userProfile.getUs_age();

                    greetingTextView.setText("Welcome," + Name+ "!");
                    NameTextView.setText(Name);
                    emailTextView.setText(email);
                    ageTextView.setText(age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "Algo salio mal!", Toast.LENGTH_LONG).show();

            }


            public void manual(View view){

        //Intent i = new Intent(this, )

    }



    });

}

    public void menu(View view) {
        Intent i = new Intent(profile.this, menu.class);
        startActivity(i);
    }

    public void logout(View view) {
        Intent sig = new Intent(profile.this, MainActivity.class);
        startActivity(sig);
    }

    public void misEventos(View view) {
        Intent i = new Intent(profile.this, mis_eventos.class);
        startActivity(i);
    }
}