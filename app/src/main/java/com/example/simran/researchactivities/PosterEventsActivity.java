package com.example.simran.researchactivities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simran on 10/3/16.
 */
public class PosterEventsActivity extends AppCompatActivity {

//    private Firebase firebase;
//    private ImageView imageView;
//    TouchImageView tv;
//    RecyclerView recyclerView;
//    private ScaleGestureDetector scaleGestureDetector;
//    private Matrix matrix = new Matrix();
//    StorageReference storageRef;
//    FirebaseApp app = FirebaseApp.getInstance();
//    FirebaseStorage storage = FirebaseStorage.getInstance(app);
//    static final int RC_PHOTO_PICKER = 1;
//    Firebase ref = new Firebase("https://console.firebase.google.com/project/firebase-researchactivities/storage/files");

//    Button btn;
//    ImageView img;
//
//    static final int CAMERA_REQUEST_CODE = 1;
//    StorageReference mStorage;
//    ProgressDialog mProgress;

//    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    Toolbar toolbar;
    List<PosterEventObject> rowListItem;
    List<PosterEventObject> allItems = new ArrayList<PosterEventObject>();

    private Button mSelectImage;
    private StorageReference mStorage, mref;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgressDialog;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    PosterEventRecycleAdapter adapter;
    private Firebase firebase;


    private Drawable getColoredArrow()
    {
        Drawable arrowDrawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        Drawable wrapped = DrawableCompat.wrap(arrowDrawable);

        if (arrowDrawable != null && wrapped != null) {
            // This should avoid tinting all the arrows
            arrowDrawable.mutate();
            DrawableCompat.setTintList(wrapped, ColorStateList.valueOf(this.getResources().getColor(R.color.white)));
        }
        return wrapped;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_events);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getColoredArrow());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);

        rowListItem = initialList();

//        final PosterEventRecycleAdapter adapter=new PosterEventRecycleAdapter(this, rowListItem);
        adapter = new PosterEventRecycleAdapter(this, rowListItem);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
//        // Setup refresh listener which triggers new data loading
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
//        {
//            @Override
//            public void onRefresh()
//            {
//                // Your code to refresh the list here.
//                // Make sure you call swipeContainer.setRefreshing(false)
//                // once the network request has completed successfully.
//                swipeContainer.setRefreshing(false);
//
////                fetchTimelineAsync(0);
////                if(fetched == false)
////                {
////                    Toast.makeText(Seminars.this, "Try again in few seconds",
////                            Toast.LENGTH_LONG).show();
////                    swipeContainer.setRefreshing(false);
////                }
////                if(fetched == true)
////                {
////
////                    adapter.clear();
////                    adapter.addAll(getAllItemList());
////                    addData();
////                    swipeContainer.setRefreshing(false);
////
////                }
//
//            }
//        });
        // Configure the refreshing colors
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

//        mStorage = FirebaseStorage.getInstance().getReference();
//
//        btn = (Button) findViewById(R.id.button);
//        img = (ImageView) findViewById(R.id.image);
//
//        mProgress = new ProgressDialog(this);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, CAMERA_REQUEST_CODE);
//            }
//        });
//

//        tv = (TouchImageView)findViewById(R.id.image);
//        tv.setImageResource(R.drawable.eventposter);
//        recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);
//


        mStorage = storage.getReferenceFromUrl("gs://firebase-researchactivities.appspot.com/");




        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        mProgressDialog = new ProgressDialog(this);
        myFab.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT && resultCode == RESULT_OK)
        {
            //System.out.println("************Inside 1");
            mProgressDialog.setMessage("Uploading Image ..");
            mProgressDialog.show();



            final Uri uri = data.getData();
            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText edittext = new EditText(this);
            final ImageView im = new ImageView(this);
            alert.setTitle("Enter description");
            alert.setView(edittext);
            im.setImageURI(uri);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton)
                {
                    //What ever you want to do with the value
//                    Editable YouEditTextValue = edittext.getText();
                    //OR
                    String youEditTextValue = edittext.getText().toString();
                    allItems.add(0, new PosterEventObject("simran13104@iiitd.ac.in", youEditTextValue , uri));
                    adapter.notifyDataSetChanged();


                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // what ever you want to do with No option.
                }
            });

            alert.show();

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    //System.out.println("************Inside 2");
                    //img.setImageDrawable();
                    //mProgress.dismiss();
                    //Uri downloadUri = taskSnapshot.getDownloadUrl();
                    //Picasso.with(PosterEventsActivity.this).load(downloadUri).fit().centerCrop().into(img);
                    Toast.makeText(PosterEventsActivity.this, "Uploading Finished... ", Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();
                }
            });
//


        }


