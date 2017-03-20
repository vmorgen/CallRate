package com.uiauto;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import android.os.Bundle;
import android.os.RemoteException;
import utils.Common;

public class PackageMatch extends UiAutomatorTestCase {
	Common common = new Common();

	@Override
	protected void setUp() throws Exception {
		common.creatFile(getClass().getName());
		common.watcherTheError(getUiDevice(), getClass().getName());// 运行错误报告监听器
	}

	@Override
	protected void tearDown() throws Exception {

	}

	public void testDemo() throws UiObjectNotFoundException, RemoteException {
		try {
			packageMatch();
		} catch (Exception e) {
			common.takeshot(getUiDevice(), getClass().getName());
			e.printStackTrace();
		} finally {
			// getUiDevice().pressHome();
		}
	}

	public void packageMatch() throws Exception {
		Bundle bundle = getParams();
		String runcount = bundle.getString("runcount");
		
		// 如果20秒内没有看到来电界面，则算作失败
		for (int j = 0; j < 40; j++) {
			String CurrentPackageName = getUiDevice().getCurrentPackageName();// UIDevice的方法--获取当前应用的包名
			if (j == 39) {
				if (!getUiDevice().isScreenOn()) {// 截图前避免熄屏
					getUiDevice().wakeUp();
				}
				common.takeshotByDate(getUiDevice(), getClass().getName(), "The_" + runcount + "_time_call_fail_");
				System.out.println("第" + runcount + "次未接到来电-" + common.getDate());
			}
			if (CurrentPackageName.contains("incall") || CurrentPackageName.contains("phone")) {
				// 20秒内来电成功
				if (!getUiDevice().isScreenOn()) {// 截图前避免熄屏
					getUiDevice().wakeUp();
				}
				common.takeshotByDate(getUiDevice(), getClass().getName(), "The_" + runcount + "_time_call_success_");
				System.out.println("第" + runcount + "次接到来电");
				j = 41;
			} else {
				sleep(500);
			}
		}
	}
}
