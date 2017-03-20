package com.uiauto;

import java.io.DataOutputStream;
import java.io.OutputStream;

import utils.CmdAdb;
import utils.Common;
import utils.Location;
import utils.PackageEvent;
import utils.Selector;

import android.os.RemoteException;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 结束关闭log开关
 * 
 * @author 魏亮
 *
 */

public class CallAfter extends UiAutomatorTestCase {
	Common common = new Common();
	PackageEvent packageEvent = new PackageEvent();

	@Override
	protected void setUp() throws Exception {
		common.creatFile(getClass().getName());
		// common.unLock(getUiDevice());
		common.watcherTheError(getUiDevice(), getClass().getName());// 运行错误报告监听器
	}

	@Override
	protected void tearDown() throws Exception {
	}

	public void testDemo() throws UiObjectNotFoundException, RemoteException {
		try {
			callEnd();
		} catch (Exception e) {
			common.takeshot(getUiDevice(), getClass().getName());
			e.printStackTrace();
		} finally {
			// getUiDevice().pressHome();
		}
	}

	public void callEnd() throws Exception {
		// 使用准备条件
		System.out.println("进行测试收尾");

		common.cmdImput("am start --activity-single-top -n com.mediatek.mtklogger/com.mediatek.mtklogger.MainActivity");// MTK项目打开log界面
		common.cmdImput("am start --activity-single-top -n cn.nubia.woodpecker/.logger.LoggerActivity");// nubia手机4.5打开log界面
		
		UiObject guanbilog = new UiObject(new UiSelector().className("android.widget.TextView").index(1).resourceId("cn.nubia.woodpecker:id/action_enable"));// nubiaUI4.5的log开关
		UiObject startStopToggleButton = new UiObject(
				new UiSelector().className("android.widget.ToggleButton").index(1).resourceId("com.mediatek.mtklogger:id/startStopToggleButton"));// MTKlog开关
		UiObject dengdaitingshiluzhi = new UiObject(new UiSelector().textContains("等待停止录制"));
		UiObject stoppingView = new UiObject(
				new UiSelector().resourceId("android:id/message").descriptionContains("停止中"));
		
		
		// 关闭log开关，为导出log做准备
		if (getUiDevice().getProductName().contains("573") || getUiDevice().getProductName().contains("575")
				|| getUiDevice().getProductName().contains("535")) {// MTK
			getUiDevice().waitForWindowUpdate("com.mediatek.mtklogger", 1000);
			startStopToggleButton.waitForExists(1000);
			if(startStopToggleButton.exists()&&startStopToggleButton.isEnabled()){
				startStopToggleButton.click();
			}
			while(dengdaitingshiluzhi.exists()){
				dengdaitingshiluzhi.waitUntilGone(1000);
			}
			
		} else {//高通
			getUiDevice().waitForWindowUpdate("cn.nubia.woodpecker", 1000);
			guanbilog.waitForExists(500);
			if (guanbilog.getContentDescription().equals("停止")) {
				guanbilog.click();
			}
			while(stoppingView.exists()){
				stoppingView.waitUntilGone(1000);
			}			
		}
		getUiDevice().pressHome();
	}
}
