package com.example.dong.jsongitapi;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class LoadData extends AsyncTask<Void, Void, List<ItemGit>> {

    private Context mContext;
    private ILoadData mILoadData;
    private List<ItemGit> mItemGits = new ArrayList<>();

    public LoadData(Context context, ILoadData loadData) {
        this.mContext = context;
        this.mILoadData = loadData;
    }

    @Override
    protected List<ItemGit> doInBackground(Void... voids) {
        JsonArrayRequest request = new JsonArrayRequest(Constants.URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Convert json array to jsonobject
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject item = response.getJSONObject(i);

                                ItemGit itemGit = new ItemGit();
                                itemGit.setName(item.getString("name"));
                                itemGit.setFullName(item.getString("full_name"));
                                itemGit.setUrl(item.getString("url"));
                                mItemGits.add(itemGit);
                            }
                        } catch (Exception ex) {
                            Log.e(TAG, ex.toString());
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e(TAG, error.toString());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(request);
        return mItemGits;
    }

    @Override
    protected void onPostExecute(List<ItemGit> itemGits) {
        super.onPostExecute(itemGits);
        mILoadData.loadData(itemGits);
    }

}
