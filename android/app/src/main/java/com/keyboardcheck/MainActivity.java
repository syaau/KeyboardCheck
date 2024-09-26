package com.keyboardcheck;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;

import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.view.WindowManager;
import android.os.Build;

public class MainActivity extends ReactActivity {
  private SoftInputAssist softInputAssist;

  final Runnable hideUI = new Runnable() {
    @Override
    public void run() {
      View decorView = getWindow().getDecorView();
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

      decorView.setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                      | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                      | View.SYSTEM_UI_FLAG_FULLSCREEN);


      decorView.setFitsSystemWindows(true);
      // getWindow().setFitsSystemWindows(true);

      // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
  };

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) {
      hideUI.run();
    }
  }

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);

    // For Android P and above, make sure we use the cutout notches as well.
    // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
    //   WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    //   layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
    //   getWindow().setAttributes(layoutParams);
    //   // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //   // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    // }

    final Handler handler = new Handler();

    getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
      public void onSystemUiVisibilityChange(int visibility) {
        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
          hideUI.run();

          // handler.postDelayed(hideUI, 0);
        }
      }
    });

    softInputAssist = new SoftInputAssist(this);

  }

  @Override
  protected void onResume() {
    softInputAssist.onResume();
    super.onResume();
  }

  @Override
  protected void onPause() {
    softInputAssist.onPause();
    super.onPause();
  }

  @Override
  public void onDestroy() {
    softInputAssist.onDestroy();
    super.onDestroy();

  }

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "KeyboardCheck";
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
   * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
   * (aka React 18) with two boolean flags.
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new DefaultReactActivityDelegate(
        this,
        getMainComponentName(),
        // If you opted-in for the New Architecture, we enable the Fabric Renderer.
        DefaultNewArchitectureEntryPoint.getFabricEnabled(), // fabricEnabled
        // If you opted-in for the New Architecture, we enable Concurrent React (i.e. React 18).
        DefaultNewArchitectureEntryPoint.getConcurrentReactEnabled() // concurrentRootEnabled
        );
  }
}
