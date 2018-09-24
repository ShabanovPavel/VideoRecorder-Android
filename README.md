Installation for React-Native

1. Installing the module

        npm install --save git+ssh://git@github.com:ShabanovPavel/VideoRecorder-Android.git


2. Pulling dependencies

        yarn


3. In  AndroidManifest.xml add 

    Permissions 

        <uses-feature android:name="android.hardware.camera" />

        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.WRITE_INTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.CAMERA" />

    And register the receiver to a mechanical volume button
    
        <receiver android:name="com.shp.video_recorder.VolumeBroadcast" >
            <intent-filter>
                <action android:name="android.intent.action.MEDIA_BUTTON" />
                <action android:name="android.media.VOLUME_CHANGED_ACTION" /> 
            </intent-filter>
        </receiver>


4. In Build.gradle ( ../android/app/) add

        dependencies {
            ...
            compile project(':shp-video-recorder')
            ...
        }


5. In Settings.gradle add

        include ':shp-video-recorder'
        project(':shp-video-recorder').projectDir = new File(rootProject.projectDir, '../node_modules/shp-video-recorder/android')


6. In MainApplication.java add
    
        import com.shp.video_recorder.PackageVideoRecord;

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                ...
                new PackageVideoRecord()
                ...
            );
        }


7. In MainActivity.java add 

        import com.shp.video_recorder.VideoRecorder;
        import android.view.KeyEvent;
        
        class MainActivity extends ReactActivity {
            ...
            @Override 
            public boolean onKeyDown(int keyCode, KeyEvent event) {
                VideoRecorder.onKeyDown(keyCode, event);
                super.onKeyDown(keyCode, event);
                return true;
            }
            ... 
        }


8. Using

```javascript

        import {VideoRecorder} from 'shp-video-recorder';

        componentDidMount(){
            //Be sure to initialize the camera
            VideoRecorder.setting();
            //You can configure the camera. Accepts the following settings
            //Image resolution
            VideoRecorder.putSetting(VideoRecorder.VIDEO_RESOLUTION,"480x320");
            //Image refresh rate
            VideoRecorder.putSetting(VideoRecorder.VIDEO_FPS,"60");
            //Bitrate 
            VideoRecorder.putSetting(VideoRecorder.VIDEO_BITRATE,"600000");
            //Duration of recording
            VideoRecorder.putSetting(VideoRecorder.VIDEO_DURATION,"10");
            //Record Quality (VIDEO_QUALITY_LOW or VIDEO_QUALITY_HIGH)
            VideoRecorder.putSetting(VideoRecorder.VIDEO_QUALITY,VideoRecorder.VIDEO_QUALITY_LOW);
        }

        //Start
        let rez=await VideoRecorder.start();

        //Stop
        let rez=await VideoRecorder.stop();

        //Make a photo during shooting, also the photo is made by pressing the volume button
        let rez=await VideoRecorder.takePicture();

```


