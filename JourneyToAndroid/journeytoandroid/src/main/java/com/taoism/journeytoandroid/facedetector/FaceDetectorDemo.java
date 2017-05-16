package com.taoism.journeytoandroid.facedetector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

import java.io.InputStream;

/**
 * Date: 2017-05-12
 * Time: 10:52
 * Author: cf
 * -----------------------------
 */

public class FaceDetectorDemo extends BaseActivity implements View.OnClickListener {

    static {
        TAG = FaceDetectorDemo.class.getSimpleName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_face_detector_demo);

        findViewById(R.id.btn_dect_face).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        releaseDetector();
    }

    private static volatile FaceDetector faceDetector;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dect_face:
                //TODO implement

//                InputStream stream = getResources().openRawResource(R.raw.face_cartoon2);
//                InputStream stream = getResources().openRawResource(R.raw.face_animal3);
//                InputStream stream = getResources().openRawResource(R.raw.face_animal);
//                InputStream stream = getResources().openRawResource(R.raw.face_animal);
//                InputStream stream = getResources().openRawResource(R.raw.face_side2);
                InputStream stream = getResources().openRawResource(R.raw.face_with_closed_eyes);
//                InputStream stream = getResources().openRawResource(R.raw.face_with_sunglasses);
//                InputStream stream = getResources().openRawResource(R.raw.face);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);


                dectWithOrigin(bitmap);


//                detectFace(bitmap, 1);


//                int width = 1000;
//                int height = 1000;
////                int width = original.getWidth();
////                int height = original.getHeight();
//
//                dectWithGooglePlayService(width, height, original);

                break;
        }
    }


    private static final int MAX_FACES = 10;

    public static FaceInfoModel dectWithOrigin(Bitmap origin) {
        return dectWithOrigin(origin, MAX_FACES);
    }

    public static FaceInfoModel dectWithOrigin(Bitmap origin, int maxFaceCount) {

        final FaceDetector.Face[] faces = new FaceDetector.Face[maxFaceCount];

        final Bitmap bitmap565 = convertTo565(origin);

        if (bitmap565 != null) {

            final FaceDetector faceDetector =
                    new FaceDetector(bitmap565.getWidth(), bitmap565.getHeight(), maxFaceCount);

            final int faceCount = faceDetector.findFaces(bitmap565, faces);

            PointF midPointF = new PointF();
            float maxEyeDistance = 0;
            int maxFaceIndex = -1;

            if (faceCount > 0) {
                for (int i = 0; i < faceCount; i++) {

                    FaceDetector.Face face = faces[i];

                    float eyeDistance = face.eyesDistance();

                    if (eyeDistance > maxEyeDistance) {
                        face.getMidPoint(midPointF);
                        maxEyeDistance = eyeDistance;
                        maxFaceIndex = i;
                    }

                    Log.i(TAG, String.format("第%d张脸, 眼睛间距%f,坐标x:%f,y:%f", i, eyeDistance, midPointF.x, midPointF.y));
                }
                Log.i(TAG, String.format("最大眼睛间距:%f,最大脸序号:%d", maxEyeDistance, maxFaceIndex));
//
                return new FaceInfoModel(midPointF,
                        maxEyeDistance,
                        maxFaceIndex);

            }
        }
        return null;
    }

    public static class FaceInfoModel {

        private PointF pointF;
        private float  eyeDistance;
        private int  eyeIndex;

        public FaceInfoModel(PointF pointF, float eyeDistance, int eyeIndex) {
            this.pointF = pointF;
            this.eyeDistance = eyeDistance;
            this.eyeIndex = eyeIndex;
        }

        public float getCenterX(){
            return this.getPointF().x;
        }

        public float getCenterY(){
            return this.getPointF().y;
        }

        public PointF getPointF() {
            return pointF;
        }

        public void setPointF(PointF pointF) {
            this.pointF = pointF;
        }

        public float getEyeDistance() {
            return eyeDistance;
        }

        public void setEyeDistance(float eyeDistance) {
            this.eyeDistance = eyeDistance;
        }

        public int getEyeIndex() {
            return eyeIndex;
        }

        public void setEyeIndex(int eyeIndex) {
            this.eyeIndex = eyeIndex;
        }
    }

    public static Bitmap convertTo565(Bitmap origin) {

        if (origin == null) {

            return null;
        }

        Bitmap result = origin;

        boolean isCopy = false;

        if (origin.getConfig() != Bitmap.Config.RGB_565) {
            isCopy = true;
            result = origin.copy(Bitmap.Config.RGB_565, true);
        } else {
            isCopy = false;
        }

        if ((result.getWidth() & 0x1) != 0) {

            result = Bitmap.createBitmap(result, 0, 0, result.getWidth() & ~0x1,
                    result.getHeight());
        }

        if(isCopy){
            if(origin != null){
                origin.recycle();
                origin = null;
            }
        }

        return result;
    }


