package ru.ostapenkoyanko.myflashlight;

import android.content.Context;
import android.hardware.camera2.CameraManager;

public class FlashClass {
    private boolean flash_on = false;
    public boolean light = true;
    private Context context;
    public FlashClass(Context context) {
        this.context = context;
    }

    public void flashOn(){
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            try {
                cameraManager.setTorchMode(cameraId, true);
                light = true;
            }
            catch (Exception e){
                light = false;

            }
            flash_on = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ОШИБКА: " + e);
        }
    }
    public void flashOff(){
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            try {
                cameraManager.setTorchMode(cameraId, false);
                light = true;
            }
            catch (Exception e){
                light = false;
            }
            flash_on = false;
        } catch (Exception e) {
        }
    }
    public boolean isFlash_on() {
        return flash_on;
    }
}