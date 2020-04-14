package uk.co.edwardquixote.Zalego.Broadcasts;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //  We declare our broadcast receiver's class instance object as a class variable.
    private AirplaneModeBroadcastReceiver airplaneModeBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Here we initialize our Button UI widget
        //  We then set an OnClickListener to it; notice that the OnClickListener is created separately outside the "onCreate()" method.
        Button btnSendLocalBroadcast = (Button) this.findViewById(R.id.btnSendLocalBroadcast);
        btnSendLocalBroadcast.setOnClickListener(clickListenerMainActivity);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //  We call our method to register our broadcast receiver here in onResume() method of our MainActivity.
        registerAirplaneModeBroadcastReceiver();

    }

    @Override
    protected void onPause() {
        super.onPause();

        //  We call the method to unregister our broadcast receiver here in onPause() method of our MainActivity.
        unregisterAirplaneModeBroadcastReceiver();

    }


    //  Here's our method where we'll register our AirplaneModeBroadcastReceiver
    private void registerAirplaneModeBroadcastReceiver() {

        //  Here we create a class instance object of our BroadcastReceiver class.
        airplaneModeBroadcastReceiver = new AirplaneModeBroadcastReceiver();

        //  Here we create an IntentFilter and set an action to it.
        //  We want to receive a broadcast when Airplane Mode status changes; ON or OFF
        //  So we set that action to our IntentFilter so we can receive broadcasts specific to that event alone.
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        //  Here we call "registerReceiver()" on our Application's context.
        getApplicationContext().registerReceiver(airplaneModeBroadcastReceiver, intentFilter);

    }

    //  Here's the method where we'll unregister our AirplaneModeBroadcastReceiver
    private void unregisterAirplaneModeBroadcastReceiver() {

        //  We first with checking if the class object of our receiver is NULL
        //  If TRUE, then we initialize it again.
        if (airplaneModeBroadcastReceiver == null) {
            airplaneModeBroadcastReceiver = new AirplaneModeBroadcastReceiver();
        }

        //  We then call "unregisterReceiver()" against our Application context.
        getApplicationContext().unregisterReceiver(airplaneModeBroadcastReceiver);

    }


    //  Here's out method to create an intent to wrap our broadcast.
    //  Then send that broadcast.
    private void createIntentAndSendBroadcast() {

        //  Here we create our broadcast intent and set an action to it
        //  We also put some data to it, as an Intent Extra.
        Intent intentSendBroadcast = new Intent();
        intentSendBroadcast.setAction("uk.co.edwardquixote.Zalego.Broadcasts.EXAMPLE_LOCAL_BROADCAST");
        intentSendBroadcast.putExtra("EXAMPLE_LOCAL_BROADCAST_DATA", "Hello World!");

        //  Here we send our broadcast.
        this.sendBroadcast(intentSendBroadcast);

    }


    //  Here's our OnClickListener, that we've set to our btnSendLocalBroadcast
    private View.OnClickListener clickListenerMainActivity = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            //  We use a SWITCH Statement to discern which view was clicked.
            switch (view.getId()) {

                case R.id.btnSendLocalBroadcast:

                    //  Handle clicks on Send Local Broadcast button here.

                    createIntentAndSendBroadcast();

                    break;

            }

        }

    };

}
