package com.tian.sharebox.func.funcScan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.func.funcBorrow.BorrowActivity;
import com.tian.sharebox.utils.LogUtil;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/19
 * @describe 扫描二维码界面
 */

public class ScanActivity extends Activity implements QRCodeView.Delegate
{
    private Toolbar mToolbar;
    private static final String TAG = "ScanActivity";

    private QRCodeView mQRCodeView;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mToolbar = (Toolbar) findViewById(R.id.activity_scan_toolbar);
        mToolbar.setNavigationIcon(R.drawable.up_arrow_style);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                handleBack();
            }
        });
        ((TextView) findViewById(R.id.activity_scan_toolbar_title_text)).setText(this.getTitle());
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mQRCodeView.showScanRect();
    }

    int requestCode = 1;

    @Override
    protected void onResume()
    {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, requestCode);
        } else
        {
            mQRCodeView.startSpot();
        }

    }

    @Override
    protected void onStop()
    {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate()
    {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result)
    {
        LogUtil.i(TAG, "result:" + result);
        ActivityRoute.dispatcherActivity(ActivityRoute.ScanActivity,ActivityRoute.BorrowActivity, result);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError()
    {
        Log.e(TAG, "打开相机出错");
    }

    boolean open = false;

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.flash_light_btn:
                flashLight();
                break;
            case R.id.qrcode_input_btn:
                Intent intent = new Intent(this, InputCodeActivity.class);
                this.startActivity(intent);
                break;
            default:

        }
    }

    private void flashLight()
    {
        open = !open;
        if (open)
        {
            mQRCodeView.openFlashlight();
        } else
        {
            mQRCodeView.closeFlashlight();
        }
        findViewById(R.id.flash_light_btn).setSelected(open);
    }

    private void handleBack()
    {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == 1)
        {
            if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                mQRCodeView.startSpot();
            }
        } else if (requestCode == 2)
        {
            if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                flashLight();
            }
        }
    }

}