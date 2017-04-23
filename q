[1mdiff --git a/app/src/main/AndroidManifest.xml b/app/src/main/AndroidManifest.xml[m
[1mindex 3e560c1..6ebcfef 100644[m
[1m--- a/app/src/main/AndroidManifest.xml[m
[1m+++ b/app/src/main/AndroidManifest.xml[m
[36m@@ -15,7 +15,8 @@[m
                 <category android:name="android.intent.category.LAUNCHER" />[m
             </intent-filter>[m
         </activity>[m
[31m-        <activity android:name=".MainActivity"></activity>[m
[32m+[m[32m        <activity android:name=".MainActivity" />[m
[32m+[m[32m        <activity android:name=".RestaurantActivity"></activity>[m
     </application>[m
 [m
 </manifest>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/mandarker/feed/MainActivity.java b/app/src/main/java/com/mandarker/feed/MainActivity.java[m
[1mindex 6203d27..017ab3f 100644[m
[1m--- a/app/src/main/java/com/mandarker/feed/MainActivity.java[m
[1m+++ b/app/src/main/java/com/mandarker/feed/MainActivity.java[m
[36m@@ -4,8 +4,11 @@[m [mimport android.content.Intent;[m
 import android.support.v7.app.AppCompatActivity;[m
 import android.os.Bundle;[m
 import android.view.View;[m
[32m+[m[32mimport android.view.ViewGroup;[m
 import android.widget.EditText;[m
 [m
[32m+[m[32mimport com.mandarker.feed.classes.OnSwipeTouchListener;[m
[32m+[m
 public class MainActivity extends AppCompatActivity {[m
 [m
     @Override[m
[1mdiff --git a/app/src/main/java/com/mandarker/feed/SwipeActivity.java b/app/src/main/java/com/mandarker/feed/SwipeActivity.java[m
[1mindex 6e7fbb5..388a696 100644[m
[1m--- a/app/src/main/java/com/mandarker/feed/SwipeActivity.java[m
[1m+++ b/app/src/main/java/com/mandarker/feed/SwipeActivity.java[m
[36m@@ -2,7 +2,10 @@[m [mpackage com.mandarker.feed;[m
 [m
 import android.support.v7.app.AppCompatActivity;[m
 import android.os.Bundle;[m
[32m+[m[32mimport android.view.ViewGroup;[m
 [m
[32m+[m[32mimport com.mandarker.feed.classes.OnSwipeTouchListener;[m
[32m+[m[32mimport com.mandarker.feed.classes.Restaurant;[m
 import com.yelp.fusion.client.connection.YelpFusionApi;[m
 import com.yelp.fusion.client.connection.YelpFusionApiFactory;[m
 [m
[36m@@ -10,6 +13,9 @@[m [mpublic class SwipeActivity extends AppCompatActivity {[m
     YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();[m
     YelpFusionApi yelpFusionApi;[m
 [m
[32m+[m[32m    Restaurant[] restaurants;[m
[32m+[m[32m    private int index;[m
[32m+[m
     @Override[m
     protected void onCreate(Bundle savedInstanceState) {[m
         super.onCreate(savedInstanceState);[m
[36m@@ -19,6 +25,18 @@[m [mpublic class SwipeActivity extends AppCompatActivity {[m
         }[m
         catch(Exception e){}[m
 [m
[32m+[m[32m        index = 0;[m
[32m+[m
[32m+[m[32m        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);[m
 [m
[32m+[m[32m        viewGroup.setOnTouchListener(new OnSwipeTouchListener(SwipeActivity.this){[m
[32m+[m[32m            public void onSwipeLeft(){[m
[32m+[m[32m                index++;[m
[32m+[m[32m            }[m
[32m+[m[32m            public void onSwipeRight(){[m
[32m+[m[32m                index++;[m
[32m+[m[32m            }[m
[32m+[m[32m        });[m
     }[m
[32m+[m
 }[m
