package utils;

import java.lang.String;

/**
 * 简介： cmd指令
 * 
 * @author panyanan
 *
 */

public class PackageEvent {
	private String settings = "am start --activity-single-top -n com.android.settings/.Settings";
	private String deskClock = "am start --activity-single-top -n cn.nubia.deskclock.preset/cn.nubia.deskclock.DeskClock";
	private String calendar = "am start --activity-single-top -n cn.nubia.calendar.preset/cn.nubia.calendar.AllInOneActivity";
	private String email = "am start --activity-single-top -n com.android.email/.activity.setup.AccountSetupTemplateActivity";
	private String file = "am start --activity-single-top -n cn.nubia.myfile/.CategoryActivity";
	private String factory = "am start --activity-single-top -n cn.nubia.factory/.FactorySingleTest";
	private String browser = "am start --activity-single-top -n com.android.browser/com.tencent.mtt.MainActivity";
	private String phone = "am start --activity-single-top -n com.android.contacts/.MmsConversationActivity ";
	private String recorder = "am start --activity-single-top -n cn.nubia.soundrecorder.preset/cn.nubia.soundrecorder.SoundRecorderBrowserActivity";
	private String stk = "am start --activity-single-top -n com.android.stk/.StkMenuActivity";
	private String swversion = "am start --activity-single-top -n cn.nubia.apps/.InterVersion";
	private String camera = "am start --activity-single-top -n com.android.camera/.CameraLauncher";
	private String message = "am start --activity-single-top -n com.android.contacts/.activities.PeopleActivity";
	private String volumeSettings = "am start --activity-single-top -n com.android.settings/.VolumeSettingsActivity";
	private String Calculator = "am start --activity-single-top -n cn.nubia.calculator2.preset/cn.nubia.calculator2.Calculator";
	private String NubiaSecurity = "am start --activity-single-top -n cn.nubia.security/.NubiaSecurity";
	private String FlashLight = "am start --activity-single-top -n cn.nubia.v5light.preset/cn.nubia.v5light.MainActivity";
	private String handbook = "am start --activity-single-top -n cn.nubia.phonemanualintegrate.preset/.MainActivity";
	private String notepad = "am start --activity-single-top -n cn.nubia.notepad.preset/cn.nubia.notepad.NoteListActivity";
	private String thememanager = "am start --activity-single-top -n cn.nubia.thememanager/.activity.ThemeTabActivity";
	private String contact = "am start --activity-single-top -n com.android.contacts/com.android.contacts.DialerActivity";
	private String video = "am start --activity-single-top -n com.android.video/com.android.video.list.app.VideoListActivity";
	private String taskmanager = "am start --activity-single-top -n com.android.systemui/.NubiaRecent.NubiaRecentsActivity";
	private String timemanager = "am start --activity-single-top -n nubia.cameraTest.deskclock.preset/nubia.cameraTest.deskclock.DeskClock";
	private String deletefile = "rm -r sdcard/zzzzztestnew";
	private String deletefile2 = "rm -r sdcard/zzzzztestnew2";

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public String getDeskClock() {
		return deskClock;
	}

	public void setDeskClock(String deskClock) {
		this.deskClock = deskClock;
	}

	public String getCalendar() {
		return calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getStk() {
		return stk;
	}

	public void setStk(String stk) {
		this.stk = stk;
	}

	public String getSwversion() {
		return swversion;
	}

	public void setSwversion(String swversion) {
		this.swversion = swversion;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCalculator() {
		return Calculator;
	}

	public void setCalculator(String calculator) {
		Calculator = calculator;
	}

	public String getNubiaSecurity() {
		return NubiaSecurity;
	}

	public void setNubiaSecurity(String nubiaSecurity) {
		NubiaSecurity = nubiaSecurity;
	}

	public String getFlashLight() {
		return FlashLight;
	}

	public void setFlashLight(String flashLight) {
		FlashLight = flashLight;
	}

	public String getHandbook() {
		return handbook;
	}

	public void setHandbook(String handbook) {
		this.handbook = handbook;
	}

	public String getNotepad() {
		return notepad;
	}

	public void setNotepad(String notepad) {
		this.notepad = notepad;
	}

	public String getContact() {
		return contact;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getVideo() {
		return video;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getThememanager() {
		return thememanager;
	}

	public void setThememanager(String thememanager) {
		this.thememanager = thememanager;
	}

	public String getVolumeSettings() {
		return volumeSettings;
	}

	public void setVolumeSettings(String volumeSettings) {
		this.volumeSettings = volumeSettings;
	}

	public String getTaskManager() {
		return taskmanager;
	}

	public String getTimeManager() {
		return timemanager;
	}

	public String getDeletefile() {
		return deletefile;
	}

	public void setDeletefile(String deletefile) {
		this.deletefile = deletefile;
	}

	public String getDeletefile2() {
		return deletefile2;
	}

	public void setDeletefile2(String deletefile2) {
		this.deletefile2 = deletefile2;
	}
}
