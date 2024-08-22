package com.example.sprint2
        ;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HashUploader {
    private static final OkHttpClient client = new OkHttpClient();

    public static void sendHash(String hash, Callback callback) {
        FormBody formBody = new FormBody.Builder()
                .add("hash", hash)
                .build();

        Request request = new Request.Builder()
                .url("https://seuservidor.com/endpoint")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                callback.onFailure("Erro: " + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onSuccess("Hash enviado com sucesso.");
                } else {
                    callback.onFailure("Falha ao enviar hash.");
                }
            }
        });
    }

    public interface Callback {
        void onSuccess(String message);
        void onFailure(String errorMessage);
    }
}
