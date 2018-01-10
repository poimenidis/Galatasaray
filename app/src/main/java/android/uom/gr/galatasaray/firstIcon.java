package android.uom.gr.galatasaray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class firstIcon extends Activity {

    private ImageView img;
    private Animation fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsticon);

        fade = AnimationUtils.loadAnimation(this,R.anim.fade_in_firsticon);
        img = (ImageView) findViewById(R.id.first);
        img.setAnimation(fade);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(firstIcon.this,MainActivity.class);
                firstIcon.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                firstIcon.this.finish();
            }
        }, 2000);

    }
}
