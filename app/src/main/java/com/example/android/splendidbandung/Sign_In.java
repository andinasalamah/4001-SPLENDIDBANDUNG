package com.example.android.splendidbandung;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Sign_In extends AppCompatActivity implements View.OnClickListener {
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 101;
    private View coordinatorLayout;
    GoogleSignInOptions gso;
    GoogleSignInAccount account;
    private int[] images = {R.drawable.tangkuban_parahu, R.drawable.panoramic_view1, R.drawable.kawah_putih_bandung, R.drawable.download};
    //TODO: Get better quality images in portrait

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);
        coordinatorLayout = findViewById(R.id.coordinator_lo);
        //Using carouselView by sayyam (https://github.com/sayyam/carouselview)
        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(images.length);
        carouselView.setImageListener(imageListener);
        //Using Google Services API
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.signin_btn).setOnClickListener(this);
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
            handleSignIn(task);
        }
    }

    private void handleSignIn(Task<GoogleSignInAccount> task) {
        try {
            account = task.getResult(ApiException.class);
            Snackbar.make(coordinatorLayout, "Kamu terdaftar sebagai " + account.getDisplayName(), Snackbar.LENGTH_LONG)
                    .show(); //TODO: lakukan tindakan pemisahan dengan guest untuk bagian ini
        } catch (ApiException e) {
            Snackbar.make(coordinatorLayout, "Login gagal! " + e.getStatusCode(), Snackbar.LENGTH_INDEFINITE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Snackbar.make(coordinatorLayout, "Kamu terdaftar sebagai " + account.getDisplayName(), Snackbar.LENGTH_LONG)
                    .show(); //TODO: gunakan sesuatu untuk bagian ini
        }

    }

    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }
    };

}
