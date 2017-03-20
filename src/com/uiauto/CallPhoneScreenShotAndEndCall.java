package com.uiauto;

import java.io.DataOutputStream;
import java.io.OutputStream;

import utils.CmdAdb;
import utils.Common;
import utils.Location;
import utils.PackageEvent;
import utils.Selector;

import android.os.Bundle;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 在被叫接通来电之后到这里来处理主叫的截图 利用批处理参数命名截图序号 挂断电话
 * 
 * @author 潘亚男、魏亮
 *
 */

public class CallPhoneScreenShotAndEndCall extends UiAutomatorTestCase {
	Common common = new Common();
	PackageEvent packageEvent = new PackageEvent();
	UiObject zhengzaibohao = new UiObject(new UiSelector().textContains("正在拨号"));

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
			callOne();
		} catch (Exception e) {
			common.takeshot(getUiDevice(), getClass().getName());
			e.printStackTrace();
		} finally {
			// getUiDevice().pressHome();
		}
	}

	public void callOne() throws Exception {
		Bundle bundle = getParams();
		// pc命令行相关操作
		String runcount = bundle.getString("runcount");
		
		if (getUiDevice().getCurrentPackageName().contains("incallui")) {
			sleep(500);
			System.out.println("第" + runcount + "次拨号开始");
			if (!getUiDevice().isScreenOn()) {// 截图前避免熄屏
				getUiDevice().wakeUp();
			}
			common.takeshotByDate(getUiDevice(), getClass().getName(), "The_" + runcount + "_time_call_success_");

		} else {
			sleep(500);
			System.out.println("第" + runcount + "次拨号失败");
			if (!getUiDevice().isScreenOn()) {// 截图前避免熄屏
				getUiDevice().wakeUp();
			}
			common.takeshotByDate(getUiDevice(), getClass().getName(), "The_" + runcount + "_time_call_fail_");
		}
		getUiDevice().pressKeyCode(6);// 挂断电话
		System.out.println("第" + runcount + "次已挂断电话");
	}
}
