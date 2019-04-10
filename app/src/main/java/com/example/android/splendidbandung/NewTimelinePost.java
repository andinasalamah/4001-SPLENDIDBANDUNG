package com.example.android.splendidbandung;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public class NewTimelinePost extends AppCompatActivity {
    private static final String TAG = "NewMenuActivity";
    private DatabaseReference mDatabase;
    private StorageReference mStorageReference;
    private EditText isi;
    private ImageView imagePost;
    private Button btn_simpan;
    private MenuAdapter mAdapter;
    private ProgressBar progressBar;
    private Uri mImageUri, downloadUri;
    private static final int PICK_IMAGE_REQ_CODE = 1337;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timeline_post);
        imagePost = findViewById(R.id.new_imageview_pos);
        isi = findViewById(R.id.new_edittext_pos);
        btn_simpan = findViewById(R.id.new_btn_simpan);
        progressBar = findViewById(R.id.new_progressbar_progress);
        mStorageReference = FirebaseStorage.getInstance().getReference("timeline");
        mDatabase = FirebaseDatabase.getInstance().getReference("timeline");
        imagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(isi.getText().toString())) {
                    Toast.makeText(NewTimelinePost.this, "Kolom wajib diisi!", Toast.LENGTH_LONG)
                            .show();
                    isi.setError("Kolom wajib diisi!");
                    return;
                }
                upload();
            }
        });

    }

    private void upload() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageReference.child(String.valueOf(System.currentTimeMillis() + getFileExtension(mImageUri)));
            final UploadTask uploadTask = fileReference.putFile(mImageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw Objects.requireNonNull(task.getException());
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        String taskResult = String.valueOf(task.getResult());
                        String desc = isi.getText().toString();
                        String uid = FirebaseAuth.getInstance().getUid();
                        String nama = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        TimeZone tz = TimeZone.getTimeZone("GMT+7");
                        dateFormat.setTimeZone(tz);
                        timeFormat.setTimeZone(tz);
                        String date = dateFormat.format(Calendar.getInstance().getTime());
                        String time = timeFormat.format(Calendar.getInstance().getTime());
                        if (uid == null || nama == null) {
                            Toast.makeText(NewTimelinePost.this, "Kamu belum login!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(NewTimelinePost.this, Sign_In.class));
                            finish();
                        }
                        Map<String, Object> taskMap = new HashMap<>();
                        try {

                            if (nama != null) {
                                taskMap.put("nama", nama);
                            }
                            taskMap.put("jam", time);
                            taskMap.put("image", taskResult);
                            taskMap.put("isi", desc);
                            taskMap.put("tanggal", date);
                            mDatabase.child(uid).updateChildren(taskMap);
                            finish();
                        } catch (NullPointerException e) {
                            Toast.makeText(NewTimelinePost.this, "Error! " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
        } else {
            String desc = isi.getText().toString();
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            String uid = user.getUid();
            String nama = user.getDisplayName();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String date = dateFormat.format(Calendar.getInstance().getTime());
            String timeMillis = String.valueOf(System.currentTimeMillis()+uid);
            if (uid == null || nama == null) {
                Toast.makeText(NewTimelinePost.this, "Kamu belum login!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(NewTimelinePost.this, Sign_In.class));
                finish();
            }

            Map<String, Object> taskMap = new HashMap<>();
            try {
                if (nama != null) {
                    taskMap.put("nama", nama);
                }
                taskMap.put("isi", desc);
                taskMap.put("tanggal", date);
                mDatabase.child(timeMillis).updateChildren(taskMap);
                finish();
            } catch (NullPointerException e) {
                Toast.makeText(NewTimelinePost.this, "Error! " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void selectImage() {
        Intent intent = new Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_IMAGE_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQ_CODE && resultCode == RESULT_OK && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this)
                    .load(mImageUri)
                    .into(imagePost);

        }
    }
}