//
//    private void detectFace(Bitmap bitmap, double scale) {
//        FaceDetector faceDetector = getFaceDetector(this);
//
//        if (!faceDetector.isOperational()) {
//            //Handle contingency
//        } else {
//            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//
//            SparseArray<Face> faces = faceDetector.detect(frame);
//
//            float left = 0;
//            float top = 0;
//            float right = 0;
//            float bottom = 0;
//
//            float maxArea = 0;
//
//            int maxFaceIndex = -1;
//
//            final int totalFaces = faces.size();
//            if (totalFaces > 0) {
//                for (int i = 0; i < totalFaces; i++) {
//
//                    Face face = faces.get(faces.keyAt(i));
//
//                    left = (float) (face.getPosition().x * scale);
//                    top = (float) (face.getPosition().y * scale);
//                    right = (float) scale * (face.getPosition().x + face.getWidth());
//                    bottom = (float) scale * (face.getPosition().y + face.getHeight());
//
//                    Log.i(TAG, String.format("第%d张脸,left:%f,top:%f,right:%f,bottom:%f", i, left, top, right, bottom));
//
//                    float area = (right - left) * (bottom - top);
//                    if (area > maxArea) {
//                        maxArea = area;
//                        maxFaceIndex = i;
//                    }
//                }
//
//                Log.i(TAG, String.format("最大脸面积:%f,最大脸序号:%d", maxArea, maxFaceIndex));
//
//            } else {
//
//            }
//        }
//
//
//    }
//
//
//
//    private Bitmap dectWithGooglePlayService(int width, int height, Bitmap original) {
//
//
//        if (width == 0 || height == 0) {
//            throw new IllegalArgumentException("width or height should not be zero!");
//        }
//        float scaleX = (float) width / original.getWidth();
//        float scaleY = (float) height / original.getHeight();
//
//        if (scaleX != scaleY) {
//
//            Bitmap.Config config =
//                    original.getConfig() != null ? original.getConfig() : Bitmap.Config.ARGB_8888;
//            Bitmap result = Bitmap.createBitmap(width, height, config);
//
//            float scale = Math.max(scaleX, scaleY);
//
//            float left = 0f;
//            float top = 0f;
//
//            float scaledWidth = width, scaledHeight = height;
//
//            PointF focusPoint = new PointF();
//
//            detectFace(original, focusPoint);
//
//            if (scaleX < scaleY) {
//
//                scaledWidth = scale * original.getWidth();
//
//                float faceCenterX = scale * focusPoint.x;
//                left = getLeftPoint(width, scaledWidth, faceCenterX);
//
//            } else {
//
//                scaledHeight = scale * original.getHeight();
//
//                float faceCenterY = scale * focusPoint.y;
//                top = getTopPoint(height, scaledHeight, faceCenterY);
//            }
//
//            RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);
//            Canvas canvas = new Canvas(result);
//            canvas.drawBitmap(original, null, targetRect, null);
//
//            original.recycle();
//
//            return result;
//        } else {
//            return original;
//        }
//
//
//    }
//
//
//    private void detectFace(Bitmap bitmap, PointF centerOfAllFaces) {
//
//        FaceDetector faceDetector = getFaceDetector(this);
//        if (!faceDetector.isOperational()) {
//            centerOfAllFaces.set(bitmap.getWidth() / 2, bitmap.getHeight() / 2); // center crop
//            return;
//        }
//        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//        SparseArray<Face> faces = faceDetector.detect(frame);
//        final int totalFaces = faces.size();
//        if (totalFaces > 0) {
//            float sumX = 0f;
//            float sumY = 0f;
//            for (int i = 0; i < totalFaces; i++) {
//                PointF faceCenter = new PointF();
//                getFaceCenter(faces.get(faces.keyAt(i)), faceCenter);
//                sumX = sumX + faceCenter.x;
//                sumY = sumY + faceCenter.y;
//
//                Log.i(TAG, String.format("第%d张脸", i + 1));
//            }
//            centerOfAllFaces.set(sumX / totalFaces, sumY / totalFaces);
//            return;
//        }
//        centerOfAllFaces.set(bitmap.getWidth() / 2, bitmap.getHeight() / 2); // center crop}
//    }
//
//    /**
//     * Calculates center of a given face
//     *
//     * @param face   Face
//     * @param center Center of the face
//     */
//    private void getFaceCenter(Face face, PointF center) {
//        float x = face.getPosition().x;
//        float y = face.getPosition().y;
//        float width = face.getWidth();
//        float height = face.getHeight();
//        center.set(x + (width / 2), y + (height / 2)); // face center in original bitmap
//    }
//
//
//    private float getTopPoint(int height, float scaledHeight, float faceCenterY) {
//        if (faceCenterY <= height / 2) { // Face is near the top edge
//            return 0f;
//        } else if ((scaledHeight - faceCenterY) <= height / 2) { // face is near bottom edge
//            return height - scaledHeight;
//        } else {
//            return (height / 2) - faceCenterY;
//        }
//    }
//
//    private float getLeftPoint(int width, float scaledWidth, float faceCenterX) {
//        if (faceCenterX <= width / 2) { // face is near the left edge.
//            return 0f;
//        } else if ((scaledWidth - faceCenterX) <= width / 2) {  // face is near right edge
//            return (width - scaledWidth);
//        } else {
//            return (width / 2) - faceCenterX;
//        }
//    }
//
//
//    private static void initDetector(Context context) {
//        if (faceDetector == null) {
//            synchronized (FaceDetectorDemo.class) {
//                if (faceDetector == null) {
//                    faceDetector = new
//                            FaceDetector.Builder(context)
//                            .setTrackingEnabled(true)
//                            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
//                            .setMode(FaceDetector.ACCURATE_MODE)
//                            .build();
//                }
//            }
//        }
//    }
//
//    public static FaceDetector getFaceDetector(Context context) {
//        initDetector(context);
//        return faceDetector;
//    }
//
//    /**
//     * Release the detector when you no longer need it.
//     * Remember to call PicassoFaceDetector.initialize(context) if you have to re-use.
//     */
//    public static void releaseDetector() {
//        if (faceDetector != null) {
//            faceDetector.release();
//            faceDetector = null;
//        }
////        mContext = null;
//
//    }
}