//        storageRefDownload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener() {
//            @Override
//            public void onSuccess(Uri uri) {
//                // Got the download URL for 'users/me/profile.png'
//            }
//        });

    }




    public List<PosterEventObject> initialList()
    {

        allItems.add(new PosterEventObject("simran2195@gmail.com", "Competition for coders", R.drawable.eventposter));
        allItems.add(new PosterEventObject("simran13104@iiitd.ac.in", "Bio Lecture", R.drawable.i1));
        allItems.add(new PosterEventObject("saloni13104@iiitd.ac.in", "Fest at IIIT Allahabad", R.drawable.i2));
        allItems.add(new PosterEventObject("simran2195@gmail.com", "The Local Train, IIT BHU", R.drawable.i3));
        allItems.add(new PosterEventObject("simran2195@gmail.com", "LabVIEW Hands On Training Session", R.drawable.i4));


        return allItems;
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
//        {
//            mProgress.setMessage("Uploading Image ..");
//            mProgress.show();
//            Uri uri = data.getData();
//            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
//            {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
//                {
//                    mProgress.dismiss();
//                    Toast.makeText(PosterEventsActivity.this, "Uploading Finished... ", Toast.LENGTH_LONG).show();
//
//                }
//            });
//        }
//
//    }
//    private void previewStoredFirebaseImage()
//    {
//        firebase.child("gs://firebase-researchactivities.appspot.com/").addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                String base64Image = (String) snapshot.getValue();
//                byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);
//                tv.setImageBitmap(
//                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
//                );
//                System.out.println("Downloaded image with length: " + imageAsBytes.length);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError error) {
//            }
//        });
//    }
//
//




//        list  = (ListView)findViewById(R.id.list);
//        img = (ImageView)findViewById(R.id.img);

//        Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
//        byte[] byteArray = baos.toByteArray();
//        encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
//
//        FirebaseListAdapter<PosterEventObject> ad = new FirebaseListAdapter<PosterEventObject>
//                (PosterEventsActivity.this,PosterEventObject.class,R.layout.poster_event_card,ref)
//        {
//            @Override
//            protected void populateView(View view, PosterEventObject data, int i) {
//
//                PosterEventObject d = new PosterEventObject();
//
//                byte[] dec = Base64.decode(encodedImage,Base64.DEFAULT);
//                Bitmap decodeByte = BitmapFactory.decodeByteArray(dec, 0, dec.length);
//                tv.setImageBitmap(decodeByte);
//
//            }
//        };
//        recyclerView.setAdapter(ad);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        messagesList.setHasFixedSize(false);
//        messagesList.setLayoutManager(layoutManager);


//        Firebase.setAndroidContext(this);
//        firebase = new Firebase("https://*firebase-url*.firebaseio.com/");


//    }



//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
//            Uri selectedImageUri = data.getData();
//
//            // Get a reference to the location where we'll store our photos
//            storageRef = storage.getReference("chat_photos");
//            // Get a reference to store file at chat_photos/<FILENAME>
//            final StorageReference photoRef = storageRef.child(selectedImageUri.getLastPathSegment());
//
//            // Upload file to Firebase Storage
//            photoRef.putFile(selectedImageUri)
//                    .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>()
//                    {
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
//                        {
//                            // When the image has successfully uploaded, we get its download URL
//                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                            // Set the download URL to the message box, so that the user can send it to the database
//                        }
//                    });
//        }
//    }


//    // Use a custom ViewHolder populated from the chat message
//    public class PosterEventViewHolder extends RecyclerView.ViewHolder
//    {
//        private static final String TAG = "PosterEventViewHolder";
//        private final Activity activity;
//
//        TextView postedBy, description;
//        TouchImageView image;
//        String imageString;
//
//        public PosterEventViewHolder(Activity activity, View itemView)
//        {
//            super(itemView);
//            this.activity = activity;
//            postedBy = (TextView) itemView.findViewById(R.id.postedByText);
//            description = (TextView) itemView.findViewById(R.id.descriptionLabel);
//            image = new TouchImageView(activity);
//            ((ViewGroup)itemView).addView(image);
//
//        }
//
//        public void bind(PosterEventObject object)
//        {
//            postedBy.setText(object.getPostedBy());
//            if (imageString.startsWith("https://firebasestorage.googleapis.com/") || imageString.startsWith("content://")) {
//                description.setVisibility(View.INVISIBLE);
//                image.setVisibility(View.VISIBLE);
//                // Use Glide (you could use Picaso) to load the image
//                Glide.with(activity)
//                        .load(imageString)
//                        .into(image);
//            }
//            else {
//                description.setVisibility(View.VISIBLE);
//                image.setVisibility(View.GONE);
//                description.setText(object.getDescription());
//            }
//        }
//    }





//    private void storeImageToFirebase()
//    {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 8; // shrink it down otherwise we will use stupid amounts of memory
//        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoUri.getPath(), options);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] bytes = baos.toByteArray();
//        String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
//
//        // we finally have our base64 string version of the image, save it.
//        firebase.child("pic").setValue(base64Image);
//        System.out.println("Stored image with length: " + bytes.length);
//    }

//    private void previewStoredFirebaseImage() {
//        firebase.child("pic").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                String base64Image = (String) snapshot.getValue();
//                byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);
//                mThumbnailPreview.setImageBitmap(
//                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
//                );
//                System.out.println("Downloaded image with length: " + imageAsBytes.length);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError error) {}
//        });
//    }



//    uploadTask = storageRef.child("images/mountains.jpg").putFile(file);
//
//// Pause the upload
//    uploadTask.pause();
//
//// Resume the upload
//    uploadTask.resume();
//
//// Cancel the upload
//    uploadTask.cancel();



}
