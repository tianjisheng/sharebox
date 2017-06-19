package com.tian.sharebox.func.funcScan;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.widget.LoadingToastView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/25
 * @describe
 */

public class InputCodeActivity extends Activity
{
    private Toolbar mToolbar;
    private EditText inputCodeEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_qrcode);
        mToolbar = (Toolbar) findViewById(R.id.activity_input_code_toolbar);
        mToolbar.setNavigationIcon(R.drawable.up_arrow_style);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                handleBack();
            }
        });
        ((TextView) findViewById(R.id.activity_input_code_toolbar_title_text)).setText(this.getTitle());
        inputCodeEdit = (EditText) findViewById(R.id.input_code_edit);
    }

    @Override
    public void onBackPressed()
    {
        handleBack();
    }

    private void handleBack()
    {
        if (loadingToastView != null && loadingToastView.isShown())
        {
            loadingToastView.hide();
        } else
        {
            super.onBackPressed();
        }

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        open = false;
        closeFlashLight();
    }

    private LoadingToastView loadingToastView = null;

    boolean open = false;

    public void onBtnClick(View view)
    {
        if (view.getId() == R.id.activity_input_box_unlock)
        {
            if (loadingToastView == null)
            {
                loadingToastView = (LoadingToastView) findViewById(R.id.loading_toast_view);
            }
            if (!loadingToastView.isShown())
            {
                //开启解锁
                ActivityRoute.dispatcherActivity(ActivityRoute.InputCodeActivity,ActivityRoute.BorrowActivity,inputCodeEdit.getText().toString());
                finish();
            } else
            {
                Toast.makeText(this, "正在解锁，请稍后", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.activity_input_flash_light_btn)
        {
            open = !open;
            if (open)
            {
                openFlashLight();
            } else
            {
                closeFlashLight();
            }
            view.setSelected(open);
        }
    }

    private Camera mCamera = null;

    private void openFlashLight()
    {
        if (mCamera == null)
        {
            boolean res = initCamera();
            if (!res)
            {
                Toast.makeText(this, "打开摄像头出错", Toast.LENGTH_SHORT).show();
            }
        }
        if (flashLightAvailable())
        {
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        }
    }

    private void closeFlashLight()
    {
        if (flashLightAvailable())
        {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private boolean flashLightAvailable()
    {
        return mCamera != null && this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    private boolean initCamera()
    {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int cameraId = 0; cameraId < Camera.getNumberOfCameras(); cameraId++)
        {
            Camera.getCameraInfo(cameraId, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK)
            {
                return startCameraById(cameraId);
            }
        }
        return false;

    }

    private boolean startCameraById(int cameraId)
    {
        try
        {
            mCamera = Camera.open(cameraId);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
