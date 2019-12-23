package ru.lae.gallery;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Lukashevich A.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Cell> galleryList;
    private Context context;

    /**
     * Конструктор
     *
     * @param context - Название файла
     * @param galleryList - все полученные изображения
     */
    public MyAdapter(Context context, ArrayList<Cell> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Заменяет содержимое
     * @param viewHolder - Таблица с изображениями
     * @param position - позиция
     */
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageFromPath(galleryList.get(position).getPath(), viewHolder.img);
    }

    /**
     * Получает ссылку для отрисовки каждого элемента
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
        }
    }

    /**
     *  размер набора данных
     * @return - размер набора данных
     */
    public int getItemCount() {
        return galleryList.size();
    }

    /**
     * Получает изображения и декодирует его
     * @param path - путь к изображению
     * @param image - картинка
     */
    private void setImageFromPath(String path, ImageView image) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            Bitmap myBitmap = ru.lae.gallery.ImageHelper.decodeSampleBitmapFromPath(imgFile.getAbsolutePath(), 200, 200);
            image.setImageBitmap(myBitmap);
        }
    }
}

