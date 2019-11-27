package com.kru.batfinder2;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.kru.batfinder2.data.DataManager;
import com.kru.batfinder2.interfaces.BatFinderApi;
import com.kru.batfinder2.models.BatDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SynchronizationService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_SYNC_BATS = "com.kru.batfinder2.action.SYNC_BATS";
    public static final String BAT_INFO = "com.kru.batfinder2.action.BAT_INFO";
    public static final String BAT_LIST = "com.kru.batfinder2.action.BAT_LIST";

    public SynchronizationService() {
        super("SynchronizationService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFoo(Context context) {
        Intent intent = new Intent(context, SynchronizationService.class);
        intent.setAction(ACTION_SYNC_BATS);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SYNC_BATS.equals(action)) {
                handleActionSyncBats();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSyncBats() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(this.getString(R.string.apiurl))
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        BatFinderApi client = retrofit.create(BatFinderApi.class);
        Call<List<BatDTO>> call = client.loadBats();

        call.enqueue(new Callback<List<BatDTO>>() {
            @Override
            public void onResponse(Call<List<BatDTO>> call, Response<List<BatDTO>> response) {
                onSuccessResponse(response.body());
                DataManager.getInstance().updateBatList(response.body());
            }

            @Override
            public void onFailure(Call<List<BatDTO>> call, Throwable t) {
                onErrorResponse();
            }
        });
    }

    private void onSuccessResponse(List<BatDTO> list) {
        Intent intent = new Intent();
        intent.setAction(BAT_INFO);
        intent.putExtra(BAT_LIST, true);
        DataManager.getInstance().updateBatList(list);
        sendBroadcast(intent);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }



    private void onErrorResponse(){
        Intent intent = new Intent();
        intent.setAction(BAT_INFO);
        intent.putExtra(BAT_LIST, false);
        sendBroadcast(intent);
    }
}
