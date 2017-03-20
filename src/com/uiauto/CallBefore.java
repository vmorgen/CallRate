package com.uiauto;

import java.io.DataOutputStream;
import java.io.OutputStream;

import utils.CmdAdb;
import utils.Common;
import utils.Location;
import utils.PackageEvent;
import utils.Selector;

import android.os.RemoteException;
import android.widget.Toast;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 实现思路： 区别：原生机（及黑莓）和nubia 4.5（及未来新UI） 1.进入log页面，log清洗； 2.开启MobileLog和NetworkLog
 * 3.关闭ModemLog和功耗Log 4.开启总Log开关。
 * 
 * @author 魏亮
 */

public class CallBefore extends UiAutomatorTestCase {
	Common common = new Common();
	PackageEvent packageEvent = new PackageEvent();

	@Override
	protected void setUp() throws Exception {
		common.creatFile(getClass().getName());
		common.unLock(getUiDevice());
		common.watcherTheError(getUiDevice(), getClass().getName());// 运行错误报告监听器
	}

	@Override
	protected void tearDown() throws Exception {
	}

	public void testDemo() throws UiObjectNotFoundException, RemoteException {
		try {
			callBefore();
		} catch (Exception e) {
			common.takeshot(getUiDevice(), getClass().getName());
			e.printStackTrace();
		} finally {
			// getUiDevice().pressHome();
		}
	}

