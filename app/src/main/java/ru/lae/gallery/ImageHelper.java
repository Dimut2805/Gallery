package ru.lae.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Класс для декодирования и вычисления размера изображения
 * @author  Lukashevich A.
 */
public class ImageHelper {
    /**
     * Расчитывание размера изображение
     * @param options - декодированное изображение
     * @param reqWidth - Ширина
     * @param reqHeight - Высора
     * @return - размер для отрисовки изображения которое поступило в options
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            int halfWidth = width / 2;
            int halfHeight = height / 2;

            while ((halfWidth / inSampleSize) > reqWidth && (halfHeight / inSampleSize) > reqHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * Декодирование изображения
     *
     * @param pathname путь к файлу
     * @param reqWidth Ширина
     * @param reqHeight Высота
     * @return - декодированное изображение
     */
    public static Bitmap decodeSampleBitmapFromPath(String pathname, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathname, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathname, options);
    }
}