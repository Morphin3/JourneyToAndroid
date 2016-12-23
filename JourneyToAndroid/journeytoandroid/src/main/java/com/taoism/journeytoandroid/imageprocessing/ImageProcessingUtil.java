package com.taoism.journeytoandroid.imageprocessing;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Date: 2016-05-24
 * Time: 19:33
 * Author: cf
 * -----------------------------
 */
public class ImageProcessingUtil {

    // Decodes image and scales it to reduce memory consumption
    public static Bitmap decodeFile(File f, int requiredSize) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE = requiredSize;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    /**
     * ScalingLogic defines how scaling should be carried out if source and
     * destination image has different aspect ratio.
     * <p/>
     * CROP: Scales the image the minimum amount while making sure that at least
     * one of the two dimensions fit inside the requested destination area.
     * Parts of the source image will be cropped to realize this.
     * <p/>
     * FIT: Scales the image the minimum amount while making sure both
     * dimensions fit inside the requested destination area. The resulting
     * destination dimensions might be adjusted to a smaller size than
     * requested.
     */
    public static enum ScalingLogic {
        CROP, FIT
    }

    public static Bitmap decodeFile(File file) {
        Bitmap unscaledBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return unscaledBitmap;
    }

    public static Bitmap decodeFile(Context context, File file, int targeSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(context,
                file,
                targeSize);

        Bitmap scaledBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return scaledBitmap;
    }


    public static int calculateSampleSize(Context context, File oldFile, int targetSize) {
        Bitmap originalBitmap = BitmapFactory.decodeFile(oldFile.getAbsolutePath());

        File unCompressedFile = getFileFromBitmap(context,
                originalBitmap,
                100,
                "temp");

        int inSampleSize = 1;
        if (unCompressedFile != null) {
            inSampleSize = (int) Math.ceil(Math.sqrt((float) unCompressedFile.length() / targetSize));
        }
        int scale = 1;
        while (inSampleSize > scale) {
            scale *= 2;
        }
        inSampleSize = scale;

        originalBitmap.recycle();

        return inSampleSize;

    }


    public static Bitmap decodeFile(File file, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize(options,
                reqWidth,
                reqHeight);
        Bitmap scaledBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        return scaledBitmap;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static File getFileFromBitmap(Context context,
                                         Bitmap bitmap,
                                         int quality,
                                         String fileName) {

        long begin = System.currentTimeMillis();

        //create a file to write bitmap data
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream("temp");
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
//            // PNG is a lossless format, the compression factor (100) is ignored
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        File f = new File(context.getExternalCacheDir(), TextUtils.isEmpty(fileName) ? "temp" : fileName);
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(f));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality /*ignored for PNG*/, out);
            out.flush();
            long end = System.currentTimeMillis();
            Log.i("cost", "BufferedOutputStream 耗时：" + (end - begin) + " ms");
            return f;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


//        File f = new File(context.getExternalCacheDir(), TextUtils.isEmpty(fileName) ? "temp" : fileName);
////        File f = new File(context.getExternalCacheDir(), UUID.randomUUID().toString());
//        try {
////            f.createNewFile();
//
//            //Convert bitmap to byte array
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality /*ignored for PNG*/, bos);
//            byte[] bitmapdata = bos.toByteArray();
//
////write the bytes in file
//            FileOutputStream fos = new FileOutputStream(f);
//            fos.write(bitmapdata);
//            fos.flush();
//            fos.close();
//
//
//            long end = System.currentTimeMillis();
//            Log.i("cost", "FileOutputStream 耗时：" + (end - begin) + " ms");
//            return f;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        return null;

    }


    /**
     * Utility function for decoding an image resource. The decoded bitmap will
     * be optimized for further scaling to the requested destination dimensions
     * and scaling logic.
     *
     * @param res          The resources object containing the image data
     * @param resId        The resource id of the image data
     * @param dstWidth     Width of destination area
     * @param dstHeight    Height of destination area
     * @param scalingLogic Logic to use to avoid image stretching
     * @return Decoded bitmap
     */
    public static Bitmap decodeResource(Resources res, int resId, int dstWidth, int dstHeight,
                                        ScalingLogic scalingLogic) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth,
                options.outHeight,
                dstWidth,
                dstHeight,
                scalingLogic);
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(res, resId, options);

        return unscaledBitmap;
    }

    /**
     * Calculate optimal down-sampling factor given the dimensions of a source
     * image, the dimensions of a destination area and a scaling logic.
     *
     * @param srcWidth     Width of source image
     * @param srcHeight    Height of source image
     * @param dstWidth     Width of destination area
     * @param dstHeight    Height of destination area
     * @param scalingLogic Logic to use to avoid image stretching
     * @return Optimal down scaling sample size for decoding
     */
    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight,
                                          ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                return srcWidth / dstWidth;
            } else {
                return srcHeight / dstHeight;
            }
        } else {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                return srcHeight / dstHeight;
            } else {
                return srcWidth / dstWidth;
            }
        }
    }

}