	public void callBefore() throws Exception {

		System.out.println("运行测试准备");
		// 将做截图的操作运用于所有机型，以应对无法获取来电界面控件时，可立刻切换至
		sleep(200);
		getUiDevice().wakeUp();
		getUiDevice().pressHome();
		getUiDevice().pressHome();
		sleep(500);
		common.takeshot(getUiDevice(), getClass().getName(), "Incall");// 作为对比图片
		sleep(500);
		getUiDevice().sleep();
		sleep(1000);
		common.takeshot(getUiDevice(), getClass().getName(), "Incallblack");// 作为熄屏下的对比图片
		common.unLock(getUiDevice());
		getUiDevice().pressHome();
		getUiDevice().pressHome();

		if (getUiDevice().getProductName().contains("BBC")) {// 黑莓

			System.out.println("-------------------------------jinru BBC panduanliucheng");
			// 进入log工具界面
			common.cmdImput("am start --activity-single-top -n cn.nubia.woodpecker/.logger.LoggerActivity");
			// log总开关logshift的resource-id为cn.nubia.woodpecker:id/action_enable
			UiObject logshift = new UiObject(new UiSelector().resourceId("cn.nubia.woodpecker:id/action_enable"));

			UiObject MobileLogshift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1)
					.childSelector(new UiSelector().resourceId("cn.nubia.woodpecker:id/self_switch")));
			UiObject NetworkLogShift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2)
					.childSelector(new UiSelector().resourceId("android:id/switch_widget")));
			UiObject ModemLogShift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(3)
					.childSelector(new UiSelector().resourceId("cn.nubia.woodpecker:id/self_switch")));
			UiObject PowerLogShift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(4)
					.childSelector(new UiSelector().resourceId("android:id/switch_widget")));

			UiObject clearButton = new UiObject(new UiSelector().resourceId("cn.nubia.woodpecker:id/action_clear"));

			UiObject stoppingView = Location.textContainsObject("停止中");
			UiObject confirmDelete = Location.textContainsObject("是");
			// UiObject deleteView = Location.textContainsObject("正在删除");

			// 以上获取控件
			// 以下操作控件

			// Log总开关是开启的 ——>置为关闭
			if (!logshift.getContentDescription().equals("开始")) {
				logshift.click();
			}
			while (stoppingView.exists()) {
				sleep(200);
			}
			// log清洗
			clearButton.click();
			confirmDelete.click();
			sleep(1000);
			// while (deleteView.exists()) {
			// sleep(200);
			// }
			// 开启mobileLog和networkLog开关
			if (MobileLogshift.getText().contains("关闭")) {
				MobileLogshift.click();
			}
			if (NetworkLogShift.getText().contains("关闭")) {
				NetworkLogShift.click();
			}
			// 关闭modemLog和PowerLog开关
			if (ModemLogShift.getText().contains("开启")) {
				ModemLogShift.click();
			}
			if (PowerLogShift.getText().contains("开启")) {
				PowerLogShift.click();
			}
			// 最后把Log总开关打开
			if (!(!logshift.getContentDescription().equals("开始"))) {

				logshift.click();
			}
			// 退回至桌面
			getUiDevice().wakeUp();
			getUiDevice().pressHome();
			getUiDevice().pressHome();

		} else if (getUiDevice().getProductName().contains("573") || getUiDevice().getProductName().contains("575")
				|| getUiDevice().getProductName().contains("535")) {// MTK
			common.cmdImput(
					"am start --activity-single-top -n com.mediatek.mtklogger/com.mediatek.mtklogger.MainActivity");
			common.cmdImput("am start --activity-single-top - n cn.nubia.launcher/com.android.launcher3.Launcher");

			UiObject startStopToggleButton = new UiObject(
					new UiSelector().resourceId("com.mediatek.mtklogger:id/startStopToggleButton"));
			UiObject dengdaitingshiluzhi = new UiObject(new UiSelector().textContains("等待停止录制"));
			UiObject dengdaikaishiluzhi = new UiObject(new UiSelector().textContains("等待开启录制"));
			UiObject clearLogImageButton = new UiObject(new UiSelector().className("android.widget.ImageButton")
					.index(2).resourceId("com.mediatek.mtklogger:id/clearLogImageButton"));
			UiObject LogSetBT = new UiObject(new UiSelector().descriptionContains("设置"));

			UiObject ClearAllLog = new UiObject(new UiSelector().textContains("全部删除"));
			UiObject EnsureClear = new UiObject(new UiSelector().textContains("确定"));
			UiObject WaitClear = Location.textContainsObject("请耐心等待");
			UiObject MobileLogShift = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
					.childSelector(new UiSelector().resourceId("com.mediatek.mtklogger:id/log_selected")));
			UiObject ModemLogShift = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(2)
					.childSelector(new UiSelector().resourceId("com.mediatek.mtklogger:id/log_selected")));
			UiObject NetworkLogShift = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(3)
					.childSelector(new UiSelector().resourceId("com.mediatek.mtklogger:id/log_selected")));
			UiObject GPSLogShift = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(4)
					.childSelector(new UiSelector().resourceId("com.mediatek.mtklogger:id/log_selected")));
			// 如果log开启，置为关闭
			startStopToggleButton.waitForExists(500);
			if (startStopToggleButton.exists() && startStopToggleButton.isEnabled()) {
				if (startStopToggleButton.isChecked()) {
					startStopToggleButton.click();
				}
			}
			while(dengdaitingshiluzhi.exists()) {
				dengdaitingshiluzhi.waitUntilGone(500);
			}
			clearLogImageButton.waitForExists(500);
			// log清理
			if (clearLogImageButton.exists() && clearLogImageButton.isEnabled()) {
				clearLogImageButton.click();
				ClearAllLog.waitForExists(500);
			}

			if (ClearAllLog.exists() && ClearAllLog.isEnabled()) {
				ClearAllLog.click();
			}
			EnsureClear.waitForExists(500);
			if (EnsureClear.exists() && EnsureClear.isEnabled()) {
				EnsureClear.click();
			}
			while(WaitClear.exists()){
				WaitClear.waitUntilGone(500);
			}
			

			// 进入Log设置
			if (LogSetBT.exists() && LogSetBT.isEnabled()) {
				LogSetBT.click();
			}
			MobileLogShift.waitForExists(1000);
			// MobileLogShift开
			if (MobileLogShift.exists() && MobileLogShift.isEnabled()) {
				if (!MobileLogShift.isChecked()) {
					MobileLogShift.click();
				}
			}
			// ModemLogShift开 MTK
			if (ModemLogShift.exists() && ModemLogShift.isEnabled()) {
				if (!ModemLogShift.isChecked()) {
					ModemLogShift.click();
				}
			}
			// NetworkLogShift开
			if (!NetworkLogShift.isChecked()) {
				NetworkLogShift.click();
			}
			// GPSLogShift关
			if (GPSLogShift.isChecked()) {
				GPSLogShift.click();
			}
			GPSLogShift.waitForExists(500);
			// 按back键，返回log首页
			getUiDevice().pressBack();
			// 将log点击开启
			startStopToggleButton.waitForExists(500);
			if (!startStopToggleButton.isChecked()) {
				startStopToggleButton.click();
			}
			while(dengdaikaishiluzhi.exists()){
				dengdaikaishiluzhi.waitUntilGone(500);
			}
			getUiDevice().wakeUp();
			getUiDevice().pressHome();
			getUiDevice().pressHome();
		} else {
			// 高通
			System.out.println("jinru NX panduanliucheng"); // nubia // UI4.5
			common.cmdImput("am start --activity-single-top -n cn.nubia.woodpecker/.logger.LoggerActivity");

			// log总开关logshift的resource-id为cn.nubia.woodpecker:id/action_enable
			UiObject logshift = new UiObject(new UiSelector().className("android.widget.TextView").index(1)
					.resourceId("cn.nubia.woodpecker:id/action_enable"));
			// logshift.click();//
			// 插桩验证log总开关logshift获取——>成功->【总开关必须在至少有一个子log开启的情况下，点击开启不然灰选】
			UiObject MobileLogShift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1)
					.childSelector(new UiSelector().resourceId("cn.nubia.woodpecker:id/self_switch")));
			UiObject NetworkLogshift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2)
					.childSelector(new UiSelector().resourceId("android:id/switchWidget")));
			UiObject ModemLogshift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(3)
					.childSelector(new UiSelector().resourceId("cn.nubia.woodpecker:id/self_switch")));
			UiObject PowerLogshift = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(4)
					.childSelector(new UiSelector().resourceId("android:id/switchWidget")));

			UiObject clearButton = new UiObject(new UiSelector().resourceId("cn.nubia.woodpecker:id/action_clear"));

			UiObject stoppingView = new UiObject(
					new UiSelector().resourceId("android:id/message").textContains("停止中"));
			UiObject confirmDelete = Location.textContainsObject("是");
			UiObject deleteView = Location.textContainsObject("正在删除"); // 以上获取控件
			// Log总开关是开启的 ——>置为关闭
			logshift.waitForExists(500);
			if (logshift.exists() && logshift.isEnabled()) {
				if (logshift.getContentDescription().equals("停止")) {
					logshift.click();
				}
			}
			// 等待log关闭
			while(stoppingView.exists()) {
				stoppingView.waitUntilGone(500);
			}

			// log清洗
			clearButton.click();
			confirmDelete.waitForExists(500);
			confirmDelete.click();
			while(deleteView.exists()){
				deleteView.waitForExists(500);
			}
			
			MobileLogShift.waitForExists(500);
			// 将MobileLog和NetworkLog开关置为开启
			if (MobileLogShift.exists() && MobileLogShift.isEnabled()) {
				if (MobileLogShift.getText().contains("关闭")) {
					MobileLogShift.click();
				}
			}
			if (NetworkLogshift.exists() && NetworkLogshift.isEnabled()) {
				if (NetworkLogshift.getText().contains("关闭")) {
					NetworkLogshift.click();
				}
			} // 关闭ModemLog和功耗Log
			if (ModemLogshift.exists() && ModemLogshift.isEnabled()) {
				if (ModemLogshift.getText().contains("开启")) {
					ModemLogshift.click();
				}
			}
			if (PowerLogshift.exists() && PowerLogshift.isEnabled()) {
				if (PowerLogshift.getText().contains("开启")) {
					PowerLogshift.click();
				}
			} // Log总开关是关闭的 ——>置为开启
			if (logshift.exists() && logshift.isEnabled()) {
				if (logshift.getContentDescription().equals("开始")) {
					logshift.click();
				}
			}
			getUiDevice().wakeUp();
			getUiDevice().pressHome();
			getUiDevice().pressHome();
		}

	}

}
