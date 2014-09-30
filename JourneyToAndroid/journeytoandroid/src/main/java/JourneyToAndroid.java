import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by chenfei on 14-9-30.
 */
public class JourneyToAndroid extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        String path=intent.getStringExtra("");

        if(path==null){

        }

    }
}
