package com.uiauto;

import java.io.FileNotFoundException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import android.os.Bundle;
import android.os.RemoteException;
import utils.ImgCompare;
import utils.Common;

public class ShotCompare extends UiAutomatorTestCase {
	Common common = new Common();

	public void imgCompare() throws RemoteException, UiObjectNotFoundException, FileNotFoundException {
		System.out.println("调用________________________ShotCompare");
		
		Bundle bundle = getParams();
		String runcount = bundle.getString("runcount");
		sleep(25000);// 等待呼叫的时间20秒
		String picName = "The_" + runcount + "_time_call_Rate_" + common.getDate();
		common.takeshot(getUiDevice(), getClass().getName(), picName);
		sleep(500);
		if (ImgCompare.sameAs("/storage/sdcard0/uiauto/CallBefore/Incall.png",
				"/storage/sdcard0/uiauto/CallTwo/" + picName + ".png", 0.95)
				|| ImgCompare.sameAs("/storage/sdcard0/uiauto/CallBefore/Incallblack.png",
						"/storage/sdcard0/uiauto/CallTwo/" + picName + ".png", 0.99)) {// 桌面截图和黑屏截图两次对比确认未收到来电
			System.out.println("第" + runcount + "次未接到来电-" + common.getDate());
		} else {
			System.out.println("第" + runcount + "次接到来电");
		}

	}
}
