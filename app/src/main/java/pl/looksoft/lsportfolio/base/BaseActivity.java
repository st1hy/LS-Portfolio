package pl.looksoft.lsportfolio.base;

import android.support.v7.app.AppCompatActivity;

import pl.looksoft.lsportfolio.application.LsApplication;
import pl.looksoft.lsportfolio.application.inject.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {

    protected final ApplicationComponent getAppComponent() {
        return ((LsApplication) getApplication()).getComponent();
    }

}
