package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 简介 断言——String包含 断言——String反向包含 断言——String相同 断言——int相等 断言——正则匹配 断言——布尔正确
 * cmd命令执行 创建截图文件夹 获取当前时间 切换输入法到android输入法 切换输入法为中文Utf7Ime输入法 滑屏搜索文字并点击 截图 滑动解锁
 * 字符串正则匹配 监听器——自定义 监视器——错误报告 监视器——电话 监视器——搜狗输入连接网络提示框 桌面滑屏 控件长按操作 滑动选择日期控件
 * 滑动对象到对应方向的SlidLength像素长度 状态栏一键加速 相机欢迎界面
 * 
 * @author panyanan luojinghua
 *
 */
// @echo *********Common类中的“/storage/sdcard0/”修改成“/storage/self/primary/”
// 2016/9/4 修改 wl

public class Common extends UiAutomatorTestCase {

	PackageEvent packageEvent = new PackageEvent();

	/**
	 * 当前日期时间
	 * 
	 * @return
	 */
	public String getDate() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		return f.format(c.getTime());
	}

	/**
	 * 断言-不终止流程 String判断
	 * 
	 * @param expected——预期
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public void assertEqual(String expected, String actual, String className, UiDevice uiDevice, String message) {
		try {
			if (expected.equals(actual)) {
				System.out.println("检查点\"" + message + "\"成功");
			} else {
				takeshot(uiDevice, className);
				System.out.println("检查点\"" + message + "\"失败: 预期:<[" + expected + "]> 实际:<[" + actual + "]>" + "失败时间是"
						+ getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断言-不终止流程 Int判断
	 * 
	 * @param expected
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public void assertEqual(int expected, int actual, String className, UiDevice uiDevice, String message) {
		try {
			if (expected == actual) {
				System.out.println("检查点\"" + message + "\"成功");
			} else {
				takeshot(uiDevice, className);
				System.out.println("检查点\"" + message + "\"失败: 预期:<[" + expected + "]> 实际:<[" + actual + "]>" + "失败时间是"
						+ getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断言-不终止流程 布尔判断
	 * 
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public void assertTrue(boolean actual, String className, UiDevice uiDevice, String message) {
		try {
			if (actual) {
				System.out.println("检查点\"" + message + "\"成功");
			} else {
				takeshot(uiDevice, className);
				System.out.println("检查点\"" + message + "\"失败: 预期:<[true]>]> 实际:<[false]>" + "失败时间是" + getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断言-不终止流程 正则匹配判断
	 * 
	 * @param regxp
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public void assertMatch(String regxp, String actual, String className, UiDevice uiDevice, String message) {
		try {
			if (regex(regxp, actual)) {
				System.out.println("检查点\"" + message + "\"成功");
			} else {
				takeshot(uiDevice, className);
				System.out.println(
						"检查点\"" + message + "\"失败: 预期:<[" + regxp + "]> 实际:<[" + actual + "]>" + "失败时间是" + getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断言-不终止流程 String包含判断（实际包括预期）
	 * 
	 * @param expected
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public void assertContains(String expected, String actual, String className, UiDevice uiDevice, String message) {
		try {
			if (actual.contains(expected)) {
				System.out.println("检查点\"" + message + "\"成功");
				// return true;
			} else {
				takeshot(uiDevice, className);
				System.out.println("检查点\"" + message + "\"失败: 预期:<[" + expected + "]> 实际:<[" + actual + "]>" + "失败时间是"
						+ getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断言-不终止流程 String反向包含判断（预期包括实际）
	 * 
	 * @param expected
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public void assertBackContains(String expected, String actual, String className, UiDevice uiDevice,
			String message) {
		try {
			if (expected.contains(actual)) {
				System.out.println("检查点\"" + message + "\"成功");
				// return true;
			} else {
				takeshot(uiDevice, className);
				System.out.println("检查点\"" + message + "\"失败: 预期:<[" + expected + "]> 实际:<[" + actual + "]>" + "失败时间是"
						+ getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 滑动解锁
	 * 
	 * @param uiDevice
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void unLock(UiDevice uiDevice) throws RemoteException, UiObjectNotFoundException {
		float width = uiDevice.getDisplayWidth();
		float height = uiDevice.getDisplayHeight();

		if (!uiDevice.isScreenOn()) {
			uiDevice.wakeUp();
			sleep(200);
			do {
				uiDevice.swipe((int) (width / 2), (int) (height / 20 * 19), (int) (width / 2), (int) (height / 2), 50);// 滑动
			} while (Location.resourceIdObject("com.android.systemui:id/notification_stack_scroller").exists());
		} else if (uiDevice.isScreenOn()
				&& Location.resourceIdObject("com.android.systemui:id/notification_stack_scroller").exists()) {
			do {
				sleep(200);
				uiDevice.swipe((int) (width / 2), (int) (height / 20 * 19), (int) (width / 2), (int) (height / 2), 50);// 滑动
			} while (Location.resourceIdObject("com.android.systemui:id/notification_stack_scroller").exists());
		}
		sleep(500);
		if (Location.textContainsObject("紧急").exists()) {// 处理部分手机数字锁的问题
			String password = "1234";
			sleep(500);
			for (int i = 0; i < password.length(); i++) {
				Location.textObject("" + password.charAt(i)).click();
				sleep(1000);
			}
			cmdImput(packageEvent.getSettings());
			scroll(uiDevice, 3, 1);
			Location.textContainsObject("安全").click();
			sleep(500);
			Location.textObject("屏幕锁定方式").click();
			sleep(500);
			for (int i = 0; i < password.length(); i++) {
				uiDevice.pressKeyCode(8 + i);// 1234
				sleep(1000);
			}
			Location.textObject("滑动解锁").click();
			sleep(200);
			uiDevice.pressHome();
		}

	}

	/**
	 * error截图方法
	 * 
	 * @param uiDevice
	 * @param classname
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void takeshot(UiDevice uiDevice, String classname) throws RemoteException, UiObjectNotFoundException {
		String file = classname.split("\\.")[classname.split("\\.").length - 1];
		uiDevice.takeScreenshot(new File("/storage/self/primary/uiauto/" + file + "/error" + getDate() + ".png"));
	}

	/**
	 * 自定义命名截图方法+日期名称
	 * 
	 * @param uiDevice
	 * @param classname
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void takeshotByDate(UiDevice uiDevice, String classname, String picName)
			throws RemoteException, UiObjectNotFoundException {
		String file = classname.split("\\.")[classname.split("\\.").length - 1];
		uiDevice.takeScreenshot(new File("/storage/self/primary/uiauto/" + file + "/" + picName + getDate() + ".png"));
	}

	/**
	 * 自定义命名截图方法
	 * 
	 * @param uiDevice
	 * @param classname
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void takeshot(UiDevice uiDevice, String classname, String picName) throws RemoteException, UiObjectNotFoundException {
		String file = classname.split("\\.")[classname.split("\\.").length - 1];
		uiDevice.takeScreenshot(new File("/storage/self/primary/uiauto/" + file + "/" + picName + ".png"));
	}

	/**
	 * 创建截图文件夹方法
	 * 
	 * @param string
	 */
	public void creatFile(String classname) {
		// File file = new
		// File("/storage/sdcard0/uiauto/"+classname.split("\\.")[classname.split("\\.").length-1]);
		// 魏亮/storage/self/primary
		// 修改点，Common类中的所有“/storage/sdcard0/”改成“/storage/self/primary/”
		File file = new File(
				"/storage/self/primary/uiauto/" + classname.split("\\.")[classname.split("\\.").length - 1]);
		file.mkdirs();// 创建目录
	}

	/**
	 * 监听任何信息方法
	 * 
	 * @param uiDevice
	 * @param className
	 * @param info
	 * @param btnName
	 */
	public void watcherTheInfo(final UiDevice uiDevice, final String className, String info, String btnName) {
		UiDevice.getInstance().registerWatcher("watcherTheError", new UiWatcher() {
			UiObject rejectBTN = Location.textObject("info");

			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						takeshot(uiDevice, className);
						Location.textObject("btnName").click();
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * 报错监听方法
	 * 
	 * @param uiDevice
	 * @param className
	 */
	public void watcherTheError(final UiDevice uiDevice, final String className) {
		UiDevice.getInstance().registerWatcher("watcherTheError", new UiWatcher() {
			UiObject rejectBTN = new UiObject(new UiSelector().textContains("停止运行"));

			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						takeshot(uiDevice, className);
						Location.resourceIdObject("android:id/button1").click();
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * 电话监听方法 滑动挂掉来电
	 * 
	 * @param uiDevice
	 * @param className
	 */
	public void watcherThePhone(final UiDevice uiDevice, final String className) {
		UiDevice.getInstance().registerWatcher("watcherThePhone", new UiWatcher() {
			UiObject rejectBTN = Location.packageNameObject("com.android.incallui");

			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						takeshot(uiDevice, className);
						swipTo(Location.resourceIdObject("com.android.incallui:id/nNBKeyguardIcons"), 400, 1);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * 搜狗弹框监听方法
	 * 
	 * @param uiDevice
	 * @param className
	 * @throws UiObjectNotFoundException
	 */
	public void watchersogou(final UiDevice uiDevice, final String className) {
		UiDevice.getInstance().registerWatcher("watcherTheSogou", new UiWatcher() {
			UiObject rejectBTN = Location.packageNameObject("com.sohu.inputmethod.sogou");

			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						takeshot(uiDevice, className);
						Location.textObject("允许").click();
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Runtime指令方法
	 * 
	 * @param commond
	 */
	public void cmdImput(String commond) {
		try {
			Runtime.getRuntime().exec(commond);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 搜索桌面图标点击方法
	 * 
	 * @param string
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public UiObject searchAndClick(String string, UiDevice uiDevice) throws UiObjectNotFoundException {
		uiDevice.pressHome();
		if (!Location.textObject(string).exists()) {
			int i = 0;
			do {
				scroll(uiDevice, 0, 1);
				++i;
			} while (!Location.textObject(string).exists() && i < 5);
		}
		Location.textObject(string).click();
		return null;
	}

	/**
	 * 切换默认输入法方法
	 * 
	 * @param uiDevice
	 * @throws UiObjectNotFoundException
	 */
	public void inputMethonSwitch(UiDevice uiDevice) throws UiObjectNotFoundException {
		PackageEvent packageEvent = new PackageEvent();
		uiDevice.pressHome();
		cmdImput(packageEvent.getSettings());
		sleep(1000);
		scroll(uiDevice, 3, 2);
		sleep(200);
		new UiObject(new UiSelector().textContains("其他")).click();
		sleep(500);
		new UiObject(new UiSelector().textContains("语言")).click();
		System.out.println(Location.textObject("当前输入法").getFromParent(Selector.resourceIdSelector("android:id/summary"))
				.getText());
		Location.textObject("当前输入法").click();
		Location.textObject("英语(美国)").click();
		sleep(500);
		uiDevice.pressHome();
	}

	/**
	 * 切换输入法为中文Utf7Ime输入法
	 * 
	 * 1.在批处理程序中增加一行，安装输入法
	 * 
	 * @adb install \\10.204.68.133\外包工作记录\测试小组-潘亚男\自动化工具\应用\Utf7Ime.apk
	 *      2.在测试类测试准备中增加一行，修改输入法为此输入法：
	 *      common.inputMethonSwitchUtf7Ime(getUiDevice());
	 *      3.将helper-library.jar加入到工程lib中
	 *      \\10.204.68.133\外包工作记录\测试小组-潘亚男\自动化工具\应用\Utf7Ime 4.在使用中文的地方使用即可输入汉字
	 *      Location.resourceIdObject("XXX").setText(Utf7ImeHelper.e("汉字输入yingyushuru"));
	 * 
	 * @param uiDevice
	 * @throws UiObjectNotFoundException
	 */
	public void inputMethonSwitchChinese(UiDevice uiDevice) throws UiObjectNotFoundException {
		PackageEvent packageEvent = new PackageEvent();
		uiDevice.pressHome();
		cmdImput(packageEvent.getSettings());
		sleep(1000);
		scroll(uiDevice, 3, 2);
		sleep(200);
		new UiObject(new UiSelector().textContains("其他")).click();
		sleep(500);
		new UiObject(new UiSelector().textContains("语言")).click();
		sleep(500);
		new UiObject(new UiSelector().textContains("UTF7")).click();
		if (Location.resourceIdObject("nubia:id/nubia_button1").exists()) {
			Location.resourceIdObject("nubia:id/nubia_button1").click();// 确认对话框
		} else {
			Location.resourceIdObject("android:id/button1").click();
		}
		sleep(1000);
		Location.textObject("当前输入法").click();
		sleep(3000);
		new UiObject(new UiSelector().textContains("UTF7")).click();
		sleep(500);
		uiDevice.pressHome();
	}

	/**
	 * 正则匹配方法
	 * 
	 * @param regxp
	 * @param actual
	 * @return
	 */
	public boolean regex(String regxp, String actual) {
		Pattern p = null; // 正则表达式
		Matcher m = null; // 操作的字符串
		boolean b = false;
		p = Pattern.compile(regxp);
		m = p.matcher(actual);
		b = m.matches();
		return b;
	}

	/**
	 * 屏幕滑屏 滑动以1/10为单位
	 * 
	 * @param ud
	 * @param direction——方向
	 * @param steps——滑动次数
	 * @throws UiObjectNotFoundException
	 */

	public void scroll(UiDevice ud, int direction, int steps) throws UiObjectNotFoundException {
		for (int i = 0; i < steps; i++) {
			if (direction == 0) {
				ud.swipe(ud.getDisplayWidth() * 9 / 10, ud.getDisplayHeight() / 2, ud.getDisplayWidth() * 1 / 10,
						ud.getDisplayHeight() / 2, 100);// 向屏幕左边
			} else if (direction == 1) {
				ud.swipe(ud.getDisplayWidth() * 1 / 10, ud.getDisplayHeight() / 2, ud.getDisplayWidth() * 9 / 10,
						ud.getDisplayHeight() / 2, 100);// 向屏幕右边
			} else if (direction == 2) {
				ud.swipe(ud.getDisplayWidth() / 2, ud.getDisplayHeight() * 1 / 10, ud.getDisplayWidth() / 2,
						ud.getDisplayHeight() * 9 / 10, 100);// 向屏幕上边
			} else if (direction == 3) {
				ud.swipe(ud.getDisplayWidth() / 2, ud.getDisplayHeight() * 9 / 10, ud.getDisplayWidth() / 2,
						ud.getDisplayHeight() * 1 / 10, 100);// 向屏幕下边
			} else {
				System.out.println("请输入方向为：0-右边，1-左边， 2-手指向下，3-手指向上");
			}
		}
	}

	/**
	 * 控件长按操作
	 * 
	 * @param ud
	 * @param uiObject
	 * @param steps
	 * @throws UiObjectNotFoundException
	 */
	public void longClick(UiDevice ud, UiObject uiObject, int steps) throws UiObjectNotFoundException {
		ud.swipe(uiObject.getBounds().centerX(), uiObject.getBounds().centerY(), uiObject.getBounds().centerX(),
				uiObject.getBounds().centerY(), steps);
	}

	/**
	 * 滑动选择日期控件 每次滑动一格，为110像素
	 * 
	 * @param uiObject
	 * @param count
	 * @param direction
	 * @throws UiObjectNotFoundException
	 */

	public void dataPicker(UiObject uiObject, int count, int direction) throws UiObjectNotFoundException {
		int x = uiObject.getBounds().centerX();
		int y = uiObject.getBounds().centerY();
		for (int i = 0; i < count; i++) {
			if (direction == 1) {
				uiObject.dragTo(x, y - 110, 20);
			} else if (direction == 0) {
				uiObject.dragTo(x, y + 110, 20);
			} else {
				System.out.println("请输入方向为：1-手指向上滑，0-手指向下滑");
			}
			sleep(200);
		}
	}

	/**
	 * 滑动对象到SlidLength像素长度
	 * 
	 * @param uiObject
	 * @param count
	 * @param direction
	 * @throws UiObjectNotFoundException
	 */

	public void swipTo(UiObject uiObject, int SlidLength, int direction) throws UiObjectNotFoundException {
		int x = uiObject.getBounds().centerX();
		int y = uiObject.getBounds().centerY();
		if (direction == 1) {
			uiObject.dragTo(x, y - SlidLength, 20);
		} else if (direction == 0) {
			uiObject.dragTo(x, y + SlidLength, 20);
		} else {
			System.out.println("请输入方向为：1-手指向上滑，0-手指向下滑");

			sleep(200);
		}
	}

	/**
	 * 任意位置一键加速删除后台
	 * 
	 * @param ud
	 * @throws UiObjectNotFoundException
	 */
	public void OneKeyClean(UiDevice ud) throws UiObjectNotFoundException {
		sleep(500);
		ud.openQuickSettings();
		sleep(1500);
		if (Location.resourceIdObject("com.android.systemui:id/dismiss_text").exists()) {
			sleep(1000);
			Location.resourceIdObject("com.android.systemui:id/dismiss_text").click();
			sleep(500);
			ud.openQuickSettings();
			sleep(500);
		}
		Location.resourceIdObject("com.android.systemui:id/qs_tips_arrow").click();
		sleep(500);
		Location.textObject("一键加速").click();
		sleep(2500);
	}

	/**
	 * 相机欢迎界面
	 * 
	 * @param ud
	 * @throws UiObjectNotFoundException
	 */
	public void skipWelcomeView(UiDevice ud) throws UiObjectNotFoundException {
		sleep(3000);
		if (Location.resourceIdObject("com.android.camera:id/ok_button").exists()) {
			Location.resourceIdObject("com.android.camera:id/ok_button").click();
			sleep(500);
			do {
				scroll(ud, 0, 1);// 向右滑动
				sleep(500);
			} while (Location.resourceIdObject("com.android.camera:id/welcome1_text_layout").exists());// 没滑动继续滑
			Location.classNameObject("android.widget.TextView").click();
		} else if (Location.resourceIdObject("com.android.camera:id/camera_welcome_viewpager").exists()) {
			sleep(500);
			do {
				scroll(ud, 0, 1);// 向右滑动
				sleep(500);
			} while (Location.resourceIdObject("com.android.camera:id/welcome1_text_layout").exists());
			Location.classNameObject("android.widget.TextView").click();
		}

	}

	// /**
	// * 测试项目地址
	// * @param propertyPath 项目对应配置文件路径
	// */
	// public String captureAddress(String propertyPath) throws Exception{
	// Properties property = new Properties();
	// property.load(new FileInputStream(propertyPath));
	// String captureAddress = property.getProperty("captureAddress");
	// return captureAddress;
	// }
}
