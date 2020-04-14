package uk.co.edwardquixote.Zalego.Broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

//  Here's our "AirplaneModeBroadcastReceiver" class.
//  It must extend "BroadcastReceiver" class for it to work as a Broadcast Receiver component.
public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //  Here we get the data passed via the broadcast Intent
        //  We then show a Toast to the user.
        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
        if (isAirplaneModeOn) {
            Toast.makeText(context, "Airplane mode is ON!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Airplane mode is OFF!", Toast.LENGTH_LONG).show();
        }

    }

}
