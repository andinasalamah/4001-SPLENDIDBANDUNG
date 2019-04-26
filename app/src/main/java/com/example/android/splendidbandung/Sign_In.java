package com.example.android.splendidbandung;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Sign_In extends AppCompatActivity implements View.OnClickListener {
    GoogleSignInOptions gso;
    GoogleSignInAccount account;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 101;
    private View coordinatorLayout;
    private int[] images = {R.drawable.foto2, R.drawable.foto4, R.drawable.foto6, R.drawable.foto5, R.drawable.foto7};
    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }
    };
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        coordinatorLayout = findViewById(R.id.coordinator_lo);
        //Using carouselView by sayyam (https://github.com/sayyam/carouselview)
        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(images.length);

        //Using Google Services API
        FirebaseApp.initializeApp(this);
        try {


            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("405184337418-b0gtnl69g7amurfc5mjbr10csjicamg1.apps.googleusercontent.com")
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mAuth = FirebaseAuth.getInstance();
            findViewById(R.id.signin_btn).setOnClickListener(this);
        } catch (Exception e) {
            Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_btn:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signin = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signin, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                account = task.getResult(ApiException.class);
                firebaseAuth();
            } catch (Exception e) {
                Toast.makeText(this, "Error!" + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    private void firebaseAuth() {
        AuthCredential auth = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(auth)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            start();
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Sign_In.this, "Error! " + e, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void start() {
        user = mAuth.getCurrentUser();
        user.getIdToken(false).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
            @Override
            public void onSuccess(GetTokenResult getTokenResult) {
            }
        });
        Intent intent = new Intent(Sign_In.this, MainMenu.class);
        intent.putExtra("DisplayName", user.getDisplayName());
        startActivity(intent);
        finish();
    }

}
