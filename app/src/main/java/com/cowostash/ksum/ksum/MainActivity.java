package com.cowostash.ksum.ksum;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private WebView mWebView;

    private String mUrl="http://ksumcowostash.surge.sh/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        // Get the activity
        mActivity = MainActivity.this;



        // Get the widgets reference from XML layout
        mWebView = (WebView) findViewById(R.id.web_view);

        // Request to render the web page
        renderWebPage(mUrl);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // Custom method to render a web page
    protected void renderWebPage(String urlToRender) {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Do something on page loading started
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Do something when page loading finished

//                // Check web view back history availability
//                if (mWebView.canGoBack()) {
//                    mButtonBack.setEnabled(true);
//                } else {
//                    mButtonBack.setEnabled(false);
//                }
//
//                // Check web view forward history availability
//                if (mWebView.canGoForward()) {
//                    mButtonForward.setEnabled(true);
//                } else {
//                    mButtonForward.setEnabled(false);
//                }
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
            }
        });

        /*
            public WebSettings getSettings ()
                Gets the WebSettings object used to control the settings for this WebView.

            Returns
                a WebSettings object that can be used to control this WebView's settings
        */
        /*
            WebSettings
                Manages settings state for a WebView. When a WebView is first created, it obtains a
                set of default settings. These default settings will be returned from any getter
                call. A WebSettings object obtained from WebView.getSettings() is tied to the life
                of the WebView. If a WebView has been destroyed, any method call on WebSettings will
                throw an IllegalStateException.
        */

        // Enable the javascript
        mWebView.getSettings().setJavaScriptEnabled(true);

        /*
            public abstract void setAppCacheEnabled (boolean flag)
                Sets whether the Application Caches API should be enabled. The default is false.
                Note that in order for the Application Caches API to be enabled, a valid database
                path must also be supplied to setAppCachePath(String).

            Parameters
                flag : true if the WebView should enable Application Caches
        */
        // Enable the caching for web view
        mWebView.getSettings().setAppCacheEnabled(true);

        /*
            public abstract void setAppCachePath (String appCachePath)
                Sets the path to the Application Caches files. In order for the Application Caches
                API to be enabled, this method must be called with a path to which the application
                can write. This method should only be called once: repeated calls are ignored.

            Parameters
                appCachePath : a String path to the directory containing Application Caches files.
        */
        /*
            public abstract File getCacheDir ()
                Returns the absolute path to the application specific cache directory on the
                filesystem. These files will be ones that get deleted first when the device runs
                low on storage. There is no guarantee when these files will be deleted.

                Note: you should not rely on the system deleting these files for you; you should
                always have a reasonable maximum, such as 1 MB, for the amount of space you consume
                with cache files, and prune those files when exceeding that space.

                The returned path may change over time if the calling app is moved to an adopted
                storage device, so only relative paths should be persisted.

                Apps require no extra permissions to read or write to the returned path,
                since this path lives in their private storage.

            Returns
                The path of the directory holding application cache files.
        */
        /*
            public String getPath ()
                Returns the path of this file.
        */
        // Specify the app cache path
        mWebView.getSettings().setAppCachePath(mContext.getCacheDir().getPath());

        /*
            public abstract void setCacheMode (int mode)
                Overrides the way the cache is used. The way the cache is used is based on the
                navigation type. For a normal page load, the cache is checked and content is
                re-validated as needed. When navigating back, content is not re-validated, instead
                the content is just retrieved from the cache. This method allows the client to
                override this behavior by specifying one of
                    LOAD_DEFAULT,
                    LOAD_CACHE_ELSE_NETWORK,
                    LOAD_NO_CACHE or
                    LOAD_CACHE_ONLY.
                The default value is LOAD_DEFAULT.

            Parameters
                mode : the mode to use
        */
        /*
            public static final int LOAD_DEFAULT
                Default cache usage mode. If the navigation type doesn't impose any specific
                behavior, use cached resources when they are available and not expired, otherwise
                load resources from the network. Use with setCacheMode(int).

            Constant Value: -1 (0xffffffff)
        */
        /*
            public static final int LOAD_CACHE_ELSE_NETWORK
                Use cached resources when they are available, even if they have expired. Otherwise
                load resources from the network. Use with setCacheMode(int).

            Constant Value: 1 (0x00000001)
        */
        /*
            public static final int LOAD_NO_CACHE
                Don't use the cache, load from the network. Use with setCacheMode(int).

            Constant Value: 2 (0x00000002)
        */
        /*
            public static final int LOAD_CACHE_ONLY
                Don't use the network, load from the cache. Use with setCacheMode(int).

            Constant Value: 3 (0x00000003)
        */
        // Set the cache mode
        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        // Render the web page
        mWebView.loadUrl(urlToRender);
    }
    private void goBack( ) {

        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }
    }


    @Override public void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override public void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        goBack();
    }
}
