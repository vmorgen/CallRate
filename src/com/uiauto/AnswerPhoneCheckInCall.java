package com.uiauto;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;

import utils.Common;
import utils.ImgCompare;
import utils.Location;
import utils.PackageEvent;
import utils.Selector;

import android.os.Bundle;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 20s内判断是否来电
 * 
 * 截图对比和包名匹配的对比： 1.截图对比适用绝大多数机型，无需单独适配机型/UI。 2.包名匹配准确，可区分不可预测的界面，如收到短信引起的界面变化等。
 * 在此，所有的机器都采用截图对比
 * 
 * @author 魏亮
 */
public class AnswerPhoneCheckInCall extends UiAutomatorTestCase {
	Common common = new Common();
	PackageEvent packageEvent = new PackageEvent();

	// ImgCompare imgCompare = new ImgCompare();

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
			callTwo();
		} catch (Exception e) {
			common.takeshot(getUiDevice(), getClass().getName());
			e.printStackTrace();
		} finally {
			// getUiDevice().pressHome();
		}
	}

	public void callTwo() throws Exception {
		/*通过Bundle.getString(key)，
		*获取 adb shell uiautomator runtest CallRate.jar -e runcount !aa! -c com.uiauto.AnswerPhoneCheckInCall 
		*中-e 传进来的键值对key=value对应着runcount=!aa!
		*/
		Bundle bundle = getParams();
		String runcount = bundle.getString("runcount");//获取key==String "runcount"的value
		
		System.out.println("被测试的设备名称是" + getUiDevice().getProductName());
		// nubia与原生机的区别，home主页的搜索框：nubia和原生google搜索不一样，前提home页不能改变。-wl160922
		UiDevice.getInstance().pressHome();//保证截图界面与参照界面相同，都是HOME页
		UiObject searchshift = Location.resourceIdObject("com.android.launcher3:id/qsb_widget");
		searchshift.waitForExists(5000);
		if (getUiDevice().getProductName().contains("NX549") || getUiDevice().getProductName().contains("NX531")) {
			
			// nubia可以获取到来电界面UI的，使用此分支！
			// 如果56秒内没有看到来电界面，则算作失败———包名获取
			System.out.println("sleep前时间" + common.getDate());
			Thread.sleep(15000);// 等待是10秒通话建立--> 因少量响铃极慢的场景，延长至15秒
			for (int j = 0; j < 60; j++) {// 循环60次大致对应时间50秒
				String CurrentPackageName = getUiDevice().getCurrentPackageName();
				System.out.println("第" + j + "次循环" + "++++++++++++时间" + common.getDate());
				if (j == 59) {
					if (!getUiDevice().isScreenOn()) {// 截图前避免熄屏
						getUiDevice().wakeUp();
					}
					common.takeshotByDate(getUiDevice(), getClass().getName(), "The_" + runcount + "_time_call_fail_");
					System.out.println("第" + runcount + "次未接到来电-" + common.getDate());
				}
				if (CurrentPackageName.contains("incall") || CurrentPackageName.contains("phone")) {
					// 55秒内来电成功
					if (!getUiDevice().isScreenOn()) {// 截图前避免熄屏
						getUiDevice().wakeUp();
					}
					common.takeshotByDate(getUiDevice(), getClass().getName(),
							"The_" + runcount + "_time_call_success_");
					System.out.println("第" + runcount + "次接到来电");
					j = 60;
				}
			}
		} else {
				
			//nubia可以无法正常获取到来电界面UI的，
			//以及原生机（包括自研的和海派的），无来电界面的使用此分支！——截图对比
			
			imgCompare2();

		}
	}

	// 2016-09-21原生机来电界面，只在上部有通知，且无法获取控件——>进行截图对比
	public void imgCompare2()
			throws RemoteException, UiObjectNotFoundException, FileNotFoundException, InterruptedException {

		Bundle bundle = getParams();
		String runcount = bundle.getString("runcount");
		Thread.sleep(15000);// 由10+56/2=38秒改为15秒，因为联通打移动超时后，主叫还没有挂断，通话超时后被叫才截图，导致误判为未接。
		System.out.println("等待15秒-截图对比" + common.getDate());
		String picName = "The_" + runcount + "_time_call_Rate_" + common.getDate();
		common.takeshot(getUiDevice(), getClass().getName(), picName);
		if (ImgCompare.sameAs("/storage/self/primary/uiauto/CallBefore/Incall.png",
				"/storage/self/primary/uiauto/AnswerPhoneCheckInCall/" + picName + ".png", 0.80)
				|| ImgCompare.sameAs("/storage/self/primary/uiauto/CallBefore/Incallblack.png",
						"/storage/self/primary/uiauto/AnswerPhoneCheckInCall/" + picName + ".png", 0.99)) {// 桌面截图和黑屏截图两次对比确认未收到来电
			System.out.println("第" + runcount + "次未接到来电-" + common.getDate());
		} else {
			System.out.println("第" + runcount + "次接到来电");
		}
	}
}
