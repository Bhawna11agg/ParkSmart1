package com.example.parksmart1.ui.tools;
<<<<<<< HEAD
=======

import android.graphics.Bitmap;
import android.graphics.Point;
>>>>>>> 905b08f02222e1f52b28e48eabfa0272d66a4441
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;

>>>>>>> 905b08f02222e1f52b28e48eabfa0272d66a4441
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.parksmart1.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

import static android.content.Context.WINDOW_SERVICE;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;
import android.content.Context;
public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    String TAG = "GenerateQRCode";
    ImageView qrImage;
    Button start, save;
    String inputValue;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        qrImage = (ImageView) root.findViewById(R.id.QR_Image);
        start = (Button) root.findViewById(R.id.generate);
   //     save = (Button) root.findViewById(R.id.save);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText (s);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inputValue = TAG.trim();
                    if (inputValue.length() > 0) {
                        WindowManager manager = (WindowManager) getContext ().getSystemService(WINDOW_SERVICE);
                        Display display = manager.getDefaultDisplay();
                        Point point = new Point();
                        display.getSize(point);
                        int width = point.x;
                        int height = point.y;
                        int smallerDimension = width < height ? width : height;
                        smallerDimension = smallerDimension * 3 / 4;

                        qrgEncoder = new QRGEncoder(
                                inputValue, null,
                                QRGContents.Type.TEXT,
                                smallerDimension);
                        try {
                            bitmap = qrgEncoder.encodeAsBitmap();
                            qrImage.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            Log.v(TAG, e.toString());
                        }
                    } else {
                        //edtValue.setError("Required");
                    }
                }
            });

//                save.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    boolean save;
//                    String result;
//                    try {
//                        save = QRGSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
//                        result = save ? "Image Saved" : "Image Not Saved";
//                        Toast.makeText(getContext (), result, Toast.LENGTH_LONG).show();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        return root;
        }

    }
